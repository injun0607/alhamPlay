'use client'

import { useMapStore } from '@/store/mapStore';
import { useGameStore } from '@/store/gameStore';

export const ActionMenu = () => {
  const { currentPosition, mapData } = useMapStore();
  const { startGathering, isGathering } = useGameStore();
  const currentTile = mapData.tiles[currentPosition.y][currentPosition.x];

  const handleAction = (action: string) => {
    switch (action) {
      case 'GATHER':
        if (!isGathering) {
          startGathering();
        }
        break;
      case 'EXPLORE':
        // TODO: 탐험 기능 구현
        break;
    }
  };

  return (
    <div className="mt-4 p-4 bg-white rounded-lg shadow">
      <h3 className="text-lg font-semibold mb-2">행동 선택</h3>
      <div className="grid grid-cols-2 gap-2">
        <button
          onClick={() => handleAction('EXPLORE')}
          className="p-3 bg-green-500 text-white rounded hover:bg-green-600 transition-colors"
        >
          탐험
        </button>
        <button
          onClick={() => handleAction('GATHER')}
          disabled={isGathering}
          className={`
            p-3 text-white rounded transition-colors
            ${isGathering ? 'bg-gray-400 cursor-not-allowed' : 'bg-yellow-500 hover:bg-yellow-600'}
          `}
        >
          {isGathering ? '채집 중...' : '채집'}
        </button>
      </div>
    </div>
  );
}; 