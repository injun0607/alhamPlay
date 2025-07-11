'use client'

import { useState, useEffect } from 'react'
import Link from 'next/link'
import '../css/codex.css'
import { useCodexItems, useCodexItemsByCategory, CodexItem } from '@/hooks/common/useCodexApi'
import CodexDetailPanel from '@/components/codex/CodexDetailPanel'

export default function CodexPage() {
    const [selectedItem, setSelectedItem] = useState<CodexItem | null>(null)
    const [showDetail, setShowDetail] = useState(false)
    const [currentPage, setCurrentPage] = useState(1)
    const [currentCategory, setCurrentCategory] = useState<'material' | 'equipment'>('material')

    // React Query로 데이터 가져오기
    const { data: allItems, isLoading, error } = useCodexItems()
    const { data: categoryItems } = useCodexItemsByCategory(currentCategory)

    // 필터링된 아이템들 (카테고리별)
    const filteredItems = categoryItems || allItems || []
    
    // 디버깅용 로그
    console.log('Codex Debug:', {
        allItems: allItems?.length,
        categoryItems: categoryItems?.length,
        filteredItems: filteredItems.length,
        currentCategory,
        selectedItem: selectedItem?.name,
        isLoading,
        error
    })
    
    const itemsPerPage = 5
    const totalPages = Math.ceil(filteredItems.length / itemsPerPage)
    const startIndex = (currentPage - 1) * itemsPerPage
    const endIndex = startIndex + itemsPerPage
    const currentItems = filteredItems.slice(startIndex, endIndex)

    // 첫 번째 아이템을 기본 선택 (카테고리 변경 시에만)
    useEffect(() => {
        if (filteredItems.length > 0) {
            // 현재 선택된 아이템이 새로운 카테고리에 없으면 첫 번째 아이템 선택
            const currentItemExists = selectedItem && filteredItems.some(item => item.id === selectedItem.id)
            if (!currentItemExists) {
                setSelectedItem(filteredItems[0])
            }
        }
    }, [filteredItems, selectedItem])

    // 카테고리 변경 시 페이지 리셋 (한 번만)
    useEffect(() => {
        setCurrentPage(1)
    }, [currentCategory])

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

    const getRarityClass = (rarity: string) => {
        switch (rarity) {
            case 'common': return 'rarity-common'
            case 'uncommon': return 'rarity-uncommon'
            case 'rare': return 'rarity-rare'
            case 'epic': return 'rarity-epic'
            case 'legendary': return 'rarity-legendary'
            default: return 'rarity-common'
        }
    }

    const getRaritySlotClass = (rarity: string) => {
        switch (rarity) {
            case 'common': return 'bg-gray-600 border-gray-500'
            case 'uncommon': return 'bg-green-600 border-green-500'
            case 'rare': return 'bg-blue-600 border-blue-500'
            case 'epic': return 'bg-purple-600 border-purple-500'
            case 'legendary': return 'bg-yellow-600 border-yellow-500'
            default: return 'bg-gray-600 border-gray-500'
        }
    }

    const getRandomCount = (itemId: number) => {
        // 임의로 등록 개수 생성 (실제로는 API에서 가져와야 함)
        const counts = [0, 1, 2, 3, 5, 8, 12, 15, 20, 25, 30, 50, 100]
        return counts[itemId % counts.length]
    }

    return (
        <div className="min-h-screen p-4 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900">
            <div className="relative max-w-7xl mx-auto">
                {/* 배경 효과 */}
                <div className="absolute inset-0 bg-black opacity-10 scan-line"></div>

                {/* 헤더 */}
                <div className="relative mb-6">
                    <h1 className="text-2xl text-amber-400 mb-2 font-['Press_Start_2P']">▶ CODEX SYSTEM</h1>
                    <div className="text-xs text-gray-400">Discover and learn about all items in the world</div>
                </div>

                {/* 메인 도감 컨테이너 */}
                <div className="relative flex gap-4">

                    {/* 왼쪽 페이지 (아이템 목록) */}
                    <div className="flex-1">
                        <div className="book-page p-6 h-[600px] overflow-y-auto">
                            <h2 className="text-lg text-amber-800 mb-4 text-center font-['Press_Start_2P']">▶ ITEM CATALOG</h2>

                            {/* 카테고리 탭 */}
                            <div className="flex gap-2 mb-4 justify-center">
                                <button
                                    className={`pixel-button px-3 py-1 text-[8px] font-bold transition-colors ${currentCategory === 'material'
                                            ? 'bg-amber-600 text-white'
                                            : 'bg-gray-600 text-gray-300'
                                        }`}
                                    onClick={() => setCurrentCategory('material')}
                                >
                                    MATERIALS
                                </button>
                                <button
                                    className={`pixel-button px-3 py-1 text-[8px] font-bold transition-colors ${currentCategory === 'equipment'
                                            ? 'bg-amber-600 text-white'
                                            : 'bg-gray-600 text-gray-300'
                                        }`}
                                    onClick={() => setCurrentCategory('equipment')}
                                >
                                    EQUIPMENT
                                </button>
                            </div>

                            {/* 로딩 상태 */}
                            {isLoading && (
                                <div className="text-center py-8">
                                    <div className="text-amber-600 text-sm">Loading items...</div>
                                </div>
                            )}

                            {/* 에러 상태 */}
                            {error && (
                                <div className="text-center py-8">
                                    <div className="text-red-600 text-sm">Failed to load items</div>
                                </div>
                            )}

                            {/* 아이템 그리드 - 항상 5개씩 */}
                            {!isLoading && !error && (
                                <div className="grid grid-cols-5 gap-2">
                                    {currentItems.map((item) => (
                                    <div
                                        key={item.id}
                                        className={`item-card p-2 aspect-square flex flex-col items-center justify-center cursor-pointer transition-all border-2 ${item.discovered ? 'discovered' : 'undiscovered'
                                            } ${selectedItem?.id === item.id ? 'selected' : ''} ${item.discovered ? getRaritySlotClass(item.rarity) : 'bg-gray-700 border-gray-600'}`}
                                        onClick={() => handleItemClick(item)}
                                    >
                                        {item.discovered ? (
                                            <>
                                                <div className="w-full h-3/4 flex items-center justify-center overflow-hidden mb-1">
                                                    {item.imageUrl ? (
                                                        <img 
                                                            src={item.imageUrl} 
                                                            alt={item.name} 
                                                            className="w-full h-full object-cover rounded"
                                                        />
                                                    ) : (
                                                        <div className={`w-full h-full ${getRarityClass(item.rarity)} rounded flex items-center justify-center`}>
                                                            <span className="text-white font-bold text-xs">{item.shortName}</span>
                                                        </div>
                                                    )}
                                                </div>
                                                <div className="text-[6px] text-white text-center leading-tight mb-1">{item.name}</div>
                                                <div className="w-full mb-1">
                                                    {/* 진행바 배경 */}
                                                    <div className="w-full h-1 bg-gray-700 rounded-full overflow-hidden">
                                                        {/* 진행바 채우기 */}
                                                        <div 
                                                            className="h-full bg-gradient-to-r from-amber-400 to-amber-600 rounded-full transition-all duration-300"
                                                            style={{ 
                                                                width: `${Math.min((getRandomCount(item.id) / 5) * 100, 100)}%` 
                                                            }}
                                                        ></div>
                                                    </div>
                                                </div>
                                                <div className="text-[5px] text-amber-300 text-center font-bold">
                                                    [{getRandomCount(item.id)}/5]
                                                </div>
                                            </>
                                        ) : (
                                            <>
                                                <div className="w-full h-3/4 silhouette rounded mb-1 flex items-center justify-center overflow-hidden">
                                                    {/* 미발견 아이템은 기본적으로 ? 표시 */}
                                                </div>
                                                <div className="text-[6px] text-gray-400 text-center leading-tight mb-1">???</div>
                                                <div className="w-full mb-1">
                                                    <div className="w-full h-1 bg-gray-700 rounded-full overflow-hidden">
                                                        <div className="h-full bg-gray-600 rounded-full" style={{ width: '0%' }}></div>
                                                    </div>
                                                </div>
                                                <div className="text-[5px] text-gray-500 text-center font-bold">
                                                    [0/5]
                                                </div>
                                            </>
                                        )}
                                    </div>
                                ))}
                                </div>
                            )}
                        </div>
                    </div>
                </div>

                {/* 페이지 네비게이션 */}
                <div className="relative mt-6 flex justify-center gap-4">
                    <button
                        className="pixel-button bg-amber-600 text-white px-4 py-2 text-xs font-bold hover:bg-amber-500 disabled:opacity-50 disabled:cursor-not-allowed"
                        onClick={() => setCurrentPage(prev => Math.max(1, prev - 1))}
                        disabled={currentPage === 1}
                    >
                        [ PREV PAGE ]
                    </button>
                    <div className="pixel-border bg-amber-100 px-4 py-2">
                        <span className="text-xs text-amber-800 font-bold">PAGE {currentPage} / {totalPages}</span>
                    </div>
                    <button
                        className="pixel-button bg-amber-600 text-white px-4 py-2 text-xs font-bold hover:bg-amber-500 disabled:opacity-50 disabled:cursor-not-allowed"
                        onClick={() => setCurrentPage(prev => Math.min(totalPages, prev + 1))}
                        disabled={currentPage === totalPages}
                    >
                        [ NEXT PAGE ]
                    </button>
                </div>

                {/* 하단 상태바 */}
                <div className="relative mt-6">
                    <div className="bg-black pixel-border p-3">
                        <div className="text-xs text-green-400 flex justify-between">
                            <span>
                                SELECTED: {selectedItem?.name || 'NONE'} | DISCOVERED: {filteredItems.filter((item: CodexItem) => item.discovered).length}/{filteredItems.length} ITEMS
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