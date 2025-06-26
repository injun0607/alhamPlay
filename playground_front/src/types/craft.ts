import { ItemRarity } from './item';

// 백엔드 EquipmentRecipeDTO에 해당하는 인터페이스
export interface EquipmentDTO{
    name: string,
    itemRarity: ItemRarity,
    description: string
}

export interface IngredientsInfoDTO{
    inventoryItemId: number,
    name: string,
    quantity: number,
    itemRarity: ItemRarity
}

export interface IngredientsInfoDTOList{
    ingredients: IngredientsInfoDTO[]
}
