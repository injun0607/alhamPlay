'use client'

import { create } from 'zustand';
import { MapData, TileAction } from '../types/map';

interface MapStore {
  mapData: MapData | null;
  currentPosition: { x: number; y: number };
  isLoading: boolean;
  error: string | null;
  movePlayer: (direction: 'NORTH' | 'SOUTH' | 'EAST' | 'WEST') => void;
  getAvailableActions: () => TileAction[];
  fetchMapData: () => Promise<void>;
}

export const useMapStore = create<MapStore>()((set, get) => ({
  mapData: null,
  currentPosition: { x: 0, y: 0 },
  isLoading: false,
  error: null,

  fetchMapData: async () => {
    try {
      set({ isLoading: true, error: null });
      const response = await fetch('/api/map');
      if (!response.ok) throw new Error('맵 데이터를 불러오는데 실패했습니다.');
      const mapData = await response.json();
      set({ mapData, isLoading: false });
    } catch (error) {
      set({ error: error instanceof Error ? error.message : '알 수 없는 에러가 발생했습니다.', isLoading: false });
    }
  },
  
  movePlayer: (direction: 'NORTH' | 'SOUTH' | 'EAST' | 'WEST') => set((state: MapStore) => {
    if (!state.mapData) return state;
    
    const { x, y } = state.currentPosition;
    const newPosition = { ...state.currentPosition };

    switch (direction) {
      case 'NORTH':
        if (y > 0) newPosition.y = y - 1;
        break;
      case 'SOUTH':
        if (y < state.mapData.size.height - 1) newPosition.y = y + 1;
        break;
      case 'EAST':
        if (x < state.mapData.size.width - 1) newPosition.x = x + 1;
        break;
      case 'WEST':
        if (x > 0) newPosition.x = x - 1;
        break;
    }

    return { currentPosition: newPosition };
  }),

  getAvailableActions: () => {
    const { mapData, currentPosition } = get();
    if (!mapData) return [];
    return mapData.tiles[currentPosition.y][currentPosition.x].actions;
  }
})); 