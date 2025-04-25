'use client'

import { motion } from 'framer-motion';
import { ParticleBackground } from '../ui/ParticleBackground';

interface StartSceneProps {
  onStartNewGame: () => void;
}

export function StartScene({ onStartNewGame }: StartSceneProps) {
  return (
    <div className="fixed inset-0 bg-black flex items-center justify-center">
      <motion.div
        key="main"
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        exit={{ opacity: 0, y: -20 }}
        className="text-center z-10"
      >
        <motion.h1
          className="text-4xl md:text-6xl font-bold text-white mb-8"
          initial={{ scale: 0.9 }}
          animate={{ scale: 1 }}
          transition={{ duration: 0.5 }}
        >
          기억의 조각
        </motion.h1>
        
        <motion.p
          className="text-gray-400 text-lg md:text-xl mb-12"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.3 }}
        >
          잊혀진 과거를 찾아 떠나는 여정
        </motion.p>

        <motion.button
          onClick={onStartNewGame}
          className="px-8 py-4 text-lg font-semibold rounded-lg bg-blue-600 hover:bg-blue-700 transition-all"
          whileHover={{ scale: 1.05 }}
          whileTap={{ scale: 0.95 }}
        >
          여정 시작
        </motion.button>
      </motion.div>
      
      <ParticleBackground />
    </div>
  );
} 