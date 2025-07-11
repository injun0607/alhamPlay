'use client'

import { useParams, useRouter } from 'next/navigation'
import '../../css/codex.css'
import { useCodexItem, CodexItem } from '@/hooks/common/useCodexApi'

export default function CodexDetailPage() {
  const params = useParams()
  const router = useRouter()
  const itemId = parseInt(params.id as string)

  // React Query로 아이템 데이터 가져오기
  const { data: item, isLoading, error } = useCodexItem(itemId)

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

  if (isLoading) {
    return (
      <div className="min-h-screen p-4 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 flex items-center justify-center">
        <div className="text-amber-400">Loading item details...</div>
      </div>
    )
  }

  if (error || !item) {
    return (
      <div className="min-h-screen p-4 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 flex items-center justify-center">
        <div className="text-red-400">아이템을 찾을 수 없습니다.</div>
      </div>
    )
  }

  return (
    <div className="min-h-screen p-4 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900">
      <div className="relative max-w-4xl mx-auto">
        {/* 배경 효과 */}
        <div className="absolute inset-0 bg-black opacity-10 scan-line"></div>
        
        {/* 헤더 */}
        <div className="relative mb-6">
          <div className="flex items-center gap-4 mb-4">
            <button 
              onClick={() => router.back()}
              className="pixel-button bg-amber-600 text-white px-4 py-2 text-xs font-bold hover:bg-amber-500"
            >
              [ BACK ]
            </button>
            <h1 className="text-2xl text-amber-400 font-['Press_Start_2P']">▶ ITEM DETAILS</h1>
          </div>
        </div>
        
        {/* 메인 컨테이너 */}
        <div className="relative">
          <div className="book-page p-6">
            <div className="space-y-6">
              {/* 아이템 이미지 및 기본 정보 */}
              <div className="text-center">
                <div className={`w-24 h-24 ${getRarityClass(item.rarity)} rounded mx-auto mb-4 flex items-center justify-center`}>
                  {/* 아이템 이미지 */}
                  <span className="text-white font-bold text-2xl">{item.shortName}</span>
                </div>
                <div className="text-xl text-amber-800 font-bold mb-2">{item.name}</div>
                <div className="text-sm text-amber-600">{item.rarity.toUpperCase()} {item.type.toUpperCase()}</div>
              </div>
              
              {/* 기본 정보 */}
              <div className="space-y-3">
                <div className="text-sm text-amber-800 font-bold">▶ BASIC INFO</div>
                <div className="text-xs text-amber-700 space-y-1">
                  <div>Type: {item.type.charAt(0).toUpperCase() + item.type.slice(1)}</div>
                  <div>Rarity: {item.rarity.charAt(0).toUpperCase() + item.rarity.slice(1)}</div>
                  <div>Weight: {item.weight} kg</div>
                  <div>Value: {item.value} gold</div>
                  <div>Stack Size: {item.stackSize}</div>
                </div>
              </div>
              
              {/* 설명 */}
              <div className="space-y-3">
                <div className="text-sm text-amber-800 font-bold">▶ DESCRIPTION</div>
                <div className="text-xs text-amber-700 leading-relaxed">
                  {item.description}
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
        
        {/* 하단 상태바 */}
        <div className="relative mt-6">
          <div className="bg-black pixel-border p-3">
            <div className="text-xs text-green-400 flex justify-between">
              <span>VIEWING: {item.name} | ID: {item.id}</span>
              <span className="blink">READY</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
} 