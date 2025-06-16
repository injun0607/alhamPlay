package kr.alham.playground

import jakarta.annotation.PostConstruct
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.EquipmentType
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.Material
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import org.springframework.stereotype.Component

@Component
class TestComponentSetting(
    private val equipmentRepository: EquipmentRepository,
    private val materialRepository: MaterialRepository
) {
    @PostConstruct
    fun init() {
        /**
         * 숲재료
         */
        materialRepository.save(
            Material(
                name = "Twisted Vine Fiber",
                description = "고목을 휘감은 덩굴의 섬유. 튼튼하고 유연하여 방어구 바인딩에 적합하다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Amber Sap",
                description = "수백 년 된 나무에서 뽑은 호박 수액. 마력 전달의 매개로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Direwolf Hide",
                description = "숲의 포식자 디어울프의 가죽. 가볍지만 뛰어난 내구성을 지닌다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Oakheart Shard",
                description = "정령나무의 단단한 중심부 조각. 근거리 무기의 손잡이에 자주 쓰인다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Luminous Spores",
                description = "밤에 빛나는 버섯의 포자. 마법 장비에 빛나는 효과를 부여할 수 있다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Fernscale Bark",
                description = "양치식물의 껍질을 강화해 만든 재료. 가볍고 내구성이 뛰어나다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Wolfroot Tendon",
                description = "숲 늑대의 힘줄. 활 시위나 채찍 손잡이에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Elderseed Resin",
                description = "고목의 씨앗에서 추출한 끈적한 수지. 마법 전도에 좋다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Barkwyrm Spine",
                description = "나무 드래곤의 척추 조각. 검이나 창의 핵심 재료로 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Sylvan Heart Core",
                description = "숲의 정령들이 지키던 생명의 핵. 전설급 장비의 중심 재료다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Dryleaf Bundle",
                description = "바싹 마른 나뭇잎 뭉치. 저급 가죽 재료 대체용으로 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Twigknit Cord",
                description = "가는 가지를 꼬아 만든 끈. 장비 바인딩에 적합하다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Sporecap Dust",
                description = "버섯 모자의 가루. 마법 도료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Thornvine Stem",
                description = "가시넝쿨의 줄기. 적을 구속하는 마법에 응용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Timberwolf Fang",
                description = "숲의 사냥꾼 송곳니. 무기 재료로 사용 시 출혈 효과를 강화한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Glowlily Petal",
                description = "밤에 빛나는 꽃잎. 장비에 야간 시야 효과를 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Sylvan Boneplate",
                description = "숲의 고대 짐승의 뼈 판. 강력한 방어구 재료다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Echoing Bark",
                description = "소리를 흡수하는 나무껍질. 은신 특화 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Ancestor’s Sap",
                description = "정령 나무의 피와 같은 수액. 회복 능력을 강화시킨다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Verdant Sigil Stone",
                description = "숲의 고대 마법이 봉인된 인장석. 자연 속성 강화에 사용된다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Barkcloth Strip",
                description = "얇은 나무껍질을 가공한 천. 기본 방어구의 내피로 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Dewdrop Pearl",
                description = "새벽 이슬이 맺힌 듯한 작은 수정. 마법 장비 기초 재료다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Mushcap Sponge",
                description = "버섯 윗부분에서 채취한 스펀지. 재료 결합에 쓰이는 흡수 재료.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Vinesilk Thread",
                description = "덩굴에서 뽑아낸 실. 유연성과 강도가 우수하다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Thistle Dust",
                description = "가시풀을 갈아 만든 가루. 무기 마법 부여에 종종 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Barkling Jawbone",
                description = "숲의 소형 수호정령 턱뼈. 악세사리 제작에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Lifebloom Nectar",
                description = "희귀 식물의 꽃꿀. 회복 장비 제작에 적합하다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Willowfang Chip",
                description = "버드나무 야수의 이빨 조각. 마법 무기 강화에 사용된다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Dryad's Whisper Leaf",
                description = "드라이어드가 속삭였다는 전설의 잎사귀. 장비에 정령 효과를 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Ancient Thorn Core",
                description = "가시의 중심부 결정체. 강력한 출혈 속성을 부여한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Spiritbark Resin",
                description = "정령나무에서만 얻을 수 있는 희귀 수지. 고급 마법 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Moonroot Shard",
                description = "달빛에 노출된 뿌리에서 추출한 결정. 야간 효과가 있다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Heartwood Branch",
                description = "숲의 정령 나무 핵에서 뽑아낸 가지. 전설급 지팡이 재료.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Elderleaf Emblem",
                description = "고대 숲의 권위를 상징하는 잎 문장. 장비에 자연 속성 최고 강화 효과.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Songstone Fragment",
                description = "자연의 노래가 담긴 수정 조각. 회복/버프계 장비의 핵심 재료.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )


        materialRepository.save(
            Material(
                name = "Twisted Vine Fiber",
                description = "고목을 휘감은 덩굴의 섬유. 튼튼하고 유연하여 방어구 바인딩에 적합하다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Amber Sap",
                description = "수백 년 된 나무에서 뽑은 호박 수액. 마력 전달의 매개로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Direwolf Hide",
                description = "숲의 포식자 디어울프의 가죽. 가볍지만 뛰어난 내구성을 지닌다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Oakheart Shard",
                description = "정령나무의 단단한 중심부 조각. 근거리 무기의 손잡이에 자주 쓰인다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Luminous Spores",
                description = "밤에 빛나는 버섯의 포자. 마법 장비에 빛나는 효과를 부여할 수 있다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Fernscale Bark",
                description = "양치식물의 껍질을 강화해 만든 재료. 가볍고 내구성이 뛰어나다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Wolfroot Tendon",
                description = "숲 늑대의 힘줄. 활 시위나 채찍 손잡이에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Elderseed Resin",
                description = "고목의 씨앗에서 추출한 끈적한 수지. 마법 전도에 좋다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Barkwyrm Spine",
                description = "나무 드래곤의 척추 조각. 검이나 창의 핵심 재료로 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Sylvan Heart Core",
                description = "숲의 정령들이 지키던 생명의 핵. 전설급 장비의 중심 재료다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Dryleaf Bundle",
                description = "바싹 마른 나뭇잎 뭉치. 저급 가죽 재료 대체용으로 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Twigknit Cord",
                description = "가는 가지를 꼬아 만든 끈. 장비 바인딩에 적합하다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Sporecap Dust",
                description = "버섯 모자의 가루. 마법 도료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Thornvine Stem",
                description = "가시넝쿨의 줄기. 적을 구속하는 마법에 응용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Timberwolf Fang",
                description = "숲의 사냥꾼 송곳니. 무기 재료로 사용 시 출혈 효과를 강화한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Glowlily Petal",
                description = "밤에 빛나는 꽃잎. 장비에 야간 시야 효과를 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Sylvan Boneplate",
                description = "숲의 고대 짐승의 뼈 판. 강력한 방어구 재료다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Echoing Bark",
                description = "소리를 흡수하는 나무껍질. 은신 특화 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Ancestor’s Sap",
                description = "정령 나무의 피와 같은 수액. 회복 능력을 강화시킨다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Verdant Sigil Stone",
                description = "숲의 고대 마법이 봉인된 인장석. 자연 속성 강화에 사용된다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Barkcloth Strip",
                description = "얇은 나무껍질을 가공한 천. 기본 방어구의 내피로 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Dewdrop Pearl",
                description = "새벽 이슬이 맺힌 듯한 작은 수정. 마법 장비 기초 재료다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Mushcap Sponge",
                description = "버섯 윗부분에서 채취한 스펀지. 재료 결합에 쓰이는 흡수 재료.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Vinesilk Thread",
                description = "덩굴에서 뽑아낸 실. 유연성과 강도가 우수하다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Thistle Dust",
                description = "가시풀을 갈아 만든 가루. 무기 마법 부여에 종종 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Barkling Jawbone",
                description = "숲의 소형 수호정령 턱뼈. 악세사리 제작에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Lifebloom Nectar",
                description = "희귀 식물의 꽃꿀. 회복 장비 제작에 적합하다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Willowfang Chip",
                description = "버드나무 야수의 이빨 조각. 마법 무기 강화에 사용된다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Dryad's Whisper Leaf",
                description = "드라이어드가 속삭였다는 전설의 잎사귀. 장비에 정령 효과를 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Ancient Thorn Core",
                description = "가시의 중심부 결정체. 강력한 출혈 속성을 부여한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Spiritbark Resin",
                description = "정령나무에서만 얻을 수 있는 희귀 수지. 고급 마법 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Moonroot Shard",
                description = "달빛에 노출된 뿌리에서 추출한 결정. 야간 효과가 있다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Heartwood Branch",
                description = "숲의 정령 나무 핵에서 뽑아낸 가지. 전설급 지팡이 재료.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Elderleaf Emblem",
                description = "고대 숲의 권위를 상징하는 잎 문장. 장비에 자연 속성 최고 강화 효과.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Songstone Fragment",
                description = "자연의 노래가 담긴 수정 조각. 회복/버프계 장비의 핵심 재료.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )
        /**
         * 사막 재료
         */

        materialRepository.save(
            Material(
                name = "Twisted Vine Fiber",
                description = "고목을 휘감은 덩굴의 섬유. 튼튼하고 유연하여 방어구 바인딩에 적합하다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Amber Sap",
                description = "수백 년 된 나무에서 뽑은 호박 수액. 마력 전달의 매개로 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Direwolf Hide",
                description = "숲의 포식자 디어울프의 가죽. 가볍지만 뛰어난 내구성을 지닌다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Oakheart Shard",
                description = "정령나무의 단단한 중심부 조각. 근거리 무기의 손잡이에 자주 쓰인다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Luminous Spores",
                description = "밤에 빛나는 버섯의 포자. 마법 장비에 빛나는 효과를 부여할 수 있다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Fernscale Bark",
                description = "양치식물의 껍질을 강화해 만든 재료. 가볍고 내구성이 뛰어나다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Wolfroot Tendon",
                description = "숲 늑대의 힘줄. 활 시위나 채찍 손잡이에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Elderseed Resin",
                description = "고목의 씨앗에서 추출한 끈적한 수지. 마법 전도에 좋다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Barkwyrm Spine",
                description = "나무 드래곤의 척추 조각. 검이나 창의 핵심 재료로 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Sylvan Heart Core",
                description = "숲의 정령들이 지키던 생명의 핵. 전설급 장비의 중심 재료다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Dryleaf Bundle",
                description = "바싹 마른 나뭇잎 뭉치. 저급 가죽 재료 대체용으로 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Twigknit Cord",
                description = "가는 가지를 꼬아 만든 끈. 장비 바인딩에 적합하다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Sporecap Dust",
                description = "버섯 모자의 가루. 마법 도료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Thornvine Stem",
                description = "가시넝쿨의 줄기. 적을 구속하는 마법에 응용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Timberwolf Fang",
                description = "숲의 사냥꾼 송곳니. 무기 재료로 사용 시 출혈 효과를 강화한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Glowlily Petal",
                description = "밤에 빛나는 꽃잎. 장비에 야간 시야 효과를 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Sylvan Boneplate",
                description = "숲의 고대 짐승의 뼈 판. 강력한 방어구 재료다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Echoing Bark",
                description = "소리를 흡수하는 나무껍질. 은신 특화 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Ancestor’s Sap",
                description = "정령 나무의 피와 같은 수액. 회복 능력을 강화시킨다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Verdant Sigil Stone",
                description = "숲의 고대 마법이 봉인된 인장석. 자연 속성 강화에 사용된다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Barkcloth Strip",
                description = "얇은 나무껍질을 가공한 천. 기본 방어구의 내피로 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Dewdrop Pearl",
                description = "새벽 이슬이 맺힌 듯한 작은 수정. 마법 장비 기초 재료다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Mushcap Sponge",
                description = "버섯 윗부분에서 채취한 스펀지. 재료 결합에 쓰이는 흡수 재료.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Vinesilk Thread",
                description = "덩굴에서 뽑아낸 실. 유연성과 강도가 우수하다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Thistle Dust",
                description = "가시풀을 갈아 만든 가루. 무기 마법 부여에 종종 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Barkling Jawbone",
                description = "숲의 소형 수호정령 턱뼈. 악세사리 제작에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Lifebloom Nectar",
                description = "희귀 식물의 꽃꿀. 회복 장비 제작에 적합하다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Willowfang Chip",
                description = "버드나무 야수의 이빨 조각. 마법 무기 강화에 사용된다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Dryad's Whisper Leaf",
                description = "드라이어드가 속삭였다는 전설의 잎사귀. 장비에 정령 효과를 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Ancient Thorn Core",
                description = "가시의 중심부 결정체. 강력한 출혈 속성을 부여한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Spiritbark Resin",
                description = "정령나무에서만 얻을 수 있는 희귀 수지. 고급 마법 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Moonroot Shard",
                description = "달빛에 노출된 뿌리에서 추출한 결정. 야간 효과가 있다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Heartwood Branch",
                description = "숲의 정령 나무 핵에서 뽑아낸 가지. 전설급 지팡이 재료.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Elderleaf Emblem",
                description = "고대 숲의 권위를 상징하는 잎 문장. 장비에 자연 속성 최고 강화 효과.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Songstone Fragment",
                description = "자연의 노래가 담긴 수정 조각. 회복/버프계 장비의 핵심 재료.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        /**
         * 빙하지역 재료
         */
        materialRepository.save(
            Material(
                name = "Frostscale Plate",
                description = "빙설 도마뱀의 비늘 판. 냉기 저항을 높이는 재료다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Icicle Thread",
                description = "눈꽃에서 뽑아낸 실. 마법 로브에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Glacier Core Dust",
                description = "빙하의 중심에서 채취한 결정 가루. 냉기 속성 강화에 쓰인다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Snow Leopard Pelt",
                description = "차가운 대지를 지배하는 야수의 가죽. 은신에 특화된 장비에 사용된다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Shiverroot Bark",
                description = "추위에 견디는 마법 나무의 껍질. 방어구 내피로 이상적이다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Frostbirch Leaf",
                description = "냉기 속에서만 자라는 자작나무 잎. 약한 냉기 저항력을 지닌다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Icetooth Fragment",
                description = "빙설 늑대의 송곳니 조각. 날카로운 무기의 소재로 이상적이다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Frozen Marrow",
                description = "냉동된 야수의 골수. 물리 방어에 강한 성질을 띤다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Glacier Soul Dust",
                description = "빙하에 갇힌 정령의 재. 장비에 냉기 마법을 부여한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Crownice Crystal",
                description = "얼음의 군주가 남긴 수정. 절대 냉기를 품고 있다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Cracked Iceflake",
                description = "얇은 얼음 조각. 낮은 등급 냉기 장비 재료.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Snowsilt Cloth",
                description = "눈에서 짜낸 섬유. 추위 방지 효과가 있다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Woolly Beast Fur",
                description = "빙설지대 야수의 털. 방한복 제작에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Chillglass Bead",
                description = "차가운 유리 결정. 마법 아이템에 주로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Frozen Spine Shard",
                description = "냉기 짐승의 척추 조각. 방어구에 강한 냉기 저항을 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Icewrought Resin",
                description = "얼어붙은 나무 수액. 장비 표면을 단단하게 코팅한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Glacial Wind Core",
                description = "빙하 속 바람의 핵. 속도 증가 장비 제작에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Cryo Sigil Dust",
                description = "냉기 룬의 잔해. 강력한 마법 장비 강화용이다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Silent Blizzard Thread",
                description = "소리 없이 휘몰아치는 눈보라에서 채취한 실. 은신 장비에 적합하다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Frozen Eternity Core",
                description = "시간조차 멈춘 얼음의 결정체. 영구 냉기 효과를 지닌다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Snowhare Fur",
                description = "눈 토끼의 따뜻한 털. 방한 장비의 내피로 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Icicle Fragment",
                description = "뾰족한 고드름 조각. 기본 냉기 속성 장비에 쓰인다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Frostnut Husk",
                description = "냉기 식물의 외피. 마법 재료 보조용.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Chillhide Strip",
                description = "차가운 짐승 가죽을 얇게 벗겨낸 조각. 갑옷 보강용.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Permafrost Thread",
                description = "녹지 않는 실. 마법 로브 강화용으로 이상적이다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Frostshard Dust",
                description = "분쇄된 얼음 수정. 마법 속성 부여에 사용된다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Snowwraith Eye",
                description = "설령의 눈동자. 냉기 속성 극대화에 필수.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Chillfang Chip",
                description = "빙설 표범의 이빨 파편. 날붙이에 빙결 효과 부여.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Cryogenic Oil",
                description = "냉기 유지용 기름. 장비의 온도를 조절한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Wendigo Spine Shard",
                description = "빙설 괴물의 척추 조각. 방어구에 광역 냉기 저항 부여.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Frozen Time Echo",
                description = "빙결된 시간의 잔재. 시간 지연/방어 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Moonice Crystal",
                description = "달빛을 반사하는 얼음 수정. 회복+냉기 하이브리드 장비용.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Crownshard of Frostmaiden",
                description = "냉기의 여왕이 남긴 왕관 조각. 마법 집중 속성을 부여한다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Absolute Zero Core",
                description = "절대영도의 핵심. 모든 생명체의 움직임을 멈추게 할 수 있다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Aurorafrost Gem",
                description = "오로라의 얼음에서 탄생한 보석. 냉기 속성의 최고 등급 재료.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        /**
         * 화산 지역 재료
         */
        materialRepository.save(
            Material(
                name = "Charstone Ore",
                description = "불의 심장에서 채굴한 암석. 무기 강화에 필수적이다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Obsidian Flake",
                description = "화산암의 얇은 조각. 날붙이 무기에 치명적인 예리함을 부여한다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Smoldering Ashdust",
                description = "끊임없이 연기 나는 재. 화염 마법 부여용으로 인기다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Flamebeast Tendon",
                description = "화염 짐승의 힘줄. 활과 채찍 같은 장비에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Cinderhide",
                description = "불에 그을린 짐승의 가죽. 열에 강하고 유연하다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Coalcrack Pebble",
                description = "갈라진 석탄 조각. 기본적인 화속성 부여 재료다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Firebeast Horn Chip",
                description = "화염 야수의 뿔 조각. 강도 높은 장비 강화에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Smeltcore Oil",
                description = "용광로 바닥에서 추출된 오일. 금속 가공의 핵심이다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Volcanic Emberheart",
                description = "화산 심장에서 꺼낸 불덩어리. 공격 속성에 불을 더한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Infernal Spine Segment",
                description = "지옥 불꽃 속 생물의 척추. 고대 장비에 쓰이는 귀중한 파편.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Charred Husk Fragment",
                description = "불에 탄 생물의 잔해. 저급 장비의 연료나 부품으로 쓰인다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Smokestone Dust",
                description = "화산재가 응결된 가루. 연막 효과 장비에 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Ironblaze Scale",
                description = "불속성 도마뱀의 비늘. 내구성이 뛰어나며 공격력을 높인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Fumehide Scrap",
                description = "유황가스에 그을린 가죽. 방어구의 내화성 강화를 도와준다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Flametail Whipcord",
                description = "불꼬리 생물의 신경줄기. 채찍류 무기 제작에 활용된다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Pyrocrystal Core",
                description = "마그마 결정의 심장부. 화염 속성 장비의 핵심 재료.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Inferno Chimera Fang",
                description = "지옥불 키메라의 송곳니. 공격 장비에 속성 폭발 효과를 부여한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Eruption Stone Scale",
                description = "분화하는 화산석에서 얻은 비늘. 방어구에 충격 저항을 더한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Ashen Phoenix Feather",
                description = "불사조의 재에서 얻은 깃털. 착용 장비에 회생 효과를 부여한다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Molten Crown Fragment",
                description = "화염 군주의 왕관 파편. 전설 무기에 불의 의지를 부여한다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Ashen Horn Chip",
                description = "불에 그을린 짐승의 뿔 조각. 저급 무기 장식용으로 쓰인다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Lavasoot Lump",
                description = "용암 가루가 뭉쳐 생긴 덩어리. 기본 내열 강화 재료다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Crackstone Flake",
                description = "지각 균열에서 채취한 석편. 방어구 강화에 소량 사용된다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Emberworm Scale",
                description = "작은 화염 벌레의 비늘. 장비에 열기 전달 효과를 부여한다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Firefang Residue",
                description = "불속성 생물의 이빨 잔해. 검의 칼날 마법 도료로 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Sulfurhide Strip",
                description = "유황 연기 속에서 채취한 가죽 조각. 방독 효과를 제공한다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Blazecore Fragment",
                description = "불의 심장이라 불리는 결정의 일부. 장비에 마나 연소 속성을 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Flare Beetle Shell",
                description = "폭발성 곤충의 등껍질. 공격 장비 강화에 사용된다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Molten Vein Fiber",
                description = "용암이 흐르는 광맥에서 채취한 섬유. 유연성과 내구성이 뛰어나다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Inferno Drake Claw",
                description = "화염 드레이크의 발톱. 파괴력 강화용 고급 재료.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Obsidian Core Chunk",
                description = "용암에서 응결된 핵심 암석. 방어구에 불반사 효과를 부여한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Volcanite Thread",
                description = "화염정령의 힘이 깃든 실. 고열에도 끄떡없는 마법 재료.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Blazing Crown Shard",
                description = "화염 군주의 왕관 파편. 착용 시 일정 확률로 화염구 발동.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Hellfire Sigil",
                description = "지옥불의 상징 문양. 전설급 무기와 방어구에 불타는 오라를 부여한다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Core of Eruption",
                description = "분출의 근원. 타격 시 지면을 흔드는 폭발 효과를 생성한다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        /**
         * 해안 지역 재료
         */

        materialRepository.save(
            Material(
                name = "Nautilus Shell Chip",
                description = "소용돌이 무늬가 아름다운 패각 조각. 장신구에 자주 쓰인다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Coral Thread",
                description = "산호초에서 얻은 가늘고 질긴 실. 방어구 스티칭에 적합하다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Krakkin Ink Resin",
                description = "심해 괴수의 검은 수지. 마법 봉인을 위한 촉매다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Salted Sharkskin",
                description = "거친 상어 가죽. 빠른 회피형 장비에 적합하다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Deepwater Gem",
                description = "심해의 압력을 견디고 생성된 보석. 마력 집중에 유리하다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Seagrass Thread",
                description = "질긴 바닷풀을 엮은 실. 방어구의 스티칭 재료로 쓰인다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Shellscale Flake",
                description = "대형 갑각류의 껍데기 조각. 방어구의 외장재로 적합하다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Echo Pearl Dust",
                description = "심해 진주의 가루. 장비에 마나 회복 효과를 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Tidebeast Tendon",
                description = "파도 짐승의 힘줄. 유연하면서도 매우 단단하다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Kraken's Breath Core",
                description = "심연에서 나온 공기의 핵. 전설급 무기에 바다의 분노를 담는다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Kelpwrap Fiber",
                description = "해조류 섬유. 유연하고 바닷물에 강하다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Crustacean Fang",
                description = "딱딱한 게 송곳니. 작은 장신구 강화 재료다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Tideglass Shard",
                description = "물결처럼 휘는 유리 조각. 마법 반사 장비에 쓰인다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Brinebeast Hide",
                description = "염수에 사는 짐승의 가죽. 염분에 강한 방어구 제작에 적합하다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Octovoid Ink Sack",
                description = "심해 문어의 먹물 주머니. 혼란 속성 장비에 응용된다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Pearled Claw Tip",
                description = "진주처럼 광택나는 집게발 끝. 고급 무기 장식용 재료.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Sea Spirit Fragment",
                description = "바다의 정령이 남긴 파편. 치유/보호 계열 장비에 주로 사용된다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Abyssal Nerve Cord",
                description = "심연 생물의 신경 다발. 고급 장신구 제작에 적합하다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Trenchwhale Spine",
                description = "해구 고래의 척추 뼈. 전설 갑옷의 기틀을 이룬다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Stormcore Pearl",
                description = "폭풍의 중심에서 자라난 진주. 물과 번개 속성을 동시에 부여한다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Sandstone Coral Chip",
                description = "바닷가 산호초의 돌기. 방어구 표면 강화에 쓰인다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Bubblestone Pebble",
                description = "파도에 깎인 둥근 돌. 장신구 장식에 쓰이는 값싼 재료.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Salted Shell Dust",
                description = "조개껍질을 간 가루. 마법 장비 보조 재료다.",
                itemRarity = ItemRarity.COMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Eelskin Strap",
                description = "전기 뱀장어의 가죽 띠. 유연성과 전기 저항을 동시에 제공한다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Kelpink Blob",
                description = "심해 해조류에서 추출한 점액. 방어구의 미끄럼 방지 기능을 향상시킨다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Coralbone Fragment",
                description = "산호와 뼈가 뒤섞인 조각. 공격 무기 강화에 적합하다.",
                itemRarity = ItemRarity.UNCOMMON
            )
        )

        materialRepository.save(
            Material(
                name = "Tide Elemental Core",
                description = "조수의 흐름을 따르는 정령의 핵. 마법 장비에 흐름 기반 효과를 부여한다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Pearlglass Lens",
                description = "진주처럼 투명한 렌즈. 시야 확대 및 명중 보정 장비에 사용된다.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Sailfish Fin Blade",
                description = "돛돔의 등지느러미를 칼날처럼 가공한 재료. 찌르기형 무기 강화에 특화.",
                itemRarity = ItemRarity.RARE
            )
        )

        materialRepository.save(
            Material(
                name = "Stormwhale Blubber",
                description = "폭풍고래의 지방. 물리/전기 피해 흡수 방어구에 쓰인다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Abyss Ink Crystal",
                description = "심연 문어의 먹물을 굳힌 결정. 혼란 마법 장비에 사용된다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Siren Scale Flake",
                description = "세이렌의 비늘 파편. 장비에 매혹 또는 방해 효과를 부여한다.",
                itemRarity = ItemRarity.EPIC
            )
        )

        materialRepository.save(
            Material(
                name = "Trench Leviathan Bone",
                description = "해구의 거대한 생물의 뼈. 전설 갑옷의 척추 강화 소재다.",
                itemRarity = ItemRarity.UNIQUE
            )
        )

        materialRepository.save(
            Material(
                name = "Crest of the Deep King",
                description = "심해 군주의 문장. 바다 속성 전설 장비에만 사용되는 최고급 재료.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

        materialRepository.save(
            Material(
                name = "Heart of the Maelstrom",
                description = "거대한 소용돌이 중심에서 채취한 심장. 물+바람 복합 속성을 부여한다.",
                itemRarity = ItemRarity.LEGENDARY
            )
        )

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
}