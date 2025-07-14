import { useQuery } from '@tanstack/react-query'
import { EquipmentCollectionDTO, MaterialCollectionDTO } from '@/types/collection'
import { ItemRarity, ItemType } from '@/types/item'

export interface CodexItem {
  id: number
  name: string
  shortName: string
  rarity: ItemRarity
  type: ItemType
  discovered: boolean
  imageUrl?: string
  description?: string
  value?: number
  stackSize?: number
  acquisition?: string[]
  craftingRecipes?: Array<{
    name: string
    materials: string
  }>
  discoveryInfo?: {
    firstFound: string
    totalFound: number
  }
}

// 임시 데이터 (실제로는 API에서 가져올 예정)
const mockCodexItems: CodexItem[] = [
  { 
    id: 1, 
    name: '철광석', 
    shortName: '철', 
    rarity: 'COMMON', 
    type: 'MATERIAL', 
    discovered: true, 
    imageUrl: '/images/glacier_mat.png',
    description: 'A basic iron ore commonly found in mines and caves.',
    value: 10,
    stackSize: 99,
    acquisition: ['Mining in Iron Mines', 'Cave Exploration'],
    craftingRecipes: [
      { name: '철검', materials: '철광석 x2 + 크리스탈 x1' },
      { name: '철갑옷', materials: '철광석 x3 + 가죽 x1' }
    ],
    discoveryInfo: {
      firstFound: 'Day 1, Iron Mine #1',
      totalFound: 47
    }
  },
  { 
    id: 2, 
    name: '크리스탈', 
    shortName: '크', 
    rarity: 'UNCOMMON', 
    type: 'MATERIAL', 
    discovered: true, 
    imageUrl: '/images/glacier_tear.png',
    description: 'A crystalline material that glows with magical energy.',
    value: 25,
    stackSize: 50,
    acquisition: ['Crystal Caves', 'Magic Shops'],
    craftingRecipes: [
      { name: '마법검', materials: '크리스탈 x3 + 철광석 x1' }
    ],
    discoveryInfo: {
      firstFound: 'Day 3, Crystal Cave',
      totalFound: 23
    }
  },
  { 
    id: 3, 
    name: '마법석', 
    shortName: '마', 
    rarity: 'RARE', 
    type: 'MATERIAL', 
    discovered: true, 
    imageUrl: '/images/dryad_heart.png',
    description: 'A rare magical stone with powerful enchantments.',
    value: 100,
    stackSize: 25,
    acquisition: ['Ancient Ruins', 'Rare Monster Drops'],
    craftingRecipes: [
      { name: '마법지팡이', materials: '마법석 x1 + 나무 x2' }
    ],
    discoveryInfo: {
      firstFound: 'Day 5, Ancient Ruins',
      totalFound: 12
    }
  },
  { 
    id: 4, 
    name: '다이아몬드', 
    shortName: '다', 
    rarity: 'EPIC', 
    type: 'MATERIAL', 
    discovered: true, 
    imageUrl: '/images/salamandar_heart.png',
    description: 'An extremely rare diamond with legendary properties.',
    value: 500,
    stackSize: 10,
    acquisition: ['Deep Mines', 'Legendary Boss Drops'],
    craftingRecipes: [
      { name: '다이아몬드 검', materials: '다이아몬드 x1 + 철광석 x5' }
    ],
    discoveryInfo: {
      firstFound: 'Day 10, Deep Mine',
      totalFound: 5
    }
  },
  { 
    id: 5, 
    name: '신화석', 
    shortName: '신', 
    rarity: 'LEGENDARY', 
    type: 'MATERIAL', 
    discovered: true, 
    imageUrl: '/images/volcanic_stone.png',
    description: 'A mythical stone said to contain the power of the gods.',
    value: 1000,
    stackSize: 5,
    acquisition: ['Mythical Realm', 'God-tier Boss Drops'],
    craftingRecipes: [
      { name: '신화의 검', materials: '신화석 x1 + 다이아몬드 x3' }
    ],
    discoveryInfo: {
      firstFound: 'Day 20, Mythical Realm',
      totalFound: 2
    }
  },
  { 
    id: 6, 
    name: '나무', 
    shortName: '나', 
    rarity: 'COMMON', 
    type: 'MATERIAL', 
    discovered: true, 
    imageUrl: '/images/wood.png',
    description: 'Basic wood material for crafting.',
    value: 5,
    stackSize: 99,
    acquisition: ['Forest', 'Tree Cutting'],
    craftingRecipes: [
      { name: '나무 방패', materials: '나무 x3' }
    ],
    discoveryInfo: {
      firstFound: 'Day 1, Forest',
      totalFound: 89
    }
  },
  { 
    id: 7, 
    name: '돌', 
    shortName: '돌', 
    rarity: 'COMMON', 
    type: 'MATERIAL', 
    discovered: true, 
    imageUrl: '/images/stone_trans.PNG',
    description: 'Basic stone material for building.',
    value: 3,
    stackSize: 99,
    acquisition: ['Mountain', 'Stone Quarry'],
    craftingRecipes: [
      { name: '돌 벽', materials: '돌 x5' }
    ],
    discoveryInfo: {
      firstFound: 'Day 1, Mountain',
      totalFound: 156
    }
  },
  { 
    id: 8, 
    name: '가죽', 
    shortName: '가', 
    rarity: 'UNCOMMON', 
    type: 'MATERIAL', 
    discovered: true, 
    imageUrl: '/images/wood_trans.PNG',
    description: 'Leather material from animal hides.',
    value: 15,
    stackSize: 50,
    acquisition: ['Hunting', 'Animal Drops'],
    craftingRecipes: [
      { name: '가죽 갑옷', materials: '가죽 x2 + 철광석 x1' }
    ],
    discoveryInfo: {
      firstFound: 'Day 2, Hunting Grounds',
      totalFound: 34
    }
  },
  { id: 9, name: '미스터리 아이템 1', shortName: '?', rarity: 'COMMON', type: 'MATERIAL', discovered: false },
  { id: 10, name: '미스터리 아이템 2', shortName: '?', rarity: 'COMMON', type: 'MATERIAL', discovered: false },
  { id: 11, name: '미스터리 아이템 3', shortName: '?', rarity: 'COMMON', type: 'MATERIAL', discovered: false },
  { id: 12, name: '미스터리 아이템 4', shortName: '?', rarity: 'COMMON', type: 'MATERIAL', discovered: false },
  { id: 13, name: '미스터리 아이템 5', shortName: '?', rarity: 'COMMON', type: 'MATERIAL', discovered: false },
  { id: 14, name: '미스터리 아이템 6', shortName: '?', rarity: 'COMMON', type: 'MATERIAL', discovered: false },
  { id: 15, name: '미스터리 아이템 7', shortName: '?', rarity: 'COMMON', type: 'MATERIAL', discovered: false },
]

// API 함수들
export const codexApi = {
  // 모든 코덱스 아이템 가져오기
  getAllItems: async (): Promise<CodexItem[]> => {
    // 실제 API 호출 시뮬레이션
    await new Promise(resolve => setTimeout(resolve, 500))
    return mockCodexItems
  },

  // 특정 아이템 가져오기
  getItemById: async (id: number): Promise<CodexItem | null> => {
    await new Promise(resolve => setTimeout(resolve, 300))
    return mockCodexItems.find(item => item.id === id) || null
  },

  // 카테고리별 아이템 가져오기
  getItemsByCategory: async (category: ItemType): Promise<CodexItem[]> => {
    await new Promise(resolve => setTimeout(resolve, 400))
    return mockCodexItems.filter(item => item.type === category)
  }
}

// React Query 훅들
export const useCodexItems = (enabled: boolean = true) => {
  return useQuery({
    queryKey: ['codex', 'items'],
    queryFn: codexApi.getAllItems,
    enabled: enabled, // 조건부 fetch
    staleTime: 10 * 60 * 1000, // 10분
    gcTime: 30 * 60 * 1000, // 30분 (이전 cacheTime)
    refetchOnWindowFocus: false,
    refetchOnMount: false,
  })
}

export const useCodexItem = (id: number, enabled: boolean = true) => {
  return useQuery({
    queryKey: ['codex', 'item', id],
    queryFn: () => codexApi.getItemById(id),
    enabled: !!id && enabled, // id가 있고 enabled가 true일 때만 fetch
    staleTime: 15 * 60 * 1000, // 15분
    gcTime: 30 * 60 * 1000, // 30분
    refetchOnWindowFocus: false,
    refetchOnMount: false,
  })
}

export const useCodexItemsByCategory = (category: ItemType, enabled: boolean = true) => {
  return useQuery({
    queryKey: ['codex', 'items', category],
    queryFn: () => codexApi.getItemsByCategory(category),
    enabled: enabled, // 조건부 fetch
    staleTime: 10 * 60 * 1000, // 10분
    gcTime: 30 * 60 * 1000, // 30분
    refetchOnWindowFocus: false,
    refetchOnMount: false,
  })
} 