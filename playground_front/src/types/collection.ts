import { ItemType, ItemRarity, EquipmentType } from "./item";
import { FieldType } from "./map";


export interface PlayerCollectionDTO{
    equipmentCollectionList: EquipmentCollectionDTO[];
    materialCollectionList: MaterialCollectionDTO[];
}


export interface EquipmentCollectionDTO{
    equipmentId: number,
    name: string,
    description: string,
    itemType: ItemType,
    equipmentType: EquipmentType,
    itemRarity: ItemRarity,
    itemImg: string,
    level: number,
    quantity: number,
    discoveredAt: string,
    isCollected: boolean
}

export interface MaterialCollectionDTO{
    materialId: number,
    name: string,
    description: string,
    itemType: ItemType,
    itemRarity: ItemRarity,
    itemImg: string,
    dropArea: FieldType,
    discoveredAt: string,
    isCollected: boolean
}