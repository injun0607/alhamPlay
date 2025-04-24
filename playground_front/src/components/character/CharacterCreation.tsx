'use client'

import { useState } from 'react';
import { useCharacterStore } from '@/store/characterStore';
import { StartingRegion } from '@/types/character';

const REGION_INFO = {
  FIRE: { name: '불의 지역', color: 'bg-red-500' },
  WATER: { name: '물의 지역', color: 'bg-blue-500' },
  ICE: { name: '얼음의 지역', color: 'bg-cyan-500' },
  FOREST: { name: '숲의 지역', color: 'bg-green-500' },
  DESERT: { name: '사막의 지역', color: 'bg-yellow-500' },
} as const;

export const CharacterCreation = () => {
  const [name, setName] = useState('');
  const [selectedRegion, setSelectedRegion] = useState<StartingRegion>('FIRE');
  const { createCharacter } = useCharacterStore();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (name.trim()) {
      createCharacter(name, selectedRegion);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-6 text-center">캐릭터 생성</h2>
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              캐릭터 이름
            </label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              className="w-full p-2 border rounded"
              placeholder="이름을 입력하세요"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              시작 지역 선택
            </label>
            <div className="grid grid-cols-2 gap-2">
              {(Object.entries(REGION_INFO) as [StartingRegion, { name: string; color: string }][]).map(([region, info]) => (
                <button
                  key={region}
                  type="button"
                  onClick={() => setSelectedRegion(region)}
                  className={`
                    p-3 rounded text-white transition-colors
                    ${info.color}
                    ${selectedRegion === region ? 'ring-2 ring-offset-2 ring-black' : ''}
                  `}
                >
                  {info.name}
                </button>
              ))}
            </div>
          </div>

          <button
            type="submit"
            className="w-full p-3 bg-blue-500 text-white rounded hover:bg-blue-600 transition-colors"
          >
            생성하기
          </button>
        </form>
      </div>
    </div>
  );
}; 