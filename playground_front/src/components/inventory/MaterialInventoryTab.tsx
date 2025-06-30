import { MaterialInventory, MaterialInventoryItemDTO } from "@/types/inventory";
import MaterialItemSlot from "./MaterialItemSlot";

interface MaterialInventoryTabProps {
    materialInventory: MaterialInventory;
}

export default function MaterialInventoryTab({ materialInventory }: MaterialInventoryTabProps) {

    const { materialItemList } = materialInventory;

    // 5개씩 그룹화
    const chunkedItems = [];
    for (let i = 0; i < materialItemList.length; i += 5) {
        chunkedItems.push(materialItemList.slice(i, i + 5));
    }

    return (
        <div className="space-y-4">
            <h2 className="text-sm text-green-400 mb-4">▶ MATERIALS</h2>
            
            {chunkedItems.map((row, rowIndex) => (
                <div key={rowIndex} className="grid grid-cols-5 gap-2">
                    {row.map((item) => (
                        <div
                            key={item.inventoryItemId}
                            className="item-slot inventory-slot bg-gradient-to-br from-gray-700 to-gray-800 flex flex-col items-center justify-center cursor-pointer hover:border-green-400 flex-shrink-0"
                        >
                            <MaterialItemSlot item={item} />
                        </div>
                    ))}
                    
                    {/* 빈 슬롯들로 5개 맞추기 */}
                    {Array.from({ length: 5 - row.length }, (_, index) => (
                        <div
                            key={`empty-${rowIndex}-${index}`}
                            className="item-slot inventory-slot bg-gradient-to-br from-gray-600 to-gray-700 flex flex-col items-center justify-center opacity-50 flex-shrink-0"
                        >
                            <MaterialItemSlot item={null} />
                        </div>
                    ))}
                </div>
            ))}
            
            {/* 아이템이 없을 때 */}
            {chunkedItems.length === 0 && (
                <div className="text-center py-8">
                    <div className="text-[8px] text-gray-400">No materials found</div>
                </div>
            )}
        </div>
    );
}