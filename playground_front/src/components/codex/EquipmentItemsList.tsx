'use client'

import { useState, useEffect, useMemo } from 'react'
import { EquipmentCollectionDTO } from '@/types/collection'
import { ItemRarity } from '@/types/item'
import { CodexItem } from '@/hooks/common/useCodexApi'
import { ResponsiveText, ResponsiveGrid } from '@/components/ui/ResponsiveContainer'
import { useGameResponsive } from '@/hooks/useResponsive'

interface EquipmentItemsListProps {
    equipmentItems: EquipmentCollectionDTO[]
    selectedRarities: ItemRarity[]
    currentPage: number
    setCurrentPage: (page: number) => void
    selectedItem: CodexItem | null
    onItemClick: (item: CodexItem) => void
    isLoading: boolean
    error: any
}

export default function EquipmentItemsList({
    equipmentItems,
    selectedRarities,
    currentPage,
    setCurrentPage,
    selectedItem,
    onItemClick,
    isLoading,
    error
}: EquipmentItemsListProps) {
    const { currentBreakpoint, shouldShowMobileUI } = useGameResponsive()
    const itemsPerPage = 15 // 5x4 그리드로 25개 아이템


    // 필터링된 아이템들 (레어리티별) - useMemo로 최적화
    const filteredItems = useMemo(() => {
        return equipmentItems.filter(equipmentItem =>
            selectedRarities.includes(equipmentItem.itemRarity)
        )
    }, [equipmentItems, selectedRarities])

    // 페이지네이션 계산 - useMemo로 최적화
    const totalPages = useMemo(() => {
        return Math.ceil(filteredItems.length / itemsPerPage)
    }, [filteredItems.length, itemsPerPage])

    const currentItems = useMemo(() => {
        const startIndex = (currentPage - 1) * itemsPerPage
        const endIndex = startIndex + itemsPerPage
        return filteredItems.slice(startIndex, endIndex)
    }, [filteredItems, currentPage, itemsPerPage])

    // 첫 번째 아이템을 기본 선택 (필터 변경 시에만)
    useEffect(() => {
        if (filteredItems.length > 0) {
            const currentItemExists = selectedItem && filteredItems.some(item => item.equipmentId === selectedItem.id)
            if (!currentItemExists) {
                // EquipmentCollectionDTO를 CodexItem으로 변환
                const firstItem = convertToCodexItem(filteredItems[0])
                onItemClick(firstItem)
            }
        }
    }, [filteredItems, selectedItem, onItemClick])

    // 카테고리 변경 시 페이지 리셋
    useEffect(() => {
        setCurrentPage(1)
    }, [selectedRarities, setCurrentPage])

    // EquipmentCollectionDTO를 CodexItem으로 변환하는 함수
    const convertToCodexItem = (equipmentItem: EquipmentCollectionDTO): CodexItem => {
        return {
            id: equipmentItem.equipmentId,
            name: equipmentItem.name,
            shortName: equipmentItem.name.substring(0, 2),
            rarity: equipmentItem.itemRarity,
            type: equipmentItem.itemType,
            discovered: equipmentItem.isCollected,
            imageUrl: equipmentItem.itemImg,
            description: equipmentItem.description,
            value: 0, // 기본값
            stackSize: 1, // 장비는 보통 1개씩
            acquisition: [`Level ${equipmentItem.level} Required`], // 레벨 요구사항
            discoveryInfo: {
                firstFound: equipmentItem.discoveredAt,
                totalFound: equipmentItem.quantity
            }
        }
    }

    const getRarityClass = (rarity: ItemRarity) => {
        switch (rarity) {
            case 'COMMON': return 'rarity-common'
            case 'UNCOMMON': return 'rarity-uncommon'
            case 'RARE': return 'rarity-rare'
            case 'EPIC': return 'rarity-epic'
            case 'UNIQUE': return 'rarity-unique'
            case 'LEGENDARY': return 'rarity-legendary'
            default: return 'rarity-common'
        }
    }

    const getRaritySlotClass = (rarity: ItemRarity) => {
        switch (rarity) {
            case 'COMMON': return 'bg-gray-600 border-gray-500'
            case 'UNCOMMON': return 'bg-green-600 border-green-500'
            case 'RARE': return 'bg-blue-600 border-blue-500'
            case 'EPIC': return 'bg-purple-600 border-purple-500'
            case 'UNIQUE': return 'bg-orange-600 border-orange-500'
            case 'LEGENDARY': return 'bg-yellow-600 border-yellow-500'
            default: return 'bg-gray-600 border-gray-500'
        }
    }

    const getRandomCount = (itemId: number) => {
        const counts = [0, 1, 2, 3, 5, 8, 12, 15, 20, 25, 30, 50, 100]
        return counts[itemId % counts.length]
    }

    const handleItemClick = (equipmentItem: EquipmentCollectionDTO) => {
        if (equipmentItem.isCollected) {
            const codexItem = convertToCodexItem(equipmentItem)
            onItemClick(codexItem)
        }
    }

    // 로딩 상태
    if (isLoading) {
        return (
            <div className="text-center py-8">
                <ResponsiveText 
                    sizes={{
                        mobile: 'text-xs',
                        'mobile-lg': 'text-sm',
                        tablet: 'text-sm',
                        'tablet-lg': 'text-sm',
                        desktop: 'text-sm',
                        'desktop-lg': 'text-sm',
                        game: 'text-base'
                    }}
                    className="text-amber-600"
                >
                    Loading equipment...
                </ResponsiveText>
            </div>
        )
    }

    // 에러 상태
    if (error) {
        return (
            <div className="text-center py-8">
                <ResponsiveText 
                    sizes={{
                        mobile: 'text-xs',
                        'mobile-lg': 'text-sm',
                        tablet: 'text-sm',
                        'tablet-lg': 'text-sm',
                        desktop: 'text-sm',
                        'desktop-lg': 'text-sm',
                        game: 'text-base'
                    }}
                    className="text-red-600"
                >
                    Failed to load equipment
                </ResponsiveText>
            </div>
        )
    }

    return (
        <div>
            {/* 아이템 그리드 - Tailwind 기본 브레이크포인트 사용 */}
            <ResponsiveGrid>
                {currentItems.map((equipmentItem) => (
                    <div
                        key={equipmentItem.equipmentId}
                        className={`item-card p-2 aspect-square flex flex-col items-center justify-center cursor-pointer transition-all border-2 ${
                            equipmentItem.isCollected ? 'discovered' : 'undiscovered'
                        } ${
                            selectedItem?.id === equipmentItem.equipmentId ? 'selected' : ''
                        } ${
                            equipmentItem.isCollected ? getRaritySlotClass(equipmentItem.itemRarity) : 'bg-gray-700 border-gray-600'
                        }`}
                        onClick={() => handleItemClick(equipmentItem)}
                    >
                        {equipmentItem.isCollected ? (
                            <>
                                <div className="w-full h-3/4 flex items-center justify-center overflow-hidden mb-1">
                                    {equipmentItem.itemImg ? (
                                        <img 
                                            src={equipmentItem.itemImg} 
                                            alt={equipmentItem.name} 
                                            className="w-full h-full object-cover rounded"
                                        />
                                    ) : (
                                        <div className={`w-full h-full ${getRarityClass(equipmentItem.itemRarity)} rounded flex items-center justify-center`}>
                                            <span className="text-white font-bold text-xs">{equipmentItem.name.substring(0, 2)}</span>
                                        </div>
                                    )}
                                </div>
                                <ResponsiveText 
                                    sizes={{
                                        mobile: 'text-[5px]',
                                        'mobile-lg': 'text-[6px]',
                                        tablet: 'text-[6px]',
                                        'tablet-lg': 'text-[6px]',
                                        desktop: 'text-[6px]',
                                        'desktop-lg': 'text-[6px]',
                                        game: 'text-[7px]'
                                    }}
                                    className="text-white text-center leading-tight mb-1"
                                >
                                    {equipmentItem.name}
                                </ResponsiveText>
                                {/* 장비 레벨 표시 */}
                                <ResponsiveText 
                                    sizes={{
                                        mobile: 'text-[4px]',
                                        'mobile-lg': 'text-[5px]',
                                        tablet: 'text-[5px]',
                                        'tablet-lg': 'text-[5px]',
                                        desktop: 'text-[5px]',
                                        'desktop-lg': 'text-[5px]',
                                        game: 'text-[6px]'
                                    }}
                                    className="text-blue-300 text-center mb-1"
                                >
                                    Lv.{equipmentItem.level}
                                </ResponsiveText>
                                <div className="w-full mb-1">
                                    {/* 진행바 배경 */}
                                    <div className="w-full h-1 bg-gray-700 rounded-full overflow-hidden">
                                        {/* 진행바 채우기 */}
                                        <div 
                                            className="h-full bg-gradient-to-r from-amber-400 to-amber-600 rounded-full transition-all duration-300"
                                            style={{ 
                                                width: `${Math.min((getRandomCount(equipmentItem.equipmentId) / 5) * 100, 100)}%` 
                                            }}
                                        ></div>
                                    </div>
                                </div>
                                <ResponsiveText 
                                    sizes={{
                                        mobile: 'text-[4px]',
                                        'mobile-lg': 'text-[5px]',
                                        tablet: 'text-[5px]',
                                        'tablet-lg': 'text-[5px]',
                                        desktop: 'text-[5px]',
                                        'desktop-lg': 'text-[5px]',
                                        game: 'text-[6px]'
                                    }}
                                    className="text-amber-300 text-center font-bold"
                                >
                                    [{getRandomCount(equipmentItem.equipmentId)}/5]
                                </ResponsiveText>
                            </>
                        ) : (
                            <>
                                <div className="w-full h-3/4 silhouette rounded mb-1 flex items-center justify-center overflow-hidden">
                                    {/* 미발견 아이템은 기본적으로 ? 표시 */}
                                </div>
                                <ResponsiveText 
                                    sizes={{
                                        mobile: 'text-[5px]',
                                        'mobile-lg': 'text-[6px]',
                                        tablet: 'text-[6px]',
                                        'tablet-lg': 'text-[6px]',
                                        desktop: 'text-[6px]',
                                        'desktop-lg': 'text-[6px]',
                                        game: 'text-[7px]'
                                    }}
                                    className="text-gray-400 text-center leading-tight mb-1"
                                >
                                    ???
                                </ResponsiveText>
                                <ResponsiveText 
                                    sizes={{
                                        mobile: 'text-[4px]',
                                        'mobile-lg': 'text-[5px]',
                                        tablet: 'text-[5px]',
                                        'tablet-lg': 'text-[5px]',
                                        desktop: 'text-[5px]',
                                        'desktop-lg': 'text-[5px]',
                                        game: 'text-[6px]'
                                    }}
                                    className="text-gray-500 text-center mb-1"
                                >
                                    Lv.?
                                </ResponsiveText>
                                <div className="w-full mb-1">
                                    <div className="w-full h-1 bg-gray-700 rounded-full overflow-hidden">
                                        <div className="h-full bg-gray-600 rounded-full" style={{ width: '0%' }}></div>
                                    </div>
                                </div>
                                <ResponsiveText 
                                    sizes={{
                                        mobile: 'text-[4px]',
                                        'mobile-lg': 'text-[5px]',
                                        tablet: 'text-[5px]',
                                        'tablet-lg': 'text-[5px]',
                                        desktop: 'text-[5px]',
                                        'desktop-lg': 'text-[5px]',
                                        game: 'text-[6px]'
                                    }}
                                    className="text-gray-500 text-center font-bold"
                                >
                                    [0/5]
                                </ResponsiveText>
                            </>
                        )}
                    </div>
                ))}
            </ResponsiveGrid>

            {/* 페이지 네비게이션 */}
            <div className="mt-6 flex justify-center gap-4">
                <button
                    className="pixel-button bg-amber-600 text-white px-4 py-2 text-xs font-bold hover:bg-amber-500 disabled:opacity-50 disabled:cursor-not-allowed"
                    onClick={() => setCurrentPage(Math.max(1, currentPage - 1))}
                    disabled={currentPage === 1}
                >
                    PREV PAGE
                </button>
                <div className="pixel-border bg-amber-100 px-4 py-2">
                    <span className="text-xs text-amber-800 font-bold">PAGE {currentPage} / {totalPages}</span>
                </div>
                <button
                    className="pixel-button bg-amber-600 text-white px-4 py-2 text-xs font-bold hover:bg-amber-500 disabled:opacity-50 disabled:cursor-not-allowed"
                    onClick={() => setCurrentPage(Math.min(totalPages, currentPage + 1))}
                    disabled={currentPage === totalPages}
                >
                    NEXT PAGE
                </button>
            </div>

            {/* 상태 정보 */}
            <div className="mt-4 text-center">
                <ResponsiveText 
                    sizes={{
                        mobile: 'text-xs',
                        'mobile-lg': 'text-xs',
                        tablet: 'text-xs',
                        'tablet-lg': 'text-xs',
                        desktop: 'text-xs',
                        'desktop-lg': 'text-xs',
                        game: 'text-sm'
                    }}
                    className="text-amber-600"
                >
                    DISCOVERED: {filteredItems.filter(item => item.isCollected).length}/{filteredItems.length} EQUIPMENT
                </ResponsiveText>
            </div>
        </div>
    )
} 