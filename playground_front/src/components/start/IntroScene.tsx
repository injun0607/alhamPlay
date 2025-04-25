'use client'

import { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { ParticleBackground } from '../ui/ParticleBackground';

interface IntroSceneProps {
  onComplete: () => void;
}

export const IntroScene = ({ onComplete }: IntroSceneProps) => {
  const [introText, setIntroText] = useState('');
  const [currentMessageIndex, setCurrentMessageIndex] = useState(0);
  const [isTypingComplete, setIsTypingComplete] = useState(false);

  const introMessages = [
    '과거의 기억이 흐릿해진다...',
    '하지만 그 속에서도...',
    '희미한 빛이 보인다.',
    '그 빛을 따라가보자.'
  ];

  useEffect(() => {
    let currentChar = 0;
    let timer: NodeJS.Timeout;

    const typeText = () => {
      const currentMessage = introMessages[currentMessageIndex];
      
      if (currentChar <= currentMessage.length) {
        setIntroText(currentMessage.slice(0, currentChar));
        currentChar++;
        timer = setTimeout(typeText, 100);
      } else {
        if (currentMessageIndex < introMessages.length - 1) {
          timer = setTimeout(() => {
            setCurrentMessageIndex(prev => prev + 1);
            currentChar = 0;
          }, 1500);
        } else {
          setIsTypingComplete(true);
          timer = setTimeout(onComplete, 2000);
        }
      }
    };

    timer = setTimeout(typeText, 100);
    return () => clearTimeout(timer);
  }, [currentMessageIndex, onComplete]);

  return (
    <div className="fixed inset-0 bg-black flex items-center justify-center">
      <motion.div
        className="text-center z-10"
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        exit={{ opacity: 0 }}
      >
        <motion.p
          className="text-2xl md:text-3xl text-white"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
        >
          {introText}
        </motion.p>

        {isTypingComplete && (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            className="mt-8 text-gray-400"
          >
            계속하려면 아무 곳이나 클릭하세요...
          </motion.div>
        )}
      </motion.div>
      
      <ParticleBackground />
    </div>
  );
}; 