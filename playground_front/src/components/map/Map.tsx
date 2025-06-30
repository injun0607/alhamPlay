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
              className={`w-20 h-20 border border-gray-300 flex items-center justify-center cursor-pointer transition-all relative overflow-hidden
                ${isSelected ? 'ring-2 ring-blue-400' : 'hover:opacity-80'}
                ${isGatheringHere ? 'bg-yellow-100' : ''}`}
              onClick={() => handleTileClick(x, y)}
              style={{
                backgroundImage: `url('/images/glacier_map.png')`,
                backgroundSize: '500% 500%',
                backgroundPosition: `${x * 25}% ${y * 25}%`
              }}
            >
              {/* 곡괭이 채집 효과 */}
              {isGatheringHere && (
                <>
                  {/* 곡괭이 이미지 */}
                  <div className="absolute inset-0 flex items-center justify-center z-20">
                    <div className="relative">
                      <svg 
                        className="w-8 h-8 text-yellow-600 pickaxe-swing" 
                        xmlns="http://www.w3.org/2000/svg" 
                        viewBox="0 0 24 24" 
                        fill="none" 
                        stroke="currentColor" 
                        strokeWidth="2" 
                        strokeLinecap="round" 
                        strokeLinejoin="round"
                      >
                        <path d="M14.7 6.3a1 1 0 0 0 0 1.4l1.6 1.6a1 1 0 0 0 1.4 0l3.77-3.77a6 6 0 0 1-7.94 7.94l-6.91 6.91a2.12 2.12 0 0 1-3-3l6.91-6.91a6 6 0 0 1 7.94-7.94l-3.76 3.76z"/>
                        <path d="M9 12l-3-3 3-3"/>
                      </svg>
                    </div>
                  </div>
                  
                  {/* 채집 파티클 효과 */}
                  <div className="absolute inset-0 z-10">
                    <div className="absolute top-1/2 left-1/2 w-2 h-2 bg-yellow-400 rounded-full gather-particle" style={{ animationDelay: '0s' }}></div>
                    <div className="absolute top-1/2 left-1/2 w-2 h-2 bg-orange-400 rounded-full gather-particle" style={{ animationDelay: '0.5s' }}></div>
                    <div className="absolute top-1/2 left-1/2 w-2 h-2 bg-red-400 rounded-full gather-particle" style={{ animationDelay: '1s' }}></div>
                  </div>
                  
                  {/* 채집 진행도 원형 표시 */}
                  <div className="absolute inset-0 flex items-center justify-center z-15">
                    <div className="relative w-12 h-12">
                      <svg className="w-12 h-12 transform -rotate-90" viewBox="0 0 36 36">
                        <path
                          className="text-gray-300"
                          stroke="currentColor"
                          strokeWidth="2"
                          fill="none"
                          d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831"
                        />
                        <path
                          className="text-yellow-500 transition-all duration-300"
                          stroke="currentColor"
                          strokeWidth="2"
                          fill="none"
                          strokeLinecap="round"
                          d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831"
                          style={{
                            strokeDasharray: `${(gatheringProgress / 100) * 100} 100`,
                            strokeDashoffset: 0
                          }}
                        />
                      </svg>
                    </div>
                  </div>
                  
                  {/* 채집 중 오버레이 */}
                  <div className="absolute inset-0 bg-yellow-200 opacity-30 animate-pulse"></div>
                </>
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