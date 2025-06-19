import { MaterialInventory, MaterialInventoryItemDTO } from "@/types/inventory";

interface MaterialInventoryTabProps {
    materialInventory: MaterialInventory;
}

export default function MaterialInventoryTab({materialInventory}:MaterialInventoryTabProps) {

    const {materialItemList} = materialInventory;
    
    return(
        <div>
            {materialItemList.map((item,idx)=>(
                <div key={idx}>
                    <h1>{item.name} - {item.itemRarity}</h1>
                </div>
            ))}
        </div>
    )
}