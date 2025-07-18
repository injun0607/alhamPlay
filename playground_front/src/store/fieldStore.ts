'use client'

import { create } from 'zustand';
import { FieldType } from '@/types/map';


interface FieldStore {
    selectedFieldType: FieldType | null;
    setSelectedField: (fieldType: FieldType) => void;
    selectedTile: { x: number; y: number } | null;
    setSelectedTile: (tile: { x: number; y: number } | null ) => void;
    resetSelectedField: () => void;
}

export const useFieldStore = create<FieldStore>((set) => ({
    selectedFieldType: null,
    selectedTile: null,
    setSelectedField: (fieldType) => {
        set({ selectedFieldType: fieldType })
    },
    setSelectedTile: (tile) => {
        set({ selectedTile: tile })
    },
    resetSelectedField: () => set({ selectedFieldType: null }),
}))