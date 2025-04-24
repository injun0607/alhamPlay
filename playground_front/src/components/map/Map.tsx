'use client'

import { useMapStore } from '@/store/mapStore';
import { GatheringProgress } from '../gathering/GatheringProgress';

export const Map = () => {
  const { mapData, currentPosition, movePlayer } = useMapStore();

  const handleTileClick = (x: number, y: number) => {
    // 클릭한 위치로 직접 이동
    const dx = x - currentPosition.x;
    const dy = y - currentPosition.y;

    // 가로 이동
    for (let i = 0; i < Math.abs(dx); i++) {
      if (dx > 0) {
        movePlayer('EAST');
      } else {
        movePlayer('WEST');
      }
    }

    // 세로 이동
    for (let i = 0; i < Math.abs(dy); i++) {
      if (dy > 0) {
        movePlayer('SOUTH');
      } else {
        movePlayer('NORTH');
      }
    }
  };

  return (
    <div className="relative">
      <div className="grid grid-cols-10 gap-0">
        {mapData.tiles.map((row, y) => (
          row.map((tile, x) => (
            <div
              key={`${x}-${y}`}
              onClick={() => handleTileClick(x, y)}
              className={`
                w-16 h-16 border border-gray-300 flex items-center justify-center cursor-pointer
                ${x === currentPosition.x && y === currentPosition.y ? 'bg-blue-200' : ''}
                hover:bg-blue-100 transition-colors
              `}
            >
              {x},{y}
            </div>
          ))
        ))}
      </div>
      <GatheringProgress />
    </div>
  );
}; 