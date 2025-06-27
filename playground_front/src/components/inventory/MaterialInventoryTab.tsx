import { MaterialInventory, MaterialInventoryItemDTO } from "@/types/inventory";

interface MaterialInventoryTabProps {
    materialInventory: MaterialInventory;
}

export default function MaterialInventoryTab({ materialInventory }: MaterialInventoryTabProps) {

    const { materialItemList } = materialInventory;

    return (

        <div>
            <div className="bg-[#222] text-white p-8 font-[PressStart2P]">
                <h1 className="text-xl mb-6 text-yellow-300">▶ INVENTORY</h1>

                <div className="bg-[#111] p-4 border-4 border-black w-fit grid grid-cols-4 gap-4">
                    {materialItemList.map((item,idx) => (
                        <div
                            key={item.inventoryItemId}
                            className="bg-[#333] border-4 border-black p-2 w-24 h-24 flex flex-col items-center justify-center"
                        >
                            <img
                                src={`https://via.placeholder.com/32?text=${item.name.charAt(0)}`}
                                alt={item.name}
                                className="w-8 h-8 mb-1"
                            />
                            <div className="text-[8px] text-white text-center leading-tight">
                                {item.name} X {item.quantity}
                            </div>
                            {/* <div className={`text-[8px] ${item.color}`}>x{item.quantity}</div> */}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    )
}