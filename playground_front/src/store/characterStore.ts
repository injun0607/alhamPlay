'use client'

import { create } from 'zustand';
import { Character, CharacterStats, StartingRegion } from '@/types/character';
import { useGameStore } from './gameStore';

interface Stats {
  strength: number;
  intelligence: number;
  dexterity: number;
  vitality: number;
  luck: number;
  mana: number;
  hp: number;
  mp: number;
  agility: number;
}

interface InventoryItem {
  name: string;
  quantity: number;
}

interface CharacterStore {
  name: string;
  stats: Stats;
  inventory: InventoryItem[];
  isCreatingCharacter: boolean;
  setCharacterName: (name: string) => void;
  setCharacterStats: (stats: Partial<Stats>) => void;
  addItemToInventory: (itemName: string, quantity: number) => void;
  startCharacterCreation: () => void;
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

const initialStats: Stats = {
  strength: 0,
  intelligence: 0,
  dexterity: 0,
  vitality: 0,
  luck: 0,
  mana: 0,
  hp: 100,
  mp: 50,
  agility: 0
};

export const useCharacterStore = create<CharacterStore>((set) => ({
  name: '',
  stats: initialStats,
  inventory: [],
  isCreatingCharacter: false,

  setCharacterName: (name) => set({ name }),
  
  setCharacterStats: (newStats) => 
    set((state) => ({
      stats: { ...state.stats, ...newStats }
    })),
  
  addItemToInventory: (itemName, quantity) => 
    set((state) => {
      const existingItem = state.inventory.find(item => item.name === itemName);
      if (existingItem) {
        return {
          inventory: state.inventory.map(item =>
            item.name === itemName
              ? { ...item, quantity: item.quantity + quantity }
              : item
          )
        };
      }
      return {
        inventory: [...state.inventory, { name: itemName, quantity }]
      };
    }),

  startCharacterCreation: () => set({ isCreatingCharacter: true }),
})); 