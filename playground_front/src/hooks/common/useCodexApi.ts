import { useQuery } from '@tanstack/react-query'
import { EquipmentCollectionDTO, MaterialCollectionDTO, PlayerCollectionDTO } from '@/types/collection'
import { ItemRarity, ItemType } from '@/types/item'
import { axiosInstance } from './axiosInstance'

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

// API 함수들
export const codexApi = {
  
  // 모든 코덱스 아이템 가져오기 (실제 API 호출)
  getAllItems: async (): Promise<PlayerCollectionDTO> => {
    const response = await axiosInstance.get<PlayerCollectionDTO>('/collection')
    return response.data
  },

  // 특정 아이템 가져오기
  getItemById: async (id: number): Promise<CodexItem | null> => {
    const response = await axiosInstance.get<CodexItem>(`/api/codex/${id}`)
    return response.data
  },

  getMaterialItems: async (): Promise<MaterialCollectionDTO[]> => {
    const response = await axiosInstance.get<MaterialCollectionDTO[]>('/collection/material')
    return response.data
  },
  getEquipmentItems: async (): Promise<EquipmentCollectionDTO[]> => {
    const response = await axiosInstance.get<EquipmentCollectionDTO[]>('/collection/equipment')
    return response.data
  },

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

export const useCodexItemsByMaterial = (enabled: boolean = true) => {
  return useQuery({
    queryKey: ['codex', 'items', 'material'],
    queryFn: () => codexApi.getMaterialItems(),
    enabled: enabled, // 조건부 fetch
    staleTime: 10 * 60 * 1000, // 10분
    gcTime: 30 * 60 * 1000, // 30분
    refetchOnWindowFocus: false,
    refetchOnMount: false,
  })
}

export const useCodexItemsByEquipment = (enabled: boolean = true) => {
  return useQuery({
    queryKey: ['codex', 'items', 'equipment'],
    queryFn: () => codexApi.getEquipmentItems(),
    enabled: enabled, // 조건부 fetch
    staleTime: 10 * 60 * 1000, // 10분
    gcTime: 30 * 60 * 1000, // 30분
    refetchOnWindowFocus: false,
    refetchOnMount: false,
  })
}

