export type PhaseType = 'PHASE1' | 'PHASE2' | 'PHASE3';

export interface Card {
  id: string;
  name: string;
  description: string;
  phaseType: PhaseType;
  effect: {
    type: string;
    value: number;
  };
  imageUrl?: string;
}

export interface BattlePhase {
  selectedCards: Card[];
  isComplete: boolean;
}

export interface BattleState {
  phase1: BattlePhase;
  phase2: BattlePhase;
  phase3: BattlePhase;
  currentPhase: PhaseType;
}

export interface BattleStore {
  battleState: BattleState;
  userCards: Card[];
  setPhaseCards: (phase: PhaseType, cards: Card[]) => void;
  completePhase: (phase: PhaseType) => void;
  moveToPhase: (phase: PhaseType) => void;
  resetBattle: () => void;
} 