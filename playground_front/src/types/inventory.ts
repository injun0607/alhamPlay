export type ResourceType = '나무' | '암석' | '나뭇잎';

export interface InventoryItem {
  id: string;
  type: ResourceType;
  quantity: number;
  createdAt: number;
}

export interface Inventory {
  items: InventoryItem[];
  addItem: (type: ResourceType) => void;
  removeItem: (id: string) => void;
  updateItemQuantity: (id: string, quantity: number) => void;
} 