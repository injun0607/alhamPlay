import { EquipmentInventory, EquipmentInventoryItemDTO } from "@/types/inventory";

interface EquipmentInventoryTabProps {
    equipmentInventory: EquipmentInventory;
}

export default function EquipmentInventoryTab({equipmentInventory}:EquipmentInventoryTabProps) {

    const {equipmentItemList} = equipmentInventory;

    return(
        <div>
            {equipmentItemList.map((item,idx)=>(
                <div key={idx}>
                    <h1>{item.name} - {item.itemRarity}</h1>
                </div>
            ))}
        </div>
    )
}