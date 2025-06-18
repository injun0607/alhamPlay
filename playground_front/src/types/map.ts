export type TileType = 'EXPLORE' | 'PEACE';

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