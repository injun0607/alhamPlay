'use client'

import { useEffect, useState } from 'react';
import { useBattleStore } from '@/store/battleStore';
import { BattlePhaseScreen } from './BattlePhaseScreen';
import { motion, AnimatePresence } from 'framer-motion';

export const BattleScreen = () => {
  const { battleState, resetBattle } = useBattleStore();
  const [showTutorial, setShowTutorial] = useState(true);

  useEffect(() => {
    // 컴포넌트 마운트 시 전투 상태 초기화
    resetBattle();
    // 3초 후 튜토리얼 메시지 숨기기
    const timer = setTimeout(() => setShowTutorial(false), 3000);
    return () => clearTimeout(timer);
  }, []);

  return (
    <div className="relative">
      <AnimatePresence>
        {showTutorial && (
          <motion.div
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -20 }}
            className="fixed inset-0 flex items-center justify-center z-50"
          >
            <div className="bg-gray-900 p-8 rounded-lg max-w-2xl w-full mx-4 text-white text-center">
              <h2 className="text-2xl font-bold mb-4">튜토리얼 전투</h2>
              <p className="text-gray-300">
                첫 번째 전투를 시작합니다. 각 페이즈에서 사용할 카드를 선택하세요.
              </p>
            </div>
          </motion.div>
        )}
      </AnimatePresence>

      <AnimatePresence mode="wait">
        {!showTutorial && (
        <motion.div
          key={battleState.currentPhase}
          initial={{ opacity: 0, x: 100 }}
          animate={{ opacity: 1, x: 0 }}
          exit={{ opacity: 0, x: -100 }}
          transition={{ 
            duration: 0.5,
            ease: "easeInOut"
          }}
        >
          <BattlePhaseScreen phaseType={battleState.currentPhase} />
        </motion.div>)}
      </AnimatePresence>
    </div>
  );
}; 