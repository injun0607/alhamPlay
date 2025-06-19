'use client'

import { useMenuStore } from '@/store/menuStore';
import { MenuType } from '@/types/menu';
import { useState } from 'react';

const menuItems: { type: MenuType; icon: string; label: string }[] = [
  { type: '인벤토리', icon: '🎒', label: '인벤토리' },
  { type: '스킬', icon: '✨', label: '스킬' },
  { type: '유저정보', icon: '👤', label: '유저정보' }
];

export const MainMenu = () => {
  const { currentMenu, openMenu } = useMenuStore();
  const [isMenuVisible, setIsMenuVisible] = useState(false);

  const toggleMenu = () => {
    setIsMenuVisible(!isMenuVisible);
  };

  return (
    <>
      {/* 햄버거 버튼 */}
      <button
        onClick={toggleMenu}
        className="fixed top-4 left-4 p-2 bg-white rounded-lg shadow-lg hover:bg-gray-100 transition-colors z-50"
      >
        <div className="w-6 h-0.5 bg-gray-600 mb-1.5"></div>
        <div className="w-6 h-0.5 bg-gray-600 mb-1.5"></div>
        <div className="w-6 h-0.5 bg-gray-600"></div>
      </button>

      {/* 메뉴 버튼들 */}
      {isMenuVisible && (
        <div className="fixed top-20 left-4 bg-white p-4 rounded-lg shadow-lg space-y-2 z-50">
          {menuItems.map((item) => (
            <button
              key={item.type}
              onClick={() => {
                openMenu(item.type);
                setIsMenuVisible(false);
              }}
              className={`
                w-full flex items-center space-x-2 p-2 rounded transition-colors
                ${currentMenu === item.type
                  ? 'bg-blue-500 text-white'
                  : 'hover:bg-gray-100'
                }
              `}
            >
              <span className="text-xl">{item.icon}</span>
              <span>{item.label}</span>
            </button>
          ))}
        </div>
      )}
    </>
  );
}; 