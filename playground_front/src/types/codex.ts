import { EquipmentType, ItemRarity, ItemType } from "./item";
import { FieldType } from "./map";

export const CollectionLevelEnums = [
    "LEVEL1",
    "LEVEL2",
    "LEVEL3",
    "LEVEL4",
    "LEVEL5",
    "LEVEL6",
    "LEVEL7",
    "LEVEL8",
    "LEVEL9",
    "LEVEL10",
    "LEVEL11",
    "LEVEL12",
    "LEVEL13",
    "LEVEL14",
    "LEVEL15",
    "LEVEL16",
    "LEVEL17",
    "LEVEL18",
    "LEVEL19",
    "LEVEL20",
    "MAX"
] as const;

export type CollectionLevelEnum = typeof CollectionLevelEnums[number];

export interface PlayerCollectionDTO{
    equipmentCollectionList: EquipmentCollectionDTO[],
    materialCollectionList: MaterialCollectionDTO[]
}


export interface EquipmentCollectionDTO{
    equipmentId: number,
    name: string,
    description: string,
    itemType: ItemType,
    equipmentType: EquipmentType,
    itemRarity: ItemRarity,
    itemImg: string,
    level: CollectionLevelEnum,
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



