'use client'

import { create } from 'zustand';
import { FieldType } from '@/types/map';


interface FieldStore {
    selectedFieldType: FieldType | null;
    setSelectedField: (fieldType: FieldType) => void;
    resetSelectedField: () => void;
}

export const useFieldStore = create<FieldStore>((set) => ({
    selectedFieldType: null,
    setSelectedField: (fieldType) => {
        set({ selectedFieldType: fieldType })
        console.log("setSelectedField : " + fieldType)
    },
    resetSelectedField: () => set({ selectedFieldType: null }),
}))