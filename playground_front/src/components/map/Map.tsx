'use client'

import React, { useState, useCallback, useEffect } from 'react';
import { FieldDataDTO, GatherMaterialDTO,  MaterialDTO } from '@/types/map';
import { ActionMenu } from './ActionMenu';
import GatherProgressBar from './GatherProgressBar';
import { TileDetailView } from './TileDetailView';
import { useFieldStore } from '@/store/fieldStore';

interface MapProps {
  fieldData: FieldDataDTO;
}

export function Map({ fieldData }: MapProps) {
  const [isGathering, setIsGathering] = useState(false);
  const [gatheringProgress, setGatheringProgress] = useState(0);
  const [gatherInfo, setGatherInfo] = useState<GatherMaterialDTO>({
    areaType: fieldData.fieldType,
    x: null,
    y: null
  });
  
  // 상세화면 관련 상태
  const [showDetailView, setShowDetailView] = useState(false);
  const { selectedTile, setSelectedTile } = useFieldStore();
  
  // 확인 모달 상태
  const [showConfirmModal, setShowConfirmModal] = useState(false);
  
  // 하루 변경 횟수 제한 (로컬 스토리지에서 관리)
  const [dailyTransformCount, setDailyTransformCount] = useState(0);
  const maxDailyTransforms = 3; // 하루 최대 3회 변경 가능

  // 하루 변경 횟수 초기화 (날짜가 바뀌면 리셋)
  useEffect(() => {
    const today = new Date().toDateString();
    const storedDate = localStorage.getItem('lastTransformDate');
    const storedCount = localStorage.getItem('dailyTransformCount');
    
    if (storedDate !== today) {
      // 날짜가 바뀌었으면 카운트 리셋
      localStorage.setItem('lastTransformDate', today);
      localStorage.setItem('dailyTransformCount', '0');
      setDailyTransformCount(0);
    } else {
      // 같은 날이면 저장된 카운트 사용
      setDailyTransformCount(parseInt(storedCount || '0'));
    }
  }, []);

  //진입시 선택된 타일 있으면 이동
  useEffect(()=>{
    if(fieldData.selectedInfo.selectedFlag){
      setSelectedTile({ x: fieldData.selectedInfo.selectedTileX , y: fieldData.selectedInfo.selectedTileY })
      setShowDetailView(true);
    }
  },[fieldData.selectedInfo.selectedFlag, fieldData.selectedInfo.selectedTileX, fieldData.selectedInfo.selectedTileY])

  
  //선택시 키보드 이벤트 처리
  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if (!showConfirmModal) return;
      
      if (e.key === 'Enter') {
        e.preventDefault();
        setShowConfirmModal(false);
        setShowDetailView(true);
      } else if (e.key === 'Escape') {
        e.preventDefault();
        setShowConfirmModal(false);
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [showConfirmModal]);

  // 타일 클릭 시 선택 상태로 변경
  const handleTileClick = (x: number, y: number) => {
    setSelectedTile({ x, y });
    setGatherInfo(prev => ({
      ...prev,
      x: x,
      y: y
    }));
  }

  // 타일 더블클릭 시 바로 상세화면 열기
  const handleTileDoubleClick = (x: number, y: number) => {
    setSelectedTile({ x, y });
    setShowConfirmModal(true);
  }

  // 선택 버튼 클릭 시 확인 모달 열기
  const showDetailViewFunc = useCallback(() => {
    setShowConfirmModal(true);
  }, []);

  const handleBackFromDetail = () => {
    setShowDetailView(false);
    setSelectedTile(null);
  }

  const handleGather = (x: number, y: number) => {
    setGatherInfo(prev => ({
      ...prev,
      x: x,
      y: y
    }));
    setIsGathering(true);
    setGatheringProgress(0);
    
    // 채집 진행도 시뮬레이션
    const interval = setInterval(() => {
      setGatheringProgress(prev => {
        if (prev >= 100) {
          clearInterval(interval);
          setIsGathering(false);
          return 100;
        }
        return prev + 10;
      });
    }, 200);
  }

  const handleTransform = (x: number, y: number) => {
    if (dailyTransformCount >= maxDailyTransforms) {
      alert('오늘의 변경 횟수를 모두 사용했습니다!');
      return;
    }

    // 변경 횟수 증가
    const newCount = dailyTransformCount + 1;
    setDailyTransformCount(newCount);
    localStorage.setItem('dailyTransformCount', newCount.toString());

    // 타일 변경 로직 (여기에 실제 변경 로직 추가)
    console.log(`타일 (${x}, ${y}) 변경 완료!`);
    
    // 변경 완료 알림
    alert(`타일 (${x}, ${y})이 성공적으로 변경되었습니다!`);
  }

  return (
    <div className={`flex flex-col items-center gap-8 p-4 transition-all duration-300
    }`}>
      {/* 필드 이름 */}
      <h1 className="text-2xl font-bold">{fieldData.name}</h1>

      {/* 하루 변경 횟수 표시 */}
      <div className="bg-blue-50 rounded-lg p-3 mb-4">
        <div className="flex items-center justify-between">
          <span className="text-sm font-semibold text-blue-800">오늘의 변경 횟수</span>
          <span className="text-sm text-blue-600">
            {dailyTransformCount} / {maxDailyTransforms}
          </span>
        </div>
        <div className="w-full bg-blue-200 rounded-full h-2 mt-2">
          <div 
            className="bg-blue-600 h-2 rounded-full transition-all duration-300"
            style={{ width: `${(dailyTransformCount / maxDailyTransforms) * 100}%` }}
          ></div>
        </div>
      </div>

      {/* 5x5 타일 맵 */}
      <div className="grid grid-cols-5 gap-1">
        {Array.from({ length: 25 }, (_, index) => {
          const x = index % 5;
          const y = Math.floor(index / 5);
          const isSelected = selectedTile && selectedTile.x === x && selectedTile.y === y;
          const isGatheringHere = isGathering && gatherInfo.x === x && gatherInfo.y === y;
          
          return (
            <div
              key={`${x}-${y}`}
              className={`w-20 h-20 border border-gray-300 flex items-center justify-center cursor-pointer transition-all relative overflow-hidden
                ${isSelected ? 'ring-2 ring-blue-400 bg-blue-50' : 'hover:opacity-80'}
                ${isGatheringHere ? 'bg-yellow-100' : ''}
                ${showDetailView ? 'cursor-not-allowed' : ''}`}
              onClick={() => !showDetailView && handleTileClick(x, y)}
              onDoubleClick={() => !showDetailView && handleTileDoubleClick(x, y)}
              style={{
                backgroundColor: 'black',
                backgroundSize: '500% 500%',
                backgroundPosition: `${x * 25}% ${y * 25}%`
              }}
            >
              {/* 곡괭이 채집 효과 */}
              {isGatheringHere && (
                <>
                  <div className="absolute inset-0 flex items-center justify-center z-20">
                    <div className="relative">
                      <svg 
                        className="w-8 h-8 text-yellow-600 pickaxe-swing" 
                        xmlns="http://www.w3.org/2000/svg" 
                        viewBox="0 0 24 24" 
                        fill="none" 
                        stroke="currentColor" 
                        strokeWidth="2" 
                        strokeLinecap="round" 
                        strokeLinejoin="round"
                      >
                        <path d="M14.7 6.3a1 1 0 0 0 0 1.4l1.6 1.6a1 1 0 0 0 1.4 0l3.77-3.77a6 6 0 0 1-7.94 7.94l-6.91 6.91a2.12 2.12 0 0 1-3-3l6.91-6.91a6 6 0 0 1 7.94-7.94l-3.76 3.76z"/>
                        <path d="M9 12l-3-3 3-3"/>
                      </svg>
                    </div>
                  </div>
                  
                  {/* 채집 파티클 효과 */}
                  <div className="absolute inset-0 z-10">
                    <div className="absolute top-1/2 left-1/2 w-2 h-2 bg-yellow-400 rounded-full gather-particle" style={{ animationDelay: '0s' }}></div>
                    <div className="absolute top-1/2 left-1/2 w-2 h-2 bg-orange-400 rounded-full gather-particle" style={{ animationDelay: '0.5s' }}></div>
                    <div className="absolute top-1/2 left-1/2 w-2 h-2 bg-red-400 rounded-full gather-particle" style={{ animationDelay: '1s' }}></div>
                  </div>
                  
                  {/* 채집 진행도 원형 표시 */}
                  <div className="absolute inset-0 flex items-center justify-center z-15">
                    <div className="relative w-12 h-12">
                      <svg className="w-12 h-12 transform -rotate-90" viewBox="0 0 36 36">
                        <path
                          className="text-gray-300"
                          stroke="currentColor"
                          strokeWidth="2"
                          fill="none"
                          d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831"
                        />
                        <path
                          className="text-yellow-500 transition-all duration-300"
                          stroke="currentColor"
                          strokeWidth="2"
                          fill="none"
                          strokeLinecap="round"
                          d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831"
                          style={{
                            strokeDasharray: `${(gatheringProgress / 100) * 100} 100`,
                            strokeDashoffset: 0
                          }}
                        />
                      </svg>
                    </div>
                  </div>
                  
                  {/* 채집 중 오버레이 */}
                  <div className="absolute inset-0 bg-yellow-200 opacity-30 animate-pulse"></div>
                </>
              )}
              
              <span className="text-sm text-white font-bold drop-shadow-lg z-10">
                ({x}, {y})
              </span>
            </div>
          );
        })}
      </div>

      <GatherProgressBar isGathering={isGathering} gatheringProgress={gatheringProgress} />
      
      <div>
        {gatherInfo.x !== null && gatherInfo.y !== null && (
          <div className="text-center">
            <span className="text-sm text-gray-600">선택된 위치: ({gatherInfo.x}, {gatherInfo.y})</span>
          </div>
        )}
      </div>
      
      {/* 선택 버튼 */}
      {selectedTile && !showDetailView && (
        <div className="flex gap-4">
          <button
            onClick={showDetailViewFunc}
            className="px-8 py-4 bg-blue-600 text-white border-4 border-blue-800 rounded-none hover:bg-blue-700 hover:border-blue-900 transition-all duration-200 font-bold pixel-font select-button"
          >
            선택
          </button>
        </div>
      )}
      
      {/* 확인 모달 */}
      {showConfirmModal && (
        <div className="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50">
          <div className="bg-gray-900 border-4 border-gray-700 rounded-none p-6 max-w-sm w-full mx-4 pixel-art-shadow">
            <h3 className="text-lg font-bold text-white pixel-font mb-4 text-center">
              해당 지역을 선택하시겠습니까?
            </h3>
            <div className="flex gap-3">
              <button 
                onClick={() => {
                  setShowConfirmModal(false);
                  setShowDetailView(true);
                }}
                className="flex-1 bg-green-600 text-white py-3 px-4 border-2 border-green-800 rounded-none hover:bg-green-700 hover:border-green-900 transition-all duration-200 font-bold pixel-font"
              >
                확인
              </button>
              <button 
                onClick={() => setShowConfirmModal(false)}
                className="flex-1 bg-gray-600 text-white py-3 px-4 border-2 border-gray-800 rounded-none hover:bg-gray-700 hover:border-gray-900 transition-all duration-200 font-bold pixel-font"
              >
                취소
              </button>
            </div>
            <div className="text-xs text-gray-400 text-center mt-3 pixel-font">
              Enter: 확인, Esc: 취소
            </div>
          </div>
        </div>
      )}
      
      {/* <ActionMenu 
        gatherInfo={gatherInfo}
        isGathering={isGathering}
        setIsGathering={setIsGathering}
        setGatheringProgress={setGatheringProgress}
      /> */}

      {/* 타일 상세화면 */}
      {showDetailView && selectedTile && (
        <TileDetailView
          fieldData={fieldData}
          selectedTile={selectedTile}
          onBack={handleBackFromDetail}
          onGather={handleGather}
          onTransform={handleTransform}
          dailyTransformCount={dailyTransformCount}
          maxDailyTransforms={maxDailyTransforms}
        />
      )}

      <style jsx>{`
        .pixel-font {
          font-family: 'Press Start 2P', 'Courier New', monospace;
          image-rendering: pixelated;
        }
        
        .select-button {
          box-shadow: 
            0 0 0 2px rgba(0,0,0,0.8),
            4px 4px 0 rgba(0,0,0,0.8),
            8px 8px 0 rgba(0,0,0,0.6);
          text-shadow: 2px 2px 0 rgba(0,0,0,0.8);
        }
        
        .select-button:hover {
          transform: translateY(2px);
          box-shadow: 
            0 0 0 2px rgba(0,0,0,0.8),
            2px 2px 0 rgba(0,0,0,0.8),
            4px 4px 0 rgba(0,0,0,0.6);
        }
        
        .select-button:active {
          transform: translateY(4px);
          box-shadow: 
            0 0 0 2px rgba(0,0,0,0.8),
            0px 0px 0 rgba(0,0,0,0.8);
        }
      `}</style>
    </div>
  );
} 