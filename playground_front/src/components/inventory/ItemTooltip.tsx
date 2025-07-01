import { MaterialInventoryItemDTO, EquipmentInventoryItemDTO } from "@/types/inventory";

interface ItemTooltipProps {
  item: MaterialInventoryItemDTO | EquipmentInventoryItemDTO | null;
  position: { x: number; y: number };
  isVisible: boolean;
}

export default function ItemTooltip({ item, position, isVisible }: ItemTooltipProps) {
  if (!item || !isVisible) return null;

  const getRarityColor = (rarity: string) => {
    switch (rarity) {
      case 'COMMON': return 'text-gray-300';
      case 'UNCOMMON': return 'text-green-300';
      case 'RARE': return 'text-blue-300';
      case 'EPIC': return 'text-purple-300';
      case 'LEGENDARY': return 'text-yellow-300';
      default: return 'text-gray-300';
    }
  };

  const getRarityBgColor = (rarity: string) => {
    switch (rarity) {
      case 'COMMON': return 'bg-gray-700';
      case 'UNCOMMON': return 'bg-green-700';
      case 'RARE': return 'bg-blue-700';
      case 'EPIC': return 'bg-purple-700';
      case 'LEGENDARY': return 'bg-yellow-700';
      default: return 'bg-gray-700';
    }
  };

  return (
    <div
      className="fixed z-50 bg-gray-800 border-2 border-gray-600 rounded-lg p-3 shadow-lg max-w-xs"
      style={{
        left: position.x + 10,
        top: position.y - 10,
        pointerEvents: 'none'
      }}
    >
      {/* 아이템 이름 */}
      <div className={`font-bold text-sm mb-2 ${getRarityColor(item.itemRarity)}`}>
        {item.name}
      </div>
      
      {/* 아이템 타입 */}
      <div className="text-xs text-gray-400 mb-2">
        Type: {item.type}
      </div>
      
      {/* 등급 */}
      <div className={`text-xs px-2 py-1 rounded mb-2 inline-block ${getRarityBgColor(item.itemRarity)}`}>
        {item.itemRarity}
      </div>
      
      {/* 수량 (재료인 경우) */}
      {'quantity' in item && (
        <div className="text-xs text-green-300 mb-2">
          Quantity: {item.quantity}
        </div>
      )}
      
      {/* 장비인 경우 추가 정보 */}
      {item.type === 'EQUIPMENT' && (
        <div className="space-y-1">
          <div className="text-xs text-gray-300">
            Equipment Item
          </div>
        </div>
      )}
      
      {/* 설명 */}
      {item.description && (
        <div className="text-xs text-gray-400 mt-2 border-t border-gray-600 pt-2">
          {item.description}
        </div>
      )}
    </div>
  );
}