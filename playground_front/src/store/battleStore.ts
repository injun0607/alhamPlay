'use client'

import { create } from 'zustand';
import { BattleStore, BattleState, PhaseType, Card, BattlePhase } from '@/types/battle';

const initialBattleState: BattleState = {
  phase1: { selectedCards: [], isComplete: false },
  phase2: { selectedCards: [], isComplete: false },
  phase3: { selectedCards: [], isComplete: false },
  currentPhase: 'PHASE1'
};

// 튜토리얼용 카드 데이터
const tutorialCards: Card[] = [
  // Phase 1 카드
  {
    id: 'tutorial-1',
    name: '기본 자세',
    description: '전투를 시작하며 기본 자세를 취합니다',
    phaseType: 'PHASE1',
    effect: { type: 'defense', value: 5 }
  },
  {
    id: 'tutorial-2',
    name: '준비 태세',
    description: '전투 시작 시 방어력을 높입니다',
    phaseType: 'PHASE1',
    effect: { type: 'defense', value: 8 }
  },
  // Phase 2 카드
  {
    id: 'tutorial-3',
    name: '기본 공격',
    description: '기본적인 공격을 가합니다',
    phaseType: 'PHASE2',
    effect: { type: 'damage', value: 10 }
  },
  {
    id: 'tutorial-4',
    name: '방어 태세',
    description: '적의 공격에 대비합니다',
    phaseType: 'PHASE2',
    effect: { type: 'defense', value: 12 }
  },
  // Phase 3 카드
  {
    id: 'tutorial-5',
    name: '마무리 일격',
    description: '강력한 마무리 공격을 가합니다',
    phaseType: 'PHASE3',
    effect: { type: 'damage', value: 15 }
  },
  {
    id: 'tutorial-6',
    name: '회복',
    description: '전투를 마무리하며 체력을 회복합니다',
    phaseType: 'PHASE3',
    effect: { type: 'heal', value: 10 }
  }
];

type PhaseKey = 'phase1' | 'phase2' | 'phase3';

export const useBattleStore = create<BattleStore>((set) => ({
  battleState: initialBattleState,
  userCards: tutorialCards,

  setPhaseCards: (phase: PhaseType, cards: Card[]) =>
    set((state) => {
      const phaseKey = phase.toLowerCase() as PhaseKey;
      const updatedPhase: BattlePhase = {
        ...state.battleState[phaseKey],
        selectedCards: cards
      };

      return {
        battleState: {
          ...state.battleState,
          [phaseKey]: updatedPhase
        }
      };
    }),

  completePhase: (phase: PhaseType) =>
    set((state) => {
      const phaseKey = phase.toLowerCase() as PhaseKey;
      const updatedPhase: BattlePhase = {
        ...state.battleState[phaseKey],
        isComplete: true
      };

      return {
        battleState: {
          ...state.battleState,
          [phaseKey]: updatedPhase,
          currentPhase: phase === 'PHASE1' ? 'PHASE2' : 
                       phase === 'PHASE2' ? 'PHASE3' : 'PHASE3'
        }
      };
    }),

  moveToPhase: (phase: PhaseType) =>
    set((state) => ({
      battleState: {
        ...state.battleState,
        currentPhase: phase
      }
    })),

  resetBattle: () =>
    set({
      battleState: initialBattleState
    })
})); 