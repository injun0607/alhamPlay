export type TileType = 'EXPLORE' | 'PEACE';

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