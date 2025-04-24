'use client'

import { useCharacterStore } from '@/store/characterStore';

export const StartScreen = () => {
  const { startCharacterCreation } = useCharacterStore();

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div className="bg-white p-8 rounded-lg shadow-lg">
        <h2 className="text-2xl font-bold mb-6 text-center">RPG Game</h2>
        <div className="space-y-4">
          <button
            onClick={startCharacterCreation}
            className="w-full p-4 bg-blue-500 text-white rounded hover:bg-blue-600 transition-colors"
          >
            새로 시작하기
          </button>
          <button
            className="w-full p-4 bg-gray-500 text-white rounded hover:bg-gray-600 transition-colors"
          >
            로그인하기
          </button>
        </div>
      </div>
    </div>
  );
}; 