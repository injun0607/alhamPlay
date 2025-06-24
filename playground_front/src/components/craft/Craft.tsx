'use client'

import { useState, useEffect } from 'react';
import { useApi } from '@/hooks/common/useApi';
import { InventoryStore } from '@/store/inventoryStore';
import { MaterialInventoryItemDTO,EquipmentInventoryItemDTO } from '@/types/inventory';
import { IngredientsInfoDTOList,IngredientsInfoDTO } from '@/types/craft';


export default function Craft() {
    const [ingredientMaterialList, setIngredientMaterialList] = useState<(MaterialInventoryItemDTO | null)[]>([null, null, null, null]);
    const [result, setResult] = useState<MaterialInventoryItemDTO | null>(null);
    const [isCrafting, setIsCrafting] = useState(false);

    const [materialList, setMaterialList] = useState<MaterialInventoryItemDTO[]>([]);
    const { post: postCraft } = useApi<EquipmentInventoryItemDTO>();

    const { materialInventory, equipmentInventory, addItem, removeItem } = InventoryStore();

    useEffect(() => {
        setMaterialList(materialInventory.materialItemList);
    }, [materialInventory]);

    const addIngredient = (material: MaterialInventoryItemDTO) => {
        const emptySlotIndex = ingredientMaterialList.findIndex(ing => ing === null);
        if (emptySlotIndex !== -1) {
            const newIngredients = [...ingredientMaterialList];
            newIngredients[emptySlotIndex] = material;
            setIngredientMaterialList(newIngredients);

            // 로컬 materialList에서도 제거
            const newMaterialList = materialList.filter(item => item.itemOrder !== material.itemOrder).sort((a, b) => a.itemOrder - b.itemOrder);
            setMaterialList(newMaterialList);
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
            const newMaterialList = [...materialList, ingredient].sort((a, b) => a.itemOrder - b.itemOrder);
            setMaterialList(newMaterialList);
        }
    }

    const craft = async () => {
        const createEquipmentRecipe = () => {
            const materialCountMap = new Map<number, { material: MaterialInventoryItemDTO, count: number }>()

            ingredientMaterialList.forEach(item => {
                if (item === null) return;
                const itemId = Number(item.id);
                if (materialCountMap.has(itemId)) {
                    console.log("yes"+itemId)
                    const existing = materialCountMap.get(itemId)!
                    existing.count += 1
                } else {
                    console.log("no"+item.id)
                    materialCountMap.set(itemId, { material: item, count: 1 })
                }
            })

            console.log(Array.from(materialCountMap.entries()));

            const ingredientList: IngredientsInfoDTOList = {
                ingredients: []
            }

            materialCountMap.forEach(({ material, count }) => {
                ingredientList.ingredients.push({ name: material.name, quantity: count, itemRarity: material.itemRarity })
            })

            // console.log(JSON.stringify(ingredientList))

            return ingredientList
        }

        // 현재 materialList를 EquipmentRecipeDTO 형태로 변환
        const equipmentRecipe: IngredientsInfoDTOList = createEquipmentRecipe();


        
        //     // 각 재료와 개수 출력
        // equipmentRecipe.ingredients.forEach((count, material) => {
        //     console.log(``);
        // });

    
        setIsCrafting(true);

        try {
            const result = await postCraft<IngredientsInfoDTOList>('/craft/equipment', equipmentRecipe)
            if (result) {
                addItem(result);
                ingredientMaterialList.forEach(item => {
                    if(item){
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



    return (
        <div className="min-h-screen bg-gradient-to-br from-gray-900 via-blue-900 to-purple-900 p-8">
            <div className="max-w-4xl mx-auto">
                {/* 헤더 */}
                <div className="text-center mb-8">
                    <h1 className="text-4xl font-bold text-white mb-2">크래프트 시스템</h1>
                    <p className="text-gray-300">재료를 조합하여 새로운 아이템을 만들어보세요!</p>
                </div>

                {/* 크래프트 영역 */}
                <div className="bg-black/30 backdrop-blur-sm rounded-2xl p-8 border border-gray-600">
                    <div className="grid grid-cols-1 lg:grid-cols-3 gap-8 items-center">

                        {/* 재료 슬롯들 */}
                        <div className="space-y-4">
                            <h2 className="text-xl font-semibold text-white mb-4">재료</h2>
                            <div className="grid grid-cols-2 gap-4">
                                {ingredientMaterialList.map((ingredient, index) => (
                                    <div
                                        key={index}
                                        className="relative group"
                                    >
                                        <div className="w-24 h-24 bg-gradient-to-br from-gray-700 to-gray-800 rounded-xl border-2 border-gray-600 hover:border-blue-400 transition-all duration-200 cursor-pointer flex items-center justify-center">
                                            {ingredient ? (
                                                <div className="text-center">
                                                    <div className="w-12 h-12 bg-gradient-to-br from-blue-500 to-purple-600 rounded-lg mx-auto mb-2 flex items-center justify-center">
                                                        <span className="text-white font-bold text-sm">
                                                            {ingredient.name.charAt(0)}
                                                        </span>
                                                    </div>
                                                    <p className="text-white text-xs font-medium truncate px-1">
                                                        {ingredient.name}
                                                    </p>
                                                    <div className={`text-xs px-2 py-1 rounded-full mt-1 ${ingredient.itemRarity === 'COMMON' ? 'bg-gray-500 text-white' :
                                                            ingredient.itemRarity === 'UNCOMMON' ? 'bg-green-500 text-white' :
                                                                ingredient.itemRarity === 'RARE' ? 'bg-blue-500 text-white' :
                                                                    ingredient.itemRarity === 'EPIC' ? 'bg-pink-500 text-white' :
                                                                        ingredient.itemRarity === 'UNIQUE' ? 'bg-purple-500 text-white' :
                                                                            ingredient.itemRarity === 'LEGENDARY' ? 'bg-orange-500 text-white' :
                                                                                'bg-yellow-500 text-black'
                                                        }`}>
                                                        {ingredient.itemRarity}
                                                    </div>
                                                </div>
                                            ) : (
                                                <div className="text-gray-400 text-center">
                                                    <div className="w-12 h-12 border-2 border-dashed border-gray-500 rounded-lg mx-auto mb-2 flex items-center justify-center">
                                                        <span className="text-2xl">+</span>
                                                    </div>
                                                    <p className="text-xs">슬롯 {index + 1}</p>
                                                </div>
                                            )}
                                        </div>

                                        {/* 제거 버튼 */}
                                        {ingredient && (
                                            <button
                                                className="absolute -top-2 -right-2 w-6 h-6 bg-red-500 hover:bg-red-600 text-white rounded-full flex items-center justify-center text-xs font-bold transition-colors"
                                                onClick={() => {
                                                    removeIngredient(index);
                                                }}
                                            >
                                                ×
                                            </button>
                                        )}
                                    </div>
                                ))}
                            </div>
                        </div>

                        {/* 화살표 */}
                        <div className="flex justify-center">
                            <div className="w-16 h-16 bg-gradient-to-r from-blue-500 to-purple-600 rounded-full flex items-center justify-center animate-pulse">
                                <svg className="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 7l5 5m0 0l-5 5m5-5H6" />
                                </svg>
                            </div>
                        </div>

                        {/* 결과 슬롯 */}
                        <div className="space-y-4">
                            <h2 className="text-xl font-semibold text-white mb-4">결과</h2>
                            <div className="flex justify-center">
                                <div className="relative">
                                    <div className="w-32 h-32 bg-gradient-to-br from-gray-700 to-gray-800 rounded-xl border-2 border-gray-600 flex items-center justify-center">
                                        {result ? (
                                            <div className="text-center">
                                                <div className="w-16 h-16 bg-gradient-to-br from-yellow-500 to-orange-600 rounded-lg mx-auto mb-3 flex items-center justify-center">
                                                    <span className="text-white font-bold text-lg">
                                                        {result.name.charAt(0)}
                                                    </span>
                                                </div>
                                                <p className="text-white font-medium truncate px-2">
                                                    {result.name}
                                                </p>
                                                <div className={`text-xs px-3 py-1 rounded-full mt-2 ${result.itemRarity === 'COMMON' ? 'bg-gray-500 text-white' :
                                                        result.itemRarity === 'UNCOMMON' ? 'bg-green-500 text-white' :
                                                            result.itemRarity === 'RARE' ? 'bg-blue-500 text-white' :
                                                                result.itemRarity === 'EPIC' ? 'bg-pink-500 text-white' :
                                                                    result.itemRarity === 'UNIQUE' ? 'bg-purple-500 text-white' :
                                                                        result.itemRarity === 'LEGENDARY' ? 'bg-orange-500 text-white' :
                                                                            'bg-yellow-500 text-black'
                                                    }`}>
                                                    {result.itemRarity}
                                                </div>
                                            </div>
                                        ) : (
                                            <div className="text-gray-400 text-center">
                                                <div className="w-16 h-16 border-2 border-dashed border-gray-500 rounded-lg mx-auto mb-3 flex items-center justify-center">
                                                    <span className="text-3xl">?</span>
                                                </div>
                                                <p className="text-sm">크래프트 결과</p>
                                            </div>
                                        )}
                                    </div>

                                    {/* 크래프트 버튼 */}
                                    <button
                                        onClick={() => {
                                            craft();
                                        }}
                                        className={`w-full mt-4 py-3 px-6 rounded-xl font-bold text-white transition-all duration-200 ${ingredientMaterialList.some(ing => ing !== null) && !isCrafting
                                                ? 'bg-gradient-to-r from-blue-500 to-purple-600 hover:from-blue-600 hover:to-purple-700 shadow-lg hover:shadow-xl'
                                                : 'bg-gray-600 cursor-not-allowed'
                                            }`}
                                        disabled={!ingredientMaterialList.some(ing => ing !== null) || isCrafting}
                                    >
                                        {isCrafting ? (
                                            <div className="flex items-center justify-center">
                                                <div className="animate-spin rounded-full h-5 w-5 border-b-2 border-white mr-2"></div>
                                                크래프팅 중...
                                            </div>
                                        ) : (
                                            '크래프트 시작'
                                        )}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {/* 인벤토리 영역 (재료 선택용) */}
                <div className="mt-8 bg-black/30 backdrop-blur-sm rounded-2xl p-6 border border-gray-600">
                    <h2 className="text-xl font-semibold text-white mb-4">인벤토리</h2>
                    <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-6 gap-4">
                        {/* 샘플 재료들 - 실제로는 인벤토리에서 가져올 데이터 */}
                        {materialList.map((material: MaterialInventoryItemDTO, index: number) => (
                            <div
                                key={index}
                                className="w-20 h-20 bg-gradient-to-br from-gray-700 to-gray-800 rounded-lg border border-gray-600 hover:border-blue-400 transition-all duration-200 cursor-pointer flex items-center justify-center group"
                                onClick={() => {
                                    addIngredient(material);
                                }}
                            >
                                <div className="text-center">
                                    <div className={`w-8 h-8 rounded-md mx-auto mb-1 flex items-center justify-center ${material.itemRarity === 'COMMON' ? 'bg-gray-500' :
                                            material.itemRarity === 'UNCOMMON' ? 'bg-green-500' :
                                                material.itemRarity === 'RARE' ? 'bg-blue-500' :
                                                    material.itemRarity === 'EPIC' ? 'bg-pink-500' :
                                                        material.itemRarity === 'UNIQUE' ? 'bg-purple-500' :
                                                            material.itemRarity === 'LEGENDARY' ? 'bg-orange-500' :

                                                                'bg-yellow-500'
                                        }`}>
                                        <span className="text-white font-bold text-xs">
                                            {material.name.charAt(0)}
                                        </span>
                                    </div>
                                    <p className="text-white text-xs font-medium truncate px-1">
                                        {material.name}
                                    </p>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    )
}