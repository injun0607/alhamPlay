'use client'
//TODO - 채집 위치 정보도 사실상 디비에 있어서 필요없을것 같긴한데 .. 변경해봐야겠음
import React, { useState, useEffect,useCallback } from 'react'
import { FieldDataDTO, GatherMaterialDTO } from '@/types/map'
import { MaterialInventoryItemDTO } from '@/types/inventory'
import { useFieldStore } from '@/store/fieldStore'
import { useApi } from '@/hooks/common/useApi';
import { InventoryStore } from '@/store/inventoryStore'

interface TileDetailViewProps {
  fieldData: FieldDataDTO;
  onBack: () => void;
}

export function TileDetailView({
  fieldData,
  onBack,
}: TileDetailViewProps) {
  const [isGathering, setIsGathering] = useState(false)
  const [isGatheringComplete, setIsGatheringComplete] = useState(false);
  const [gatherResult, setGatherResult] = useState<MaterialInventoryItemDTO | null>(null);
  const [gatheringProgress, setGatheringProgress] = useState(0)
  const [showGatherResult, setShowGatherResult] = useState(false)
  const { selectedTile } = useFieldStore();
  const { addItem } = InventoryStore();

  const { post: postGathering, loading } = useApi<MaterialInventoryItemDTO>();

  const requestGatheringResult = useCallback(async () => {
    try {
      const result = await postGathering<null>('/field/gather', null);
      if (result) {
        addItem(result);
        setGatherResult(result);
        setShowGatherResult(true);
      }
    } catch (error) {
      console.error('채집 결과 요청 실패:', error);
      // 에러 시 기본 자원 지급 (임시)
      const fallbackResult: MaterialInventoryItemDTO = {
        id: 0,
        inventoryItemId: 0,
        itemImg: '',
        quantity: 1,
        name: '나무',
        description: '나무',
        type: 'MATERIAL',
        itemRarity: "COMMON",
        itemOrder: 0
      };
      setGatherResult(fallbackResult);
      setShowGatherResult(true);
    } finally {
      setIsGatheringComplete(false);
    }
  }, [postGathering, addItem]);

  useEffect(() => {
    if (isGatheringComplete && selectedTile?.selectedTileX !== null && selectedTile?.selectedTileY !== null) {
      requestGatheringResult();
    }
  }, [isGatheringComplete, selectedTile?.selectedTileX, selectedTile?.selectedTileY, requestGatheringResult]);

  const startGathering = () => {
    if (isGathering || !selectedTile || selectedTile?.selectedTileX == null || selectedTile?.selectedTileY == null) return;

    setIsGathering(true);
    setGatheringProgress(0);

    let progress = 0;
    const interval = setInterval(() => {
      progress += 1;
      setGatheringProgress(progress);

      if (progress >= 100) {
        clearInterval(interval);
        setIsGathering(false);
        setIsGatheringComplete(true);
      }
    }, 50);
  };


  // 채집 결과가 있으면 3초 후 자동으로 숨기기
  useEffect(() => {
    if (showGatherResult && gatherResult) {
      const timer = setTimeout(() => {
        setShowGatherResult(false)
      }, 3000)
      return () => clearTimeout(timer)
    }
  }, [showGatherResult, gatherResult])

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

        {/* 채집물 이미지 또는 결과 */}
        <div className="flex justify-center items-center my-4 relative">
          {loading ? (
            <div className="text-yellow-200 text-center pixel-font py-8 px-4 bg-gray-800 border-2 border-yellow-400 rounded">
              결과물 확인중입니다...
            </div>
          ) : showGatherResult && gatherResult ? (
            // 채집 결과 표시
            <div className="gather-result-popup">
              <div className="bg-gradient-to-br from-yellow-600 to-yellow-800 border-4 border-yellow-500 rounded-none p-4 text-center pixel-art-shadow">
                <div className="text-yellow-300 text-xs mb-2 pixel-font">RESOURCE FOUND!</div>
                <div className="flex items-center justify-center gap-3 mb-2">
                  <div className="w-12 h-12 bg-gradient-to-br from-gray-600 to-gray-700 border-2 border-gray-500 flex items-center justify-center">
                    <span className="text-white font-bold text-lg">{gatherResult.name.charAt(0)}</span>
                  </div>
                  <div className="text-center">
                    <div className="text-white font-bold text-sm pixel-font">{gatherResult.name}</div>
                    <div className="text-xs" style={{ 
                      color: gatherResult.itemRarity === 'COMMON' ? '#888888' :
                             gatherResult.itemRarity === 'UNCOMMON' ? '#4CAF50' :
                             gatherResult.itemRarity === 'RARE' ? '#2196F3' :
                             gatherResult.itemRarity === 'EPIC' ? '#9C27B0' :
                             gatherResult.itemRarity === 'UNIQUE' ? '#E91E63' :
                             gatherResult.itemRarity === 'LEGENDARY' ? '#FF9800' : '#888888'
                    }}>
                      {gatherResult.itemRarity}
                    </div>
                  </div>
                </div>
                <div className="text-yellow-400 font-bold text-sm pixel-font">x{gatherResult.quantity}</div>
              </div>
            </div>
          ) : (
            // 기존 광석 이미지
            <div className={`ore-pixel ${isGathering ? 'ore-shake' : ''}`}>
              <img
                src="/images/diamond_pixel.png"
                alt="광석"
                className="w-16 h-16"
                style={{ imageRendering: 'pixelated' }}
              />
            </div>
          )}
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
            onClick={startGathering}
            disabled={isGathering}
            className={`w-full py-4 px-4 border-2 font-bold pixel-font transition-all duration-200 ${isGathering
                ? 'bg-yellow-800 border-yellow-600 text-yellow-200 cursor-not-allowed'
                : 'bg-yellow-600 border-yellow-500 text-white hover:bg-yellow-700 hover:border-yellow-600'
              }`}
          >
            {isGathering ? '채집 중...' : '채집하기'}
          </button>
        </div>

        {/* 설명 */}
        <div className="mt-4 text-xs text-gray-400 space-y-1 pixel-font">
          <div>• 채집: 해당 타일에서 재료를 수집합니다</div>
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
        .ore-shake {
          animation: ore-shake-anim 0.3s infinite alternate;
        }
        @keyframes ore-shake-anim {
          0% { transform: translate(0, 0) rotate(-3deg); }
          50% { transform: translate(2px, -2px) rotate(3deg); }
          100% { transform: translate(-2px, 2px) rotate(-3deg); }
        }
        .gather-result-popup {
          animation: popup-appear 0.5s ease-out;
        }
        @keyframes popup-appear {
          0% { 
            transform: scale(0.5) translateY(20px);
            opacity: 0;
          }
          100% { 
            transform: scale(1) translateY(0);
            opacity: 1;
          }
        }
      `}</style>
    </div>
  )
}