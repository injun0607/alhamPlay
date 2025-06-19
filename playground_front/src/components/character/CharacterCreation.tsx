'use client'

import { useState } from 'react';
import { motion } from 'framer-motion';
import { useCharacterStore } from '@/store/characterStore';
import { useRouter } from 'next/navigation';

interface RegionChoice {
  id: number;
  name: string;
  description: string;
  location: string;
  stats: {
    strength: number;
    intelligence: number;
    dexterity: number;
    vitality: number;
    luck: number;
    mana: number;
  };
  items: Array<{
    name: string;
    quantity: number;
  }>;
}

const REGION_CHOICES: RegionChoice[] = [
  {
    id: 1,
    name: "불의 숨결",
    description: "불타는 도시에서 검을 움켜쥐고 서 있는 너",
    location: "화산지대",
    stats: {
      strength: 2,
      intelligence: 0,
      dexterity: 0,
      vitality: 0,
      luck: 0,
      mana: 1
    },
    items: [
      { name: "잿빛 단검", quantity: 1 },
      { name: "조악한 용암석", quantity: 1 }
    ]
  },
  {
    id: 2,
    name: "잊힌 모래 왕국",
    description: "사막의 유적 앞에서 바람을 가르는 너",
    location: "사막지대",
    stats: {
      strength: 0,
      intelligence: 2,
      dexterity: 0,
      vitality: 0,
      luck: 1,
      mana: 0
    },
    items: [
      { name: "바람의 천조각", quantity: 1 },
      { name: "거친 모래가루", quantity: 1 }
    ]
  },
  {
    id: 3,
    name: "에메랄드 심장",
    description: "깊은 숲에서 눈을 감고 숨죽인 너",
    location: "숲지대",
    stats: {
      strength: 0,
      intelligence: 0,
      dexterity: 2,
      vitality: 1,
      luck: 0,
      mana: 0
    },
    items: [
      { name: "엉성한 나무껍질", quantity: 1 },
      { name: "정령 약초", quantity: 1 }
    ]
  },
  {
    id: 4,
    name: "영원의 숨결",
    description: "하얀 눈밭 위, 쓰러진 몸을 일으키는 너",
    location: "빙하지대",
    stats: {
      strength: 0,
      intelligence: 1,
      dexterity: 0,
      vitality: 2,
      luck: 0,
      mana: 0
    },
    items: [
      { name: "서리 칼날", quantity: 1 },
      { name: "얼어붙은 결정편", quantity: 1 }
    ]
  },
  {
    id: 5,
    name: "망각의 균열",
    description: "어두운 동굴, 손끝으로 벽을 더듬는 너",
    location: "지하/동굴",
    stats: {
      strength: 0,
      intelligence: 0,
      dexterity: 1,
      vitality: 0,
      luck: 2,
      mana: 0
    },
    items: [
      { name: "균열 파편", quantity: 1 },
      { name: "수상한 고서", quantity: 1 }
    ]
  },
  {
    id: 6,
    name: "천둥의 지평선",
    description: "번개 치는 고원, 칼끝에 피를 묻힌 너",
    location: "번개고원",
    stats: {
      strength: 1,
      intelligence: 0,
      dexterity: 2,
      vitality: 0,
      luck: 0,
      mana: 0
    },
    items: [
      { name: "방전된 금속조각", quantity: 1 },
      { name: "전기 부적", quantity: 1 }
    ]
  }
];

export const CharacterCreation = () => {
  const router = useRouter();
  const { setCharacterStats, addItemToInventory, setCharacterName } = useCharacterStore();
  const [name, setName] = useState("");
  const [selectedRegion, setSelectedRegion] = useState<RegionChoice | null>(null);
  const [error, setError] = useState("");

  const handleRegionSelect = (region: RegionChoice) => {
    setSelectedRegion(region);
  };

  const handleSubmit = () => {
    if (!name.trim()) {
      setError("이름을 입력해주세요");
      return;
    }
    if (!selectedRegion) {
      setError("지역을 선택해주세요");
      return;
    }

    // 캐릭터 이름 설정
    setCharacterName(name);

    // 스탯 설정
    setCharacterStats(selectedRegion.stats);

    // 아이템 추가
    selectedRegion.items.forEach(item => {
      addItemToInventory(item.name, item.quantity);
    });

    // 튜토리얼 전투 페이지로 라우팅
    router.push('/battle/tutorial');
  };

  return (
    <div className="fixed inset-0 bg-black flex items-center justify-center">
      <div className="bg-gray-900 p-8 rounded-lg shadow-xl max-w-4xl w-full mx-4">
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          className="text-white"
        >
          <h2 className="text-2xl font-bold mb-6 text-center">기억 조각 - 성향 선택</h2>
          
          <div className="mb-6">
            <label className="block text-sm font-medium mb-2">이름을 입력하세요</label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              className="w-full px-4 py-2 bg-gray-800 rounded border border-gray-700 focus:outline-none focus:border-blue-500"
              placeholder="캐릭터 이름"
            />
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {REGION_CHOICES.map((region) => (
              <motion.div
                key={region.id}
                className={`p-4 rounded-lg cursor-pointer ${
                  selectedRegion?.id === region.id
                    ? 'bg-blue-600'
                    : 'bg-gray-800 hover:bg-gray-700'
                }`}
                onClick={() => handleRegionSelect(region)}
                whileHover={{ scale: 1.02 }}
                whileTap={{ scale: 0.98 }}
              >
                <h3 className="text-xl font-bold mb-2">{region.name}</h3>
                <p className="text-gray-300 mb-2">{region.description}</p>
                <p className="text-sm text-gray-400 mb-2">위치: {region.location}</p>
                
                <div className="text-sm">
                  <h4 className="font-medium mb-1">기본 스탯:</h4>
                  <div className="grid grid-cols-2 gap-2">
                    {Object.entries(region.stats).map(([stat, value]) => (
                      value > 0 && (
                        <div key={stat} className="text-blue-300">
                          {stat}: +{value}
                        </div>
                      )
                    ))}
                  </div>
                </div>

                <div className="mt-2 text-sm">
                  <h4 className="font-medium mb-1">시작 아이템:</h4>
                  <div className="text-gray-400">
                    {region.items.map((item, index) => (
                      <div key={index}>
                        {item.name} x{item.quantity}
                      </div>
                    ))}
                  </div>
                </div>
              </motion.div>
            ))}
          </div>

          {error && (
            <motion.p
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              className="text-red-500 mt-4 text-center"
            >
              {error}
            </motion.p>
          )}

          <motion.div className="mt-6 text-center">
            <motion.button
              onClick={handleSubmit}
              className="px-8 py-3 bg-blue-600 rounded-lg text-lg font-semibold hover:bg-blue-700 transition-colors"
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
            >
              선택 완료
            </motion.button>
          </motion.div>
        </motion.div>
      </div>
    </div>
  );
}; 