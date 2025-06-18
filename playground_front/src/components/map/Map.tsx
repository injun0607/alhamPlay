'use client'

import React, { useState, useCallback, useEffect } from 'react';
import { FieldDataDTO } from '@/types/map';
import { ActionMenu } from './ActionMenu';
import { GatherMaterialDTO } from '@/types/map';

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

  const handleTileClick = (x: number, y: number) => {
    setGatherInfo(prev => ({
      ...prev,
      x: x,
      y: y
    }));
    alert(x + ', ' + y);
  }

  const handleKeyDown = useCallback((e: KeyboardEvent) => {
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

  return (
    <div className="flex flex-col items-center gap-8 p-4">
      {/* 필드 이름 */}
      <h1 className="text-2xl font-bold">{fieldData.name}</h1>

      {/* 5x5 타일 맵 */}
      <div className="grid grid-cols-5">
        {Array.from({ length: 25 }, (_, index) => {
          const x = index % 5;
          const y = Math.floor(index / 5);
          return (
            <div
              key={`${x}-${y}`}
              className="w-20 h-20 border border-gray-300 flex items-center justify-center cursor-pointer hover:opacity-80 transition-opacity relative"
              onClick={() => handleTileClick(x, y)}
              style={{
                backgroundImage: `url('/images/glacier_map.png')`,
                backgroundSize: '500% 500%', // 5x5 그리드를 위해 500%
                backgroundPosition: `${x * 25}% ${y * 25}%` // 각 타일의 위치 계산
              }}
            >
              <span className="text-sm text-white font-bold drop-shadow-lg">
                ({x}, {y})
              </span>
            </div>
          );
        })}
      </div>
      <div>
        {gatherInfo.x !== null && gatherInfo.y !== null && (
          <div>
            <span>x: {gatherInfo.x}</span>
            <span>y: {gatherInfo.y}</span>
          </div>
        )}
      </div>
      <ActionMenu gatherInfo={gatherInfo} />
    </div>
  );
} 