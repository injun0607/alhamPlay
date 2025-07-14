'use client'

import { CodexItem } from '@/hooks/common/useCodexApi'
import { ItemRarity } from '@/types/item'

interface CodexDetailPanelProps {
    item: CodexItem
    isVisible: boolean
    onClose: () => void
}

export default function CodexDetailPanel({ item, isVisible, onClose }: CodexDetailPanelProps) {
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

    if (!isVisible) return null

    return (
        <>
            {/* 배경 오버레이 - 외부 클릭 감지용 */}
            <div 
                className="fixed inset-0 bg-black bg-opacity-30 z-[999]"
                onClick={onClose}
            />
            
            {/* 상세 패널 */}
            <div className={`fixed top-20 left-0 right-0 bg-gradient-to-t from-gray-900 via-gray-800 to-gray-900 border-t-2 border-amber-600 transform transition-transform duration-500 ease-out detail-panel-active ${isVisible ? 'translate-y-0' : 'translate-y-full'}`} style={{ zIndex: 1000 }}>
                {/* 닫기 버튼 */}
                <div className="absolute top-2 right-4">
                    <button
                        onClick={onClose}
                        className="pixel-button bg-red-600 text-white px-3 py-1 text-xs font-bold hover:bg-red-500"
                    >
                        [ X ]
                    </button>
                </div>
                
                {/* 상세 정보 내용 */}
                <div className="p-6 max-h-[85vh] overflow-y-auto detail-scrollbar">
                    <div className="max-w-4xl mx-auto">
                        <div className="space-y-6">
                            {/* 아이템 이미지 */}
                            <div className="text-center">
                                <div className={`w-32 h-32 ${getRarityClass(item.rarity)} rounded mx-auto mb-4 flex items-center justify-center`}>
                                    {item.imageUrl ? (
                                        <img 
                                            src={item.imageUrl} 
                                            alt={item.name} 
                                            className="w-full h-full object-cover rounded"
                                        />
                                    ) : (
                                        <span className="text-white font-bold text-3xl">{item.shortName}</span>
                                    )}
                                </div>
                                <div className="text-xl text-amber-800 font-bold mb-2">{item.name}</div>
                                <div className="text-sm text-amber-600">{item.rarity} {item.type}</div>
                            </div>
                            
                            {/* 아이템 설명 */}
                            <div className="space-y-3">
                                <div className="text-sm text-amber-800 font-bold">▶ DESCRIPTION</div>
                                <div className="text-xs text-amber-700 leading-relaxed">
                                    {item.description}
                                </div>
                            </div>
                            
                            {/* 기본 정보 */}
                            <div className="space-y-3">
                                <div className="text-sm text-amber-800 font-bold">▶ BASIC INFO</div>
                                <div className="text-xs text-amber-700 space-y-1">
                                    <div>Type: {item.type + item.type.slice(1)}</div>
                                    <div>Rarity: {item.rarity + item.rarity.slice(1)}</div>
                                    <div>Value: {item.value} gold</div>
                                    <div>Stack Size: {item.stackSize}</div>
                                </div>
                            </div>
                            
                            {/* 획득 방법 */}
                            {item.acquisition && item.acquisition.length > 0 && (
                                <div className="space-y-3">
                                    <div className="text-sm text-amber-800 font-bold">▶ ACQUISITION</div>
                                    <div className="text-xs text-amber-700 space-y-1">
                                        {item.acquisition.map((method, index) => (
                                            <div key={index}>• {method}</div>
                                        ))}
                                    </div>
                                </div>
                            )}
                            
                            {/* 크래프트 레시피 */}
                            {item.craftingRecipes && item.craftingRecipes.length > 0 && (
                                <div className="space-y-3">
                                    <div className="text-sm text-amber-800 font-bold">▶ CRAFTING RECIPES</div>
                                    <div className="space-y-2">
                                        {item.craftingRecipes.map((recipe, index) => (
                                            <div key={index} className="pixel-border bg-amber-50 p-3">
                                                <div className="text-sm text-amber-800 font-bold">{recipe.name}</div>
                                                <div className="text-xs text-amber-700">{recipe.materials}</div>
                                            </div>
                                        ))}
                                    </div>
                                </div>
                            )}
                            
                            {/* 레벨별 해금 스킬 */}
                            <div className="space-y-3">
                                <div className="text-sm text-amber-800 font-bold">▶ UNLOCK SKILLS</div>
                                <div className="space-y-2">
                                    <div className="pixel-border bg-amber-50 p-3">
                                        <div className="text-sm text-amber-800 font-bold">Level 1 - Basic Crafting</div>
                                        <div className="text-xs text-amber-700">Unlock basic crafting recipes for this item</div>
                                    </div>
                                    <div className="pixel-border bg-amber-50 p-3">
                                        <div className="text-sm text-amber-800 font-bold">Level 3 - Advanced Crafting</div>
                                        <div className="text-xs text-amber-700">Unlock advanced crafting recipes and improved efficiency</div>
                                    </div>
                                    <div className="pixel-border bg-amber-50 p-3">
                                        <div className="text-sm text-amber-800 font-bold">Level 5 - Master Crafting</div>
                                        <div className="text-xs text-amber-700">Unlock master-level recipes and special bonuses</div>
                                    </div>
                                </div>
                            </div>
                            
                            {/* 발견 정보 */}
                            {item.discoveryInfo && (
                                <div className="space-y-3">
                                    <div className="text-sm text-amber-800 font-bold">▶ DISCOVERY</div>
                                    <div className="text-xs text-amber-700">
                                        First discovered: {item.discoveryInfo.firstFound}<br/>
                                        Total found: {item.discoveryInfo.totalFound} times
                                    </div>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
} 