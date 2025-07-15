/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: ["class"],
  content: [
    './pages/**/*.{ts,tsx}',
    './components/**/*.{ts,tsx}',
    './app/**/*.{ts,tsx}',
    './src/**/*.{ts,tsx}',
  ],
  prefix: "",
  safelist: [
    // 기본 그리드 컬럼 클래스들
    'grid-cols-1', 'grid-cols-2', 'grid-cols-3', 'grid-cols-4', 'grid-cols-5', 'grid-cols-6',
    'grid-cols-7', 'grid-cols-8', 'grid-cols-9', 'grid-cols-10', 'grid-cols-11', 'grid-cols-12',
    // 반응형 그리드 클래스들 - 모든 브레이크포인트에 대해
    'sm:grid-cols-1', 'sm:grid-cols-2', 'sm:grid-cols-3', 'sm:grid-cols-4', 'sm:grid-cols-5', 'sm:grid-cols-6',
    'md:grid-cols-1', 'md:grid-cols-2', 'md:grid-cols-3', 'md:grid-cols-4', 'md:grid-cols-5', 'md:grid-cols-6',
    'lg:grid-cols-1', 'lg:grid-cols-2', 'lg:grid-cols-3', 'lg:grid-cols-4', 'lg:grid-cols-5', 'lg:grid-cols-6',
    'xl:grid-cols-1', 'xl:grid-cols-2', 'xl:grid-cols-3', 'xl:grid-cols-4', 'xl:grid-cols-5', 'xl:grid-cols-6',
    '2xl:grid-cols-1', '2xl:grid-cols-2', '2xl:grid-cols-3', '2xl:grid-cols-4', '2xl:grid-cols-5', '2xl:grid-cols-6',
    // 커스텀 브레이크포인트 그리드 클래스들
    'mobile:grid-cols-1','mobile:grid-cols-2','mobile:grid-cols-3','mobile:grid-cols-4','mobile:grid-cols-5','mobile:grid-cols-6',
    'mobile-lg:grid-cols-1','mobile-lg:grid-cols-2','mobile-lg:grid-cols-3','mobile-lg:grid-cols-4','mobile-lg:grid-cols-5','mobile-lg:grid-cols-6',
    'tablet:grid-cols-1','tablet:grid-cols-2','tablet:grid-cols-3','tablet:grid-cols-4','tablet:grid-cols-5','tablet:grid-cols-6',
    'tablet-lg:grid-cols-1','tablet-lg:grid-cols-2','tablet-lg:grid-cols-3','tablet-lg:grid-cols-4','tablet-lg:grid-cols-5','tablet-lg:grid-cols-6',
    'desktop:grid-cols-1','desktop:grid-cols-2','desktop:grid-cols-3','desktop:grid-cols-4','desktop:grid-cols-5','desktop:grid-cols-6',
    'desktop-lg:grid-cols-1','desktop-lg:grid-cols-2','desktop-lg:grid-cols-3','desktop-lg:grid-cols-4','desktop-lg:grid-cols-5','desktop-lg:grid-cols-6',
    'game:grid-cols-1','game:grid-cols-2','game:grid-cols-3','game:grid-cols-4','game:grid-cols-5','game:grid-cols-6',
  ],
  theme: {
    // 기본 브레이크포인트를 대체할 커스텀 스크린 정의
    screens: {
      mobile:      '480px',      // 모바일
      'mobile-lg': '640px',      // 큰 모바일
      tablet:      '768px',      // 태블릿
      'tablet-lg': '1024px',     // 큰 태블릿
      desktop:     '1280px',     // 데스크톱
      'desktop-lg':'1536px',     // 큰 데스크톱
      game:        '1920px',     // 게임 전용 (풀HD)
    },
    container: {
      center: true,
      padding: '2rem',
      screens: {
        '2xl': '1400px',
      },
    },
    extend: {
      colors: {
        border:     'hsl(var(--border))',
        input:      'hsl(var(--input))',
        ring:       'hsl(var(--ring))',
        background: 'hsl(var(--background))',
        foreground: 'hsl(var(--foreground))',
        primary: {
          DEFAULT:    'hsl(var(--primary))',
          foreground: 'hsl(var(--primary-foreground))',
        },
        secondary: {
          DEFAULT:    'hsl(var(--secondary))',
          foreground: 'hsl(var(--secondary-foreground))',
        },
        destructive: {
          DEFAULT:    'hsl(var(--destructive))',
          foreground: 'hsl(var(--destructive-foreground))',
        },
        muted: {
          DEFAULT:    'hsl(var(--muted))',
          foreground: 'hsl(var(--muted-foreground))',
        },
        accent: {
          DEFAULT:    'hsl(var(--accent))',
          foreground: 'hsl(var(--accent-foreground))',
        },
        popover: {
          DEFAULT:    'hsl(var(--popover))',
          foreground: 'hsl(var(--popover-foreground))',
        },
        card: {
          DEFAULT:    'hsl(var(--card))',
          foreground: 'hsl(var(--card-foreground))',
        },
      },
      borderRadius: {
        lg: 'var(--radius)',
        md: 'calc(var(--radius) - 2px)',
        sm: 'calc(var(--radius) - 4px)',
      },
      keyframes: {
        'accordion-down': {
          from: { height: '0' },
          to:   { height: 'var(--radix-accordion-content-height)' },
        },
        'accordion-up': {
          from: { height: 'var(--radix-accordion-content-height)' },
          to:   { height: '0' },
        },
      },
      animation: {
        'accordion-down': 'accordion-down 0.2s ease-out',
        'accordion-up':   'accordion-up   0.2s ease-out',
      },
    },
  },
  plugins: [require('tailwindcss-animate')],
};
