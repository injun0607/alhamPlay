'use client'

import React, { useState, useCallback, useEffect } from 'react';
import { FieldDataDTO, GatherMaterialDTO,  MaterialDTO } from '@/types/map';
import { ActionMenu } from './ActionMenu';
import GatherProgressBar from './GatherProgressBar';

interface MapProps {
  id: number;
  fieldData: FieldDataDTO;
}

export function Map({ id, fieldData }: MapProps) {

  
  const [isGathering, setIsGathering] = useState(false);
  const [gatheringProgress, setGatheringProgress] = useState(0);
  const [gatherInfo, setGatherInfo] = useState<GatherMaterialDTO>({
    areaId: id,
    x: null,
    y: null
  });
 


  const handleTileClick = (x: number, y: number) => {
    setGatherInfo(prev => ({
      ...prev,
      x: x,
      y: y
    }));
  }

  const handleKeyDown = useCallback((e: KeyboardEvent) => {
    // 방향키만 처리하고 다른 키는 무시
    if (!['ArrowUp', 'ArrowDown', 'ArrowLeft', 'ArrowRight'].includes(e.key) || isGathering) {
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
  },[isGathering])

  useEffect(() => {
    window.addEventListener('keydown', handleKeyDown);
    return () => {
      window.removeEventListener('keydown', handleKeyDown);
    }
  },[handleKeyDown])




  // 채집 시작 함수
  

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
        setIsGathering={setIsGathering}
        setGatheringProgress={setGatheringProgress}
      />
    </div>
  );
} 