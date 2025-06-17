'use client'

import { create } from 'zustand';
import { MapData, MapTile, TileType, TileAction } from '../types/map';

interface MapStore {
  mapData: MapData;
  currentPosition: { x: number; y: number };
  movePlayer: (direction: 'NORTH' | 'SOUTH' | 'EAST' | 'WEST') => void;
  getAvailableActions: () => TileAction[];
}

const createInitialMap = (): MapTile[][] => {
  const map: MapTile[][] = [];
  for (let y = 0; y < 10; y++) {
    map[y] = [];
    for (let x = 0; x < 10; x++) {
      map[y][x] = {
        position: { x, y },
        type: 'EXPLORE', // 기본적으로 탐험맵으로 설정
        actions: [
          { type: 'EXPLORE', available: true },
          { type: 'GATHER', available: true },
          { type: 'MOVE', available: true },
          { type: 'MAP_MOVE', available: true }
        ],
        explored: false
      };
    }
  }
  return map;
};

export const useMapStore = create<MapStore>()((set, get) => ({
  mapData: {
    tiles: createInitialMap(),
    size: { width: 5, height: 5 }
  },
  currentPosition: { x: 0, y: 0 },
  
  movePlayer: (direction: 'NORTH' | 'SOUTH' | 'EAST' | 'WEST') => set((state: MapStore) => {
    const { x, y } = state.currentPosition;
    const newPosition = { ...state.currentPosition };

    switch (direction) {
      case 'NORTH':
        if (y > 0) newPosition.y = y - 1;
        break;
      case 'SOUTH':
        if (y < 9) newPosition.y = y + 1;
        break;
      case 'EAST':
        if (x < 9) newPosition.x = x + 1;
        break;
      case 'WEST':
        if (x > 0) newPosition.x = x - 1;
        break;
    }

    return { currentPosition: newPosition };
  }),

  getAvailableActions: () => {
    const { mapData, currentPosition } = get();
    return mapData.tiles[currentPosition.y][currentPosition.x].actions;
  }
})); 