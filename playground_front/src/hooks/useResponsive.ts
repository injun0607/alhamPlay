import { useState, useEffect } from 'react'

export type Breakpoint = 'mobile' | 'mobile-lg' | 'tablet' | 'tablet-lg' | 'desktop' | 'desktop-lg' | 'game'

const breakpoints = {
  mobile: 480,
  'mobile-lg': 640,
  tablet: 768,
  'tablet-lg': 1024,
  desktop: 1280,
  'desktop-lg': 1536,
  game: 1920
}

export function useGameResponsive() {
  const [currentBreakpoint, setCurrentBreakpoint] = useState<Breakpoint>('mobile')
  const [shouldShowMobileUI, setShouldShowMobileUI] = useState(false)

  useEffect(() => {
    const handleResize = () => {
      const width = window.innerWidth
      let newBreakpoint: Breakpoint = 'mobile'

      if (width >= breakpoints.game) {
        newBreakpoint = 'game'
      } else if (width >= breakpoints['desktop-lg']) {
        newBreakpoint = 'desktop-lg'
      } else if (width >= breakpoints.desktop) {
        newBreakpoint = 'desktop'
      } else if (width >= breakpoints['tablet-lg']) {
        newBreakpoint = 'tablet-lg'
      } else if (width >= breakpoints.tablet) {
        newBreakpoint = 'tablet'
      } else if (width >= breakpoints['mobile-lg']) {
        newBreakpoint = 'mobile-lg'
      } else {
        newBreakpoint = 'mobile'
      }

      const newShouldShowMobileUI = width < breakpoints.tablet

      setCurrentBreakpoint(newBreakpoint)
      setShouldShowMobileUI(newShouldShowMobileUI)
    }

    // 초기 실행
    handleResize()

    window.addEventListener('resize', handleResize)
    return () => window.removeEventListener('resize', handleResize)
  }, [])

  return { currentBreakpoint, shouldShowMobileUI }
} 