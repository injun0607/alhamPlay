export type StartingRegion = 'FIRE' | 'WATER' | 'ICE' | 'FOREST' | 'DESERT';

export interface CharacterStats {
  hp: number;
  mp: number;
  strength: number;
  intelligence: number;
  agility: number;
  luck: number;
}

export interface Character {
  name: string;
  startingRegion: StartingRegion;
  stats: CharacterStats;
  position: {
    x: number;
    y: number;
  };
} 