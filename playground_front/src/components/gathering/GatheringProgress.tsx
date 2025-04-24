'use client'

import { useEffect, useState } from 'react';
import { useGameStore } from '@/store/gameStore';
import { useMapStore } from '@/store/mapStore';

export const GatheringProgress = () => {
  const { isGathering, gatheringProgress, gatheringTime, updateGatheringProgress, finishGathering, gatherResource, lastGatheredResource } = useGameStore();
  const { currentPosition } = useMapStore();
  const [showResourceMessage, setShowResourceMessage] = useState(false);

  useEffect(() => {
    if (!isGathering) return;

    const startTime = Date.now();
    const interval = setInterval(() => {
      const elapsed = Date.now() - startTime;
      const progress = Math.min(100, (elapsed / gatheringTime) * 100);
      
      updateGatheringProgress(progress);

      if (progress >= 100) {
        clearInterval(interval);
        const resource = gatherResource();
        finishGathering();
        setShowResourceMessage(true);
        setTimeout(() => setShowResourceMessage(false), 3000);
      }
    }, 100);

    return () => clearInterval(interval);
  }, [isGathering, gatheringTime, updateGatheringProgress, finishGathering, gatherResource]);

  if (!isGathering && !showResourceMessage) return null;

  // 현재 타일의 위치 계산 (타일 크기 64px 기준)
  const tileSize = 64;
  const left = currentPosition.x * tileSize;
  const top = currentPosition.y * tileSize;

  return (
    <div 
      className="absolute bg-black bg-opacity-50 text-white p-2 rounded-lg"
      style={{
        left: `${left}px`,
        top: `${top}px`,
        width: `${tileSize}px`,
        zIndex: 50
      }}
    >
      {isGathering ? (
        <>
          <div className="text-center text-sm mb-1">채집 중...</div>
          <div className="w-full bg-gray-600 rounded-full h-2">
            <div
              className="bg-yellow-500 h-2 rounded-full transition-all duration-100"
              style={{ width: `${gatheringProgress}%` }}
            />
          </div>
          <div className="text-center text-xs mt-1">
            {Math.round(gatheringProgress)}%
          </div>
        </>
      ) : showResourceMessage && lastGatheredResource && (
        <div className="text-center text-sm">
          {lastGatheredResource} 획득!
        </div>
      )}
    </div>
  );
}; 