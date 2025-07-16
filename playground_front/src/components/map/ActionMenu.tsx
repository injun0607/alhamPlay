'use client'

import { GatherMaterialDTO } from '@/types/map';
import { useApi } from '@/hooks/common/useApi';
import { MaterialInventoryItemDTO } from '@/types/inventory';
import { useRef, useState } from 'react';
import { useEffect } from 'react';
import { useCallback } from 'react';
import { InventoryStore } from '@/store/inventoryStore';
import GatherResult from './GatherResult';

interface ActionMenuProps {
  gatherInfo: GatherMaterialDTO;
  isGathering: boolean;
  setIsGathering: (isGathering: boolean) => void;
  setGatheringProgress: (gatheringProgress: number) => void;
}

export const ActionMenu = ({ gatherInfo, isGathering, setIsGathering, setGatheringProgress }: ActionMenuProps) => {

  const { addItem } = InventoryStore();
  const [isGatheringComplete, setIsGatheringComplete] = useState(false);
  const [gatherResult, setGatherResult] = useState<MaterialInventoryItemDTO | null>(null);
  const [showGatherResult, setShowGatherResult] = useState(false);
  const { areaType, x, y } = gatherInfo;
  const refIsGathering = useRef(isGathering);

  // 백엔드 API 요청을 위한 훅
  const { post: postGathering } = useApi<MaterialInventoryItemDTO>();

  // 백엔드에 채집 결과 요청
  const requestGatheringResult = useCallback(async () => {
    try {
      const result = await postGathering('/field/gather', gatherInfo);
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
  }, [gatherInfo, postGathering, addItem]);

  // 채집 완료 시 백엔드 요청
  useEffect(() => {
    if (isGatheringComplete && gatherInfo.x !== null && gatherInfo.y !== null) {
      requestGatheringResult();
    }
  }, [isGatheringComplete, gatherInfo.x, gatherInfo.y, requestGatheringResult]);

  const startGathering = () => {
    if(isGathering || gatherInfo.x === null || gatherInfo.y === null) return;

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

  const handleGatherKeyPress = useCallback((e: KeyboardEvent) => {
    const isSpace = e.key === ' ' || e.code === 'Space';
    if(refIsGathering.current && !isSpace) return;
    if(isSpace){
      e.preventDefault();
      startGathering();
    }
  }, [startGathering])

  useEffect(() => {
    refIsGathering.current = isGathering;
  }, [isGathering])

  useEffect(() =>{
    window.addEventListener('keydown', handleGatherKeyPress);
    return () => {
      window.removeEventListener('keydown', handleGatherKeyPress);
    }
  }, [handleGatherKeyPress])

  const handleAction = (action: string) => {
    if (areaType === null || x === null || y === null) {
      alert('채집 위치를 선택해주세요.');
      return;
    }

    switch (action) {
      case 'GATHER':
        startGathering();
        break;
      case 'EXPLORE':
        alert('모험 ' + areaType + ' ' + x + ' ' + y);
        break;
    }
  };

  return (
    <>
      <div>
        {/* 하단 선택지 */}
        <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
          <div className="space-y-2">
            <button 
            onClick={() => handleAction('GATHER')}
            disabled={isGathering || isGatheringComplete}
            className="w-full pixel-button bg-green-600 text-white py-2 text-xs font-bold hover:bg-green-500">
              {isGathering ? '⛏️ MINING...' : isGatheringComplete ? '⏳ PROCESSING...' : '⛏️ MINE'}
            </button>
          </div>
          <div className="space-y-2">
            <button className="w-full pixel-button bg-blue-600 text-white py-2 text-xs font-bold hover:bg-blue-500">
              EXPLORE
            </button>
          </div>
        </div>
      </div>

      {/* 채집 결과 알림 */}
      <GatherResult 
        result={gatherResult}
        isVisible={showGatherResult}
        onClose={() => setShowGatherResult(false)}
      />
    </>
  );
}; 