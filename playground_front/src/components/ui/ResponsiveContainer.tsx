import React from 'react'
import { useGameResponsive } from '@/hooks/useResponsive'

interface ResponsiveContainerProps {
    children: React.ReactNode
    maxWidth?: 'mobile' | 'mobile-lg' | 'tablet' | 'tablet-lg' | 'desktop' | 'desktop-lg' | 'game'
    className?: string
}

export function ResponsiveContainer({ children, maxWidth = 'game', className = '' }: ResponsiveContainerProps) {
    const { currentBreakpoint } = useGameResponsive()

    // 최대 너비 클래스 결정
    const getMaxWidthClass = () => {
        switch (maxWidth) {
            case 'mobile': return 'max-w-[480px]'
            case 'mobile-lg': return 'max-w-[640px]'
            case 'tablet': return 'max-w-[768px]'
            case 'tablet-lg': return 'max-w-[1024px]'
            case 'desktop': return 'max-w-[1280px]'
            case 'desktop-lg': return 'max-w-[1536px]'
            case 'game': return 'max-w-[1920px]'
            default: return 'max-w-full'
        }
    }

    const maxWidthClass = getMaxWidthClass()

    return (
        <div className={`mx-auto ${maxWidthClass} ${className}`}>
            {children}
        </div>
    )
}

// 게임 전용 반응형 그리드 컴포넌트
interface ResponsiveGridProps {
    children: React.ReactNode
    className?: string
    cols?: {
        mobile?: number
        'mobile-lg'?: number
        tablet?: number
        'tablet-lg'?: number
        desktop?: number
        'desktop-lg'?: number
        game?: number
    }
}

export const ResponsiveGrid: React.FC<ResponsiveGridProps> = ({
    children,
    className = '',
    cols = {
        mobile: 3,
        'mobile-lg': 3,
        tablet: 5,
        'tablet-lg': 5,
        desktop: 5,
        'desktop-lg': 5,
        game: 5
    }
}) => {
    // Tailwind CSS가 인식할 수 있는 방식으로 클래스 생성
    const getGridClasses = () => {
        const mobile = cols.mobile || 1
        const mobileLg = cols['mobile-lg'] || mobile
        const tablet = cols.tablet || mobileLg
        const tabletLg = cols['tablet-lg'] || tablet
        const desktop = cols.desktop || tabletLg
        const desktopLg = cols['desktop-lg'] || desktop
        const game = cols.game || desktopLg

        // 각 브레이크포인트별 그리드 클래스 매핑
        const gridClassMap: { [key: number]: string } = {
            1: 'grid-cols-1',
            2: 'grid-cols-2',
            3: 'grid-cols-3',
            4: 'grid-cols-4',
            5: 'grid-cols-5',
            6: 'grid-cols-6',
            7: 'grid-cols-7',
            8: 'grid-cols-8',
            9: 'grid-cols-9',
            10: 'grid-cols-10',
            11: 'grid-cols-11',
            12: 'grid-cols-12'
        }

        return [
            gridClassMap[mobile],
            `mobile-lg:${gridClassMap[mobileLg]}`,
            `tablet:${gridClassMap[tablet]}`,
            `tablet-lg:${gridClassMap[tabletLg]}`,
            `desktop:${gridClassMap[desktop]}`,
            `desktop-lg:${gridClassMap[desktopLg]}`,
            `game:${gridClassMap[game]}`
        ].join(' ')
    }

    return (
        <div className={`grid gap-4 ${getGridClasses()} ${className}`}>
            {children}
        </div>
    )
}

interface ResponsiveTextProps {
    children: React.ReactNode
    sizes: {
        mobile: string
        'mobile-lg': string
        tablet: string
        'tablet-lg': string
        desktop: string
        'desktop-lg': string
        game: string
    }
    className?: string
}

export function ResponsiveText({ children, sizes, className = '' }: ResponsiveTextProps) {
    const { currentBreakpoint } = useGameResponsive()

    // 현재 브레이크포인트에 맞는 텍스트 크기 클래스 선택
    const getTextClass = () => {
        const textClass = sizes[currentBreakpoint] || sizes.mobile
        return textClass
    }

    const textClass = getTextClass()

    return (
        <div className={`${textClass} ${className}`}>
            {children}
        </div>
    )
} 