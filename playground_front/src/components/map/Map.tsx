'use client'

import React, { useState, useCallback, useEffect } from 'react';
import { FieldDataDTO, GatherMaterialDTO,  MaterialDTO } from '@/types/map';
import { ActionMenu } from './ActionMenu';
import { useApi } from '@/hooks/common/useApi';
import GatherProgressBar from './GatherProgressBar';

interface MapProps {
  id: number;
  fieldData: FieldDataDTO;
}

export function Map({ id, fieldData }: MapProps) {
  const [gatherInfo, setGatherInfo] = useState<GatherMaterialDTO>({
    areaId: id,
    x: null,
    y: null
  });
  const [isGathering, setIsGathering] = useState(false);
  const [gatheringProgress, setGatheringProgress] = useState(0);
  const [isGatheringComplete, setIsGatheringComplete] = useState(false);

  // 백엔드 API 요청을 위한 훅
  const { post: postGathering } = useApi<MaterialDTO>();

  const handleTileClick = (x: number, y: number) => {
    setGatherInfo(prev => ({
      ...prev,
      x: x,
      y: y
    }));
  }

  const handleKeyDown = useCallback((e: KeyboardEvent) => {
    // 방향키만 처리하고 다른 키는 무시
    if (!['ArrowUp', 'ArrowDown', 'ArrowLeft', 'ArrowRight'].includes(e.key)) {
      return;
    }

    // 방향키 이벤트 처리
    e.preventDefault();
    e.stopPropagation();

    setGatherInfo(prev => {
      let x = prev?.x
      let y = prev?.y

      if(x === null || y === null){
        x = 0;
        y = 0;
      }else{
        switch(e.key){
          case 'ArrowUp':
            y = Math.max(0, y-1);
            break;
          case 'ArrowDown':
            y = Math.min(4, y+1);
            break;
          case 'ArrowLeft':
            x = Math.max(0, x-1);
            break;
          case 'ArrowRight':
            x = Math.min(4, x+1);
            break;
          default:
            return prev;
        }
      }
      return {...prev, x, y};
    })
  },[])

  useEffect(() => {
    window.addEventListener('keydown', handleKeyDown);
    return () => {
      window.removeEventListener('keydown', handleKeyDown);
    }
  },[handleKeyDown])

  // 백엔드에 채집 결과 요청
  const requestGatheringResult = useCallback(async () => {
    try {
      const result = await postGathering('/field/gather', gatherInfo);
      if (result) {
        showGatheringComplete(result);
      }
    } catch (error) {
      console.error('채집 결과 요청 실패:', error);
      // 에러 시 기본 자원 지급 (임시)
      const fallbackResult: MaterialDTO = {
           name: '나무', itemRarity: "COMMON"
      };
      showGatheringComplete(fallbackResult);
    } finally {
      setIsGatheringComplete(false);
    }
  },[]);

  // 채집 완료 시 백엔드 요청
  useEffect(() => {
    if (isGatheringComplete && gatherInfo.x !== null && gatherInfo.y !== null) {
      requestGatheringResult();
    }
  }, [isGatheringComplete, gatherInfo.x, gatherInfo.y, requestGatheringResult]);

  // 채집 시작 함수
  const startGathering = () => {
    setIsGathering(true);
    setGatheringProgress(0);
    
    // 프로그레스 시뮬레이션 (실제로는 API 호출 시간에 맞춰 조정)
    const interval = setInterval(() => {
      setGatheringProgress(prev => {
        if (prev >= 100) {
          clearInterval(interval);
          setIsGathering(false);
          setIsGatheringComplete(true);
          return 100;
        }
        return prev + 1;
      });
    }, 50);
  };

  // 채집 완료 알림 표시
  const showGatheringComplete = (result: MaterialDTO) => {
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

  return (
    <div className="flex flex-col items-center gap-8 p-4">
      {/* 필드 이름 */}
      <h1 className="text-2xl font-bold">{fieldData.name}</h1>

      {/* 5x5 타일 맵 */}
      <div className="grid grid-cols-5 gap-1">
        {Array.from({ length: 25 }, (_, index) => {
          const x = index % 5;
          const y = Math.floor(index / 5);
          const isSelected = gatherInfo.x === x && gatherInfo.y === y;
          const isGatheringHere = isGathering && isSelected;
          
          return (
            <div
              key={`${x}-${y}`}
              className={`w-20 h-20 border border-gray-300 flex items-center justify-center cursor-pointer transition-all relative
                ${isSelected ? 'ring-2 ring-blue-400' : 'hover:opacity-80'}
                ${isGatheringHere ? 'animate-pulse bg-yellow-400' : ''}`}
              onClick={() => handleTileClick(x, y)}
              style={{
                backgroundImage: `url('/images/glacier_map.png')`,
                backgroundSize: '500% 500%',
                backgroundPosition: `${x * 25}% ${y * 25}%`
              }}
            >
              {isGatheringHere && (
                <div className="absolute inset-0 bg-yellow-300 opacity-50 animate-ping rounded" />
              )}
              <span className="text-sm text-white font-bold drop-shadow-lg z-10">
                ({x}, {y})
              </span>
            </div>
          );
        })}
      </div>

      <GatherProgressBar isGathering={isGathering} gatheringProgress={gatheringProgress} />
      
      <div>
        {gatherInfo.x !== null && gatherInfo.y !== null && (
          <div className="text-center">
            <span className="text-sm text-gray-600">선택된 위치: ({gatherInfo.x}, {gatherInfo.y})</span>
          </div>
        )}
      </div>
      
      <ActionMenu 
        gatherInfo={gatherInfo} 
        isGathering={isGathering}
        isGatheringComplete={isGatheringComplete}
        onStartGathering={startGathering}
      />
    </div>
  );
} 