'use client'

import React, { useState } from 'react'
import { FieldDataDTO, GatherMaterialDTO } from '@/types/map'

interface TileDetailViewProps {
  fieldData: FieldDataDTO
  selectedTile: { x: number; y: number }
  onBack: () => void
  onGather: (x: number, y: number) => void
  onTransform: (x: number, y: number) => void
  dailyTransformCount: number
  maxDailyTransforms: number
}

export function TileDetailView({
  fieldData,
  selectedTile,
  onBack,
  onGather,
  onTransform,
  dailyTransformCount,
  maxDailyTransforms
}: TileDetailViewProps) {
  const [isGathering, setIsGathering] = useState(false)
  const [isTransforming, setIsTransforming] = useState(false)

  const handleGather = async () => {
    setIsGathering(true)
    onGather(selectedTile.x, selectedTile.y)
    // 채집 완료 후 상태 리셋
    setTimeout(() => setIsGathering(false), 3000)
  }

  const handleTransform = async () => {
    if (dailyTransformCount >= maxDailyTransforms) {
      alert('오늘의 변경 횟수를 모두 사용했습니다!')
      return
    }
    
    setIsTransforming(true)
    onTransform(selectedTile.x, selectedTile.y)
    // 변경 완료 후 상태 리셋
    setTimeout(() => setIsTransforming(false), 2000)
  }

  const canTransform = dailyTransformCount < maxDailyTransforms

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 max-w-md w-full mx-4">
        {/* 헤더 */}
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-bold text-gray-800">
            {fieldData.name} - 타일 ({selectedTile.x}, {selectedTile.y})
          </h2>
          <button
            onClick={onBack}
            className="text-gray-500 hover:text-gray-700 text-2xl"
          >
            ×
          </button>
        </div>

        {/* 타일 정보 */}
        <div className="mb-6">
          <div className="bg-gray-100 rounded-lg p-4 mb-4">
            <h3 className="font-semibold text-gray-800 mb-2">타일 정보</h3>
            <div className="text-sm text-gray-600 space-y-1">
              <div>위치: ({selectedTile.x}, {selectedTile.y})</div>
              <div>지역: {fieldData.name}</div>
              <div>상태: {isGathering ? '채집 중...' : isTransforming ? '변경 중...' : '대기 중'}</div>
            </div>
          </div>

          {/* 변경 횟수 정보 */}
          <div className="bg-blue-50 rounded-lg p-4 mb-4">
            <h3 className="font-semibold text-blue-800 mb-2">오늘의 변경 횟수</h3>
            <div className="flex items-center justify-between">
              <span className="text-sm text-blue-600">
                {dailyTransformCount} / {maxDailyTransforms}
              </span>
              <div className="w-32 bg-blue-200 rounded-full h-2">
                <div 
                  className="bg-blue-600 h-2 rounded-full transition-all duration-300"
                  style={{ width: `${(dailyTransformCount / maxDailyTransforms) * 100}%` }}
                ></div>
              </div>
            </div>
          </div>
        </div>

        {/* 행동 버튼들 */}
        <div className="space-y-3">
          {/* 채집 버튼 */}
          <button
            onClick={handleGather}
            disabled={isGathering || isTransforming}
            className={`w-full py-3 px-4 rounded-lg font-semibold transition-all duration-200 ${
              isGathering
                ? 'bg-yellow-400 text-yellow-800 cursor-not-allowed'
                : 'bg-yellow-500 hover:bg-yellow-600 text-white'
            }`}
          >
            {isGathering ? '채집 중...' : '채집하기'}
          </button>

          {/* 변경 버튼 */}
          <button
            onClick={handleTransform}
            disabled={!canTransform || isGathering || isTransforming}
            className={`w-full py-3 px-4 rounded-lg font-semibold transition-all duration-200 ${
              !canTransform
                ? 'bg-gray-400 text-gray-600 cursor-not-allowed'
                : isTransforming
                ? 'bg-purple-400 text-purple-800 cursor-not-allowed'
                : 'bg-purple-500 hover:bg-purple-600 text-white'
            }`}
          >
            {!canTransform 
              ? '변경 횟수 소진' 
              : isTransforming 
              ? '변경 중...' 
              : '타일 변경하기'
            }
          </button>
        </div>

        {/* 설명 */}
        <div className="mt-4 text-xs text-gray-500 space-y-1">
          <div>• 채집: 해당 타일에서 재료를 수집합니다</div>
          <div>• 변경: 타일의 특성을 변경하여 다른 재료를 얻을 수 있습니다</div>
          <div>• 변경은 하루에 {maxDailyTransforms}회만 가능합니다</div>
        </div>
      </div>
    </div>
  )
} 