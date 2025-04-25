'use client'

import { useState } from 'react';
import { AnimatePresence } from 'framer-motion';
import { StartScene } from '@/components/start/StartScene';
import { IntroScene } from '@/components/start/IntroScene';
import { useRouter } from 'next/navigation';

export default function Home() {
  const [currentScene, setCurrentScene] = useState<'start' | 'intro'>('start');
  const router = useRouter();

  const handleStartNewGame = () => {
    setCurrentScene('intro');
  };

  const handleIntroComplete = () => {
    router.push('/character/create');
  };

  return (
    <AnimatePresence mode="wait">
      {currentScene === 'start' && (
        <StartScene onStartNewGame={handleStartNewGame} />
      )}
      {currentScene === 'intro' && (
        <IntroScene onComplete={handleIntroComplete} />
      )}
    </AnimatePresence>
  );
}
