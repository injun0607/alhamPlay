export type MenuType = '인벤토리' | '스킬' | '유저정보';

export interface MenuState {
  isOpen: boolean;
  currentMenu: MenuType | null;
  openMenu: (menu: MenuType) => void;
  closeMenu: () => void;
} 