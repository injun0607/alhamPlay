'use client'

import { create } from 'zustand';
import { UserInventory, EquipmentInventory, MaterialInventory, EquipmentInventoryItemDTO, MaterialInventoryItemDTO } from '@/types/inventory';
import { ItemType, ItemRarity } from '@/types/item';
import { axiosInstance } from '@/hooks/common/axiosInstance';

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
