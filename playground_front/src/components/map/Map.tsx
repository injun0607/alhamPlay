'use client'

import React, { useState, useCallback, useEffect } from 'react';
import { DailyTileInfo, FieldDataDTO, GatherMaterialDTO, MaterialDTO } from '@/types/map';
import { TileDetailView } from './TileDetailView';
import { useFieldStore } from '@/store/fieldStore';
import { useApi } from '@/hooks/common/useApi';
import { CommonResponse } from '@/types/response';

interface MapProps {
  fieldData: FieldDataDTO;
}

interface CurSelectedTile {
  x: number | null;
  y: number | null;
}

export function Map({ fieldData }: MapProps) {

  const { post } = useApi<CommonResponse<DailyTileInfo>>();
  // 상세화면 관련 상태
  const [showDetailView, setShowDetailView] = useState(false);
  const { selectedTile, setSelectedTile } = useFieldStore();

  const [curSelectedTile, setCurSelectedTile] = useState<CurSelectedTile>({ x: null, y: null });
  // 확인 모달 상태
  const [showConfirmModal, setShowConfirmModal] = useState(false);
  const [tileChanged, setTileChanged] = useState(false);

  const availableUpdateCount = selectedTile?.availableUpdateCount ?? 3;

  // curSelectedTile이 변경될 때마다 tileChanged 상태 업데이트
  useEffect(() => {
    if (curSelectedTile.x !== null && curSelectedTile.y !== null && selectedTile) {
      const isChanged = curSelectedTile.x !== selectedTile.selectedTileX || curSelectedTile.y !== selectedTile.selectedTileY;
      setTileChanged(isChanged);
    } else {
      setTileChanged(false);
    }
  }, [curSelectedTile.x, curSelectedTile.y, selectedTile?.selectedTileX, selectedTile?.selectedTileY]);


  useEffect(() => {
    if (selectedTile?.selectedTileFlag) {
      setShowDetailView(true);
    } else {
      setShowDetailView(false);
    }
  }, [selectedTile?.selectedTileFlag, selectedTile?.selectedTileX, selectedTile?.selectedTileY])


  //선택시 키보드 이벤트 처리
  useEffect(() => {
    const handleKeyDown = async (e: KeyboardEvent) => {
      if (!showConfirmModal) return;

      if (e.key === 'Enter') {
        e.preventDefault();
        confirmTile();
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
    setCurSelectedTile({ x: x, y: y })
  }

  // 타일 더블클릭 시 바로 상세화면 열기
  const handleTileDoubleClick = (x: number, y: number) => {
    setCurSelectedTile({ x: x, y: y })
    setShowConfirmModal(true);
  }

  // 선택 버튼 클릭 시 확인 모달 열기
  const confirmTile = useCallback(async () => {
    //기존에 미리 선택된걸 재 클릭시 상세화면 열기
    if (curSelectedTile.x == selectedTile?.selectedTileX && curSelectedTile.y == selectedTile?.selectedTileY) {
      setShowConfirmModal(false);
      setShowDetailView(true);
      return;
    }

    try {
      if (curSelectedTile.x == null || curSelectedTile.y == null) return;
      const res = await post("/field/tiles", { x: curSelectedTile?.x, y: curSelectedTile?.y })
      if (res.status == "OK") {
        setSelectedTile(res.data ?? null);
        setShowConfirmModal(false);
      } else if (res.status == "NO_CONTENT") {
        alert("오늘의 변경 횟수를 모두 사용했습니다!");
        setShowConfirmModal(false);
        setShowDetailView(true);
      }
      else {
        alert("타일 선택에 실패했습니다.");
      }
    } catch (error) {
      console.error(error);
    }
  }, [curSelectedTile.x, curSelectedTile.y, post, setSelectedTile]);

  const handleBackFromDetail = () => {
    setShowDetailView(false);
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
            {availableUpdateCount} / 3
          </span>
        </div>
        <div className="w-full bg-blue-200 rounded-full h-2 mt-2">
          <div
            className="bg-blue-600 h-2 rounded-full transition-all duration-300"
            style={{ width: `${(availableUpdateCount / 3) * 100}%` }}
          ></div>
        </div>
      </div>

      {/* 5x5 타일 맵 */}
      <div className="grid grid-cols-5 gap-1">
        {Array.from({ length: 25 }, (_, index) => {
          const x = index % 5;
          const y = Math.floor(index / 5);
          const isSelected = selectedTile && selectedTile.selectedTileX === x && selectedTile.selectedTileY === y;

          return (
            <div
              key={`${x}-${y}`}
              className={`w-20 h-20 border border-gray-300 flex items-center justify-center cursor-pointer transition-all relative overflow-hidden
                ${isSelected ? 'ring-2 ring-blue-400 bg-blue-50' : 'hover:opacity-80'}
                ${showDetailView ? 'cursor-not-allowed' : ''}`}
              onClick={() => !showDetailView && handleTileClick(x, y)}
              onDoubleClick={() => !showDetailView && handleTileDoubleClick(x, y)}
              style={{
                backgroundColor: 'black',
                backgroundSize: '500% 500%',
                backgroundPosition: `${x * 25}% ${y * 25}%`
              }}
            >
              <span className="text-sm text-white font-bold drop-shadow-lg z-10">
                ({x}, {y})
              </span>
            </div>
          );
        })}
      </div>

      {/* 선택 버튼 */}
      {selectedTile && !showDetailView && (
        <div className="flex gap-4">
          <button
            onClick={() => setShowConfirmModal(true)}
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
              {tileChanged ? "해당 지역으로 변경하시겠습니까?" : "해당 지역을 선택하시겠습니까?"}
            </h3>
            <div className="flex gap-3">
              <button
                onClick={() => {
                  confirmTile();
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

      {/* 타일 상세화면 */}
      {showDetailView && selectedTile?.selectedTileFlag && (
        <TileDetailView
          fieldData={fieldData}
          onBack={handleBackFromDetail}
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