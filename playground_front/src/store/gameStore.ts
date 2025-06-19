'use client'

import { create } from 'zustand';
import { Character } from '@/types/character';
import { ResourceType } from '@/types/inventory';
// import { useInventoryStore } from './inventoryStore';

interface GameState {
  character: Character | null;
  isGathering: boolean;
  gatheringProgress: number;
  gatheringTime: number;
  lastGatheredResource: ResourceType | null;
  setCharacter: (character: Character) => void;
  updateCharacterStats: (stats: Partial<Character['stats']>) => void;
  updateCharacterPosition: (position: { x: number; y: number }) => void;
  startGathering: () => void;
  updateGatheringProgress: (progress: number) => void;
  finishGathering: () => void;
  gatherResource: () => void;
}

export const useGameStore = create<GameState>()((set, get) => ({
  character: null,
  isGathering: false,
  gatheringProgress: 0,
  gatheringTime: 5000, // 기본 채집 시간 5초
  lastGatheredResource: null,
  
  setCharacter: (character) => set({ character }),
  
  updateCharacterStats: (stats) => set((state) => ({
    character: state.character ? {
      ...state.character,
      stats: {
        ...state.character.stats,
        ...stats
      }
    } : null
  })),
  
  updateCharacterPosition: (position) => set((state) => ({
    character: state.character ? {
      ...state.character,
      position
    } : null
  })),

  startGathering: () => {
    const { character } = get();
    if (!character) return;

    // 민첩성에 따라 채집 시간 조정 (민첩성 10당 10% 감소)
    const agilityBonus = character.stats.agility / 10 * 0.1;
    const adjustedTime = Math.max(1000, get().gatheringTime * (1 - agilityBonus));

    set({
      isGathering: true,
      gatheringProgress: 0,
      gatheringTime: adjustedTime
    });
  },

  updateGatheringProgress: (progress) => set({ gatheringProgress: progress }),

  finishGathering: () => set({
    isGathering: false,
    gatheringProgress: 0
  }),

  gatherResource: () => {
    const resources: ResourceType[] = ['나무', '암석', '나뭇잎'];
    const randomResource = resources[Math.floor(Math.random() * resources.length)];
    
    set({ lastGatheredResource: randomResource });
    
    // 인벤토리에 아이템 추가
    // useInventoryStore.getState().addItem(randomResource);
    
    // TODO: 추후 블록체인 연동 시 여기에 자원 획득 로직 추가
    // 1. 맵의 자원 정보 확인 (블록체인)
    // 2. 유저의 NFT 업데이트 (블록체인)
    
    return randomResource;
  }
})); 