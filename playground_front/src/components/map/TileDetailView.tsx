'use client'

import React, { useState } from 'react'
import { FieldDataDTO } from '@/types/map'
import { useFieldStore } from '@/store/fieldStore'

interface TileDetailViewProps {
  fieldData: FieldDataDTO
  onBack: () => void
  onGather: (x: number, y: number) => void
  onTransform: (x: number, y: number) => void
  dailyTransformCount: number
  maxDailyTransforms: number
}

export function TileDetailView({
  fieldData,
  onBack,
  onGather,
  onTransform,
  dailyTransformCount,
  maxDailyTransforms
}: TileDetailViewProps) {
  const [isGathering, setIsGathering] = useState(false)
  const [isTransforming, setIsTransforming] = useState(false)
  const [gatheringProgress, setGatheringProgress] = useState(0)
  const { selectedTile } = useFieldStore();
  
  const handleGather = () => {
    setIsGathering(true)
    setGatheringProgress(0)
    if(!selectedTile || selectedTile?.selectedTileX == null || selectedTile?.selectedTileY == null) return;
    onGather(selectedTile.selectedTileX , selectedTile.selectedTileY)
    const interval = setInterval(() => {
      setGatheringProgress(prev => {
        if (prev >= 100) {
          clearInterval(interval)
          setIsGathering(false)
          return 100
        }
        return prev + 2
      })
    }, 100)
  }

  const handleTransform = () => {
    if(!selectedTile || selectedTile?.selectedTileX == null || selectedTile?.selectedTileY == null) return;
    if (dailyTransformCount >= maxDailyTransforms) {
      alert('오늘의 변경 횟수를 모두 사용했습니다!')
      return
    }
    setIsTransforming(true)
    onTransform(selectedTile.selectedTileX, selectedTile.selectedTileY)
    setTimeout(() => setIsTransforming(false), 2000)
  }

  const canTransform = dailyTransformCount < maxDailyTransforms

  return (
    <div className="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50">
      <div className="bg-gray-900 border-4 border-gray-700 rounded-none p-6 max-w-md w-full mx-4 pixel-art-shadow">
        {/* 헤더 */}
        <div className="flex justify-between items-center mb-6 border-b-2 border-gray-600 pb-4">
          <h2 className="text-xl font-bold text-white pixel-font">
            {fieldData.name} - ({selectedTile?.selectedTileX}, {selectedTile?.selectedTileY})
          </h2>
          <button
            onClick={onBack}
            className="text-gray-400 hover:text-white text-2xl pixel-font"
          >
            ×
          </button>
        </div>

        {/* 타일 정보 */}
        <div className="mb-6">
          <div className="bg-gray-800 border-2 border-gray-600 rounded-none p-4 mb-4">
            <h3 className="font-semibold text-yellow-400 pixel-font mb-2">타일 정보</h3>
            <div className="text-sm text-gray-300 space-y-1 pixel-font">
              <div>위치: ({selectedTile?.selectedTileX}, {selectedTile?.selectedTileY})</div>
              <div>지역: {fieldData.name}</div>
              <div>상태: {isGathering ? '채집 중...' : isTransforming ? '변경 중...' : '대기 중'}</div>
            </div>
          </div>

          {/* 변경 횟수 정보 */}
          <div className="bg-blue-900 border-2 border-blue-700 rounded-none p-4 mb-4">
            <h3 className="font-semibold text-blue-300 pixel-font mb-2">오늘의 변경 횟수</h3>
            <div className="flex items-center justify-between">
              <span className="text-sm text-blue-200">
                {dailyTransformCount} / {maxDailyTransforms}
              </span>
              <div className="w-32 bg-blue-800 border border-blue-600 h-4">
                <div
                  className="bg-blue-500 h-full transition-all duration-300"
                  style={{ width: `${(dailyTransformCount / maxDailyTransforms) * 100}%` }}
                ></div>
              </div>
            </div>
          </div>
        </div>

        {/* 채집 진행도 표시 */}
        {isGathering && (
          <div className="mb-6 bg-gray-800 border-2 border-gray-600 rounded-none p-4 relative">
            <div className="flex items-center justify-between mb-2">
              <span className="text-yellow-400 pixel-font">채집 진행도</span>
              <span className="text-yellow-300 pixel-font">{gatheringProgress}%</span>
            </div>
            <div className="w-full bg-gray-700 border border-gray-600 h-6 rounded-none">
              <div
                className="bg-yellow-500 h-full transition-all duration-100"
                style={{ width: `${gatheringProgress}%` }}
              ></div>
            </div>
            {/* 곡괭이 애니메이션 */}
            <div className="flex justify-center mt-4">
              <div className="relative">
                <svg
                  className="w-12 h-12 text-yellow-400 pickaxe-swing"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                >
                  <path d="M14.7 6.3a1 1 0 0 0 0 1.4l1.6 1.6a1 1 0 0 0 1.4 0l3.77-3.77a6 6 0 0 1-7.94 7.94l-6.91 6.91a2.12 2.12 0 0 1-3-3l6.91-6.91a6 6 0 0 1 7.94-7.94l-3.76 3.76z" />
                  <path d="M9 12l-3-3 3-3" />
                </svg>
              </div>
            </div>
          </div>
        )}

        {/* 행동 버튼들 */}
        <div className="space-y-3">
          {/* 채집 버튼 */}
          <button
            onClick={handleGather}
            disabled={isGathering || isTransforming}
            className={`w-full py-4 px-4 border-2 font-bold pixel-font transition-all duration-200 ${
              isGathering
                ? 'bg-yellow-800 border-yellow-600 text-yellow-200 cursor-not-allowed'
                : 'bg-yellow-600 border-yellow-500 text-white hover:bg-yellow-700 hover:border-yellow-600'
            }`}
          >
            {isGathering ? '채집 중...' : '채집하기'}
          </button>

          {/* 변경 버튼 */}
          <button
            onClick={handleTransform}
            disabled={!canTransform || isGathering || isTransforming}
            className={`w-full py-4 px-4 border-2 font-bold pixel-font transition-all duration-200 ${
              !canTransform
                ? 'bg-gray-700 border-gray-600 text-gray-400 cursor-not-allowed'
                : isTransforming
                ? 'bg-purple-800 border-purple-600 text-purple-200 cursor-not-allowed'
                : 'bg-purple-600 border-purple-500 text-white hover:bg-purple-700 hover:border-purple-600'
            }`}
          >
            {!canTransform
              ? '변경 횟수 소진'
              : isTransforming
              ? '변경 중...'
              : '위치변경'}
          </button>
        </div>

        {/* 설명 */}
        <div className="mt-4 text-xs text-gray-400 space-y-1 pixel-font">
          <div>• 채집: 해당 타일에서 재료를 수집합니다</div>
          <div>• 변경: 타일의 특성을 변경하여 다른 재료를 얻을 수 있습니다</div>
          <div>• 변경은 하루에 {maxDailyTransforms}회만 가능합니다</div>
        </div>
      </div>
      <style jsx>{`
        .pixel-art-shadow {
          box-shadow:
            0 0 0 2 rgba(0,0,0,0.8),
            4px 4px 0 rgba(0,0,0,0.8),
            8px 8px 0 rgba(0,0,0,0.6);
        }
        .pixel-font {
          font-family: 'Press Start 2P', 'Courier New', monospace;
          image-rendering: pixelated;
        }
        .pickaxe-swing {
          animation: swing 1s infinite alternate;
        }
        @keyframes swing {
          0% { transform: rotate(-15deg); }
          100% { transform: rotate(15deg); }
        }
      `}</style>
    </div>
  )
}