'use client'

import { create } from 'zustand';
import { UserInventory, EquipmentInventory, MaterialInventory, EquipmentInventoryItemDTO, MaterialInventoryItemDTO } from '@/types/inventory';

interface InventoryStore {
  equipmentInventory: EquipmentInventory;
  materialInventory: MaterialInventory;
  isInventoryInitialized: boolean;
  maxInventorySize: number;
  initInventory: (initData: UserInventory) => void;
  addItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => boolean;
  removeItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => void;
  canAddItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => boolean;
}

export const InventoryStore = create<InventoryStore>()((set, get) => ({
  equipmentInventory: {
    equipmentItemList: [],
  },
  materialInventory: {
    materialItemList: [],
  },
  isInventoryInitialized: false,
  maxInventorySize: 40,
  
  initInventory: (initData: UserInventory) => {
    set({
      equipmentInventory: initData.playerEquipmentInventory,
      materialInventory: initData.playerMaterialInventory,
      isInventoryInitialized: true
    })
  },

  canAddItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => {
    const state = get();
    if (item.type === "MATERIAL" && 'quantity' in item) {
      const existingItem = state.materialInventory.materialItemList.find(i => i.inventoryItemId === item.inventoryItemId);
      if (existingItem) {
        return true; // 기존 아이템은 수량만 증가하므로 항상 가능
      } else {
        return state.materialInventory.materialItemList.length < state.maxInventorySize;
      }
    } else if (item.type === "EQUIPMENT") {
      return state.equipmentInventory.equipmentItemList.length < state.maxInventorySize;
    }
    return false;
  },

  addItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => {
    const state = get();
    
    // 인벤토리 용량 체크
    if (!state.canAddItem(item)) {
      return false;
    }

    set((state) => {
      if(item.type === "MATERIAL" && 'quantity' in item){
      const existingItem = state.materialInventory.materialItemList.find(i => i.inventoryItemId === item.inventoryItemId);
        if(existingItem){
          return {
            materialInventory: {
              materialItemList: state.materialInventory.materialItemList.map(i => 
                i.inventoryItemId === item.inventoryItemId ? 
                {...i, quantity: i.quantity + 1} : i)
                .sort((a,b)=>a.itemOrder-b.itemOrder)
            }
          }
        }else{
          return {
            materialInventory: {
              materialItemList: [...state.materialInventory.materialItemList, item as MaterialInventoryItemDTO].sort((a,b)=>a.itemOrder-b.itemOrder)
            }
          }
        }
      }
      else if(item.type === "EQUIPMENT"){
        return {
          equipmentInventory: {
            equipmentItemList: [...state.equipmentInventory.equipmentItemList, item as EquipmentInventoryItemDTO].sort((a,b)=>a.itemOrder-b.itemOrder)
          }
        }
      }
      return state;
    })
    
    return true;
  },

  removeItem: (item: EquipmentInventoryItemDTO | MaterialInventoryItemDTO) => {
    set((state) => {
      if(item.type === "MATERIAL" && 'quantity' in item){
        const existingItem = state.materialInventory.materialItemList.find(i => i.inventoryItemId === item.inventoryItemId);
        if(existingItem){
          if(existingItem.quantity > 1){
            return {
              materialInventory: {
                materialItemList: state.materialInventory.materialItemList.map(i => 
                  i.inventoryItemId === item.inventoryItemId ? 
                  {...i, quantity: i.quantity - 1} : i)
                  .sort((a,b)=>a.itemOrder-b.itemOrder)
              }
            }
          }else{
            return {
              materialInventory: {
                materialItemList: state.materialInventory.materialItemList.filter(i => i.inventoryItemId !== item.inventoryItemId).sort((a,b)=>a.itemOrder-b.itemOrder)
              }
            } 
          }
        }
      }
      else if(item.type === "EQUIPMENT"){
        return {
          equipmentInventory: {
            equipmentItemList: state.equipmentInventory.equipmentItemList.filter(i => i.inventoryItemId !== item.inventoryItemId).sort((a,b)=>a.itemOrder-b.itemOrder)
          }
        }
      }
      return state;
    })
  }
}))
