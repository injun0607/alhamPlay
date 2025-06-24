'use client'

import { GatherMaterialDTO } from '@/types/map';
import { useApi } from '@/hooks/common/useApi';
import { MaterialInventoryItemDTO } from '@/types/inventory';
import { useRef, useState } from 'react';
import { useEffect } from 'react';
import { useCallback } from 'react';
import { InventoryStore } from '@/store/inventoryStore';


interface ActionMenuProps {
  gatherInfo: GatherMaterialDTO;
  isGathering: boolean;
  setIsGathering: (isGathering: boolean) => void;
  setGatheringProgress: (gatheringProgress: number) => void;
}

export const ActionMenu = ({ gatherInfo, isGathering, setIsGathering, setGatheringProgress }: ActionMenuProps) => {

  const { addItem } = InventoryStore();
  const [isGatheringComplete, setIsGatheringComplete] = useState(false);
  const { areaId, x, y } = gatherInfo;
  const refIsGathering = useRef(isGathering);



  // 백엔드 API 요청을 위한 훅
  const { post: postGathering } = useApi<MaterialInventoryItemDTO>();

  // 백엔드에 채집 결과 요청
  const requestGatheringResult = useCallback(async () => {
    try {
      const result = await postGathering('/field/gather', gatherInfo);
      if (result) {
        addItem(result);
        showGatheringComplete(result);
      }
    } catch (error) {
      console.error('채집 결과 요청 실패:', error);
      // 에러 시 기본 자원 지급 (임시)
      const fallbackResult: MaterialInventoryItemDTO = {
        id: 0,
        name: '나무',
        description: '나무',
        type: 'MATERIAL',
        itemRarity: "COMMON",
        itemOrder: 0
      };
      showGatheringComplete(fallbackResult);
    } finally {
      setIsGatheringComplete(false);
    }
  }, []);

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

  // 채집 완료 알림 표시
  const showGatheringComplete = (result: MaterialInventoryItemDTO) => {
    const notification = document.createElement('div');
    notification.className = 'fixed bottom-4 left-4 bg-yellow-600 text-white p-3 rounded-lg border-2 border-yellow-400 z-50 min-w-48';

    const resourcesHtml = `<div class="text-sm">'📦' ${result.name} - ${result.itemRarity}</div>`


    notification.innerHTML = `
      <div class="font-bold">🎒 자원 획득!</div>
      ${resourcesHtml}
    `;

    document.body.appendChild(notification);

    setTimeout(() => {
      if (document.body.contains(notification)) {
        document.body.removeChild(notification);
      }
    }, 5000);
  };

  const handleAction = (action: string) => {
    if (areaId === null || x === null || y === null) {
      alert('채집 위치를 선택해주세요.');
      return;
    }

    switch (action) {
      case 'GATHER':
        startGathering();
        break;
      case 'EXPLORE':
        alert('모험 ' + areaId + ' ' + x + ' ' + y);
        break;
    }
  };

  return (
    <div>
      {/* 하단 선택지 */}
      <div className="flex gap-4 mt-4">
        <button
          onClick={() => handleAction('GATHER')}
          disabled={isGathering || isGatheringComplete}
          className="px-8 py-4 bg-gradient-to-b from-blue-500 to-blue-700 text-white font-bold rounded-lg border-2 border-blue-300 disabled:opacity-50 disabled:cursor-not-allowed hover:from-blue-600 hover:to-blue-800 transition-all"
        >
          {isGathering ? '⛏️ 채집 중...' : isGatheringComplete ? '⏳ 처리 중...' : '⛏️ 채집'}
        </button>
        <button
          onClick={() => handleAction('EXPLORE')}
          disabled={isGathering || isGatheringComplete}
          className="px-8 py-4 bg-gradient-to-b from-green-500 to-green-700 text-white font-bold rounded-lg border-2 border-green-300 disabled:opacity-50 disabled:cursor-not-allowed hover:from-green-600 hover:to-green-800 transition-all"
        >
          🗺️ 모험
        </button>
      </div>
    </div>
  );
}; 