'use client'

import { GatherMaterialDTO } from '@/types/map';

interface ActionMenuProps {
  gatherInfo: GatherMaterialDTO;
  isGathering: boolean;
  isGatheringComplete: boolean;
  onStartGathering: () => void;
}

export const ActionMenu = ({ gatherInfo, isGathering, isGatheringComplete, onStartGathering }: ActionMenuProps) => {
  const { areaId, x, y } = gatherInfo;

  const handleAction = (action: string) => {
    if (areaId === null || x === null || y === null) {
      alert('채집 위치를 선택해주세요.');
      return;
    }

    switch (action) {
      case 'GATHER':
        onStartGathering();
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