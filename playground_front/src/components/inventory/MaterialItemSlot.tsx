import { MaterialInventoryItemDTO } from "@/types/inventory";
import { useMemo } from "react";

interface ItemSlotProps {
    item: MaterialInventoryItemDTO | null;
}

export default function ItemSlot({ item }: ItemSlotProps) {

    const imgSrc = [
        "/images/glacier_mat.png",
        "/images/wood.png",
        "/images/dry_branch.png",
        "/images/dryad_heart.png",
        "/images/glacier_tear.png",
        "/images/green_branch.png",
        "/images/ice_branch.png",
        "/images/salamandar_heart.png",
        "/images/stone_spear.png",
        "/images/stone_trans.PNG",
        "/images/volcanic_branch.png",
        "/images/volcanic_stone.png",
        "/images/wood_trans.png",
    ]

    // 아이템 ID를 기반으로 고정된 이미지 선택 (useMemo로 최적화)
    const itemImageSrc = useMemo(() => {
        if (!item) return null;
        
        // 아이템 ID를 기반으로 일관된 이미지 선택
        const index = item.inventoryItemId % imgSrc.length;
        return imgSrc[index];
    }, [item?.inventoryItemId]); // 아이템 ID가 변경될 때만 재계산

    return item ? (
    <>
        <div className="w-8 h-8 bg-gradient-to-br from-white-500 to-white-600 rounded mb-1 flex items-center justify-center">
            <img src={itemImageSrc || imgSrc[0]} alt={item.name} className="w-8 h-8" />
        </div>
        <div className="text-[6px] text-green-300">
            x{item.quantity}
        </div>
    </>
    ):(
        <div className="text-[8px] text-gray-500">EMPTY</div>
    )
}
