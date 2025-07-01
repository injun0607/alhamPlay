'use client'

import { useState, useEffect } from 'react';
import { useApi } from '@/hooks/common/useApi';
import { InventoryStore } from '@/store/inventoryStore';
import { MaterialInventoryItemDTO, EquipmentInventoryItemDTO } from '@/types/inventory';
import { IngredientsInfoDTOList, IngredientsInfoDTO } from '@/types/craft';
import MaterialItemSlot from '../inventory/MaterialItemSlot';
import "@/app/css/craft.css";

interface CraftProps {
    setIsOpen: (isOpen: boolean) => void;
  }

export default function Craft({ setIsOpen }: CraftProps) {
    const [ingredientMaterialList, setIngredientMaterialList] = useState<(MaterialInventoryItemDTO | null)[]>([null, null, null, null]);
    const [result, setResult] = useState<MaterialInventoryItemDTO | null>(null);
    const [isCrafting, setIsCrafting] = useState(false);

    const [materialList, setMaterialList] = useState<MaterialInventoryItemDTO[]>([]);
    const { post: postCraft } = useApi<EquipmentInventoryItemDTO>();

    const { materialInventory, equipmentInventory, addItem, removeItem } = InventoryStore();

    useEffect(() => {
        setMaterialList(materialInventory.materialItemList);
    }, [materialInventory.materialItemList]);

    const addIngredient = (material: MaterialInventoryItemDTO) => {
        const emptySlotIndex = ingredientMaterialList.findIndex(ing => ing === null);
        if (emptySlotIndex !== -1) {
            //재료 세팅
            const newIngredients = [...ingredientMaterialList];
            newIngredients[emptySlotIndex] = { ...material, quantity: 1 };
            setIngredientMaterialList(newIngredients);

            // 로컬 materialList에서도 제거
            const selectedMaterial = materialList.find(item => item.inventoryItemId === material.inventoryItemId);
            if (selectedMaterial && selectedMaterial.quantity > 1) {

                const newMaterialList = materialList.map(item =>
                    item.inventoryItemId === material.inventoryItemId
                        ? { ...item, quantity: item.quantity - 1 }
                        : item
                ).sort((a, b) => a.itemOrder - b.itemOrder);

                setMaterialList(newMaterialList);
            } else {
                const newMaterialList = materialList.filter(item => item.inventoryItemId !== material.inventoryItemId).sort((a, b) => a.itemOrder - b.itemOrder);
                setMaterialList(newMaterialList);
            }

        }
    }

    const removeIngredient = (index: number) => {
        const ingredient = ingredientMaterialList[index];
        if (ingredient) {
            // 슬롯에서 제거
            const newIngredients = [...ingredientMaterialList];
            newIngredients[index] = null;
            setIngredientMaterialList(newIngredients);

            // 로컬 materialList에도 다시 추가
            const selectedMaterial = materialList.find(item => item.inventoryItemId === ingredient.inventoryItemId);
            if (selectedMaterial) {
                const newMaterialList = materialList.map(item =>
                    item.inventoryItemId === ingredient.inventoryItemId
                        ? { ...item, quantity: item.quantity + 1 }
                        : item
                ).sort((a, b) => a.itemOrder - b.itemOrder);

                setMaterialList(newMaterialList);
            } else {
                //새로추가
                materialList.push({
                    ...ingredient,
                    quantity: 1
                });
                setMaterialList(materialList);
            }


        }
    }

    const craft = async () => {
        const createEquipmentRecipe = () => {

            const materialCountMap = new Map<number, { material: MaterialInventoryItemDTO, count: number }>()

            ingredientMaterialList.forEach(item => {
                if (item === null) return;
                const itemId = Number(item.inventoryItemId);
                if (materialCountMap.has(itemId)) {
                    console.log("yes" + itemId)
                    const existing = materialCountMap.get(itemId)!
                    existing.count += 1
                } else {
                    console.log("no" + item.id)
                    materialCountMap.set(itemId, { material: item, count: 1 })
                }
            })

            // console.log(Array.from(materialCountMap.entries()));

            const ingredientList: IngredientsInfoDTOList = {
                //생각해야하는게 -> 한번 돌아서 아이템이 몇개가 얼마나있는지 알고 그 상태로 만들어야하는것
                ingredients: []
            }

            materialCountMap.forEach(({ material, count }) => {
                ingredientList.ingredients.push({ inventoryItemId: material.inventoryItemId, name: material.name, quantity: count, itemRarity: material.itemRarity })
            })

            // console.log(JSON.stringify(ingredientList))

            return ingredientList
        }

        // 현재 materialList를 EquipmentRecipeDTO 형태로 변환
        const equipmentRecipe: IngredientsInfoDTOList = createEquipmentRecipe();
        setIsCrafting(true);

        try {
            const result = await postCraft<IngredientsInfoDTOList>('/craft/equipment', equipmentRecipe)
            if (result) {
                addItem(result);
                ingredientMaterialList.forEach(item => {
                    if (item) {
                        removeItem(item);
                    }
                });
                setIngredientMaterialList([null, null, null, null]);
                alert("크래프트 성공 : " + result.name + " itemRarity : " + result.itemRarity);
            }
        } catch (error) {
            console.error('크래프트 실패:', error);
        } finally {
            setIsCrafting(false);
        }

    }

    const getRarityClass = (rarity: string) => {
        switch (rarity) {
            case 'COMMON': return 'bg-gradient-to-br from-gray-600 to-gray-700';
            case 'UNCOMMON': return 'bg-gradient-to-br from-green-600 to-green-700';
            case 'RARE': return 'bg-gradient-to-br from-blue-600 to-blue-700';
            case 'EPIC': return 'bg-gradient-to-br from-purple-600 to-purple-700';
            case 'LEGENDARY': return 'bg-gradient-to-br from-yellow-600 to-orange-600';
            default: return 'bg-gradient-to-br from-gray-600 to-gray-700';
        }
    }

    const getRarityTextClass = (rarity: string) => {
        switch (rarity) {
            case 'COMMON': return 'text-gray-300';
            case 'UNCOMMON': return 'text-green-300';
            case 'RARE': return 'text-blue-300';
            case 'EPIC': return 'text-purple-300';
            case 'LEGENDARY': return 'text-yellow-300';
            default: return 'text-gray-300';
        }
    }


    return (
        <div className="craft-container">
            {/* 헤더 */}
            <div className="craft-header">
                <h2 className="text-sm text-orange-400 font-bold mb-1">▶ CRAFTING SYSTEM</h2>
                <div className="text-xs text-gray-400">Combine materials to create powerful items</div>
            </div>

            {/* 크래프트 영역 */}
            <div className="craft-section">
                <div className="flex flex-col items-center craft-ingredients">
                    {/* 재료 슬롯들 */}
                    <div className="w-full">
                        <h3 className="text-xs text-green-400 font-bold mb-2 text-center">▶ INGREDIENTS</h3>
                        <div className="craft-ingredients-grid">
                            {ingredientMaterialList.map((ingredient, index) => (
                                <div
                                    key={index}
                                    className={`craft-slot craft-ingredient-slot ${ingredient ? 'filled' : ''} bg-gradient-to-br from-gray-700 to-gray-800 flex flex-col items-center justify-center cursor-pointer`}
                                    onClick={() => ingredient && removeIngredient(index)}
                                >
                                    {ingredient ? (
                                        <>
                                            <div className={`craft-ingredient-icon ${getRarityClass(ingredient.itemRarity)} rounded mb-1 flex items-center justify-center`}>
                                                <span className="text-white font-bold text-xs">{ingredient.name.charAt(0)}</span>
                                            </div>
                                            <div className="craft-ingredient-name text-white text-center leading-tight">{ingredient.name}</div>
                                            <div className={`craft-ingredient-quantity ${getRarityTextClass(ingredient.itemRarity)}`}>x{ingredient.quantity}</div>
                                        </>
                                    ) : (
                                        <div className="text-[8px] text-gray-500">EMPTY</div>
                                    )}
                                </div>
                            ))}
                        </div>
                    </div>
                </div>

                {/* 크래프트 버튼 */}
                <div className="mt-4 flex justify-center">
                    <button
                        onClick={craft}
                        disabled={!ingredientMaterialList.some(ing => ing !== null) || isCrafting}
                        className={`pixel-button craft-button font-bold ${ingredientMaterialList.some(ing => ing !== null) && !isCrafting
                            ? 'bg-gradient-to-r from-orange-600 to-red-600 text-white hover:from-orange-500 hover:to-red-500 crafting'
                            : 'bg-gray-600 text-gray-400 cursor-not-allowed'
                            }`}
                    >
                        {isCrafting ? 'CRAFTING...' : 'CRAFT ITEM'}
                    </button>
                </div>
            </div>

            {/* 인벤토리 영역 */}
            <div className="craft-inventory">
                <h3 className="text-xs text-green-400 font-bold mb-2">▶ AVAILABLE MATERIALS</h3>
                <div className="space-y-4 craft-inventory">
                    {/* 5개씩 그룹화 */}
                    {(() => {
                        const chunkedItems = [];
                        for (let i = 0; i < materialList.length; i += 5) {
                            chunkedItems.push(materialList.slice(i, i + 5));
                        }
                        
                        return chunkedItems.map((row, rowIndex) => (
                            <div key={rowIndex} className="grid grid-cols-5 gap-2">
                                {row.map((material) => (
                                    <div
                                        key={material.inventoryItemId}
                                        className="item-slot craft-inventory-slot bg-gradient-to-br from-gray-700 to-gray-800 flex flex-col items-center justify-center cursor-pointer hover:border-green-400 flex-shrink-0"
                                        onClick={() => addIngredient(material)}
                                    >
                                        <MaterialItemSlot item={material} />
                                    </div>
                                ))}
                                
                                {/* 빈 슬롯들로 5개 맞추기 */}
                                {Array.from({ length: 5 - row.length }, (_, index) => (
                                    <div
                                        key={`empty-${rowIndex}-${index}`}
                                        className="item-slot craft-inventory-slot bg-gradient-to-br from-gray-600 to-gray-700 flex flex-col items-center justify-center opacity-50 flex-shrink-0"
                                    >
                                        <MaterialItemSlot item={null} />
                                    </div>
                                ))}
                            </div>
                        ));
                    })()}
                    
                    {/* 아이템이 없을 때 */}
                    {materialList.length === 0 && (
                        <div className="text-center py-8">
                            <div className="text-[8px] text-gray-400">No materials found</div>
                        </div>
                    )}
                </div>
            </div>

            {/* 하단 상태바 */}
            <div className="craft-status">
                <div className="bg-black pixel-border p-2">
                    <div className="craft-status-text text-green-400 flex justify-between">
                        <span>RECIPE: {ingredientMaterialList.filter(ing => ing !== null).map(ing => `${ing?.name} x${ing?.quantity}`).join(' + ')}</span>
                        <span className={ingredientMaterialList.some(ing => ing !== null) ? 'pulse' : ''}>
                            {ingredientMaterialList.some(ing => ing !== null) ? 'READY TO CRAFT' : 'ADD MATERIALS'}
                        </span>
                    </div>
                </div>
            </div>

            {/* 닫기 버튼 */}
            <button
                onClick={() => setIsOpen(false)}
                className="craft-close w-full pixel-button bg-gray-600 text-white py-2 text-xs font-bold hover:bg-gray-500"
            >
                CLOSE
            </button>
        </div>
    )
}