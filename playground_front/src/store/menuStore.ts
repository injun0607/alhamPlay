'use client'

import { create } from 'zustand';
import { MenuState, MenuType } from '@/types/menu';

export const useMenuStore = create<MenuState>()((set) => ({
  isOpen: false,
  currentMenu: null,
  
  openMenu: (menu: MenuType) => set({ isOpen: true, currentMenu: menu }),
  closeMenu: () => set({ isOpen: false, currentMenu: null })
})); 