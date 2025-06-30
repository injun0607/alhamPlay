import { MaterialInventoryItemDTO } from "@/types/inventory";


interface ItemSlotProps {
    item: MaterialInventoryItemDTO | null;
}

export default function ItemSlot({ item }: ItemSlotProps) {
    return item ? (
    <>
        <div className="w-8 h-8 bg-gradient-to-br from-white-500 to-white-600 rounded mb-1 flex items-center justify-center">
            <img src="/images/glacier_mat.png" alt={item.name} className="w-8 h-8" />
        </div>
        <div className="text-[6px] text-green-300">
            x{item.quantity}
        </div>
    </>
    ):(
        <div className="text-[8px] text-gray-500">EMPTY</div>
    )
}
