'use client'

import { useState, Fragment, useCallback, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { useBattleStore } from '@/store/battleStore';
import { Card, PhaseType, BattlePhase } from '@/types/battle';
import { FaArrowRight } from 'react-icons/fa';
import { tsParticles } from "@tsparticles/engine";
import { loadSlim } from "@tsparticles/slim";
import type { Container, Engine } from "@tsparticles/engine";

interface BattlePhaseScreenProps {
  phaseType: PhaseType;
  onPhaseComplete?: () => void;
  onBack?: () => void;
}

interface OrderedCard extends Card {
  order?: number;
  manaCost?: number; // 카드별 마나 소모량
}

const PhaseTitle: Record<PhaseType, string> = {
  PHASE1: '전투 준비 페이즈',
  PHASE2: '전투 페이즈',
  PHASE3: '결전 페이즈'
};

// 튜토리얼용 임시 마나 설정
const TUTORIAL_MAX_MANA = 10;
const CARD_MANA_COST = 2; // 각 카드의 기본 마나 소모량

export const BattlePhaseScreen = ({ phaseType, onPhaseComplete, onBack }: BattlePhaseScreenProps) => {
  const { battleState, userCards, setPhaseCards, completePhase, moveToPhase } = useBattleStore();
  const [selectedCards, setSelectedCards] = useState<OrderedCard[]>([]);
  const [currentMana, setCurrentMana] = useState(TUTORIAL_MAX_MANA);
  const [isCompleting, setIsCompleting] = useState(false);

  const availableCards = userCards.filter(card => card.phaseType === phaseType);
  const currentPhaseState = battleState[phaseType.toLowerCase() as keyof typeof battleState] as BattlePhase;

  // 이전 상태 복원을 위한 useEffect
  useEffect(() => {
    const phaseKey = phaseType.toLowerCase() as keyof typeof battleState;
    const currentState = battleState[phaseKey] as BattlePhase;
    if (currentState && Array.isArray(currentState.selectedCards) && currentState.selectedCards.length > 0) {
      setSelectedCards(currentState.selectedCards as OrderedCard[]);
    } else {
      setSelectedCards([]);
    }
  }, [phaseType, battleState]);

  // 현재 사용한 총 마나 계산
  const usedMana = selectedCards.length * CARD_MANA_COST;
  const remainingMana = TUTORIAL_MAX_MANA - usedMana;

  const handleCardSelect = (card: Card) => {
    if (phaseType === 'PHASE2') {
      // 마나 체크
      if (remainingMana < CARD_MANA_COST) {
        return; // 마나가 부족하면 선택 불가
      }
      // 새로운 카드 추가 (순서 정보와 마나 소모량 포함)
      setSelectedCards(prev => [...prev, { ...card, order: prev.length + 1, manaCost: CARD_MANA_COST }]);
    } else {
      // 페이즈 1, 3: 단일 선택
      if (selectedCards.find(c => c.id === card.id)) {
        setSelectedCards([]);
      } else {
        setSelectedCards([{ ...card }]);
      }
    }
  };

  const handleRemoveCard = (index: number) => {
    setSelectedCards(prev => prev.filter((_, i) => i !== index));
  };

  const handleConfirm = async () => {
    setIsCompleting(true);
    
    // 페이즈 데이터 저장
    setPhaseCards(phaseType, selectedCards);
    
    // 페이드 아웃을 위한 지연
    await new Promise(resolve => setTimeout(resolve, 800));
    
    // 페이즈 완료 처리
    completePhase(phaseType);
    
    // 완료 메시지 표시 후 다음 페이즈로 이동
    await new Promise(resolve => setTimeout(resolve, 300));
    setIsCompleting(false);
    
    // 커스텀 완료 핸들러 호출
    onPhaseComplete?.();
  };

  const handleBack = () => {
    // 현재 상태 저장
    setPhaseCards(phaseType, selectedCards);
    
    const previousPhase = phaseType === 'PHASE2' ? 'PHASE1' : 
                         phaseType === 'PHASE3' ? 'PHASE2' : null;
    
    if (previousPhase) {
      moveToPhase(previousPhase);
      onBack?.();
    }
  };

  // 마나 게이지 컴포넌트
  const ManaGauge = () => {
    if (phaseType !== 'PHASE2') return null;

    return (
      <div className="mb-6 bg-gray-800 p-4 rounded-lg">
        <div className="flex justify-between items-center mb-2">
          <h3 className="text-lg font-semibold text-blue-400">마나</h3>
          <span className="text-blue-400">{remainingMana} / {TUTORIAL_MAX_MANA}</span>
        </div>
        <div className="w-full h-4 bg-gray-700 rounded-full overflow-hidden">
          <div 
            className="h-full bg-blue-500 transition-all duration-300"
            style={{ width: `${(remainingMana / TUTORIAL_MAX_MANA) * 100}%` }}
          />
        </div>
        <p className="text-sm text-gray-400 mt-2">
          각 카드 사용 비용: {CARD_MANA_COST} 마나
        </p>
      </div>
    );
  };

  // 선택된 카드의 순서를 표시하는 컴포넌트
  const SelectedCardsOrder = () => {
    if (phaseType !== 'PHASE2' || selectedCards.length === 0) return null;

    return (
      <div className="mb-6 bg-gray-800 p-4 rounded-lg">
        <h3 className="text-lg font-semibold mb-3 text-blue-400">선택 순서</h3>
        <div className="flex flex-wrap items-center gap-2">
          {selectedCards.map((card, index) => (
            <Fragment key={`${card.id}-${index}`}>
              <motion.div
                key={`card-${card.id}-${index}`}
                initial={{ opacity: 0, scale: 0.8 }}
                animate={{ opacity: 1, scale: 1 }}
                className="bg-gray-700 px-3 py-2 rounded-full flex items-center cursor-pointer hover:bg-gray-600"
                onClick={() => handleRemoveCard(index)}
              >
                <span className="text-blue-400 mr-2">{index + 1}</span>
                <span>{card.name}</span>
                <span className="ml-2 text-blue-300">({CARD_MANA_COST} 마나)</span>
              </motion.div>
              {index < selectedCards.length - 1 && (
                <motion.div
                  key={`arrow-${card.id}-${index}`}
                  initial={{ opacity: 0, scale: 0 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ delay: 0.1 }}
                >
                  <FaArrowRight className="text-blue-400 mx-2" />
                </motion.div>
              )}
            </Fragment>
          ))}
        </div>
      </div>
    );
  };

  // 이전 페이즈의 선택 정보를 표시하는 컴포넌트
  const PreviousPhaseInfo = ({ phase }: { phase: PhaseType }) => {
    const phaseState = battleState[phase.toLowerCase() as keyof typeof battleState] as BattlePhase;
    if (!phaseState.selectedCards.length) return null;

    return (
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        className="bg-gray-800 rounded-lg p-4 mb-4"
      >
        <h3 className="text-lg font-semibold mb-2 text-blue-400">
          {PhaseTitle[phase]} 선택 카드
        </h3>
        <div className="flex flex-wrap items-center gap-2">
          {phaseState.selectedCards.map((card: Card, index: number) => (
            <Fragment key={`${card.id}-${index}`}>
              <div
                key={`prev-card-${card.id}-${index}`}
                className="bg-gray-700 rounded-lg p-3 text-sm"
              >
                <div className="flex justify-between items-center mb-1">
                  <p className="font-medium">{card.name}</p>
                  {phase === 'PHASE2' && (
                    <span className="text-blue-400 text-xs ml-2">순서: {index + 1}</span>
                  )}
                </div>
                <p className="text-gray-400 text-xs">{card.effect.type}: {card.effect.value}</p>
              </div>
              {phase === 'PHASE2' && index < phaseState.selectedCards.length - 1 && (
                <motion.div
                  key={`prev-arrow-${card.id}-${index}`}
                  initial={{ opacity: 0, scale: 0 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ delay: 0.1 }}
                >
                  <FaArrowRight className="text-blue-400 mx-2" />
                </motion.div>
              )}
            </Fragment>
          ))}
        </div>
      </motion.div>
    );
  };

  const particlesInit = useCallback(async () => {
    await loadSlim(tsParticles);
    
    await tsParticles.load({
      id: "battleParticles",
      options: {
        background: {
          opacity: 0.5
        },
        particles: {
          color: {
            value: "#ffffff"
          },
          move: {
            direction: "none",
            enable: true,
            outModes: {
              default: "bounce"
            },
            random: false,
            speed: 2,
            straight: false
          },
          number: {
            value: 80,
            density: {
              enable: true,
              width: 800,
              height: 800
            }
          },
          opacity: {
            value: 0.5
          },
          shape: {
            type: "circle"
          },
          size: {
            value: { min: 1, max: 3 }
          }
        },
        detectRetina: true
      }
    });
  }, []);

  useEffect(() => {
    particlesInit();
  }, [particlesInit]);

  return (
    <div className="relative w-full h-full flex flex-col items-center justify-center p-4">
      <div id="battleParticles" className="fixed inset-0 -z-10" />
      <div className="fixed inset-0 bg-gray-900 text-white p-8 overflow-y-auto">
        <AnimatePresence>
          {isCompleting && (
            <motion.div
              initial={{ opacity: 0, scale: 0.8 }}
              animate={{ opacity: 1, scale: 1 }}
              exit={{ opacity: 0, scale: 0.8 }}
              className="fixed inset-0 flex items-center justify-center z-50"
            >
              <div className="absolute inset-0 bg-black bg-opacity-50" />
              <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                className="bg-gray-800/90 backdrop-blur-sm p-8 rounded-lg text-center shadow-xl border border-blue-500/30 relative z-10 max-w-md w-full mx-4"
              >
                <div className="absolute inset-0 bg-blue-500/5 rounded-lg" />
                <h3 className="text-3xl font-bold text-blue-400 mb-4 relative">
                  {PhaseTitle[phaseType]} 완료
                </h3>
                <p className="text-xl text-gray-300 relative">
                  {selectedCards.length}개의 카드가 선택되었습니다
                </p>
                <div className="mt-6 text-gray-400 relative">
                  다음 페이즈로 이동합니다...
                </div>
              </motion.div>
            </motion.div>
          )}
        </AnimatePresence>

        <div className={`max-w-6xl mx-auto ${isCompleting ? 'pointer-events-none' : ''}`}>
          <div className="flex justify-between items-center mb-8">
            <motion.h2
              key={phaseType}
              initial={{ opacity: 0, x: -20 }}
              animate={{ opacity: 1, x: 0 }}
              className="text-3xl font-bold"
            >
              {PhaseTitle[phaseType]}
            </motion.h2>
            {phaseType !== 'PHASE1' && (
              <motion.button
                onClick={onBack}
                className="px-4 py-2 bg-gray-700 rounded hover:bg-gray-600 transition-colors"
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
              >
                이전 페이즈로
              </motion.button>
            )}
          </div>

          {phaseType === 'PHASE2' && <ManaGauge />}
          <SelectedCardsOrder />

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mb-8">
            {availableCards.map(card => {
              const isDisabled = phaseType === 'PHASE2' && remainingMana < CARD_MANA_COST;
              return (
                <motion.div
                  key={card.id}
                  className={`p-4 rounded-lg cursor-pointer relative ${
                    isDisabled 
                      ? 'bg-gray-700 opacity-50 cursor-not-allowed'
                      : phaseType === 'PHASE2'
                        ? 'bg-gray-800 hover:bg-gray-700'
                        : selectedCards.find(c => c.id === card.id)
                          ? 'bg-blue-600'
                          : 'bg-gray-800 hover:bg-gray-700'
                  }`}
                  onClick={() => !isDisabled && handleCardSelect(card)}
                  whileHover={!isDisabled ? { scale: 1.02 } : {}}
                >
                  <h3 className="text-xl font-bold mb-2">{card.name}</h3>
                  <p className="text-gray-300 mb-2">{card.description}</p>
                  <div className="text-sm text-gray-400">
                    <p>효과: {card.effect.type} {card.effect.value}</p>
                    {phaseType === 'PHASE2' && (
                      <p className="mt-1 text-blue-300">마나 소모: {CARD_MANA_COST}</p>
                    )}
                  </div>
                </motion.div>
              );
            })}
          </div>

          <div className="text-center mb-8">
            <motion.button
              onClick={handleConfirm}
              disabled={selectedCards.length === 0 || isCompleting}
              className={`px-8 py-3 rounded text-lg font-bold ${
                selectedCards.length === 0 || isCompleting
                  ? 'bg-gray-600 cursor-not-allowed'
                  : 'bg-blue-500 hover:bg-blue-600'
              }`}
              whileHover={selectedCards.length > 0 && !isCompleting ? { scale: 1.05 } : {}}
              whileTap={selectedCards.length > 0 && !isCompleting ? { scale: 0.95 } : {}}
            >
              {isCompleting ? '완료 중...' : '페이즈 완료'}
            </motion.button>
          </div>

          {/* 이전 페이즈 정보 표시 */}
          <div className="mt-8 space-y-4">
            {phaseType === 'PHASE2' && <PreviousPhaseInfo phase="PHASE1" />}
            {phaseType === 'PHASE3' && (
              <>
                <PreviousPhaseInfo phase="PHASE1" />
                <PreviousPhaseInfo phase="PHASE2" />
              </>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}; 