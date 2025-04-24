'use client'

import { create } from 'zustand';
import { Inventory, InventoryItem, ResourceType } from '@/types/inventory';

export const useInventoryStore = create<Inventory>()((set) => ({
  items: [],
  
  addItem: (type: ResourceType) => {
    set((state) => {
      // 같은 타입의 아이템이 있는지 확인
      const existingItem = state.items.find(item => item.type === type);
      
      if (existingItem) {
        // 이미 있는 아이템의 수량 증가
        return {
          items: state.items.map(item =>
            item.id === existingItem.id
              ? { ...item, quantity: item.quantity + 1 }
              : item
          )
        };
      } else {
        // 새로운 아이템 추가
        const newItem: InventoryItem = {
          id: crypto.randomUUID(),
          type,
          quantity: 1,
          createdAt: Date.now()
        };
        return { items: [...state.items, newItem] };
      }
    });
  },
  
  removeItem: (id: string) => {
    set((state) => ({
      items: state.items.filter(item => item.id !== id)
    }));
  },
  
  updateItemQuantity: (id: string, quantity: number) => {
    set((state) => ({
      items: state.items.map(item =>
        item.id === id
          ? { ...item, quantity: Math.max(0, quantity) }
          : item
      )
    }));
  }
})); 