'use client'

import { create } from 'zustand';
import { UserInventory, EquipmentInventory, MaterialInventory } from '@/types/inventory';
import { ItemType, ItemRarity } from '@/types/item';
import { axiosInstance } from '@/hooks/common/axiosInstance';

// 타입을 직접 정의
interface EquipmentInventoryItemDTO {
  id: number;
  name: string;
  description: string;
  type: ItemType;
  itemRarity: ItemRarity;
  itemOrder: number;
}

interface MaterialInventoryItemDTO {
  id: number;
  name: string;
  description: string;
  type: ItemType;
  itemRarity: ItemRarity;
  itemOrder: number;
}

interface InventoryStore {
  equipmentInventory: EquipmentInventory;
  materialInventory: MaterialInventory;
  isInventoryInitialized: boolean;
  initInventory: (initData: UserInventory) => void;
  addItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => void;
  removeItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => void;
}

export const InventoryStore = create<InventoryStore>()((set) => ({
  equipmentInventory: {
    equipmentItemList: [],
  },
  materialInventory: {
    materialItemList: [],
  },
  isInventoryInitialized: false,
  
  initInventory: (initData: UserInventory) => {
    set({
      equipmentInventory: initData.playerEquipmentInventory,
      materialInventory: initData.playerMaterialInventory,
      isInventoryInitialized: true
    })
  },

  addItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => {
    set((state) => {
      if(item.type === "EQUIPMENT"){
        return {
          equipmentInventory: {
            equipmentItemList: [...state.equipmentInventory.equipmentItemList, item].sort((a,b)=>a.itemOrder-b.itemOrder)
          }
        }
      }
      else{
        return {
          materialInventory: {
            materialItemList: [...state.materialInventory.materialItemList, item].sort((a,b)=>a.itemOrder-b.itemOrder)
          }
        }
      }
    })
  },

  removeItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => {
    set((state) => {
      if(item.type === "EQUIPMENT"){
        return {
          equipmentInventory: {
            equipmentItemList: state.equipmentInventory.equipmentItemList.filter(i => i.itemOrder !== item.itemOrder).sort((a,b)=>a.itemOrder-b.itemOrder)
          }
        }
      }
      else{
        return {
          materialInventory: {
            materialItemList: state.materialInventory.materialItemList.filter(i => i.itemOrder !== item.itemOrder).sort((a,b)=>a.itemOrder-b.itemOrder)
          }
        }
      }
    })
  }
}))
