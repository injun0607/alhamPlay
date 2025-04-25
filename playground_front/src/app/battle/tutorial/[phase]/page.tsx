'use client';

import { BattlePhaseWrapper } from '@/components/battle/BattlePhaseWrapper';
import { PhaseType } from '@/types/battle';
import { useParams } from 'next/navigation';

export default function BattlePhasePage() {
  const params = useParams();
  const phase = params?.phase as string;
  const currentPhase = `PHASE${phase.slice(-1)}` as PhaseType;

  return <BattlePhaseWrapper phase={currentPhase} />;
}
