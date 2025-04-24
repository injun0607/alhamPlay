'use client'

import { useInventoryStore } from '@/store/inventoryStore';

export const Inventory = () => {
  const { items, removeItem, updateItemQuantity } = useInventoryStore();

  return (
    <div className="p-4">
      <div className="space-y-2">
        {items.length === 0 ? (
          <p className="text-gray-500 text-center">인벤토리가 비어있습니다</p>
        ) : (
          items.map((item) => (
            <div
              key={item.id}
              className="flex items-center justify-between p-2 bg-gray-50 rounded"
            >
              <div>
                <span className="font-medium">{item.type}</span>
                <span className="text-sm text-gray-500 ml-2">
                  x{item.quantity}
                </span>
              </div>
              <div className="flex items-center space-x-2">
                <button
                  onClick={() => updateItemQuantity(item.id, item.quantity - 1)}
                  className="text-gray-500 hover:text-gray-700"
                  disabled={item.quantity <= 1}
                >
                  -
                </button>
                <button
                  onClick={() => updateItemQuantity(item.id, item.quantity + 1)}
                  className="text-gray-500 hover:text-gray-700"
                >
                  +
                </button>
                <button
                  onClick={() => removeItem(item.id)}
                  className="text-red-500 hover:text-red-700"
                >
                  삭제
                </button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}; 