import { ItemRarity } from "./item";

export type TileType = 'EXPLORE' | 'PEACE';

export const FieldTypes = [
  "FOREST",
  "CAVE",
  "MOUNTAIN",
  "LAKE",
  "VOLCANO",
] as const;

export type FieldType = typeof FieldTypes[number];


export interface FieldDataDTO{
  name: string;
  description: string;
  fieldType: string;
}


export interface GatherMaterialDTO{
  areaId: number | null;
  x: number | null;
  y: number | null;
}

export interface MaterialDTO{
  itemRarity: ItemRarity,
  name: string
}

export interface TileAction {
  type: 'EXPLORE' | 'GATHER' | 'MOVE' | 'MAP_MOVE' | 'INN' | 'BLACKSMITH' | 'MARKET' | 'GUILD';
  available: boolean;
}

export interface MapTile {
  position: {
    x: number;
    y: number;
  };
  type: TileType;
  actions: TileAction[];
  explored: boolean;
}

export interface MapData {
  tiles: MapTile[][];
  size: {
    width: number;
    height: number;
  };
} 