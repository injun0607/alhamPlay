'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'
import { ResponsiveContainer, ResponsiveGrid, ResponsiveText } from '@/components/ui/ResponsiveContainer'
import { useGameResponsive } from '@/hooks/useResponsive'
import './globals.css'

export default function MainTownPage() {
    const router = useRouter()
    const [selectedMenu, setSelectedMenu] = useState<string | null>(null)
    const { currentBreakpoint} = useGameResponsive()

    // 캐릭터 정보 (임시 데이터)
    const characterInfo = {
        name: '모험가',
        stats: {
            atk: 25,
            def: 18,
            ste: 15,
            dex: 22,
            int: 12,
            luk: 8
        }
    }

    // 메뉴 정보
    const menus = [
        {
            id: 'gathering',
            name: '채집',
            description: '아이템을 수집하는 곳',
            path: '/field',
            color: 'from-green-600 to-green-800',
            hoverColor: 'hover:from-green-500 hover:to-green-700'
        },
        {
            id: 'battle',
            name: '전투',
            description: '뱀서류 게임으로 전투하는 곳',
            path: '/battle',
            color: 'from-red-600 to-red-800',
            hoverColor: 'hover:from-red-500 hover:to-red-700'
        },
        {
            id: 'codex',
            name: '도감',
            description: '수집한 아이템들을 확인하는 곳',
            path: '/codex',
            color: 'from-amber-600 to-amber-800',
            hoverColor: 'hover:from-amber-500 hover:to-amber-700'
        }
    ]

    const handleMenuClick = (menu: any) => {
        setSelectedMenu(menu.id)
        // 잠시 후 페이지 이동 (애니메이션 효과)
        setTimeout(() => {
            router.push(menu.path)
        }, 300)
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
                    <ResponsiveContainer maxWidth="desktop">
                        <ResponsiveText 
                            sizes={{
                                mobile: 'text-xl',
                                'mobile-lg': 'text-2xl',
                                tablet: 'text-2xl',
                                'tablet-lg': 'text-3xl',
                                desktop: 'text-3xl',
                                'desktop-lg': 'text-3xl',
                                game: 'text-4xl'
                            }}
                            className="font-['Press_Start_2P'] text-amber-400 text-center"
                        >
                            🎮 RPG 게임
                        </ResponsiveText>
                    </ResponsiveContainer>
                </div>

                {/* 메인 콘텐츠 */}
                <div className="flex-1 flex items-center justify-center p-8">
                    <ResponsiveContainer maxWidth="desktop">
                        <ResponsiveGrid 
                            cols={{
                                mobile: 1,
                                'mobile-lg': 1,
                                tablet: 2,
                                'tablet-lg': 2,
                                desktop: 2,
                                'desktop-lg': 2,
                                game: 2
                            }}
                            className="gap-6"
                        >
                            
                            {/* 왼쪽 - 캐릭터 스탯 정보 */}
                            <div className="bg-black bg-opacity-70 backdrop-blur-sm border-2 border-amber-600 rounded-lg p-6">
                                <ResponsiveText 
                                    sizes={{
                                        mobile: 'text-lg',
                                        'mobile-lg': 'text-xl',
                                        tablet: 'text-xl',
                                        'tablet-lg': 'text-2xl',
                                        desktop: 'text-2xl',
                                        'desktop-lg': 'text-2xl',
                                        game: 'text-3xl'
                                    }}
                                    className="font-['Press_Start_2P'] text-amber-400 text-center mb-6"
                                >
                                    👤 캐릭터 정보
                                </ResponsiveText>
                                
                                {/* 닉네임 */}
                                <div className="text-center mb-6">
                                    <ResponsiveText 
                                        sizes={{
                                            mobile: 'text-lg',
                                            'mobile-lg': 'text-xl',
                                            tablet: 'text-xl',
                                            'tablet-lg': 'text-2xl',
                                            desktop: 'text-2xl',
                                            'desktop-lg': 'text-2xl',
                                            game: 'text-3xl'
                                        }}
                                        className="font-bold text-white mb-2"
                                    >
                                        {characterInfo.name}
                                    </ResponsiveText>
                                </div>

                                {/* 스탯 정보 */}
                                <ResponsiveGrid 
                                    cols={{
                                        mobile: 2,
                                        'mobile-lg': 2,
                                        tablet: 2,
                                        'tablet-lg': 2,
                                        desktop: 2,
                                        'desktop-lg': 2,
                                        game: 2
                                    }}
                                    className="gap-4"
                                >
                                    <div className="text-center p-3 bg-gray-800 bg-opacity-50 rounded-lg">
                                        <div className="text-red-400 font-bold text-lg">ATK</div>
                                        <div className="text-white text-2xl font-bold">{characterInfo.stats.atk}</div>
                                    </div>
                                    <div className="text-center p-3 bg-gray-800 bg-opacity-50 rounded-lg">
                                        <div className="text-blue-400 font-bold text-lg">DEF</div>
                                        <div className="text-white text-2xl font-bold">{characterInfo.stats.def}</div>
                                    </div>
                                    <div className="text-center p-3 bg-gray-800 bg-opacity-50 rounded-lg">
                                        <div className="text-green-400 font-bold text-lg">STE</div>
                                        <div className="text-white text-2xl font-bold">{characterInfo.stats.ste}</div>
                                    </div>
                                    <div className="text-center p-3 bg-gray-800 bg-opacity-50 rounded-lg">
                                        <div className="text-yellow-400 font-bold text-lg">DEX</div>
                                        <div className="text-white text-2xl font-bold">{characterInfo.stats.dex}</div>
                                    </div>
                                    <div className="text-center p-3 bg-gray-800 bg-opacity-50 rounded-lg">
                                        <div className="text-purple-400 font-bold text-lg">INT</div>
                                        <div className="text-white text-2xl font-bold">{characterInfo.stats.int}</div>
                                    </div>
                                    <div className="text-center p-3 bg-gray-800 bg-opacity-50 rounded-lg">
                                        <div className="text-pink-400 font-bold text-lg">LUK</div>
                                        <div className="text-white text-2xl font-bold">{characterInfo.stats.luk}</div>
                                    </div>
                                </ResponsiveGrid>
                            </div>

                            {/* 오른쪽 - 메뉴 버튼들 */}
                            <div className="bg-black bg-opacity-70 backdrop-blur-sm border-2 border-amber-600 rounded-lg p-6">
                                <div className="space-y-4">
                                    {menus.map((menu) => (
                                        <div
                                            key={menu.id}
                                            className={`relative group cursor-pointer transform transition-all duration-300 hover:scale-105 ${
                                                selectedMenu === menu.id ? 'scale-105' : ''
                                            }`}
                                            onClick={() => handleMenuClick(menu)}
                                        >
                                            {/* 메뉴 버튼 */}
                                            <div className={`bg-gradient-to-r ${menu.color} ${menu.hoverColor} rounded-lg p-4 border-2 border-transparent group-hover:border-white transition-all duration-300`}>
                                                <div className="flex items-center justify-between">
                                                    <div>
                                                        <ResponsiveText 
                                                            sizes={{
                                                                mobile: 'text-lg',
                                                                'mobile-lg': 'text-xl',
                                                                tablet: 'text-xl',
                                                                'tablet-lg': 'text-xl',
                                                                desktop: 'text-xl',
                                                                'desktop-lg': 'text-xl',
                                                                game: 'text-2xl'
                                                            }}
                                                            className="font-bold text-white"
                                                        >
                                                            {menu.name}
                                                        </ResponsiveText>
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
                                                            className="text-white text-opacity-90 mt-1"
                                                        >
                                                            {menu.description}
                                                        </ResponsiveText>
                                                    </div>
                                                    <div className="text-2xl">
                                                        {menu.id === 'gathering' && '🌲'}
                                                        {menu.id === 'battle' && '⚔️'}
                                                        {menu.id === 'codex' && '📚'}
                                                    </div>
                                                </div>

                                                {/* 클릭 효과 */}
                                                <div className="absolute inset-0 bg-white bg-opacity-0 group-hover:bg-opacity-10 transition-all duration-300 rounded-lg"></div>
                                            </div>

                                            {/* 선택 효과 */}
                                            {selectedMenu === menu.id && (
                                                <div className="absolute -inset-1 bg-amber-400 rounded-lg animate-pulse opacity-50"></div>
                                            )}
                                        </div>
                                    ))}
                                </div>
                            </div>
                        </ResponsiveGrid>

                        {/* 하단 안내 */}
                        <div className="text-center mt-8">
                            <div className="bg-black bg-opacity-50 backdrop-blur-sm border border-amber-600 rounded-lg p-4 inline-block">
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
                                    className="text-amber-400"
                                >
                                    💡 원하는 메뉴를 클릭하여 이동하세요
                                </ResponsiveText>
                            </div>
                        </div>
                    </ResponsiveContainer>
                </div>

                {/* 하단 상태바 */}
                <div className="bg-black bg-opacity-50 backdrop-blur-sm border-t-2 border-amber-500 p-3">
                    <ResponsiveContainer maxWidth="desktop">
                        <div className="text-xs text-green-400 flex justify-between">
                            <span>
                                🏰 메인타운 | 👤 {characterInfo.name} | 💰 골드: 1,250
                            </span>
                            <span className="blink">
                                READY ({currentBreakpoint})
                            </span>
                        </div>
                    </ResponsiveContainer>
                </div>
            </div>
        </div>
    )
}
