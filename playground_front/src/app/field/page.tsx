'use client'

import { useState,useEffect } from 'react'
import { useRouter } from 'next/navigation'

import '../globals.css'
import { useApi } from '@/hooks/common/useApi'
import { FieldDataDTO, FieldType } from '@/types/map'
import { useFieldStore } from '@/store/fieldStore'
import { CommonResponse } from '@/types/response'


export default function FieldSelectionPage() {
    const router = useRouter()

    const { post } = useApi<FieldDataDTO>();
    const { get } = useApi<CommonResponse<FieldDataDTO>>();
    const [checking, setChecking] = useState(true);

    const selectField = async (fieldType: FieldType) => {
        console.log(fieldType)
        try{
            const result = await post<FieldType>(`/field/select`,fieldType);
            if(result){
                router.push(`/field/select`)
            }
        }catch(error){
            console.error(error)
            // 에러 시 사용자에게 알림
            alert('필드 선택 중 오류가 발생했습니다.')
        }
    }

    useEffect(() => {    
        const fetchFieldData = async () => {
            const result = await get('/field/select');
            if(result.status === 'FOUND'){
                router.push(`/field/select`)
            } else{
                setChecking(false);
            }
        }
        fetchFieldData();
    }, [get]);
 
    // 필드 정보
    const fields = [
        {
            id: 'fire',
            name: '화염지역',
            description: '용암과 화염이 가득한 뜨거운 지역',
            type: 'VOLCANO' as FieldType,
            color: 'from-red-600 to-orange-600',
            hoverColor: 'hover:from-red-500 hover:to-orange-500',
            icon: '🔥'
        },
        {
            id: 'forest',
            name: '숲지역',
            description: '푸른 나무와 생명이 가득한 지역',
            type: 'FOREST' as FieldType,
            color: 'from-green-600 to-emerald-600',
            hoverColor: 'hover:from-green-500 hover:to-emerald-500',
            icon: '🌲'
        },
        {
            id: 'ice',
            name: '빙하지역',
            description: '얼음과 눈으로 뒤덮인 차가운 지역',
            type: 'GLACIER' as FieldType,
            color: 'from-blue-600 to-cyan-600',
            hoverColor: 'hover:from-blue-500 hover:to-cyan-500',
            icon: '❄️'
        },
        {
            id: 'desert',
            name: '사막지역',
            description: '모래와 열기로 가득한 건조한 지역',
            type: 'DESERT' as FieldType,
            color: 'from-yellow-600 to-amber-600',
            hoverColor: 'hover:from-yellow-500 hover:to-amber-500',
            icon: '🏜️'
        },
        {
            id: 'white',
            name: '해얀지역',
            description: '신비로운 빛이 가득한 순백의 지역',
            type: 'COAST' as FieldType,
            color: 'from-gray-600 to-slate-600',
            hoverColor: 'hover:from-gray-500 hover:to-slate-500',
            icon: '✨'
        }
    ]



    if(checking){
        return <div>Loading...</div>
    }

    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-900 via-purple-900 to-indigo-900 relative overflow-hidden">
            {/* 배경 효과 */}
            <div className="absolute inset-0 bg-black opacity-20"></div>
            <div className="absolute inset-0 bg-[url('/images/town-bg.jpg')] bg-cover bg-center opacity-30"></div>
            
            {/* 메인 컨테이너 */}
            <div className="relative z-10 min-h-screen flex flex-col">
                {/* 헤더 - 게임 타이틀 */}
                <div className="bg-black bg-opacity-50 backdrop-blur-sm border-b-2 border-amber-500 p-4">
                    <div className="max-w-6xl mx-auto">
                        <h1 className="text-3xl font-['Press_Start_2P'] text-amber-400 text-center">
                            🗺️ 필드 선택
                        </h1>
                    </div>
                </div>

                {/* 메인 콘텐츠 */}
                <div className="flex-1 flex items-center justify-center p-8">
                    <div className="max-w-6xl mx-auto w-full">
                        <div className="text-center mb-8">
                            <h2 className="text-2xl font-['Press_Start_2P'] text-white mb-4">
                                🎯 어느 지역으로 가시겠습니까?
                            </h2>
                            <p className="text-gray-300 text-lg">
                                각 지역마다 다른 아이템과 몬스터가 있습니다
                            </p>
                        </div>

                        {/* 필드 카드들 */}
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                            {fields.map((field) => (
                                <div
                                    key={field.id}
                                    className={`relative group cursor-pointer transform transition-all duration-300 hover:scale-105`}
                                    onClick={() => selectField(field.type)}
                                >
                                    {/* 카드 배경 */}
                                    <div className={`bg-gradient-to-br ${field.color} ${field.hoverColor} rounded-lg p-6 h-48 flex flex-col justify-between border-2 border-transparent group-hover:border-white transition-all duration-300`}>
                                        {/* 지역 아이콘 */}
                                        <div className="text-6xl text-center mb-4 group-hover:scale-110 transition-transform duration-300">
                                            {field.icon}
                                        </div>
                                        
                                        {/* 지역 정보 */}
                                        <div className="text-center">
                                            <h3 className="text-xl font-bold text-white mb-2">
                                                {field.name}
                                            </h3>
                                            <p className="text-white text-opacity-90 text-sm">
                                                {field.description}
                                            </p>
                                        </div>

                                        {/* 클릭 효과 */}
                                        <div className="absolute inset-0 bg-white bg-opacity-0 group-hover:bg-opacity-10 transition-all duration-300 rounded-lg"></div>
                                    </div>
                                </div>
                            ))}
                        </div>

                        {/* 하단 안내 */}
                        <div className="text-center mt-8">
                            <div className="bg-black bg-opacity-50 backdrop-blur-sm border border-amber-600 rounded-lg p-4 inline-block">
                                <p className="text-amber-400 text-sm">
                                    💡 원하는 지역을 클릭하여 이동하세요
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                {/* 하단 상태바 */}
                <div className="bg-black bg-opacity-50 backdrop-blur-sm border-t-2 border-amber-500 p-3">
                    <div className="max-w-6xl mx-auto">
                        <div className="text-xs text-green-400 flex justify-between">
                            <span>
                                🗺️ 필드 선택 | 👤 모험가 | 💰 골드: 1,250
                            </span>
                            <span className="blink">
                                READY
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
} 