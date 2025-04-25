'use client'

import { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { BattlePhaseScreen } from './BattlePhaseScreen';
import { useBattleStore } from '@/store/battleStore';
import { PhaseType, BattlePhase } from '@/types/battle';

const phaseOrder: PhaseType[] = ['PHASE1','PHASE2','PHASE3'];


type BattleStateType = {
  [K in Lowercase<PhaseType>]: BattlePhase;
};

export const BattlePhaseWrapper = ({ phase }: { phase: PhaseType }) => {
  const router = useRouter();
  const { battleState, moveToPhase,completePhase } = useBattleStore();
  const state = battleState as BattleStateType;

  const handlePhaseComplete = () => {
    const currentPhaseIndex = phaseOrder.indexOf(phase);
    if (currentPhaseIndex < phaseOrder.length - 1) {
      const nextPhase = phaseOrder[currentPhaseIndex + 1];
      completePhase(phase);
      router.push(`/battle/tutorial/${nextPhase.toLowerCase()}`);
    }
  };

  const handleBack = () => {
    const currentPhaseIndex = phaseOrder.indexOf(phase);
    if (currentPhaseIndex > 0) {
      const previousPhase = phaseOrder[currentPhaseIndex - 1];
      const previousPhaseKey = previousPhase.toLowerCase() as keyof BattleStateType;
      if (state[previousPhaseKey].isComplete) {
        router.push(`/battle/tutorial/${previousPhase.toLowerCase()}`);
      }
    }
  };

  useEffect(() => {
    // 현재 페이즈의 인덱스를 찾습니다
    const currentPhaseIndex = phaseOrder.indexOf(phase);
    const currentPhaseKey = phase.toLowerCase() as keyof BattleStateType;
    
    // 이전 페이즈들이 완료되지 않았다면 첫 번째 미완료 페이즈로 이동
    for (let i = 0; i < currentPhaseIndex; i++) {
      const prevPhase = phaseOrder[i];
      const prevPhaseKey = prevPhase.toLowerCase() as keyof BattleStateType;
      if (!state[prevPhaseKey].isComplete) {
        router.push(`/battle/tutorial/${prevPhase.toLowerCase()}`);
        return;
      }
    }

    // 현재 페이즈 상태 설정
    moveToPhase(phase);
  }, [phase, router, moveToPhase]);

  return (
    <BattlePhaseScreen
      phaseType={phase}
      onPhaseComplete={handlePhaseComplete}
      onBack={handleBack}
    />
  );
}; 