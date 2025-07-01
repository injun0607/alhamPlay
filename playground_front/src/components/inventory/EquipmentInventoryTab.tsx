import { EquipmentInventory, EquipmentInventoryItemDTO } from "@/types/inventory";
import EquipmentItemSlot from "./EquipmentItemSlot";
import ItemTooltip from "./ItemTooltip";
import { useState, useMemo } from "react";

interface EquipmentInventoryTabProps {
    equipmentInventory: EquipmentInventory;
}

export default function EquipmentInventoryTab({equipmentInventory}:EquipmentInventoryTabProps) {
    const [tooltipItem, setTooltipItem] = useState<EquipmentInventoryItemDTO | null>(null);
    const [tooltipPosition, setTooltipPosition] = useState({ x: 0, y: 0 });
    const [isTooltipVisible, setIsTooltipVisible] = useState(false);

    const {equipmentItemList} = equipmentInventory;

    // 40개로 제한
    const limitedItemList = equipmentItemList.slice(0, 40);

    // 40개 슬롯을 5x8 그리드로 생성 (useMemo로 최적화)
    const totalSlots = 40;
    const slotsPerRow = 5;
    const totalRows = Math.ceil(totalSlots / slotsPerRow);
    
    const slots = useMemo(() => {
        const slots = [];
        for (let i = 0; i < totalSlots; i++) {
            const item = limitedItemList[i] || null;
            slots.push({ item, index: i });
        }
        return slots;
    }, [limitedItemList]); // limitedItemList가 변경될 때만 재계산

    const handleMouseEnter = (item: EquipmentInventoryItemDTO, event: React.MouseEvent) => {
        setTooltipItem(item);
        setTooltipPosition({ x: event.clientX, y: event.clientY });
        setIsTooltipVisible(true);
    };

    const handleMouseLeave = () => {
        setIsTooltipVisible(false);
    };

    return(
        <div className="space-y-4">
            <div className="flex justify-between items-center mb-4">
                <h2 className="text-sm text-green-400">▶ EQUIPMENT</h2>
                <div className="text-xs text-gray-400">
                    {limitedItemList.length}/40
                </div>
            </div>
            
            <div className="grid grid-cols-5 gap-2">
                {slots.map(({ item, index }) => (
                    <div
                        key={index}
                        className={`item-slot inventory-slot flex flex-col items-center justify-center cursor-pointer flex-shrink-0 ${
                            item 
                                ? 'bg-gradient-to-br from-gray-700 to-gray-800 hover:border-green-400' 
                                : 'bg-gradient-to-br from-gray-600 to-gray-700 opacity-50'
                        }`}
                        onMouseEnter={item ? (e) => handleMouseEnter(item, e) : undefined}
                        onMouseLeave={item ? handleMouseLeave : undefined}
                    >
                        <EquipmentItemSlot item={item} />
                    </div>
                ))}
            </div>

            {/* 툴팁 */}
            <ItemTooltip 
                item={tooltipItem}
                position={tooltipPosition}
                isVisible={isTooltipVisible}
            />
        </div>
    );
}