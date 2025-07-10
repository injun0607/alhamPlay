'use client'

import { EquipmentInventory, EquipmentInventoryItemDTO } from "@/types/inventory";
import { InventoryStore } from "@/store/inventoryStore";
import { useApi } from '@/hooks/common/useApi';
import EquipmentItemSlot from "./EquipmentItemSlot";
import ItemTooltip from "./ItemTooltip";
import { useState, useMemo, useCallback } from "react";
import { MaterialInventoryItemDTO } from "@/types/inventory";

interface EquipmentInventoryTabProps {
    equipmentInventory: EquipmentInventory;
}

export default function EquipmentInventoryTab({equipmentInventory}:EquipmentInventoryTabProps) {


    const {post} = useApi<MaterialInventoryItemDTO[]>();

    const [tooltipItem, setTooltipItem] = useState<EquipmentInventoryItemDTO | null>(null);
    const [tooltipPosition, setTooltipPosition] = useState({ x: 0, y: 0 });
    const [isTooltipVisible, setIsTooltipVisible] = useState(false);
    
    const {addItem,removeItem} = InventoryStore();

    // 클릭된 아이템 상태 추가
    const [clickedItem, setClickedItem] = useState<EquipmentInventoryItemDTO | null>(null);
    const [clickedItemPosition, setClickedItemPosition] = useState({ x: 0, y: 0 });

    // 분해 결과 모달 상태
    const [disassembleResult, setDisassembleResult] = useState<MaterialInventoryItemDTO[] | null>(null);
    const [isResultModalVisible, setIsResultModalVisible] = useState(false);

    const {equipmentItemList} = equipmentInventory;

    // 40개로 제한
    const limitedItemList = equipmentItemList.slice(0, 40);

    // 40개 슬롯을 5x8 그리드로 생성 (useMemo로 최적화)
    const totalSlots = 40;
    const slotsPerRow = 5;
    const totalRows = Math.ceil(totalSlots / slotsPerRow);
    
    const slots = useMemo(() => {
        const slots = [];
        for (let i = 0; i < totalSlots; i++) {
            const item = limitedItemList[i] || null;
            slots.push({ item, index: i });
        }
        return slots;
    }, [limitedItemList]); // limitedItemList가 변경될 때만 재계산

    const handleMouseEnter = (item: EquipmentInventoryItemDTO, event: React.MouseEvent) => {
        // 클릭된 아이템이 있으면 툴팁 표시하지 않음
        if (clickedItem) return;
        
        setTooltipItem(item);
        setTooltipPosition({ x: event.clientX, y: event.clientY });
        setIsTooltipVisible(true);
    };

    const handleMouseLeave = () => {
        setIsTooltipVisible(false);
    };

    const handleItemClick = (item: EquipmentInventoryItemDTO, event: React.MouseEvent) => {
        // 클릭 시 툴팁 숨기고 클릭된 아이템 설정
        setIsTooltipVisible(false);
        setClickedItem(item);
        
        // 클릭된 요소의 위치 정보 가져오기
        const target = event.currentTarget as HTMLElement;
        const rect = target.getBoundingClientRect();
        
        // 슬롯 왼쪽에 버튼이 나타나도록 위치 계산
        setClickedItemPosition({ 
            x: rect.left - 40, // 슬롯 왼쪽으로 80px
            y: rect.top
        });
    };

    const handleRegister = () => {
        // 등록 로직 구현
        console.log('등록:', clickedItem);
        setClickedItem(null);
    };

    const handleDisassemble = async () => {
        if(!clickedItem) return;
        // 분해 로직 구현
        try{
            const result = await post<EquipmentInventoryItemDTO>('/dismantle/equipment', clickedItem);
            
            if(result){
                result.forEach(item => {
                    addItem(item);
                });
                removeItem(clickedItem);
                
                // 분해 결과 모달 표시
                setDisassembleResult(result);
                setIsResultModalVisible(true);
            }

        } catch(error){
            console.error('분해 실패:', error);
        } finally{
            setClickedItem(null);
        }
    };

    const handleBackgroundClick = () => {
        // 배경 클릭 시 클릭된 아이템 초기화
        setClickedItem(null);
        setIsTooltipVisible(false);
    };

    const closeResultModal = () => {
        setIsResultModalVisible(false);
        setDisassembleResult(null);
    };

    return(
        <div className="space-y-4 relative" onClick={handleBackgroundClick}>
            <div className="flex justify-between items-center mb-4">
                <h2 className="text-sm text-green-400">▶ EQUIPMENT</h2>
                <div className="text-xs text-gray-400">
                    {limitedItemList.length}/40
                </div>
            </div>
            
            <div className="grid grid-cols-5 gap-2">
                {slots.map(({ item, index }) => (
                    <div
                        key={index}
                        className={`item-slot inventory-slot flex flex-col items-center justify-center cursor-pointer flex-shrink-0 ${
                            item 
                                ? 'bg-gradient-to-br from-gray-700 to-gray-800 hover:border-green-400' 
                                : 'bg-gradient-to-br from-gray-600 to-gray-700 opacity-50'
                        }`}
                        onMouseEnter={item ? (e) => handleMouseEnter(item, e) : undefined}
                        onMouseLeave={item ? handleMouseLeave : undefined}
                        onClick={item ? (e) => {
                            e.stopPropagation();
                            handleItemClick(item, e);
                        } : undefined}
                    >
                        <EquipmentItemSlot item={item} />
                    </div>
                ))}
            </div>

            {/* 툴팁 */}
            <ItemTooltip 
                item={tooltipItem}
                position={tooltipPosition}
                isVisible={isTooltipVisible}
            />

            {/* 클릭된 아이템 액션 버튼 */}
            {clickedItem && (
                <div 
                    className="fixed z-50 bg-gray-800 border border-green-400 rounded-lg p-2 shadow-lg"
                    style={{
                        left: clickedItemPosition.x,
                        top: clickedItemPosition.y
                    }}
                    onClick={(e) => e.stopPropagation()}
                >
                    <div className="flex flex-col gap-1">
                        <button
                            className="px-3 py-1 text-xs bg-green-600 hover:bg-green-700 text-white rounded transition-colors"
                            onClick={handleRegister}
                        >
                            등록
                        </button>
                        <button
                            className="px-3 py-1 text-xs bg-red-600 hover:bg-red-700 text-white rounded transition-colors"
                            onClick={handleDisassemble}
                        >
                            분해
                        </button>
                    </div>
                </div>
            )}

            {/* 분해 결과 모달 */}
            {isResultModalVisible && disassembleResult && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
                    <div className="bg-gray-800 border border-green-400 rounded-lg p-6 max-w-md w-full mx-4">
                        <div className="flex justify-between items-center mb-4">
                            <h3 className="text-lg text-green-400 font-bold">분해 완료!</h3>
                            <button 
                                onClick={closeResultModal}
                                className="text-gray-400 hover:text-white text-xl"
                            >
                                ×
                            </button>
                        </div>
                        
                        <div className="mb-4">
                            <p className="text-gray-300 text-sm mb-3">
                                다음 아이템들을 획득했습니다:
                            </p>
                            <div className="space-y-2 max-h-48 overflow-y-auto">
                                {disassembleResult.map((item, index) => (
                                    <div key={index} className="flex items-center gap-3 p-2 bg-gray-700 rounded">
                                        <div className="w-6 h-6 bg-gradient-to-br from-white-500 to-white-600 rounded flex items-center justify-center">
                                            <img src="/images/glacier_mat.png" alt={item.name} className="w-6 h-6" />
                                        </div>
                                        <div className="flex-1">
                                            <div className="text-sm text-white">{item.name}</div>
                                            <div className="text-xs text-gray-400">수량: {item.quantity}</div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                        
                        <button
                            onClick={closeResultModal}
                            className="w-full px-4 py-2 bg-green-600 hover:bg-green-700 text-white rounded transition-colors"
                        >
                            확인
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
}