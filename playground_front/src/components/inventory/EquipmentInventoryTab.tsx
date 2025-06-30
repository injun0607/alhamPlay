import { EquipmentInventory, EquipmentInventoryItemDTO } from "@/types/inventory";
import EquipmentItemSlot from "./EquipmentItemSlot";

interface EquipmentInventoryTabProps {
    equipmentInventory: EquipmentInventory;
}

export default function EquipmentInventoryTab({equipmentInventory}:EquipmentInventoryTabProps) {

    const {equipmentItemList} = equipmentInventory;

    // 5개씩 그룹화
    const chunkedItems = [];
    for (let i = 0; i < equipmentItemList.length; i += 5) {
        chunkedItems.push(equipmentItemList.slice(i, i + 5));
    }

    // 등급별 색상 매핑
    const getRarityColor = (rarity: string) => {
        switch (rarity) {
            case 'COMMON': return 'from-gray-500 to-gray-600';
            case 'UNCOMMON': return 'from-green-500 to-green-600';
            case 'RARE': return 'from-blue-500 to-blue-600';
            case 'EPIC': return 'from-purple-500 to-purple-600';
            case 'LEGENDARY': return 'from-yellow-500 to-yellow-600';
            default: return 'from-gray-500 to-gray-600';
        }
    };

    return(
        <div className="space-y-4">
            <h2 className="text-sm text-green-400 mb-4">▶ EQUIPMENT</h2>
            
            {chunkedItems.map((row, rowIndex) => (
                <div key={rowIndex} className="grid grid-cols-5 gap-2">
                    {row.map((item) => (
                        <div
                            key={item.inventoryItemId}
                            className="item-slot inventory-slot bg-gradient-to-br from-gray-700 to-gray-800 flex flex-col items-center justify-center cursor-pointer hover:border-green-400 flex-shrink-0"
                        >
                            <EquipmentItemSlot item={item} />
                        </div>
                    ))}
                    
                    {/* 빈 슬롯들로 5개 맞추기 */}
                    {Array.from({ length: 5 - row.length }, (_, index) => (
                        <div
                            key={`empty-${rowIndex}-${index}`}
                            className="item-slot inventory-slot bg-gradient-to-br from-gray-600 to-gray-700 flex flex-col items-center justify-center opacity-50 flex-shrink-0"
                        >
                            <EquipmentItemSlot item={null} />
                        </div>
                    ))}
                </div>
            ))}
            
            {/* 아이템이 없을 때 */}
            {chunkedItems.length === 0 && (
                <div className="text-center py-8">
                    <div className="text-[8px] text-gray-400">No equipment found</div>
                </div>
            )}
        </div>
    );
}