# AlhamPlay

Kotlin/Spring Boot로 작성된 백엔드와 Next.js로 구현된 프론트엔드로 구성

## 프로젝트 구조

```
.
├── playground/         # Kotlin + Spring Boot 백엔드
└── playground_front/   # Next.js 프론트엔드
```

### 백엔드 디렉터리 구조

```
playground/
├── build.gradle.kts
└── src/
    └── main/
        ├── kotlin/kr/alham/playground/
        │   ├── config/        # Spring 설정
        │   ├── controller/    # REST API 컨트롤러
        │   ├── domain/        # 도메인 엔티티
        │   ├── repository/    # 데이터 저장소
        │   ├── service/       # 비즈니스 로직
        │   ├── loader/        # 초기 데이터 로딩
        │   └── ...            # common, event 등
        └── resources/
            ├── templates/
            └── json/
                ├── item/
                ├── recipe/
                └── locales/
```

### 프론트엔드 디렉터리 구조

```
playground_front/
├── package.json
└── src/
    ├── app/           # Next.js App Router 엔트리
    ├── components/    # UI 및 게임 컴포넌트
    │   ├── game/
    │   ├── map/
    │   ├── character/
    │   └── ...
    ├── store/         # Zustand 상태 관리
    ├── hooks/         # 커스텀 훅
    └── types/         # 타입 정의
```

## 요구 사항

- **Java 17** 및 Gradle Wrapper
- **Node.js 18** 이상 및 npm 또는 yarn

