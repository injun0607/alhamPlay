'use client'

import { create } from 'zustand';

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