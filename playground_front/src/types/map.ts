import { ItemRarity } from "./item";

export type TileType = 'EXPLORE' | 'PEACE';

export const FieldTypes = [
  'FOREST',
  'DESERT',
  'GLACIER',
  'VOLCANO',
  'COAST',
  'CHAOS',
] as const;

export type FieldType = typeof FieldTypes[number];


export interface FieldDataDTO{
  name: string;
  description: string;
  fieldType: FieldType;
  selectedInfo: SelectedTileInfo;
}


export interface GatherMaterialDTO{
  areaType: FieldType;
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

export interface SelectedTileInfo{
  selectedFlag: boolean;
  selectedTileX: number;
  selectedTileY: number;
}