# 게임 반응형 시스템 가이드

## 브레이크포인트 정의

```typescript
// tailwind.config.js에 정의된 브레이크포인트
'mobile': '320px',      // 작은 모바일
'mobile-lg': '480px',    // 큰 모바일  
'tablet': '768px',       // 태블릿
'tablet-lg': '1024px',   // 큰 태블릿
'desktop': '1280px',     // 데스크톱
'desktop-lg': '1440px',  // 큰 데스크톱
'game': '1920px',        // 게임 전용 (풀HD)
```

## 1. ResponsiveContainer 사용법

```tsx
import { ResponsiveContainer } from '@/components/ui/ResponsiveContainer'

// 기본 사용법
<ResponsiveContainer maxWidth="desktop">
  <div>콘텐츠</div>
</ResponsiveContainer>

// 최대 너비 옵션
maxWidth: 'mobile' | 'mobile-lg' | 'tablet' | 'tablet-lg' | 'desktop' | 'desktop-lg' | 'game'
```

## 2. ResponsiveGrid 사용법

```tsx
import { ResponsiveGrid } from '@/components/ui/ResponsiveContainer'

// 기본 사용법 (자동으로 반응형 그리드)
<ResponsiveGrid>
  <div>아이템 1</div>
  <div>아이템 2</div>
</ResponsiveGrid>

// 커스텀 컬럼 설정
<ResponsiveGrid 
  cols={{
    mobile: 1,        // 모바일: 1열
    tablet: 2,        // 태블릿: 2열
    desktop: 3,       // 데스크톱: 3열
    game: 4           // 게임: 4열
  }}
>
  <div>아이템 1</div>
  <div>아이템 2</div>
  <div>아이템 3</div>
</ResponsiveGrid>
```

## 3. ResponsiveText 사용법

```tsx
import { ResponsiveText } from '@/components/ui/ResponsiveContainer'

// 기본 사용법 (자동으로 반응형 텍스트 크기)
<ResponsiveText>
  게임 타이틀
</ResponsiveText>

// 커스텀 텍스트 크기 설정
<ResponsiveText 
  sizes={{
    mobile: 'text-sm',
    tablet: 'text-lg', 
    desktop: 'text-2xl',
    game: 'text-4xl'
  }}
>
  게임 타이틀
</ResponsiveText>
```

## 4. useGameResponsive 훅 사용법

```tsx
import { useGameResponsive } from '@/hooks/useResponsive'

function MyComponent() {
  const { 
    currentBreakpoint, 
    shouldShowMobileUI, 
    shouldShowDesktopUI,
    getGridCols,
    getTextSize 
  } = useGameResponsive()

  return (
    <div>
      {/* 현재 브레이크포인트 표시 */}
      <p>현재: {currentBreakpoint}</p>
      
      {/* 조건부 렌더링 */}
      {shouldShowMobileUI() && <MobileUI />}
      {shouldShowDesktopUI() && <DesktopUI />}
      
      {/* 동적 그리드 컬럼 */}
      <div className={`grid grid-cols-${getGridCols()}`}>
        {/* 아이템들 */}
      </div>
      
      {/* 동적 텍스트 크기 */}
      <div className={getTextSize()}>
        텍스트
      </div>
    </div>
  )
}
```

## 5. Tailwind CSS 직접 사용법

```tsx
// 브레이크포인트별 클래스 적용
<div className="
  text-sm mobile-lg:text-base tablet:text-lg desktop:text-xl game:text-2xl
  grid-cols-1 mobile-lg:grid-cols-1 tablet:grid-cols-2 desktop:grid-cols-3 game:grid-cols-4
">
  콘텐츠
</div>
```

## 6. 실제 사용 예시

### 메인페이지 레이아웃
```tsx
// 2열 레이아웃 (캐릭터 정보 + 메뉴)
<ResponsiveGrid 
  cols={{
    mobile: 1,        // 모바일: 세로 배치
    tablet: 2,        // 태블릿부터: 2열 배치
    desktop: 2,
    game: 2
  }}
>
  <CharacterInfo />
  <MenuButtons />
</ResponsiveGrid>
```

### 필드 선택 페이지
```tsx
// 필드 카드 그리드
<ResponsiveGrid 
  cols={{
    mobile: 1,        // 모바일: 1열
    tablet: 2,        // 태블릿: 2열
    'tablet-lg': 3,   // 큰 태블릿: 3열
    desktop: 3,       // 데스크톱: 3열
    game: 4           // 게임: 4열
  }}
>
  {fields.map(field => <FieldCard key={field.id} field={field} />)}
</ResponsiveGrid>
```

### 텍스트 크기 조정
```tsx
// 게임 타이틀
<ResponsiveText 
  sizes={{
    mobile: 'text-xl',
    tablet: 'text-2xl',
    desktop: 'text-3xl',
    game: 'text-4xl'
  }}
  className="font-['Press_Start_2P'] text-amber-400"
>
  🎮 RPG 게임
</ResponsiveText>
```

## 7. 모바일 최적화 팁

1. **터치 친화적 버튼 크기**: 모바일에서는 최소 44px 높이
2. **간격 조정**: 모바일에서는 더 넓은 간격 사용
3. **텍스트 가독성**: 모바일에서는 더 큰 폰트 크기
4. **그리드 단순화**: 모바일에서는 1-2열로 제한

## 8. 게임 전용 최적화

1. **풀HD 지원**: 1920px 이상에서 최적화
2. **큰 화면 활용**: 더 많은 컬럼과 큰 텍스트
3. **고해상도 이미지**: 게임 모드에서 고해상도 에셋 사용

이 시스템을 사용하면 일관된 반응형 경험을 제공하면서도 각 디바이스에 최적화된 UI를 구현할 수 있습니다. 