'use client'

import { MaterialInventoryItemDTO } from '@/types/inventory';
import { useEffect, useState } from 'react';

interface GatherResultProps {
  result: MaterialInventoryItemDTO | null;
  isVisible: boolean;
  onClose: () => void;
}

export default function GatherResult({ result, isVisible, onClose }: GatherResultProps) {
  const [isAnimating, setIsAnimating] = useState(false);

  useEffect(() => {
    if (isVisible && result) {
      setIsAnimating(true);
      
      // 5초 후 자동으로 닫기
      const timer = setTimeout(() => {
        handleClose();
      }, 5000);

      return () => clearTimeout(timer);
    }
  }, [isVisible, result]);

  const handleClose = () => {
    setIsAnimating(false);
    setTimeout(() => {
      onClose();
    }, 300);
  };

  // 키보드 이벤트 처리
  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if (isVisible) {
        e.preventDefault();
        handleClose();
      }
    };

    if (isVisible) {
      document.addEventListener('keydown', handleKeyDown);
      return () => document.removeEventListener('keydown', handleKeyDown);
    }
  }, [isVisible]);

  if (!isVisible || !result) return null;

  const rarityColor = {
    'COMMON': '#888888',
    'UNCOMMON': '#4CAF50', 
    'RARE': '#2196F3',
    'EPIC': '#9C27B0',
    'UNIQUE': '#E91E63',
    'LEGENDARY': '#FF9800'
  }[result.itemRarity] || '#888888';

  return (
    <>
      {/* CSS 애니메이션 스타일 */}
      <style jsx>{`
        @keyframes notificationSlideIn {
          from {
            transform: translateX(-100%);
            opacity: 0;
          }
          to {
            transform: translateX(0);
            opacity: 1;
          }
        }
        
        @keyframes notificationSlideOut {
          from {
            transform: translateX(0);
            opacity: 1;
          }
          to {
            transform: translateX(-100%);
            opacity: 0;
          }
        }
        
        @keyframes notificationPulse {
          0%, 100% { 
            box-shadow: 
              inset 2px 2px 0px rgba(255,255,255,0.3),
              inset -2px -2px 0px rgba(0,0,0,0.5),
              4px 4px 0px rgba(0,0,0,0.8);
          }
          50% { 
            box-shadow: 
              inset 2px 2px 0px rgba(255,255,255,0.3),
              inset -2px -2px 0px rgba(0,0,0,0.5),
              4px 4px 0px rgba(0,0,0,0.8),
              0 0 20px rgba(255, 193, 7, 0.5);
          }
        }
      `}</style>

      {/* 알림창 */}
      <div 
        className={`fixed bottom-6 left-6 bg-gradient-to-br from-gray-800 to-gray-900 text-white p-4 rounded-none border-3 border-black z-50 min-w-64 font-press-start shadow-lg cursor-pointer
          ${isAnimating ? 'animate-[notificationSlideIn_0.3s_ease-out,notificationPulse_2s_infinite]' : 'animate-[notificationSlideOut_0.3s_ease-out]'}`}
        style={{
          fontFamily: 'Press Start 2P, cursive',
          border: '3px solid #000',
          boxShadow: `
            inset 2px 2px 0px rgba(255,255,255,0.3),
            inset -2px -2px 0px rgba(0,0,0,0.5),
            4px 4px 0px rgba(0,0,0,0.8)
          `
        }}
        onClick={handleClose}
      >
        {/* 헤더 */}
        <div className="flex items-center gap-2 mb-2">
          <div className="w-6 h-6 bg-yellow-500 border border-yellow-400 flex items-center justify-center">
            <span className="text-black font-bold text-xs">⛏️</span>
          </div>
          <div className="text-sm font-bold text-yellow-400">RESOURCE FOUND!</div>
        </div>

        {/* 아이템 정보 */}
        <div className="flex items-center gap-3 mt-2 p-2 bg-gray-700 border border-gray-600">
          <div className="w-8 h-8 bg-gradient-to-br from-gray-600 to-gray-700 border border-gray-500 flex items-center justify-center">
            {/*TODO 아이템 이미지로 변경 필요 */}
            <span className="text-white font-bold text-xs">{result.name.charAt(0)}</span>
          </div>
          <div className="flex-1">
            <div className="text-xs font-bold text-white">{result.name}</div>
            <div className="text-xs" style={{ color: rarityColor }}>{result.itemRarity}</div>
          </div>
          <div className="text-xs text-yellow-400 font-bold">x{result.quantity}</div>
        </div>

        {/* 안내 텍스트 */}
        <div className="mt-2 text-xs text-gray-400 text-center">
          Press any key or click to dismiss
        </div>
      </div>
    </>
  );
}