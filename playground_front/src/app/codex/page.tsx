'use client'

import { useState, useEffect } from 'react'
import Link from 'next/link'
import '../css/codex.css'
import { useCodexItems, useCodexItemsByMaterial, useCodexItemsByEquipment, CodexItem } from '@/hooks/common/useCodexApi'
import CodexDetailPanel from '@/components/codex/CodexDetailPanel'
import { ItemRarity, ItemType, ItemRarities } from '@/types/item'
import { useCodexStore } from '@/store/codexStore'
import MaterialItemsList from '@/components/codex/MaterialItemsList'
import EquipmentItemsList from '@/components/codex/EquipmentItemsList'





export default function CodexPage() {
    const [selectedItem, setSelectedItem] = useState<CodexItem | null>(null)
    const [showDetail, setShowDetail] = useState(false)
    const [currentPage, setCurrentPage] = useState(1)
    const [currentCategory, setCurrentCategory] = useState<ItemType>('MATERIAL')
    const [selectedRarities, setSelectedRarities] = useState<ItemRarity[]>([...ItemRarities])

    const { isFetchAllItems, isFetchMaterialItems, isFetchEquipmentItems, setIsFetchAllItems, setIsFetchMaterialItems, setIsFetchEquipmentItems, resetAllFlags } = useCodexStore()

    // React Query로 데이터 가져오기 - 조건부 fetch
    const { data: allItems, isLoading, error, refetch } = useCodexItems(isFetchAllItems)
    const { data: materialItems, refetch: refetchMaterial } = useCodexItemsByMaterial(isFetchMaterialItems)
    const { data: equipmentItems, refetch: refetchEquipment } = useCodexItemsByEquipment(isFetchEquipmentItems)

    // 도감 갱신 플래그에 따른 조건부 fetch
    useEffect(() => {
        if (isFetchAllItems) {
            setIsFetchAllItems(false)
            setIsFetchMaterialItems(false)
            setIsFetchEquipmentItems(false)
        } else if(isFetchMaterialItems){
            setIsFetchMaterialItems(false)
        } else if(isFetchEquipmentItems){
            setIsFetchEquipmentItems(false)
        }
    }, [isFetchAllItems, isFetchMaterialItems, isFetchEquipmentItems])

    // 상세 패널 열림/닫힘에 따른 스크롤 제어
    useEffect(() => {
        if (showDetail) {
            document.body.style.overflow = 'hidden'
        } else {
            document.body.style.overflow = 'auto'
        }

        return () => {
            document.body.style.overflow = 'auto'
        }
    }, [showDetail])

    // 레어리티 토글 함수
    const toggleRarity = (rarity: ItemRarity) => {
        setSelectedRarities(prev => 
            prev.includes(rarity) 
                ? prev.filter(r => r !== rarity)
                : [...prev, rarity]
        )
        setCurrentPage(1)
    }

    // 모든 레어리티 선택/해제
    const toggleAllRarities = () => {
        if (selectedRarities.length === 6) {
            setSelectedRarities([])
        } else {
            setSelectedRarities(['COMMON', 'UNCOMMON', 'RARE', 'EPIC', 'UNIQUE', 'LEGENDARY'])
        }
        setCurrentPage(1)
    }

    // 아이템 선택 시 상세 정보 표시
    const handleItemClick = (item: CodexItem) => {
        if (item.discovered) {
            setSelectedItem(item)
            setShowDetail(true)
        }
    }

    // 상세 정보 닫기
    const handleCloseDetail = () => {
        setShowDetail(false)
    }

    // 도감 강제 갱신 함수 (테스트용)
    const forceRefreshCodex = () => {
        setIsFetchAllItems(true)
    }

    const getRarityFilterClass = (rarity: ItemRarity) => {
        const isSelected = selectedRarities.includes(rarity)
        switch (rarity) {
            case 'COMMON': return `postit-button ${isSelected ? 'bg-gray-500 text-white' : 'bg-gray-300 text-gray-700'}`
            case 'UNCOMMON': return `postit-button ${isSelected ? 'bg-green-500 text-white' : 'bg-green-300 text-green-700'}`
            case 'RARE': return `postit-button ${isSelected ? 'bg-blue-500 text-white' : 'bg-blue-300 text-blue-700'}`
            case 'EPIC': return `postit-button ${isSelected ? 'bg-purple-500 text-white' : 'bg-purple-300 text-purple-700'}`
            case 'UNIQUE': return `postit-button ${isSelected ? 'bg-orange-500 text-white' : 'bg-orange-300 text-orange-700'}`
            case 'LEGENDARY': return `postit-button ${isSelected ? 'bg-yellow-500 text-white' : 'bg-yellow-300 text-yellow-700'}`
            default: return `postit-button ${isSelected ? 'bg-gray-500 text-white' : 'bg-gray-300 text-gray-700'}`
        }
    }

    return (
        <div className="min-h-screen p-4 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900">
            <div className="relative max-w-7xl mx-auto">
                {/* 배경 효과 */}
                <div className="absolute inset-0 bg-black opacity-10 scan-line"></div>

                {/* 헤더 */}
                <div className={`relative mb-6 transition-all duration-500 ${showDetail ? 'opacity-50 scale-95' : 'opacity-100 scale-100'}`}>
                    <div className="flex justify-between items-center mb-2">
                        <h1 className={`text-2xl font-['Press_Start_2P'] transition-all duration-500 ${showDetail ? 'text-gray-500' : 'text-amber-400'}`}>
                            ▶ CODEX SYSTEM
                        </h1>
                        {/* 테스트용 갱신 버튼 */}
                        <button
                            onClick={forceRefreshCodex}
                            className="pixel-button bg-blue-600 text-white px-3 py-1 text-xs font-bold hover:bg-blue-500"
                        >
                            [ REFRESH ]
                        </button>
                    </div>
                    <div className={`text-xs transition-all duration-500 ${showDetail ? 'text-gray-500' : 'text-gray-400'}`}>
                        Discover and learn about all items in the world
                    </div>
                </div>

                {/* 레어리티 필터 - 포스트잇 스타일 */}
                <div className={`relative mb-4 transition-all duration-500 ${showDetail ? 'opacity-30 scale-90' : 'opacity-100 scale-100'}`}>
                    <div className="flex flex-wrap gap-2 justify-center">
                        {/* 전체 선택/해제 버튼 */}
                        <button
                            onClick={toggleAllRarities}
                            className={`postit-button px-3 py-1 text-[10px] font-bold transition-all transform hover:scale-105 ${
                                selectedRarities.length === 6 
                                    ? 'bg-amber-500 text-white' 
                                    : 'bg-amber-300 text-amber-700'
                            }`}
                        >
                            {selectedRarities.length === 6 ? 'ALL' : 'NONE'}
                        </button>
                        
                        {/* 레어리티별 토글 버튼들 */}
                        {ItemRarities.map((rarity) => (
                            <button
                                key={rarity}
                                onClick={() => toggleRarity(rarity)}
                                className={`${getRarityFilterClass(rarity)} px-3 py-1 text-[10px] font-bold transition-all transform hover:scale-105`}
                            >
                                {rarity}
                            </button>
                        ))}
                    </div>
                </div>

                {/* 메인 도감 컨테이너 */}
                <div className={`relative flex gap-4 transition-all duration-500 ${showDetail ? 'opacity-40 scale-95' : 'opacity-100 scale-100'}`}>
                    {/* 왼쪽 페이지 (아이템 목록) */}
                    <div className="flex-1">
                        <div className="book-page custom-scrollbar p-6 h-[600px] overflow-y-auto">
                            <h2 className="text-lg text-amber-800 mb-4 text-center font-['Press_Start_2P']">▶ ITEM CATALOG</h2>

                            {/* 카테고리 탭 */}
                            <div className="flex gap-2 mb-4 justify-center">
                                <button
                                    className={`pixel-button px-3 py-1 text-[8px] font-bold transition-colors ${currentCategory === 'MATERIAL'
                                            ? 'bg-amber-600 text-white'
                                            : 'bg-gray-600 text-gray-300'
                                        }`}
                                    onClick={() => setCurrentCategory('MATERIAL')}
                                >
                                    MATERIALS
                                </button>
                                <button
                                    className={`pixel-button px-3 py-1 text-[8px] font-bold transition-colors ${currentCategory === 'EQUIPMENT'
                                            ? 'bg-amber-600 text-white'
                                            : 'bg-gray-600 text-gray-300'
                                        }`}
                                    onClick={() => setCurrentCategory('EQUIPMENT')}
                                >
                                    EQUIPMENT
                                </button>
                            </div>

                            {/* 카테고리별 컴포넌트 렌더링 */}
                            {currentCategory === 'MATERIAL' ? (
                                <MaterialItemsList
                                    materialItems={materialItems || []}
                                    selectedRarities={selectedRarities}
                                    currentPage={currentPage}
                                    setCurrentPage={setCurrentPage}
                                    selectedItem={selectedItem}
                                    onItemClick={handleItemClick}
                                    isLoading={isLoading}
                                    error={error}
                                />
                            ) : (
                                <EquipmentItemsList
                                    equipmentItems={equipmentItems || []}
                                    selectedRarities={selectedRarities}
                                    currentPage={currentPage}
                                    setCurrentPage={setCurrentPage}
                                    selectedItem={selectedItem}
                                    onItemClick={handleItemClick}
                                    isLoading={isLoading}
                                    error={error}
                                />
                            )}
                        </div>
                    </div>
                </div>

                {/* 하단 상태바 */}
                <div className={`relative mt-6 transition-all duration-500 ${showDetail ? 'opacity-40 scale-95' : 'opacity-100 scale-100'}`}>
                    <div className="bg-black pixel-border p-3">
                        <div className="text-xs text-green-400 flex justify-between">
                            <span>
                                SELECTED: {selectedItem?.name || 'NONE'} | CATEGORY: {currentCategory} | FILTER: {selectedRarities.length}/6 RARITIES
                            </span>
                            <span className="blink">
                                {isLoading ? 'LOADING...' : error ? 'ERROR' : 'READY'}
                            </span>
                        </div>
                    </div>
                </div>

                {/* 상세 정보 패널 */}
                <CodexDetailPanel 
                    item={selectedItem!}
                    isVisible={showDetail}
                    onClose={handleCloseDetail}
                />
            </div>
        </div>
    )
} 