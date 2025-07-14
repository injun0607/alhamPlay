'use client'

import { create } from 'zustand';

interface CodexStore {
    // 상태
    isFetchAllItems: boolean;
    isFetchMaterialItems: boolean;
    isFetchEquipmentItems: boolean;
    
    // 액션
    setIsFetchAllItems: (isFetchAllItems: boolean) => void;
    setIsFetchMaterialItems: (isFetchMaterialItems: boolean) => void;
    setIsFetchEquipmentItems: (isFetchEquipmentItems: boolean) => void;
    
    // 편의 메서드들
    resetAllFlags: () => void;
    setAllFlags: (value: boolean) => void;
}

export const useCodexStore = create<CodexStore>((set) => ({
    // 초기 상태
    isFetchAllItems: false,
    isFetchMaterialItems: false,
    isFetchEquipmentItems: false,
    
    // 개별 액션
    setIsFetchAllItems: (isFetchAllItems) => set({isFetchAllItems}),
    setIsFetchMaterialItems: (isFetchMaterialItems) => set({ isFetchMaterialItems }),
    setIsFetchEquipmentItems: (isFetchEquipmentItems) => set({ isFetchEquipmentItems }),
    
    // 편의 메서드들
    resetAllFlags: () => set({
        isFetchAllItems: false,
        isFetchMaterialItems: false,
        isFetchEquipmentItems: false,
    }),
    
    setAllFlags: (value) => set({
        isFetchAllItems: value,
        isFetchMaterialItems: value,
        isFetchEquipmentItems: value,
    }),
}))
