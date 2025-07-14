# 🎮 AlhamPlay - Web3 RPG Game

**Web3 기반 RPG 게임**으로, 블록체인 기술을 활용한 몰입형 게임 경험을 제공합니다.

## 🌟 프로젝트 개요

AlhamPlay는 Next.js 기반의 웹3 RPG 게임으로, 사용자의 게임 진행 상황과 아이템이 블록체인에 기록되는 혁신적인 게임 시스템을 제공합니다.

### 🎯 주요 특징

- **Web3 통합**: NFT 기반 캐릭터 시스템
- **블록체인 기록**: 맵의 자원 및 아이템이 블록체인에 기록
- **자동 전투 시스템**: 카드 순서 선택을 통한 전략적 전투
- **탐험 시스템**: 10x10 맵에서의 자원 채집 및 탐험
- **성장 시스템**: 체력, 마나, 힘, 지력, 민첩, 행운 스탯 시스템

## 🚀 기술 스택

### Frontend
- **Next.js 15.3.1** - React 기반 프레임워크
- **TypeScript** - 타입 안정성
- **Tailwind CSS** - 스타일링
- **Zustand** - 상태 관리
- **React Query** - 서버 상태 관리
- **Framer Motion** - 애니메이션
- **Phaser.js** - 게임 엔진

### Web3 & Blockchain
- **Immutable X** - NFT 및 블록체인 통합
- **Axios** - API 통신

## 🎮 게임 시스템

### 캐릭터 시스템
- **NFT 기반**: 신규 생성 시 NFT로 생성
- **스탯 시스템**: 체력, 마나, 힘, 지력, 민첩, 행운
- **성장 시스템**: 게임 진행에 따른 스탯 상승

### 전투 시스템
- **자동 진행**: 카드 순서 선택 후 자동 전투
- **카드 시스템**: 최대 10개 카드 선택 (특성에 따라 20개까지)
- **자원 소모**: 체력/마나를 소모하여 카드 사용

### 맵 시스템
- **탐험맵**: 탐험, 채집, 이동, 맵이동 기능
- **평화맵**: 여관, 대장간, 시장, 모험가 사무소
- **10x10 영역**: 동서남북으로 연결된 맵 구조

### 아이템 시스템
- **코덱 시스템**: 아이템 정보 및 수집 시스템
- **인벤토리**: 재료 및 장비 관리
- **크래프트**: 아이템 제작 시스템

## 📁 프로젝트 구조

```
playground_front/
├── src/
│   ├── app/                    # Next.js App Router
│   │   ├── battle/            # 전투 시스템
│   │   ├── character/         # 캐릭터 생성
│   │   ├── codex/            # 아이템 도감
│   │   ├── field/            # 필드/맵 시스템
│   │   └── survival/         # 생존 시스템
│   ├── components/            # React 컴포넌트
│   │   ├── battle/           # 전투 관련 컴포넌트
│   │   ├── character/        # 캐릭터 관련 컴포넌트
│   │   ├── codex/           # 도감 관련 컴포넌트
│   │   ├── craft/           # 크래프트 시스템
│   │   ├── inventory/       # 인벤토리 시스템
│   │   ├── map/             # 맵 관련 컴포넌트
│   │   └── ui/              # UI 컴포넌트
│   ├── hooks/               # 커스텀 훅
│   ├── store/               # Zustand 상태 관리
│   └── types/               # TypeScript 타입 정의
├── public/                  # 정적 파일
└── package.json
```

## 🛠️ 설치 및 실행

### 필수 요구사항
- Node.js 18.0.0 이상
- npm 또는 yarn

### 설치
```bash
# 저장소 클론
git clone [repository-url]
cd playground_front

# 의존성 설치
npm install
# 또는
yarn install
```

### 개발 서버 실행
```bash
# 개발 모드 실행 (Turbopack 사용)
npm run dev
# 또는
yarn dev
```

브라우저에서 [http://localhost:3000](http://localhost:3000)을 열어 게임을 확인하세요.

### 빌드
```bash
# 프로덕션 빌드
npm run build
# 또는
yarn build

# 프로덕션 서버 실행
npm run start
# 또는
yarn start
```

## 🎯 주요 기능

### 🗺️ 맵 시스템
- **탐험맵**: 자원 채집 및 탐험
- **평화맵**: 상점 및 휴식 기능
- **좌표 기반**: 10x10 영역 내 정확한 위치 기반 활동

### ⚔️ 전투 시스템
- **카드 선택**: 전략적 카드 순서 선택
- **자동 진행**: 선택된 카드에 따른 자동 전투
- **자원 관리**: 체력/마나 소모 시스템

### 📚 코덱 시스템
- **아이템 수집**: 발견한 아이템 정보 기록
- **상세 정보**: 아이템 설명, 획득 방법, 크래프트 레시피
- **진행도 추적**: 아이템별 수집 진행도 표시

### 🎒 인벤토리 시스템
- **재료 관리**: 채집한 재료 보관
- **장비 관리**: 장착 가능한 장비 관리
- **크래프트**: 재료를 이용한 아이템 제작

## 🔧 개발 가이드

### 상태 관리
- **Zustand**: 전역 상태 관리
- **React Query**: 서버 상태 관리
- **커스텀 훅**: 재사용 가능한 로직

### 스타일링
- **Tailwind CSS**: 유틸리티 퍼스트 CSS
- **커스텀 CSS**: 게임 특화 스타일
- **반응형 디자인**: 다양한 화면 크기 지원

### 타입 안정성
- **TypeScript**: 엄격한 타입 체크
- **인터페이스 정의**: 명확한 데이터 구조
- **타입 가드**: 런타임 타입 검증

## 🚧 개발 중인 기능

- [ ] 블록체인 통합 (Immutable X)
- [ ] NFT 캐릭터 시스템
- [ ] 멀티플레이어 기능
- [ ] 추가 맵 및 콘텐츠
- [ ] 소셜 기능

## 📝 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.

## 🤝 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 문의

프로젝트에 대한 문의사항이 있으시면 이슈를 생성해 주세요.

---

**AlhamPlay** - Web3의 미래를 게임으로 경험하세요! 🎮✨
