'use client'

import { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { motion } from 'framer-motion';
import { useBattleStore } from '@/store/battleStore';

export default function TutorialBattlePage() {
  const router = useRouter();
  const { resetBattle } = useBattleStore();

  useEffect(() => {
    resetBattle();
    // 3초 후 첫 번째 페이즈로 자동 이동
    const timer = setTimeout(() => {
      router.push('/battle/tutorial/phase1');
    }, 3000);

    return () => clearTimeout(timer);
  }, []);

  return (
    <div className="fixed inset-0 bg-black flex items-center justify-center">
      <motion.div
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        className="bg-gray-900 p-8 rounded-lg max-w-2xl w-full mx-4 text-white text-center"
      >
        <h2 className="text-2xl font-bold mb-4">튜토리얼 전투</h2>
        <p className="text-gray-300">
          첫 번째 전투를 시작합니다. 각 페이즈에서 사용할 카드를 선택하세요.
        </p>
      </motion.div>
    </div>
  );
} 