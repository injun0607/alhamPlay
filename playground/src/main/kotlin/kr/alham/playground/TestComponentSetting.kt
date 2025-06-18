package kr.alham.playground

import jakarta.annotation.PostConstruct
import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardAttribute
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.EquipmentType
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.Material
import kr.alham.playground.repository.area.FieldAreaRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.card.CardService
import org.springframework.stereotype.Component

@Component
class TestComponentSetting(
    private val equipmentRepository: EquipmentRepository,
    private val materialRepository: MaterialRepository,
    private val cardService: CardService,
    private val fieldAreaRepository: FieldAreaRepository

) {
    @PostConstruct
    fun init() {
        /**
         * 숲재료
         */


        /**
         * 사막 재료
         */

        materialRepository.save(
            Material(
                name = "Twisted Vine Fiber",
                description = "고목을 휘감은 덩굴의 섬유. 튼튼하고 유연하여 방어구 바인딩에 적합하다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Amber Sap",
                description = "수백 년 된 나무에서 뽑은 호박 수액. 마력 전달의 매개로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Direwolf Hide",
                description = "숲의 포식자 디어울프의 가죽. 가볍지만 뛰어난 내구성을 지닌다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Oakheart Shard",
                description = "정령나무의 단단한 중심부 조각. 근거리 무기의 손잡이에 자주 쓰인다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Luminous Spores",
                description = "밤에 빛나는 버섯의 포자. 마법 장비에 빛나는 효과를 부여할 수 있다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Fernscale Bark",
                description = "양치식물의 껍질을 강화해 만든 재료. 가볍고 내구성이 뛰어나다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Wolfroot Tendon",
                description = "숲 늑대의 힘줄. 활 시위나 채찍 손잡이에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Elderseed Resin",
                description = "고목의 씨앗에서 추출한 끈적한 수지. 마법 전도에 좋다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Barkwyrm Spine",
                description = "나무 드래곤의 척추 조각. 검이나 창의 핵심 재료로 쓰인다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Sylvan Heart Core",
                description = "숲의 정령들이 지키던 생명의 핵. 전설급 장비의 중심 재료다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Dryleaf Bundle",
                description = "바싹 마른 나뭇잎 뭉치. 저급 가죽 재료 대체용으로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Twigknit Cord",
                description = "가는 가지를 꼬아 만든 끈. 장비 바인딩에 적합하다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Sporecap Dust",
                description = "버섯 모자의 가루. 마법 도료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Thornvine Stem",
                description = "가시넝쿨의 줄기. 적을 구속하는 마법에 응용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Timberwolf Fang",
                description = "숲의 사냥꾼 송곳니. 무기 재료로 사용 시 출혈 효과를 강화한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Glowlily Petal",
                description = "밤에 빛나는 꽃잎. 장비에 야간 시야 효과를 부여한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Sylvan Boneplate",
                description = "숲의 고대 짐승의 뼈 판. 강력한 방어구 재료다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Echoing Bark",
                description = "소리를 흡수하는 나무껍질. 은신 특화 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Ancestor’s Sap",
                description = "정령 나무의 피와 같은 수액. 회복 능력을 강화시킨다.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Verdant Sigil Stone",
                description = "숲의 고대 마법이 봉인된Ancient Thorn Core 인장석. 자연 속성 강화에 사용된다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Barkcloth Strip",
                description = "얇은 나무껍질을 가공한 천. 기본 방어구의 내피로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Dewdrop Pearl",
                description = "새벽 이슬이 맺힌 듯한 작은 수정. 마법 장비 기초 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Mushcap Sponge",
                description = "버섯 윗부분에서 채취한 스펀지. 재료 결합에 쓰이는 흡수 재료.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Vinesilk Thread",
                description = "덩굴에서 뽑아낸 실. 유연성과 강도가 우수하다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Thistle Dust",
                description = "가시풀을 갈아 만든 가루. 무기 마법 부여에 종종 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Barkling Jawbone",
                description = "숲의 소형 수호정령 턱뼈. 악세사리 제작에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Lifebloom Nectar",
                description = "희귀 식물의 꽃꿀. 회복 장비 제작에 적합하다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Willowfang Chip",
                description = "버드나무 야수의 이빨 조각. 마법 무기 강화에 사용된다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Dryad's Whisper Leaf",
                description = "드라이어드가 속삭였다는 전설의 잎사귀. 장비에 정령 효과를 부여한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.FOREST
            )
        )


        materialRepository.save(
            Material(
                name = "Spiritbark Resin",
                description = "정령나무에서만 얻을 수 있는 희귀 수지. 고급 마법 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Moonroot Shard",
                description = "달빛에 노출된 뿌리에서 추출한 결정. 야간 효과가 있다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Heartwood Branch",
                description = "숲의 정령 나무 핵에서 뽑아낸 가지. 전설급 지팡이 재료.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Elderleaf Emblem",
                description = "고대 숲의 권위를 상징하는 잎 문장. 장비에 자연 속성 최고 강화 효과.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.FOREST
            )
        )

        materialRepository.save(
            Material(
                name = "Songstone Fragment",
                description = "자연의 노래가 담긴 수정 조각. 회복/버프계 장비의 핵심 재료.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.FOREST
            )
        )

        /**
         * 빙하지역 재료
         */
        materialRepository.save(
            Material(
                name = "Frostscale Plate",
                description = "빙설 도마뱀의 비늘 판. 냉기 저항을 높이는 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Icicle Thread",
                description = "눈꽃에서 뽑아낸 실. 마법 로브에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Glacier Core Dust",
                description = "빙하의 중심에서 채취한 결정 가루. 냉기 속성 강화에 쓰인다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Snow Leopard Pelt",
                description = "차가운 대지를 지배하는 야수의 가죽. 은신에 특화된 장비에 사용된다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Shiverroot Bark",
                description = "추위에 견디는 마법 나무의 껍질. 방어구 내피로 이상적이다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Frostbirch Leaf",
                description = "냉기 속에서만 자라는 자작나무 잎. 약한 냉기 저항력을 지닌다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Icetooth Fragment",
                description = "빙설 늑대의 송곳니 조각. 날카로운 무기의 소재로 이상적이다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Frozen Marrow",
                description = "냉동된 야수의 골수. 물리 방어에 강한 성질을 띤다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Glacier Soul Dust",
                description = "빙하에 갇힌 정령의 재. 장비에 냉기 마법을 부여한다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Crownice Crystal",
                description = "얼음의 군주가 남긴 수정. 절대 냉기를 품고 있다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Cracked Iceflake",
                description = "얇은 얼음 조각. 낮은 등급 냉기 장비 재료.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Snowsilt Cloth",
                description = "눈에서 짜낸 섬유. 추위 방지 효과가 있다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Woolly Beast Fur",
                description = "빙설지대 야수의 털. 방한복 제작에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Chillglass Bead",
                description = "차가운 유리 결정. 마법 아이템에 주로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Frozen Spine Shard",
                description = "냉기 짐승의 척추 조각. 방어구에 강한 냉기 저항을 부여한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Icewrought Resin",
                description = "얼어붙은 나무 수액. 장비 표면을 단단하게 코팅한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Glacial Wind Core",
                description = "빙하 속 바람의 핵. 속도 증가 장비 제작에 쓰인다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Cryo Sigil Dust",
                description = "냉기 룬의 잔해. 강력한 마법 장비 강화용이다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Silent Blizzard Thread",
                description = "소리 없이 휘몰아치는 눈보라에서 채취한 실. 은신 장비에 적합하다.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Frozen Eternity Core",
                description = "시간조차 멈춘 얼음의 결정체. 영구 냉기 효과를 지닌다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Snowhare Fur",
                description = "눈 토끼의 따뜻한 털. 방한 장비의 내피로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Icicle Fragment",
                description = "뾰족한 고드름 조각. 기본 냉기 속성 장비에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Frostnut Husk",
                description = "냉기 식물의 외피. 마법 재료 보조용.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Chillhide Strip",
                description = "차가운 짐승 가죽을 얇게 벗겨낸 조각. 갑옷 보강용.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Permafrost Thread",
                description = "녹지 않는 실. 마법 로브 강화용으로 이상적이다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Frostshard Dust",
                description = "분쇄된 얼음 수정. 마법 속성 부여에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Snowwraith Eye",
                description = "설령의 눈동자. 냉기 속성 극대화에 필수.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Chillfang Chip",
                description = "빙설 표범의 이빨 파편. 날붙이에 빙결 효과 부여.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Cryogenic Oil",
                description = "냉기 유지용 기름. 장비의 온도를 조절한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Wendigo Spine Shard",
                description = "빙설 괴물의 척추 조각. 방어구에 광역 냉기 저항 부여.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Frozen Time Echo",
                description = "빙결된 시간의 잔재. 시간 지연/방어 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Moonice Crystal",
                description = "달빛을 반사하는 얼음 수정. 회복+냉기 하이브리드 장비용.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Crownshard of Frostmaiden",
                description = "냉기의 여왕이 남긴 왕관 조각. 마법 집중 속성을 부여한다.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Absolute Zero Core",
                description = "절대영도의 핵심. 모든 생명체의 움직임을 멈추게 할 수 있다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.GLACIER
            )
        )

        materialRepository.save(
            Material(
                name = "Aurorafrost Gem",
                description = "오로라의 얼음에서 탄생한 보석. 냉기 속성의 최고 등급 재료.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.GLACIER
            )
        )

        /**
         * 화산 지역 재료
         */
        materialRepository.save(
            Material(
                name = "Charstone Ore",
                description = "불의 심장에서 채굴한 암석. 무기 강화에 필수적이다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Obsidian Flake",
                description = "화산암의 얇은 조각. 날붙이 무기에 치명적인 예리함을 부여한다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Smoldering Ashdust",
                description = "끊임없이 연기 나는 재. 화염 마법 부여용으로 인기다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Flamebeast Tendon",
                description = "화염 짐승의 힘줄. 활과 채찍 같은 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Cinderhide",
                description = "불에 그을린 짐승의 가죽. 열에 강하고 유연하다.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Coalcrack Pebble",
                description = "갈라진 석탄 조각. 기본적인 화속성 부여 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Firebeast Horn Chip",
                description = "화염 야수의 뿔 조각. 강도 높은 장비 강화에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Smeltcore Oil",
                description = "용광로 바닥에서 추출된 오일. 금속 가공의 핵심이다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Volcanic Emberheart",
                description = "화산 심장에서 꺼낸 불덩어리. 공격 속성에 불을 더한다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Infernal Spine Segment",
                description = "지옥 불꽃 속 생물의 척추. 고대 장비에 쓰이는 귀중한 파편.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Charred Husk Fragment",
                description = "불에 탄 생물의 잔해. 저급 장비의 연료나 부품으로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Smokestone Dust",
                description = "화산재가 응결된 가루. 연막 효과 장비에 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Ironblaze Scale",
                description = "불속성 도마뱀의 비늘. 내구성이 뛰어나며 공격력을 높인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Fumehide Scrap",
                description = "유황가스에 그을린 가죽. 방어구의 내화성 강화를 도와준다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Flametail Whipcord",
                description = "불꼬리 생물의 신경줄기. 채찍류 무기 제작에 활용된다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Pyrocrystal Core",
                description = "마그마 결정의 심장부. 화염 속성 장비의 핵심 재료.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Inferno Chimera Fang",
                description = "지옥불 키메라의 송곳니. 공격 장비에 속성 폭발 효과를 부여한다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Eruption Stone Scale",
                description = "분화하는 화산석에서 얻은 비늘. 방어구에 충격 저항을 더한다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Ashen Phoenix Feather",
                description = "불사조의 재에서 얻은 깃털. 착용 장비에 회생 효과를 부여한다.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Molten Crown Fragment",
                description = "화염 군주의 왕관 파편. 전설 무기에 불의 의지를 부여한다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Ashen Horn Chip",
                description = "불에 그을린 짐승의 뿔 조각. 저급 무기 장식용으로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Lavasoot Lump",
                description = "용암 가루가 뭉쳐 생긴 덩어리. 기본 내열 강화 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Crackstone Flake",
                description = "지각 균열에서 채취한 석편. 방어구 강화에 소량 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Emberworm Scale",
                description = "작은 화염 벌레의 비늘. 장비에 열기 전달 효과를 부여한다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Firefang Residue",
                description = "불속성 생물의 이빨 잔해. 검의 칼날 마법 도료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Sulfurhide Strip",
                description = "유황 연기 속에서 채취한 가죽 조각. 방독 효과를 제공한다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Blazecore Fragment",
                description = "불의 심장이라 불리는 결정의 일부. 장비에 마나 연소 속성을 부여한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Flare Beetle Shell",
                description = "폭발성 곤충의 등껍질. 공격 장비 강화에 사용된다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Molten Vein Fiber",
                description = "용암이 흐르는 광맥에서 채취한 섬유. 유연성과 내구성이 뛰어나다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Inferno Drake Claw",
                description = "화염 드레이크의 발톱. 파괴력 강화용 고급 재료.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Obsidian Core Chunk",
                description = "용암에서 응결된 핵심 암석. 방어구에 불반사 효과를 부여한다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Volcanite Thread",
                description = "화염정령의 힘이 깃든 실. 고열에도 끄떡없는 마법 재료.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Blazing Crown Shard",
                description = "화염 군주의 왕관 파편. 착용 시 일정 확률로 화염구 발동.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Hellfire Sigil",
                description = "지옥불의 상징 문양. 전설급 무기와 방어구에 불타는 오라를 부여한다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.VOLCANO
            )
        )

        materialRepository.save(
            Material(
                name = "Core of Eruption",
                description = "분출의 근원. 타격 시 지면을 흔드는 폭발 효과를 생성한다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.VOLCANO
            )
        )

        /**
         * 해안 지역 재료
         */

        materialRepository.save(
            Material(
                name = "Nautilus Shell Chip",
                description = "소용돌이 무늬가 아름다운 패각 조각. 장신구에 자주 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Coral Thread",
                description = "산호초에서 얻은 가늘고 질긴 실. 방어구 스티칭에 적합하다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Krakkin Ink Resin",
                description = "심해 괴수의 검은 수지. 마법 봉인을 위한 촉매다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Salted Sharkskin",
                description = "거친 상어 가죽. 빠른 회피형 장비에 적합하다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Deepwater Gem",
                description = "심해의 압력을 견디고 생성된 보석. 마력 집중에 유리하다.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Seagrass Thread",
                description = "질긴 바닷풀을 엮은 실. 방어구의 스티칭 재료로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Shellscale Flake",
                description = "대형 갑각류의 껍데기 조각. 방어구의 외장재로 적합하다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Echo Pearl Dust",
                description = "심해 진주의 가루. 장비에 마나 회복 효과를 부여한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Tidebeast Tendon",
                description = "파도 짐승의 힘줄. 유연하면서도 매우 단단하다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Kraken's Breath Core",
                description = "심연에서 나온 공기의 핵. 전설급 무기에 바다의 분노를 담는다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Kelpwrap Fiber",
                description = "해조류 섬유. 유연하고 바닷물에 강하다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Crustacean Fang",
                description = "딱딱한 게 송곳니. 작은 장신구 강화 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Tideglass Shard",
                description = "물결처럼 휘는 유리 조각. 마법 반사 장비에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Brinebeast Hide",
                description = "염수에 사는 짐승의 가죽. 염분에 강한 방어구 제작에 적합하다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Octovoid Ink Sack",
                description = "심해 문어의 먹물 주머니. 혼란 속성 장비에 응용된다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Pearled Claw Tip",
                description = "진주처럼 광택나는 집게발 끝. 고급 무기 장식용 재료.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Sea Spirit Fragment",
                description = "바다의 정령이 남긴 파편. 치유/보호 계열 장비에 주로 사용된다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Abyssal Nerve Cord",
                description = "심연 생물의 신경 다발. 고급 장신구 제작에 적합하다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Trenchwhale Spine",
                description = "해구 고래의 척추 뼈. 전설 갑옷의 기틀을 이룬다.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Stormcore Pearl",
                description = "폭풍의 중심에서 자라난 진주. 물과 번개 속성을 동시에 부여한다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Sandstone Coral Chip",
                description = "바닷가 산호초의 돌기. 방어구 표면 강화에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Bubblestone Pebble",
                description = "파도에 깎인 둥근 돌. 장신구 장식에 쓰이는 값싼 재료.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Salted Shell Dust",
                description = "조개껍질을 간 가루. 마법 장비 보조 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Eelskin Strap",
                description = "전기 뱀장어의 가죽 띠. 유연성과 전기 저항을 동시에 제공한다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Kelpink Blob",
                description = "심해 해조류에서 추출한 점액. 방어구의 미끄럼 방지 기능을 향상시킨다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Coralbone Fragment",
                description = "산호와 뼈가 뒤섞인 조각. 공격 무기 강화에 적합하다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Tide Elemental Core",
                description = "조수의 흐름을 따르는 정령의 핵. 마법 장비에 흐름 기반 효과를 부여한다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Pearlglass Lens",
                description = "진주처럼 투명한 렌즈. 시야 확대 및 명중 보정 장비에 사용된다.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Sailfish Fin Blade",
                description = "돛돔의 등지느러미를 칼날처럼 가공한 재료. 찌르기형 무기 강화에 특화.",
                itemRarity = ItemRarity.RARE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Stormwhale Blubber",
                description = "폭풍고래의 지방. 물리/전기 피해 흡수 방어구에 쓰인다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Abyss Ink Crystal",
                description = "심연 문어의 먹물을 굳힌 결정. 혼란 마법 장비에 사용된다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Siren Scale Flake",
                description = "세이렌의 비늘 파편. 장비에 매혹 또는 방해 효과를 부여한다.",
                itemRarity = ItemRarity.EPIC,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Trench Leviathan Bone",
                description = "해구의 거대한 생물의 뼈. 전설 갑옷의 척추 강화 소재다.",
                itemRarity = ItemRarity.UNIQUE,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Crest of the Deep King",
                description = "심해 군주의 문장. 바다 속성 전설 장비에만 사용되는 최고급 재료.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.COAST
            )
        )

        materialRepository.save(
            Material(
                name = "Heart of the Maelstrom",
                description = "거대한 소용돌이 중심에서 채취한 심장. 물+바람 복합 속성을 부여한다.",
                itemRarity = ItemRarity.LEGENDARY,
                dropArea = FieldType.COAST
            )
        )

        /**
         * COMMON 장비 추가 생성
         */
        materialRepository.save(Material(
            name = "Whispering Vine Tip",
            description = "부드러운 속삭임을 머금은 덩굴 끝부분으로, 은밀함을 돕는 포션에 쓰인다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Mossglisten Pebble",
            description = "이끼가 엉겨 붙은 작은 자갈로, 기초 치유 연마제 원료로 활용된다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.FOREST
        ))

        // DESERT 지역 – COMMON 재료 2종 추가
        materialRepository.save(Material(
            name = "Scorched Cactus Pulp",
            description = "태양 아래 말라버린 선인장 과육으로, 생명력 회복 물약 보조재료다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Dustworn Quartz Chip",
            description = "사막 바람에 깎인 수정 파편으로, 정밀 연금 도구 윤활제에 쓰인다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.DESERT
        ))

        // GLACIER 지역 – COMMON 재료 2종 추가
        materialRepository.save(Material(
            name = "Frostwoven Thread",
            description = "차가운 공기를 엮어 만든 실로, 방한 의류 보강재로 사용된다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Snowmelt Lily Petal",
            description = "녹는 눈 속에서 피어나는 백합 꽃잎으로, 순환 회복 포션 원료다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.GLACIER
        ))

        // VOLCANO 지역 – COMMON 재료 2종 추가
        materialRepository.save(Material(
            name = "Cindered Root Fragment",
            description = "불타다 남은 뿌리 조각으로, 발화 장치 작동 촉매에 쓰인다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Molten Glass Shard",
            description = "용암이 식어 굳은 유리 조각, 간이 방어구 장식에 활용된다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.VOLCANO
        ))

        // COAST 지역 – COMMON 재료 2종 추가
        materialRepository.save(Material(
            name = "Saltcrystal Sliver",
            description = "바닷물 결정이 응집된 조각, 고농축 전해질 포션 보조재료다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Tide-lashed Kelp Strip",
            description = "조류에 감긴 다시마 줄기로, 점착 유연제 원료로 사용된다.",
            itemRarity = ItemRarity.COMMON,
            dropArea = FieldType.COAST
        ))

        // FOREST 지역 – COMMON 재료 10종
        materialRepository.save(
            Material(
                name = "Dewspun Moss",
                description = "이슬방울을 머금은 이끼 조각으로, 기초 치유 물약 재료로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Shadowleaf Pod",
                description = "숲 그늘에서 자란 작은 꼬투리로, 은은한 향이 나는 향수 원료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Quietmoss Cloth",
                description = "부드러운 이끼 섬유로 만든 천 조각, 경량 갑옷 보강재로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Blightthorn Sprig",
                description = "병리 가시가 돋친 작은 가지, 독 연금약 조제에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Starpetal Dust",
                description = "밤하늘의 꽃잎을 말려 분말로 만든 재료, 간단한 완화 효과를 가진다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Moonshroud Bark",
                description = "달빛 아래 자란 나무 껍질, 수면제 조합에 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Glimmerfern Frond",
                description = "미약하게 빛을 내는 양치식물 잎사귀, 염료로 활용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Emberroot Shard",
                description = "불씨 뿌리 조각으로, 미약한 열기를 내는 점화제로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Faefoam Pellet",
                description = "요정의 거품 같은 알갱이, 마법진 활성화 재료로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Fairydew Droplet",
                description = "요정의 이슬 방울이 응축된 미세 액체, 정령 소환에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        // DESERT 지역 – COMMON 재료 10종
        materialRepository.save(
            Material(
                name = "Scorchsand Granule",
                description = "작은 모래 입자로, 기초 내열 포션 재료로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Dunevine Husk",
                description = "사막 덩굴 껍질 파편, 보습 연고 원료로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Sunscourge Pebble",
                description = "태양열로 달궈진 작은 돌 조각, 방어 마법 보조재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Mirage Bloom Petal",
                description = "사막의 환영꽃 꽃잎, 환각 유도 물약에 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Sandshell Fragment",
                description = "모래 속에서 수집한 조개껍데기 조각, 미백 화장품 원료로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Scorpionspine Chip",
                description = "전갈의 가시 조각, 독성 포션 강화제로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Sunburnt Gourd Husk",
                description = "태양에 그을린 박과 껍질 조각, 방어 연금술 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Zephyring Dust",
                description = "바람의 잔재가 섞인 미세 분말, 속도 부여 물약에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Aridroot Tendril",
                description = "건조 환경에 자생하는 뿌리 가닥, 갈증 해소제 원료로 활용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Goldenflare Grain",
                description = "금빛을 띠는 곡물 알갱이, 체력 회복 포션에 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )

        // GLACIER 지역 – COMMON 재료 10종
        materialRepository.save(
            Material(
                name = "Permafrost Pellet",
                description = "영구 동토층에서 떼어낸 작은 알갱이로, 냉기 저항 포션 원료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Iceveil Fiber",
                description = "엷은 얼음 막을 닮은 섬유, 방한 의류 보강재로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Stormicicle Chip",
                description = "폭풍 속 빙결 조각, 번개 속성 강화 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Breachice Crystal",
                description = "균열 빙하에서 채집한 수정, 투명 망토 제작에 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Glintsnow Dust",
                description = "반짝이는 눈가루, 은빛 염료 원료로 활용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Frostbloom Fragment",
                description = "얼어붙은 꽃잎 조각, 얼음 정령 호출 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Winterflare Bark",
                description = "차가운 불꽃을 담은 나무 껍질, 특수 횃불 제작에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Coldheart Pebble",
                description = "차가운 감정을 품은 돌 조각, 냉기 함정 제작 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Snowdrift Strand",
                description = "눈더미에서 건진 섬유질 조각, 방한 담요 보강용이다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Boreal Shard",
                description = "북녘 숲 빙결 조각, 얼음 룬 스톤 원료로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        // VOLCANO 지역 – COMMON 재료 10종
        materialRepository.save(
            Material(
                name = "Magmaberry Husk",
                description = "용암 대지를 견딘 열매 껍질, 내열 물약 재료로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Scorchcore Pellet",
                description = "불꽃 중심부에서 떨어진 알갱이, 발화 포션 원료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Lavafern Tendril",
                description = "용암 옆에서 자란 양치식물 가닥, 열 마법 강화제로 활용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Emberbur Spore",
                description = "잿더미에서 자라는 균류 포자, 연소 독 포션 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Magmaflow Thread",
                description = "끓는 용암을 닮은 섬유, 내열 섬유 갑옷 보강재로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Fireglow Husk",
                description = "잔불처럼 빛나는 껍질 조각, 야간 신호용 횃불 제작에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Searstone Fragment",
                description = "뜨겁게 달궈진 돌 조각, 무기 담금질 보조재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Ashroot Bark",
                description = "재 속에서 자란 뿌리 껍질, 독성 해독제 원료로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Flameglass Sliver",
                description = "용암이 식어 굳은 유리 조각, 빛나는 마법 장식에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Blazeleaf Strip",
                description = "열기를 품은 잎사귀 줄기, 화염 방어 포션 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        // COAST 지역 – COMMON 재료 10종
        materialRepository.save(
            Material(
                name = "Saltpetal Husk",
                description = "염분을 가득 머금은 꽃잎 껍질, 소금 결정 추출 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Tidefoam Droplet",
                description = "파도 거품이 응결된 작은 방울, 수면제 강화제로 활용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Brinepearl Chip",
                description = "짠물 속 진주 파편, 광채 물약 원료로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Wavevine Thread",
                description = "파도줄기를 닮은 섬유, 유연성 포션 보강재다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Stormshell Fragment",
                description = "폭풍 해안에서 얻은 껍데기 조각, 방어력 포션에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Seafoam Dust",
                description = "바다 거품을 말린 분말, 가벼운 부력 포션 원료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Coralmoss Strand",
                description = "산호 이끼 가닥, 재생 물약 조합에 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Deepcurrent Crystal",
                description = "심해 전류를 품은 수정, 속성 전환 룬 제작에 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Tidereed Husk",
                description = "조수 갈대 껍질, 목재 방어구 경량화에 쓰이는 섬유다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Nautiflare Pellet",
                description = "해저 화염 떨림이 담긴 알갱이, 불 속성 포션 기초 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        // FOREST 지역 – COMMON 재료 2종 추가
        materialRepository.save(
            Material(
                name = "Morningsong Mushroom",
                description = "이른 아침 숲바닥에 피어나는 버섯으로, 기운을 돋우는 포션에 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Bramblekin Thistle",
                description = "가시가 돋은 작은 엉겅퀴, 기초 보호 마법 강화재로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.FOREST
            )
        )

        // DESERT 지역 – COMMON 재료 2종 추가
        materialRepository.save(
            Material(
                name = "Dustveil Bloom",
                description = "모래 폭풍 속에서도 피어나는 꽃으로, 시야 차단 물약 조합에 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Thornwhisper Spine",
                description = "가늘고 날카로운 사막 가시, 소량의 독성 포션 제조 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.DESERT
            )
        )

        // GLACIER 지역 – COMMON 재료 2종 추가
        materialRepository.save(
            Material(
                name = "Hoarfrost Lichen",
                description = "서리 덮인 바위에 붙은 지의류, 냉기 저항 물약 보조재료로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Glacial Melt Droplet",
                description = "빙하가 녹으며 떨어진 순수 물방울, 정화 포션 원료로 활용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.GLACIER
            )
        )

        // VOLCANO 지역 – COMMON 재료 2종 추가
        materialRepository.save(
            Material(
                name = "Charflare Ember",
                description = "불꽃이 사그라든 잿더미 조각으로, 점화 장치 보조재료로 사용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Pyroclast Dust",
                description = "화산 분출물에서 얻은 미세 분말, 열 폭발 포션 제조 재료다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        // COAST 지역 – COMMON 재료 2종 추가
        materialRepository.save(
            Material(
                name = "Barnacle Paste",
                description = "따개비를 곱게 갈아 만든 반죽으로, 접착 포션 원료로 활용된다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Seaglint Sand",
                description = "조개 조각이 섞인 반짝이는 모래, 은빛 염료와 연금 재료로 쓰인다.",
                itemRarity = ItemRarity.COMMON,
                dropArea = FieldType.COAST
            )
        )

        /**
         * 추가 UNCOMMON 재료
         */

        // FOREST 지역 – UNCOMMON 재료 8종
        materialRepository.save(
            Material(
                name = "Echoflora Bloom",
                description = "숲의 속삭임이 깃든 꽃잎으로, 기억 강화 포션 재료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Sylphwing Petal",
                description = "요정의 날개를 닮은 꽃잎, 정신 회복 연금약 원료로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Gloomshroud Spore",
                description = "어둠에 강한 버섯 포자로, 야간 투시 물약 제조에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Verdantstem Shoot",
                description = "강인한 생명력을 담은 새싹, 체력 회복 포션 강화재로 활용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Gladeheart Sap",
                description = "신성한 숲의 심장수액으로, 축복 부여 물약 재료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Thornvale Spike",
                description = "험준한 가시줄기 조각, 방어력 강화 염료 원료로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Wilderwood Essence",
                description = "거친 숲의 기운을 응축한 정수, 공격 포션에 은은한 추가 효과를 더한다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )
        materialRepository.save(
            Material(
                name = "Spiritbloom Pollen",
                description = "정령의 꽃가루로, 영혼 소환 의식에 사용되는 귀한 재료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.FOREST
            )
        )

        // DESERT 지역 – UNCOMMON 재료 8종
        materialRepository.save(
            Material(
                name = "Mirageglass Shard",
                description = "환영이 비치는 유리 조각으로, 시야 교란 포션 조합에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Dunewhisper Resin",
                description = "사막 바람의 속삭임을 머금은 수지, 은신 물약 강화재로 활용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Sunscourge Carapace",
                description = "태양에 그을린 껍질 조각, 화염 방어 염료 원료로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Siroccobloom Nectar",
                description = "열풍 속에서 피는 꽃의 진한 꿀, 체온 조절 물약 재료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Cinderwell Sand",
                description = "잿더미 속 미묘하게 빛나는 모래, 정밀 연금 장비 윤활제 원료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Gilded Cactus Spine",
                description = "황금빛을 띠는 선인장 가시로, 전기 방어 포션 강화재로 활용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Aridshroud Petal",
                description = "건조한 모래바람을 견딘 꽃잎, 불안정 속성 포션 조합에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.DESERT
            )
        )
        materialRepository.save(
            Material(
                name = "Emberwoven Fibers",
                description = "잔불을 엮어 만든 섬유질, 스태미나 연장 염료에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.DESERT
            )
        )

        // GLACIER 지역 – UNCOMMON 재료 8종
        materialRepository.save(
            Material(
                name = "Frostveil Petal",
                description = "차가운 안개가 깃든 꽃잎으로, 냉기 속성 포션 원료로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Stormfrost Essence",
                description = "폭풍 속에서 응결된 냉기 정수, 번개 방어 물약 부재료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Icetide Pearl",
                description = "빙하 파도 속에서 빚어진 진주, 특수 장신구 제작 재료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Snowsong Sap",
                description = "함박눈 속에 맺힌 수액, 수면 안정제 강화 원료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Hailsteel Chip",
                description = "우박처럼 단단한 금속 조각, 갑옷 강화 첨가제로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Glimmerfrost Vine",
                description = "은빛 서리가 맺힌 덩굴 조각, 은폐 마법 보조재료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Crystalbreeze Sap",
                description = "투명한 바람의 수액, 정밀 마법 도구 윤활제로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )
        materialRepository.save(
            Material(
                name = "Wintreshroud Pollen",
                description = "혹한을 견뎌낸 꽃가루, 얼음 정령 강화 포션에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.GLACIER
            )
        )

        // VOLCANO 지역 – UNCOMMON 재료 8종
        materialRepository.save(
            Material(
                name = "Infernoheart Ember",
                description = "심연의 불씨를 품은 재로, 파괴 속성 포션 강화재로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Pyroclast Vein",
                description = "용암 흐름이 응고된 선맥, 화염 장판 주문 부재료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Magmaheart Core",
                description = "마그마의 중심 에너지를 응축한 핵심, 화속성 무기 룬 제작에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Ashenflame Spores",
                description = "잿더미에서 자라는 불꽃 균사체, 독성 함유 포션 조합에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Blazebloom Nectar",
                description = "용암열을 머금은 꽃의 꿀, 체온 상승 물약 원료로 활용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Searshock Residue",
                description = "격변적 열기에 남은 잔해, 충격파 방어 물약 부재료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Cinderbark Essence",
                description = "탄화한 나무 껍질 수액, 지속 화염 보호막 주문 강화재로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )
        materialRepository.save(
            Material(
                name = "Sulfurspike Cord",
                description = "유황 가시가 뭉친 섬유질, 중독 연성 포션 조합에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.VOLCANO
            )
        )

        // COAST 지역 – UNCOMMON 재료 8종
        materialRepository.save(
            Material(
                name = "Tidalwreath Shell",
                description = "조수의 고리 모양으로 빚어진 껍데기, 순환 물리 보호막 포션에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Saltglimmer Pearl",
                description = "염분을 머금은 진주, 마법 장신구 윤활제로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Brineheart Essence",
                description = "심해의 염류 정수, 수분 회복 포션 강화재로 활용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Marid's Tear",
                description = "물 정령의 눈물로 만들어진 액체, 정령 소환 의식에 필수적인 재료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Surgeleaf Frond",
                description = "파도의 힘을 머금은 잎사귀, 속도 강화 물약 부재료다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Abyssal Sand",
                description = "심해 퇴적물이 섞인 모래, 깊은 수중 탐사 포션 원료로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Corallight Spore",
                description = "빛을 발하는 산호 균사체, 재생 속성 물약에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )
        materialRepository.save(
            Material(
                name = "Driftwood Resin",
                description = "해류에 깎인 나무 수지, 접착 및 방수 연고 재료로 활용된다.",
                itemRarity = ItemRarity.UNCOMMON,
                dropArea = FieldType.COAST
            )
        )

        /**
         * 추가 RARE 재료
         */

        materialRepository.save(Material(
            name = "Spiritroot Bloom",
            description = "숲의 깊은 뿌리에서 피어난 꽃으로, 정령 소환 및 자연 계열 주문의 힘을 증폭한다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Mysticfern Frond",
            description = "비전의 기운이 깃든 양치식물 잎사귀로, 마나 회복 포션 및 마법 도구 강화에 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Whisperbark Fragment",
            description = "고요한 숲의 나무껍질 파편으로, 소리 흡수 및 은신 주문에 필수적인 재료다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.FOREST
        ))

        // DESERT 지역 – RARE 재료 3종 추가
        materialRepository.save(Material(
            name = "Shifting Sandstone",
            description = "영원히 흘러가는 모래처럼 부드럽게 움직이는 사암 결정으로, 공간 변경 주문의 핵심 재료다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Mirage Orchid Petal",
            description = "오아시스의 환영난초 꽃잎으로, 착시 마법 및 인식 교란 포션에 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Solarflare Prism",
            description = "태양빛을 굴절시키는 프리즘 결정체로, 빛 계열 주문을 강력히 증폭한다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.DESERT
        ))

        // GLACIER 지역 – RARE 재료 3종 추가
        materialRepository.save(Material(
            name = "Frostshard Prism",
            description = "냉기를 굴절시키는 수정 파편으로, 빙속성 포션 및 주문 강화에 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Icebound Spine",
            description = "얼어붙은 식물의 가시로, 냉기 함정 제작 및 방패 주문 강화에 필수적인 재료다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Silentsnow Pearl",
            description = "고요한 눈밭에서 채취한 진주로, 은폐 및 잠행 마법 강화 재료다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.GLACIER
        ))

        // VOLCANO 지역 – RARE 재료 3종 추가
        materialRepository.save(Material(
            name = "Pyrestone Shard",
            description = "불꽃을 머금은 돌 파편으로, 화염 함정 및 폭발 주문 강화에 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Scoriaheart Fragment",
            description = "용암 암설 스코리아의 핵심 파편으로, 방어구 강화 룬 제작에 사용된다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Caldera Embergem",
            description = "칼데라 깊은 곳에서 채취한 불꽃 결정체로, 폭발 마법 증폭에 필수적이다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.VOLCANO
        ))

        // COAST 지역 – RARE 재료 3종 추가
        materialRepository.save(Material(
            name = "Maelstrom Pearl",
            description = "소용돌이 속에서 형성된 진주로, 어둠 속성 주문 및 방어 강화에 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Coralshadow Spire",
            description = "산호의 어둠 기운이 응축된 결정으로, 은폐 및 환각 주문 증폭에 사용된다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Tsunami Shard",
            description = "거대한 해일의 잔재가 깃든 수정 파편으로, 물 속성 폭발 주문 강화에 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.COAST
        ))

        // FOREST 지역 – RARE 재료 3종
        materialRepository.save(Material(
            name = "Eldergrove Sap",
            description = "수백 년 된 고목에서 채취한 진액으로, 초자연적 치유력을 지닌 전설級 포션 재료다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Moonpetal Nectar",
            description = "달빛을 머금은 꽃잎에서 추출한 꿀로, 야간 증폭 및 정신 강화 포션에 사용된다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Radiantwood Core",
            description = "숲의 정수가 응축된 나무 심장부로, 마법 장비 강화 및 룬 문양 각인 재료다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.FOREST
        ))

        // DESERT 지역 – RARE 재료 3종
        materialRepository.save(Material(
            name = "Sunforged Amber",
            description = "사막의 태양열로 단단해진 호박으로, 태양 속성 주문 증폭에 탁월한 재료다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Duneblood Pearl",
            description = "모래 폭풍 속 희귀 조개에서 얻은 진주로, 열 저항 및 체력 회복 포션에 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Scorchfire Essence",
            description = "불타는 모래바람의 정수를 응축한 액체로, 파괴 속성 포션과 폭발물 제작에 필수적이다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.DESERT
        ))

        // GLACIER 지역 – RARE 재료 3종
        materialRepository.save(Material(
            name = "Everfrost Heart",
            description = "영겁의 냉기가 결정화된 수정 심장으로, 강력한 냉기 속성 주문 제작에 사용된다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Hailstone Core",
            description = "우박의 핵심 에너지를 압축한 결정체로, 방어력 강화 물약 및 보호막 주문 부재료로 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Aurorafrost Crystal",
            description = "북극광의 빛을 담은 얼음 수정으로, 시각 교란 및 매혹 주문 강화에 활용된다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.GLACIER
        ))

        // VOLCANO 지역 – RARE 재료 3종
        materialRepository.save(Material(
            name = "Obsidianheart Core",
            description = "용암이 식어 응결된 흑요석 핵으로, 방어구 및 무기 룬 각인에 사용된다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Infernalsteel Fragment",
            description = "지옥불꽃이 통과한 금속 조각으로, 화염 속성 무기 및 갑옷 강화에 탁월하다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Volcanic Embergem",
            description = "용암심장에서 솟아난 불꽃 결정체로, 연소 주문 증폭 및 폭발물 제조에 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.VOLCANO
        ))

        // COAST 지역 – RARE 재료 3종
        materialRepository.save(Material(
            name = "Tidebound Coral",
            description = "조수의 힘이 깃든 산호로, 해일 주문 및 수류 마법 강화에 사용된다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Seastorm Essence",
            description = "폭풍 해양의 영혼을 응축한 정수로, 소환 및 보호 주문 부재료로 쓰인다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Moonlit Nautilus",
            description = "달빛이 스며든 달팽이 껍데기로, 은폐 및 혼란 주문 강화 원료다.",
            itemRarity = ItemRarity.RARE,
            dropArea = FieldType.COAST
        ))

        /**
         * 추가 UNIQUE 재료
         */
        materialRepository.save(Material(
            name = "Heartwood of the Ancients",
            description = "고대의 심장부에서 채취한 특별한 나무 심지로, 최고의 생명력 증강 마법에 사용되는 유일한 재료다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Sylvan Soulseed",
            description = "숲의 정령이 깃든 씨앗으로, 자연의 근원을 깨우는 소환 의식에 필수적인 재료다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Verdantwyrm Scale",
            description = "전설 속 숲의 용 비늘 조각으로, 대자연의 보호막 주문에 단 하나뿐인 강화제로 쓰인다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Celestial Sporecluster",
            description = "별빛과 숲의 생기가 결합된 희귀 포자 송이로, 치유와 재생 마법에 혁명적인 힘을 부여한다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.FOREST
        ))

        // DESERT 지역 – UNIQUE 재료 4종
        materialRepository.save(Material(
            name = "Duneshaper's Hourglass",
            description = "모래의 흐름을 담은 유리 모래시계로, 시간 조작 주문에 단 하나뿐인 핵심 재료다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Sandwyrm's Fang",
            description = "사막의 모래벌레 이빨로, 고대의 늙은 모래벌레가 남긴 희귀한 독성 강화제로 쓰인다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Mirageheart Opal",
            description = "환영 속에서만 빛나는 오팔로, 현실 왜곡 및 환각 마법의 근원 재료다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Scorched Sunfire Sigil",
            description = "태양의 정수를 담아 불타는 주술로 새겨진 고대 문장, 화염 마법 연구에 필수적이다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.DESERT
        ))

        // GLACIER 지역 – UNIQUE 재료 4종
        materialRepository.save(Material(
            name = "Frostwyrm's Heart",
            description = "빙설 속 고대 용의 심장 결정으로, 극한의 냉기 마법에 단 하나뿐인 힘을 부여한다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Echo of the Eternal Winter",
            description = "영원의 한기를 담은 숨결 결정체로, 시간과 공간을 얼려버리는 마법에 사용된다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Glacial Wyrmscale",
            description = "빙하의 용 비늘 조각으로, 방어와 냉기 반사 마법 강화에 유일한 핵심 재료다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Celestine Iceheart",
            description = "별빛이 응결된 얼음 결정으로, 신성한 냉기 주문 및 보호막 생성에 쓰인다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.GLACIER
        ))

        // VOLCANO 지역 – UNIQUE 재료 4종
        materialRepository.save(Material(
            name = "Molten Titan Core",
            description = "거대한 화산 거인의 핵심 에너지가 응축된 결정체로, 강력한 화염 파괴 주문의 원천이다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Embermaw Scale",
            description = "용암 속 용의 비늘로, 극한 화염 방어와 반격 주문에 단 하나뿐인 강화제로 쓰인다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Inferno Serpent Eye",
            description = "화염의 뱀 정수로 이루어진 수정 구슬로, 불속성 집중 및 폭발 마법에 유일하게 사용된다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Pyroclast Heartstone",
            description = "화산 분출물의 심장이 응결된 돌로, 화염 속성 주문 및 방어구 룬 조각에 필요하다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.VOLCANO
        ))

        // COAST 지역 – UNIQUE 재료 4종
        materialRepository.save(Material(
            name = "Soul of the Leviathan",
            description = "심연의 괴물 레비아탄의 영혼 정수로, 해일 소환 및 해양 정령 마법에 필수적이다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Abyssal Pearlheart",
            description = "심해의 심장부에서 채취한 진주 결정으로, 깊은 수중 마법과 치유에 유일한 힘을 준다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Tidalwyrm Scale",
            description = "해양의 용 비늘로, 물 속성 방어와 정화 주문 강화에 단 하나뿐인 핵심 재료다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Tempestborn Shell",
            description = "폭풍에 탄생한 조개껍질로, 폭풍 소환 및 대기 마법 연구에 필수적인 재료다.",
            itemRarity = ItemRarity.UNIQUE,
            dropArea = FieldType.COAST
        ))

        // FOREST 지역 – EPIC 재료 3종
        materialRepository.save(Material(
            name = "Arcaneleaf Petal",
            description = "고대 숲의 마법 기운이 깃든 꽃잎으로, 강력한 자연 계열 마법 증폭제 역할을 한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Eldershade Bud",
            description = "숲의 어두운 장막 속에서 피어난 꽃봉오리로, 은신 및 그림자 마법에 극대화된 효과를 부여한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.FOREST
        ))
        materialRepository.save(Material(
            name = "Sylvanmoon Nectar",
            description = "달빛 아래서만 채취할 수 있는 꿀로, 치유와 보호의 자연 마법을 비약적으로 강화한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.FOREST
        ))

        // DESERT 지역 – EPIC 재료 3종
        materialRepository.save(Material(
            name = "Sunsorrow Catalyst",
            description = "사막의 뜨거운 태양열을 응축한 수정으로, 파괴적인 태양 속성 주문을 폭발적으로 증폭한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Duneburst Essence",
            description = "모래 폭풍의 진수를 농축한 액체로, 공간 변형 및 모래 속성 마법에 막대한 시너지 효과를 제공한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.DESERT
        ))
        materialRepository.save(Material(
            name = "Mirageflame Heart",
            description = "환영과 불꽃이 공존하는 심장 결정체로, 착시와 화염 속성의 복합 마법을 구현하는 핵심 재료다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.DESERT
        ))

        // GLACIER 지역 – EPIC 재료 3종
        materialRepository.save(Material(
            name = "Cryostone Shard",
            description = "극한의 냉기가 응결된 수정 파편으로, 얼음 마법의 위력을 한층 더 극대화시킨다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Winterborn Echo",
            description = "영원의 한기를 담은 메아리 결정으로, 시간 동결 및 강력한 보호막 주문에 필수적인 재료다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.GLACIER
        ))
        materialRepository.save(Material(
            name = "Glacialweave Thread",
            description = "빙결 에너지가 실처럼 엮인 섬유로, 얼음 방어구 및 마법 도구의 성능을 극대화한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.GLACIER
        ))

        // VOLCANO 지역 – EPIC 재료 3종
        materialRepository.save(Material(
            name = "Eternalflame Essence",
            description = "용암의 영원한 불꽃 기운을 농축한 정수로, 불속성 파괴 마법을 극도로 강화한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Pyrofury Shard",
            description = "격렬한 화염 폭풍 속에서 얻은 수정 파편으로, 폭발 및 화염 함정 마법에 치명적인 위력을 제공한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.VOLCANO
        ))
        materialRepository.save(Material(
            name = "Magmaforged Ember",
            description = "화산 속 태고의 불씨를 간직한 잔광으로, 무기 룬 각인과 방어구 강화에 최상의 결과를 낸다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.VOLCANO
        ))

        // COAST 지역 – EPIC 재료 3종
        materialRepository.save(Material(
            name = "Stormtide Essence",
            description = "폭풍 해일의 거센 기운을 압축한 액체로, 물과 바람 마법을 동시에 증폭하는 만능 재료다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Aegir's Breath",
            description = "심해의 신 아에기르의 숨결을 담은 결정으로, 해양 정령 소환 및 보호 주문에 절대적인 힘을 부여한다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.COAST
        ))
        materialRepository.save(Material(
            name = "Seastar Prism",
            description = "바닷속 별빛이 결정화된 프리즘으로, 치유와 방어를 동시에 수행하는 고급 마법에 사용된다.",
            itemRarity = ItemRarity.EPIC,
            dropArea = FieldType.COAST
        ))

        // FOREST 지역 – LEGENDARY 재료 1종
        materialRepository.save(Material(
            name = "Primordial Sylva Root",
            description = "원초적 숲의 정신이 응결된 뿌리 심장으로, 자연 마법을 완전하게 재창조하는 전설급 재료다.",
            itemRarity = ItemRarity.LEGENDARY,
            dropArea = FieldType.FOREST
        ))

        // DESERT 지역 – LEGENDARY 재료 1종
        materialRepository.save(Material(
            name = "Pharaohstone of the Eternal Dunes",
            description = "영원의 사막을 지키는 고대 파라오의 유물로, 시간과 모래를 다스리는 전설급 마법의 핵심 원료다.",
            itemRarity = ItemRarity.LEGENDARY,
            dropArea = FieldType.DESERT
        ))

        // GLACIER 지역 – LEGENDARY 재료 1종
        materialRepository.save(Material(
            name = "Heart of the Endless Tundra",
            description = "끝없는 툰드라의 심장 결정체로, 세계를 얼리는 절대 냉기 마법에 필요한 전설급 재료다.",
            itemRarity = ItemRarity.LEGENDARY,
            dropArea = FieldType.GLACIER
        ))

        // VOLCANO 지역 – LEGENDARY 재료 1종
        materialRepository.save(Material(
            name = "Core of the Primordial Volcano",
            description = "원시 화산의 핵 에너지를 봉인한 결정으로, 대지를 불태우는 전설급 재앙 마법의 원천이다.",
            itemRarity = ItemRarity.LEGENDARY,
            dropArea = FieldType.VOLCANO
        ))

        // COAST 지역 – LEGENDARY 재료 1종
        materialRepository.save(Material(
            name = "Abyssal Monolith Crystal",
            description = "심해의 광맥이 응결된 모노리스 결정으로, 해양 대마법의 중심이 되는 전설급 재료다.",
            itemRarity = ItemRarity.LEGENDARY,
            dropArea = FieldType.COAST
        ))


        /**
         * 숲 장비 아이템
         */
        equipmentRepository.save(
            Equipment(
                name = "Barkskin Vest",
                description = "단단한 나무껍질로 만든 조끼. 숲의 기운이 착용자를 감싸준다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Direfang Dagger",
                description = "디어울프의 송곳니로 만든 단검. 날렵하고 민첩한 자에게 어울린다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Sylvan Bow",
                description = "정령나무 가지로 제작된 활. 화살마다 자연의 축복이 담겨 있다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Emeraldguard Helm",
                description = "숲의 정령이 깃든 투구. 착용 시 정신 저항력이 증가한다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Whisperroot Blade",
                description = "뿌리에서 자라난 듯한 검. 적중 시 적의 정신을 교란시킨다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Mossthread Hood",
                description = "습한 숲에서 자란 이끼실로 짜인 후드. 소리를 흡수해 은신에 도움이 된다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Sapwood Blade",
                description = "수액 나무로 만든 날. 가볍지만 내구성이 낮다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Verdant Ring",
                description = "푸른 덩굴이 감싼 반지. 착용 시 자연 치유력이 약간 증가한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Forest Warden's Boots",
                description = "숲의 감시자가 남긴 부츠. 정령들에게 덜 들킨다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Thornmail Vest",
                description = "가시덤불로 짜인 조끼. 근접 공격자에게 반격 피해를 준다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Oaken Antler Helm",
                description = "고대 사슴의 뿔을 조각한 투구. 야생 동물에게 호의적 반응을 유도한다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Rootbound Armor",
                description = "나무뿌리가 감싼 중갑. 적중 시 뿌리로 적의 움직임을 저지한다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Spiritleaf Cloak",
                description = "정령 나뭇잎으로 짜인 망토. 은신 중 회복 효과가 강화된다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Elderwood Longbow",
                description = "고대의 나무로 만든 활. 한 발 한 발에 숲의 분노가 담긴다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Wildheart Totem",
                description = "야생의 정령이 깃든 토템. 주변 아군의 공격력을 일정 시간 증가시킨다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Leafbound Bracers",
                description = "굳어진 나뭇잎으로 만든 손목 보호대. 가벼운 방어에 적합하다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Twigsnapper Boots",
                description = "마른 가지로 짠 부츠. 이동 시 소리를 줄여준다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Briarthorn Belt",
                description = "가시덤불로 만든 허리띠. 맞은 적에게 출혈을 유발한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Greeneye Circlet",
                description = "초록빛 눈을 본뜬 관. 숲 속 은신 중 탐지 확률이 감소한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Spiritbranch Wand",
                description = "정령나무 가지로 만든 지팡이. 자연 계열 마법 위력을 증가시킨다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Mossbeard Shield",
                description = "고목의 껍질로 만든 방패. 자연 치유 효과를 강화시킨다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Warden's Vineblade",
                description = "덩굴처럼 휘감는 검. 일정 확률로 적을 구속한다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Antler King's Pauldrons",
                description = "숲의 지배자의 뿔로 만든 어깨 보호대. 위압감을 준다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Verdant Wrath",
                description = "대자연의 분노를 담은 양손검. 벼락처럼 무작위의 힘이 깃든다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Voice of the Grove",
                description = "숲의 정령이 속삭이는 목걸이. 착용자 주변에 회복의 오라를 펼친다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        /**
         * 사막 지역 장비
         */
        equipmentRepository.save(
            Equipment(
                name = "Suncloth Hood",
                description = "태양빛을 반사하는 얇은 모자. 열을 약간 차단해준다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Glassfang Spear",
                description = "사막 전갈의 송곳니로 만든 창. 적중 시 출혈 효과가 있다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Scarab Scale Mail",
                description = "금빛 스카라베의 껍질을 이어 만든 갑옷. 방어력이 우수하다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Djinnbound Gauntlets",
                description = "정령의 문양이 새겨진 건틀릿. 일정 확률로 화염 마법이 발동한다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Mirage Blade",
                description = "착용자조차 자신의 위치를 헷갈릴 정도로 흔들리는 환영의 검.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Dustveil Mantle",
                description = "모래폭풍을 막아주는 망토. 시야 방해 효과를 일부 무시한다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Sunstone Band",
                description = "햇빛으로 반짝이는 간단한 반지. 햇볕 피해를 약간 줄여준다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Scorchfang Kukri",
                description = "불탄 송곳니를 본뜬 칼날. 공격 시 적을 잠시 화상 상태로 만든다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Mirage Walker's Wraps",
                description = "신기루 위를 걷는 듯한 감각. 적의 추적에서 벗어날 확률이 높아진다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Sandskull Mask",
                description = "모래 속에서 발굴된 해골을 본뜬 가면. 저주 마법에 강하다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Burnished Bronze Bracers",
                description = "태양에 달궈진 청동 팔찌. 마법 방어력을 강화한다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Djinncaller Amulet",
                description = "정령의 이름이 새겨진 부적. 화염 마법의 위력을 높여준다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Scarab King's Armor",
                description = "전설적인 스카라베 왕의 껍질을 이어붙인 중갑. 생명력을 회복시킨다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Eclipsing Blade",
                description = "일식을 담은 검. 공격 시 적의 시야를 차단한다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Pharaoh’s Grasp",
                description = "고대 왕의 손을 형상화한 장갑. 적을 한 번에 제압할 수도 있다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Windswept Hood",
                description = "사막 바람에 맞춰 짜인 후드. 이물질 피해를 줄인다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Grainwalker Sandals",
                description = "모래 위를 빠르게 걷는 이들을 위한 신발. 이동 속도가 소폭 증가한다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Sandglass Amulet",
                description = "시간의 흐름을 상징하는 부적. 기절 지속 시간이 감소한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Copperfang Claws",
                description = "구리 전갈의 발톱을 본뜬 장비. 빠른 연속 타격이 가능하다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Dune King's Mantle",
                description = "사막 군주의 어깨 망토. 햇빛 반사 효과로 명중률을 낮춘다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Emberglass Rod",
                description = "모래가 녹아 만들어진 지팡이. 적중 시 적을 태울 수 있다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Mirageplate Armor",
                description = "신기루의 환상을 입힌 갑옷. 적이 착용자의 위치를 오인할 수 있다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Cursed Sunblade",
                description = "영겁의 태양에 저주받은 검. 일정 확률로 적을 실명시킨다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Anubis’s Judgment",
                description = "사자의 신이 내린 도끼. 처형 확률이 존재한다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Desert Sovereign’s Helm",
                description = "고대 황제의 왕관. 생명력과 마나가 동시 회복된다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        /**
         * 빙하 지역 장비
         */

        equipmentRepository.save(
            Equipment(
                name = "Snowhide Boots",
                description = "눈표범 가죽으로 만든 부츠. 미끄러운 지형에서도 잘 버틴다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Frostfang Axe",
                description = "빙설 야수의 이빨을 붙인 도끼. 적중 시 냉기 피해를 준다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Icereaver Cloak",
                description = "얼음 정령의 힘이 담긴 망토. 냉기 마법 피해를 감소시킨다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Glacierborne Shield",
                description = "빙하에서 잘라낸 거대한 방패. 마법 피해를 튕겨낸다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Permafrost Lance",
                description = "영원히 녹지 않는 얼음으로 만든 창. 적중 시 행동 속도를 늦춘다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Snowweave Scarf",
                description = "눈결처럼 짜여진 목도리. 냉기 피해를 소량 감소시킨다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Icetip Dagger",
                description = "얼음 조각으로 만든 단검. 짧은 시간동안 적의 이동속도를 느리게 한다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Chillborn Pauldrons",
                description = "한기 속에서 태어난 견갑. 착용 시 체온이 안정된다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Snowstrider Greaves",
                description = "눈 위를 걸어다니는 자의 경갑. 빙판길 이동 속도가 증가한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Frozenlight Pendant",
                description = "얼어붙은 빛이 담긴 목걸이. 마법 저항력이 증가한다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Frostmark Cloak",
                description = "냉기의 각인이 새겨진 망토. 일정 확률로 적의 공격을 반사한다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Cryoheart Plate",
                description = "냉기의 심장을 담은 중갑. 공격을 받을수록 냉기 반격력이 상승한다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Shatterspike Hammer",
                description = "한 방에 얼음도, 방어구도 부술 수 있는 강력한 해머.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Glacial Crown",
                description = "빙하 군주의 왕관. 주변의 시간을 잠시 느리게 만든다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Abyssal Iceblade",
                description = "심해의 냉기를 품은 검. 영구 동결 효과를 일으킬 수 있다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Coldhide Bracers",
                description = "냉기에 강한 짐승의 가죽으로 만든 손목 보호구.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "FrostTread Boots",
                description = "빙판길에 최적화된 발걸음. 빙결 내성 소폭 증가.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Snowveil Hood",
                description = "눈보라를 가리는 두건. 원거리 공격에 명중 저항을 부여한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Icecore Pendant",
                description = "빙정의 핵을 담은 목걸이. 일정 확률로 마법 데미지를 무효화한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Cryoshard Daggers",
                description = "차가운 수정 파편으로 만든 쌍검. 연타 시 빙결 확률 증가.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Glacierwalker Cloak",
                description = "빙하 위를 걷는 자의 망토. 받는 냉기 피해를 크게 줄인다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Hailborn Greatsword",
                description = "우박처럼 적을 몰아치는 대검. 적중 시 넉백 발생.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Frozen Wyrm Helm",
                description = "얼음 드래곤의 두개골을 가공한 투구. 마법 저항 상승.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ARMOR
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Winter's Requiem",
                description = "눈보라 속에서 울리는 종소리 같은 활. 적중 시 지속 피해.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )

        equipmentRepository.save(
            Equipment(
                name = "Crown of the White Silence",
                description = "설원 왕국의 왕관. 광역 빙결 마법 시전 가능.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ACCESSORY
            )
        )
        /**
         * 화산 지역 장비
         */
        equipmentRepository.save(
            Equipment(
                name = "Ashwrap Sandals",
                description = "화산재로 만든 샌들. 불에 대한 저항을 소량 제공한다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Charclaw Blades",
                description = "용암 짐승의 발톱으로 만든 쌍검. 타격 시 열상 효과를 입힌다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Molten Iron Chestplate",
                description = "녹은 철을 식혀 만든 갑옷. 물리 공격에 강하다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Inferno Mantle",
                description = "화염의 망토. 일정 시간마다 불꽃 보호막을 생성한다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Volcanic Edge",
                description = "마그마의 심장에서 태어난 대검. 적을 불태우며 산산조각 낸다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Cindershade Gloves",
                description = "잿더미를 흡수한 장갑. 불꽃 피해를 소폭 감소시킨다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Coalspine Buckler",
                description = "숯돌로 만든 소형 방패. 가볍고 기본적인 방어력을 제공한다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "LavaLinked Chain",
                description = "용암처럼 이어진 쇠사슬 목걸이. 힘이 증가한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Smeltguard Helm",
                description = "용광로 작업장용 헬멧에서 개조된 장비. 열기 저항이 높다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Pyroclast Lance",
                description = "화산 폭발 조각으로 만든 창. 타격 시 폭열 효과가 발생한다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Magmawalker Boots",
                description = "용암 위를 걷는 듯한 부츠. 뜨거운 지형에서도 데미지를 받지 않는다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Volcanite Chestplate",
                description = "화산석으로 제작된 갑옷. 불 피해 흡수 능력이 있다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Blazefury Katars",
                description = "연속 공격에 특화된 쌍날 검. 타격 시 불길을 남긴다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Infernal Crown",
                description = "불의 군주의 왕관. 일정 시간마다 화염 폭풍을 소환한다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Corepiercer Greatsword",
                description = "화산의 핵을 꿰뚫은 대검. 일격에 적을 증발시킬 수 있다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Emberhide Belt",
                description = "불에 그을린 가죽으로 만든 허리띠. 불속성 공격을 소폭 감소시킨다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "AshFlecked Helm",
                description = "화산재가 묻은 투구. 시야를 보존하면서 열기 저항이 높다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Ignis Gauntlets",
                description = "화염의 인장을 품은 건틀릿. 근접 공격 시 타격감이 상승한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Charburn Tunic",
                description = "화상 자국처럼 그을린 로브. 열기 피해를 흡수하여 체력 회복.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Moltensteel Mace",
                description = "녹은 강철을 단련한 철퇴. 적중 시 폭발 효과 발생.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Lava Mantle",
                description = "용암의 흐름이 살아 숨 쉬는 망토. 피격 시 확률로 불꽃 생성.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Drakefang Sabers",
                description = "화염 드레이크의 이빨로 만든 쌍도. 연타 시 화염 축적 효과 발생.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Cindersoul Circlet",
                description = "불의 영혼이 깃든 관. 마법 공격 시 불덩이 생성.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Volcanic Howl",
                description = "화산의 울부짖음을 담은 대검. 적을 밀쳐내며 화염 대미지를 준다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Mantle of the Infernal Star",
                description = "지옥별의 망토. 모든 화염 마법의 효과가 강화된다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ARMOR
            )
        )

        /**
         * 해안 지역 장비
         */
        equipmentRepository.save(
            Equipment(
                name = "Tideleather Gloves",
                description = "바닷가 상어가죽으로 만든 장갑. 습기에 강하다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Coral Rapier",
                description = "산호로 만든 가볍고 예리한 검. 찌르기 공격에 특화되었다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Deepcurrent Helm",
                description = "심해 흐름의 기운이 깃든 투구. 물속 이동 속도가 증가한다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Stormscale Armor",
                description = "바다 괴수의 비늘로 만든 갑옷. 번개 저항이 있다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Krakenlash Whip",
                description = "심해의 촉수로 만든 채찍. 광범위 적중과 구속 효과를 가진다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Seabreeze Tunic",
                description = "해풍을 담은 천옷. 가볍고 빠르게 건조된다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Crabshell Buckler",
                description = "단단한 게 껍질로 만든 방패. 물리 방어에 적절하다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Tideworn Chainmail",
                description = "바닷물에 쓸려 닳은 체인메일. 부식 저항력이 뛰어나다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Bubblecharm Necklace",
                description = "물방울 모양 부적. 익사 저항이 증가한다.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ACCESSORY
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Deepsea Diver’s Helm",
                description = "심해 잠수용 헬멧에서 개조한 장비. 수중 시야 확보에 유리하다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Eelhide Gauntlets",
                description = "미끄러운 전기뱀장어 가죽으로 만든 장갑. 전격 공격에 강하다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Stormcaller Staff",
                description = "폭풍의 정수를 품은 지팡이. 번개 마법을 증폭시킨다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Siren’s Voice Ring",
                description = "세이렌의 목소리를 봉인한 반지. 사용 시 적을 매혹시킬 수 있다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ACCESSORY
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Trident of the Leviathan",
                description = "심해 괴수의 힘이 담긴 삼지창. 강력한 범위 피해를 입힌다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Tsunami Mantle",
                description = "해일의 분노가 담긴 망토. 공격을 받을 때마다 물방어막이 생긴다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Saltguard Vest",
                description = "바닷물에 강한 가죽 조끼. 녹 방지 기능이 있다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Shellwoven Mitts",
                description = "조개껍질을 엮어 만든 장갑. 경량 방어구에 좋다.",
                itemRarity = ItemRarity.COMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Coralcrest Helm",
                description = "산호로 장식된 투구. 마나 회복 속도 소폭 증가.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Tidalstep Greaves",
                description = "조수의 흐름을 따라 만들어진 경갑. 물속 이동 속도 증가.",
                itemRarity = ItemRarity.UNCOMMON,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Seawatch Tower Shield",
                description = "바다 경비대가 사용한 대형 방패. 방어력과 어그로 유지에 강하다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Foamveil Cloak",
                description = "바다의 거품을 형상화한 망토. 회피율이 증가한다.",
                itemRarity = ItemRarity.RARE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Maelstrom Harpoon",
                description = "소용돌이의 힘을 담은 작살. 원거리에서 적을 끌어당긴다.",
                itemRarity = ItemRarity.EPIC,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Abyss Whisper Mask",
                description = "심연의 목소리를 듣는 가면. 착용 시 일정 확률로 적을 혼란시킨다.",
                itemRarity = ItemRarity.UNIQUE,
                equipmentType = EquipmentType.ARMOR
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Scepter of the Sea King",
                description = "바다 왕의 지팡이. 해일 마법의 위력이 증가한다.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )
        equipmentRepository.save(
            Equipment(
                name = "Leviathan’s Spine",
                description = "거대한 바다 괴수의 척추로 만든 검. 적중 시 넓은 범위에 충격파 발생.",
                itemRarity = ItemRarity.LEGENDARY,
                equipmentType = EquipmentType.WEAPON
            )
        )

    }

    @PostConstruct
    fun cardInit() {
        val playerCards: List<PlayerCard> = listOf(
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Mental Focus",
                description = "Increase your INT by 2.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 2.0,
                effectSelfStat = TargetElementStatus.INT
            ),
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Steady Footing",
                description = "30% chance to evade attacks.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.3,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Quick Slash",
                description = "Deal 8 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 8.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Heavy Blow",
                description = "Deal 12 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 12.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Guard Stance",
                description = "Brace yourself and gain 6 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 6.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "First Aid",
                description = "Heal yourself for 10 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 10.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Sacrificial Strike",
                description = "Deal 15 damage while taking 5 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 5.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 15.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Adrenaline Rush",
                description = "Boost STR by 4 for a short time.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 4.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Distracting Shout",
                description = "Lower opponent DEX by 2.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 2.0,
                effectOpponentStat = TargetElementStatus.DEX
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Feint Step",
                description = "50% chance to evade the next attack.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.5,
                effectSelfStat = TargetElementStatus.HP
            )
        )

        val monsterCards: List<MonsterCard> = listOf(
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Roar",
                description = "The monster boosts its ATK by 2.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 2.0,
                effectSelfStat = TargetElementStatus.ATK
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Skulk",
                description = "25% chance to evade an attack.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.25,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Claw Swipe",
                description = "Inflict 7 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 7.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Bite",
                description = "Inflict 9 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 9.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Tail Whip",
                description = "Inflict 5 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 5.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Thick Hide",
                description = "Gain 6 HP as protection.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 6.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Drain",
                description = "Heal 8 HP by siphoning energy.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Savage Charge",
                description = "Deal 20 damage but suffer 10 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 10.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 20.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Enrage",
                description = "Increase STR by 4 as the battle ends.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 4.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Intimidate",
                description = "Reduce opponent STR by 3.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 3.0,
                effectOpponentStat = TargetElementStatus.STR
            )
        )

        playerCards.forEach {
            cardService.savePlayerCard(it)
        }

        monsterCards.forEach {
            cardService.saveMonsterCard(it)
        }


        val playerCardsTwo: List<PlayerCard> = listOf(
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Mental Focus",
                description = "Increase your INT by 2.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 2.0,
                effectSelfStat = TargetElementStatus.INT
            ),
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Steady Footing",
                description = "30% chance to evade attacks.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.3,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Quick Slash",
                description = "Deal 8 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 8.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Heavy Blow",
                description = "Deal 12 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 12.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Guard Stance",
                description = "Brace yourself and gain 6 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 6.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "First Aid",
                description = "Heal yourself for 10 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 10.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Sacrificial Strike",
                description = "Deal 15 damage while taking 5 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 5.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 15.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Adrenaline Rush",
                description = "Boost STR by 4 for a short time.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 4.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Distracting Shout",
                description = "Lower opponent DEX by 2.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 2.0,
                effectOpponentStat = TargetElementStatus.DEX
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Feint Step",
                description = "50% chance to evade the next attack.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.5,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Arcane Insight",
                description = "Sharpen your mind to gain 3 INT.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 3.0,
                effectSelfStat = TargetElementStatus.INT
            ),
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Tactical Retreat",
                description = "25% chance to slip away unharmed.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.25,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Piercing Strike",
                description = "Deal 11 damage with a precise blow.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 11.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Shield Bash",
                description = "Stun the foe for 6 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 6.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Fortify Armor",
                description = "Bolster your defenses, gaining 8 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Recovery Chant",
                description = "Restore 12 HP with a quick hymn.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 12.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Defiant Roar",
                description = "Weaken the foe's DEX by 2.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 2.0,
                effectOpponentStat = TargetElementStatus.DEX
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Smoke Bomb",
                description = "60% chance to evade as you disengage.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.6,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Last Stand",
                description = "Deal 18 damage but suffer 8 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 18.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Battle Cry",
                description = "Rally yourself, gaining 5 STR.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 5.0,
                effectSelfStat = TargetElementStatus.STR
            )
        )

        val monsterCardsTwo: List<MonsterCard> = listOf(
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Roar",
                description = "The monster boosts its ATK by 2.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 2.0,
                effectSelfStat = TargetElementStatus.ATK
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Skulk",
                description = "25% chance to evade an attack.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.25,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Claw Swipe",
                description = "Inflict 7 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 7.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Bite",
                description = "Inflict 9 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 9.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Tail Whip",
                description = "Inflict 5 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 5.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Thick Hide",
                description = "Gain 6 HP as protection.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 6.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Drain",
                description = "Heal 8 HP by siphoning energy.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Savage Charge",
                description = "Deal 20 damage but suffer 10 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 10.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 20.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Enrage",
                description = "Increase STR by 4 as the battle ends.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 4.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Intimidate",
                description = "Reduce opponent STR by 3.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 3.0,
                effectOpponentStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Ferocious Howl",
                description = "Raise ATK by 3 with a terrifying cry.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 3.0,
                effectSelfStat = TargetElementStatus.ATK
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Sneaky Approach",
                description = "35% chance to avoid detection.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.35,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Venom Spit",
                description = "Spray venom to deal 10 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 10.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Rending Claws",
                description = "Rip into the foe for 9 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 9.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Bone Shield",
                description = "Harden your hide to gain 7 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 7.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Regenerate",
                description = "Recover 9 HP rapidly.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 9.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Frenzy",
                description = "Deal 16 damage but take 6 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 6.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 16.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Dark Empowerment",
                description = "Surge with power, gaining 5 STR.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 5.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Terrifying Screech",
                description = "Drop opponent DEX by 3.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 3.0,
                effectOpponentStat = TargetElementStatus.DEX
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Slippery Escape",
                description = "50% chance to dodge retaliation.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.5,
                effectSelfStat = TargetElementStatus.HP
            )
        )

        playerCardsTwo.forEach {
            cardService.savePlayerCard(it)
        }

        monsterCardsTwo.forEach {
            cardService.saveMonsterCard(it)
        }


        val playerCardsThree: List<PlayerCard> = listOf(
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Mental Focus",
                description = "Increase your INT by 2.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 2.0,
                effectSelfStat = TargetElementStatus.INT
            ),
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Steady Footing",
                description = "30% chance to evade attacks.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.3,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Quick Slash",
                description = "Deal 8 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 8.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Heavy Blow",
                description = "Deal 12 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 12.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Guard Stance",
                description = "Brace yourself and gain 6 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 6.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "First Aid",
                description = "Heal yourself for 10 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 10.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Sacrificial Strike",
                description = "Deal 15 damage while taking 5 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 5.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 15.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Adrenaline Rush",
                description = "Boost STR by 4 for a short time.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 4.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Distracting Shout",
                description = "Lower opponent DEX by 2.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 2.0,
                effectOpponentStat = TargetElementStatus.DEX
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Feint Step",
                description = "50% chance to evade the next attack.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.5,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Arcane Insight",
                description = "Sharpen your mind to gain 3 INT.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 3.0,
                effectSelfStat = TargetElementStatus.INT
            ),
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Tactical Retreat",
                description = "25% chance to slip away unharmed.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.25,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Piercing Strike",
                description = "Deal 11 damage with a precise blow.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 11.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Shield Bash",
                description = "Stun the foe for 6 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 6.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Fortify Armor",
                description = "Bolster your defenses, gaining 8 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Recovery Chant",
                description = "Restore 12 HP with a quick hymn.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 12.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Defiant Roar",
                description = "Weaken the foe's DEX by 2.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 2.0,
                effectOpponentStat = TargetElementStatus.DEX
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Smoke Bomb",
                description = "60% chance to evade as you disengage.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.6,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Last Stand",
                description = "Deal 18 damage but suffer 8 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 18.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Battle Cry",
                description = "Rally yourself, gaining 5 STR.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 5.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            PlayerCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Meditative Calm",
                description = "Focus your mind to gain 2 INT.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 2.0,
                effectSelfStat = TargetElementStatus.INT
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Side Step",
                description = "40% chance to dodge an attack.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.4,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Twin Strike",
                description = "Deliver two quick hits for 13 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 13.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Reinforce Shield",
                description = "Bolster defenses, gaining 9 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 9.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Healing Surge",
                description = "Restore 14 HP in a burst of energy.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 14.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Poisoned Edge",
                description = "Lower opponent DEF by 3.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 3.0,
                effectOpponentStat = TargetElementStatus.DEF
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Flanking Maneuver",
                description = "Strike from the side for 17 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 17.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Enduring Spirit",
                description = "Bolster DEX by 3 to press on.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 3.0,
                effectSelfStat = TargetElementStatus.DEX
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Backflip Escape",
                description = "55% chance to evade a counter.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.55,
                effectSelfStat = TargetElementStatus.HP
            ),
            PlayerCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Devastating Blow",
                description = "Deal 22 damage but take 10 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 10.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 22.0,
                effectOpponentStat = TargetElementStatus.HP
            )
        )

        val monsterCardsThree: List<MonsterCard> = listOf(
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Roar",
                description = "The monster boosts its ATK by 2.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 2.0,
                effectSelfStat = TargetElementStatus.ATK
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Skulk",
                description = "25% chance to evade an attack.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.25,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Claw Swipe",
                description = "Inflict 7 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 7.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Bite",
                description = "Inflict 9 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 9.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Tail Whip",
                description = "Inflict 5 damage to the opponent.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 5.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Thick Hide",
                description = "Gain 6 HP as protection.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 6.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Drain",
                description = "Heal 8 HP by siphoning energy.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Savage Charge",
                description = "Deal 20 damage but suffer 10 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 10.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 20.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Enrage",
                description = "Increase STR by 4 as the battle ends.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 4.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Intimidate",
                description = "Reduce opponent STR by 3.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 3.0,
                effectOpponentStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Ferocious Howl",
                description = "Raise ATK by 3 with a terrifying cry.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 3.0,
                effectSelfStat = TargetElementStatus.ATK
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Sneaky Approach",
                description = "35% chance to avoid detection.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.35,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Venom Spit",
                description = "Spray venom to deal 10 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 10.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Rending Claws",
                description = "Rip into the foe for 9 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 9.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Bone Shield",
                description = "Harden your hide to gain 7 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 7.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Regenerate",
                description = "Recover 9 HP rapidly.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 9.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Frenzy",
                description = "Deal 16 damage but take 6 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 6.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 16.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Dark Empowerment",
                description = "Surge with power, gaining 5 STR.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 5.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Terrifying Screech",
                description = "Drop opponent DEX by 3.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 3.0,
                effectOpponentStat = TargetElementStatus.DEX
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Slippery Escape",
                description = "50% chance to dodge retaliation.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.5,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Rising Fury",
                description = "Increase ATK by 2.5 before battle.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 2.5,
                effectSelfStat = TargetElementStatus.ATK
            ),
            MonsterCard(
                battlePhase = BattlePhase.PREPARATION,
                name = "Hidden Movement",
                description = "30% chance to avoid detection.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.3,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Maul",
                description = "Crush the foe for 11 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 11.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Crushing Bite",
                description = "Chomp down for 13 damage.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 13.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Stone Carapace",
                description = "Harden defenses to gain 8 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.DEFENCE,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Life Siphon",
                description = "Drain energy to heal 10 HP.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.HEAL,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 10.0,
                effectSelfStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Brutal Rampage",
                description = "Deal 18 damage and take 8 yourself.",
                cardTarget = CardTarget.MUTUAL,
                cardType = CardType.ATTACK,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 8.0,
                effectSelfStat = TargetElementStatus.HP,
                effectOpponentNum = 18.0,
                effectOpponentStat = TargetElementStatus.HP
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Savage Strength",
                description = "Gain 4 STR in a final burst.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.BUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 4.0,
                effectSelfStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.ENGAGEMENT,
                name = "Curse of Weakness",
                description = "Lower opponent STR by 3.",
                cardTarget = CardTarget.OPPONENT,
                cardType = CardType.DEBUFF,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectOpponentNum = 3.0,
                effectOpponentStat = TargetElementStatus.STR
            ),
            MonsterCard(
                battlePhase = BattlePhase.FINALIZATION,
                name = "Moonlit Escape",
                description = "55% chance to flee unharmed.",
                cardTarget = CardTarget.SELF,
                cardType = CardType.EVASION,
                cardAttribute = CardAttribute.NONE,
                cost = 0,
                effectSelfNum = 0.55,
                effectSelfStat = TargetElementStatus.HP
            )
        )

        playerCardsThree.forEach {
            cardService.savePlayerCard(it)
        }

        monsterCardsThree.forEach {
            cardService.saveMonsterCard(it)
        }


    }

    @PostConstruct
    fun initField(){
        fieldAreaRepository.save(
            FieldArea(
                name = "ForestArea",
                description = "깊고 넓은 숲",
                fieldType = FieldType.FOREST,
            )
        )

        fieldAreaRepository.save(
            FieldArea(
                name = "VolcanoArea",
                description = "용암이 있는 화산 지역",
                fieldType = FieldType.VOLCANO,
            )
        )

        fieldAreaRepository.save(
            FieldArea(
                name = "GlacierArea",
                description = "얼음과 눈으로 뒤덮인 빙하 지역",
                fieldType = FieldType.GLACIER,
            )
        )

        fieldAreaRepository.save(
            FieldArea(
                name = "CoastalArea",
                description = "바다와 해변이 있는 해안 지역",
                fieldType = FieldType.COAST,
            )
        )

        fieldAreaRepository.save(
            FieldArea(
                name = "DesertArea",
                description = "뜨겁고 건조한 사막 지역",
                fieldType = FieldType.DESERT,
            )
        )
    }


}