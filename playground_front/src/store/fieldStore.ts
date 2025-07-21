'use client'

import { create } from 'zustand';
import { DailyTileInfo, FieldType } from '@/types/map';



interface FieldStore {
    selectedFieldType: FieldType | null;
    setSelectedField: (fieldType: FieldType) => void;
    selectedTile: DailyTileInfo | null;
    setSelectedTile: (dailyTileInfo: DailyTileInfo | null ) => void;
    resetSelectedField: () => void;
}

export const useFieldStore = create<FieldStore>((set) => ({
    selectedFieldType: null,
    selectedTile: null,
    setSelectedField: (fieldType) => {
        set({ selectedFieldType: fieldType })
    },
    setSelectedTile: (dailyTileInfo) => {
        set({ selectedTile: dailyTileInfo })
    },
    resetSelectedField: () => set({ selectedFieldType: null }),
}))