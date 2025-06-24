import { ItemType, ItemRarity } from "./item";


export type ResourceType = '나무' | '암석' | '나뭇잎';

export interface InventoryItem {
  id: string;
  type: ResourceType;
  quantity: number;
  createdAt: number;
}

export interface UserInventory {
  playerEquipmentInventory: EquipmentInventory;
  playerMaterialInventory: MaterialInventory;
}


export interface MaterialInventoryItemDTO{
  id: number,
  name: string,
  description: string,
  type: ItemType,
  itemRarity: ItemRarity,
  itemOrder: number,
  itemImg: string,
}

export interface EquipmentInventoryItemDTO{
  id: number,
  name: string,
  description: string,
  type: ItemType,
  itemRarity: ItemRarity,
  itemOrder: number
}

export interface EquipmentInventory{
  equipmentItemList: EquipmentInventoryItemDTO[];
}

export interface MaterialInventory{
  materialItemList: MaterialInventoryItemDTO[];
}

