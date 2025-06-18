'use client'

import { GatherMaterialDTO } from '@/types/map';

interface ActionMenuProps{
  gatherInfo: GatherMaterialDTO;
}

export const ActionMenu = ({gatherInfo}: ActionMenuProps) => {
  
  const {areaId, x, y} = gatherInfo;



  const handleAction = (action: string) => {
    if(areaId === null || x === null || y === null){
      alert('채집 위치를 선택해주세요.');
      return;
    }

    switch (action) {
      case 'GATHER':
        
        alert('채집 ' + areaId + ' ' + x + ' ' + y);
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
        className="px-6 py-3 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors">
          채집
        </button>
        <button 
        onClick={() => handleAction('EXPLORE')}
        className="px-6 py-3 bg-green-500 text-white rounded-lg hover:bg-green-600 transition-colors">
          모험
        </button>
      </div>
    </div>
  );
}; 