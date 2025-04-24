'use client'

import { create } from 'zustand';
import { Character, CharacterStats, StartingRegion } from '@/types/character';
import { useGameStore } from './gameStore';

interface CharacterStore {
  character: Character | null;
  isCreating: boolean;
  startCharacterCreation: () => void;
  createCharacter: (name: string, startingRegion: StartingRegion) => void;
}

const getInitialStats = (region: StartingRegion): CharacterStats => {
  const baseStats: CharacterStats = {
    hp: 100,
    mp: 50,
    strength: 10,
    intelligence: 10,
    agility: 10,
    luck: 10
  };

  // 지역별 특수 능력치
  switch (region) {
    case 'FIRE':
      return {
        ...baseStats,
        strength: 15,  // 불의 지역: 힘 +5
        hp: 120       // 불의 지역: 체력 +20
      };
    case 'WATER':
      return {
        ...baseStats,
        intelligence: 15,  // 물의 지역: 지력 +5
        mp: 70            // 물의 지역: 마나 +20
      };
    case 'ICE':
      return {
        ...baseStats,
        agility: 15,  // 얼음의 지역: 민첩 +5
        luck: 15      // 얼음의 지역: 행운 +5
      };
    case 'FOREST':
      return {
        ...baseStats,
        hp: 120,      // 숲의 지역: 체력 +20
        mp: 70        // 숲의 지역: 마나 +20
      };
    case 'DESERT':
      return {
        ...baseStats,
        strength: 15,  // 사막의 지역: 힘 +5
        agility: 15    // 사막의 지역: 민첩 +5
      };
    default:
      return baseStats;
  }
};

export const useCharacterStore = create<CharacterStore>()((set) => {
  const gameStore = useGameStore.getState();

  return {
    character: null,
    isCreating: false,
    
    startCharacterCreation: () => set({ isCreating: true }),
    
    createCharacter: (name: string, startingRegion: StartingRegion) => {
      const newCharacter = {
        name,
        startingRegion,
        stats: getInitialStats(startingRegion),
        position: { x: 0, y: 0 }
      };
      
      // gameStore에도 캐릭터 정보 저장
      gameStore.setCharacter(newCharacter);
      
      set({
        character: newCharacter,
        isCreating: false
      });
    }
  };
}); 