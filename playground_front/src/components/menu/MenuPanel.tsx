'use client'

import { useMenuStore } from '@/store/menuStore';
import { Inventory } from '../inventory/Inventory';
import { MenuType } from '@/types/menu';

const MenuContent = ({ type }: { type: MenuType }) => {
  switch (type) {
    case '인벤토리':
      return (
        <div className="p-4">
          <h3 className="text-lg font-semibold mb-4">인벤토리</h3>
          <Inventory />
        </div>
      );
    case '스킬':
      return (
        <div className="p-4">
          <h3 className="text-lg font-semibold mb-4">스킬</h3>
          <p className="text-gray-500">스킬 시스템 준비 중...</p>
        </div>
      );
    case '유저정보':
      return (
        <div className="p-4">
          <h3 className="text-lg font-semibold mb-4">유저정보</h3>
          <p className="text-gray-500">유저정보 시스템 준비 중...</p>
        </div>
      );
    default:
      return null;
  }
};

export const MenuPanel = () => {
  const { isOpen, currentMenu, closeMenu } = useMenuStore();

  if (!isOpen || !currentMenu) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg shadow-xl w-full max-w-md">
        <div className="flex justify-between items-center p-4 border-b">
          <h2 className="text-xl font-semibold">{currentMenu}</h2>
          <button
            onClick={closeMenu}
            className="text-gray-500 hover:text-gray-700"
          >
            ✕
          </button>
        </div>
        <MenuContent type={currentMenu} />
      </div>
    </div>
  );
}; 