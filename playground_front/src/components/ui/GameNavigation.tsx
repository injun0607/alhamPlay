'use client'

import React, { useState, useEffect, useCallback } from 'react';
import Inventory from '@/components/inventory/Inventory';
import Craft from '@/components/craft/Craft';
import { InventoryStore } from '@/store/inventoryStore';
import { useApi } from '@/hooks/common/useApi';
import { UserInventory } from '@/types/inventory';

interface GameNavigationProps {
    className?: string;
}

export default function GameNavigation({ className = '' }: GameNavigationProps) {
    const [isInventoryOpen, setIsInventoryOpen] = useState(false);
    const [isCraftOpen, setIsCraftOpen] = useState(false);
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const {isInventoryInitialized,initInventory} = InventoryStore();
    const {data,loading,error,get} = useApi<UserInventory>();


    const toggleCraft = useCallback(() => {
        setIsCraftOpen(prev => !prev);
    }, []);

    const toggleInventory = useCallback(() => {
        setIsInventoryOpen(prev => !prev);
    }, []);

    const toggleMenu = useCallback(() => {
        setIsMenuOpen(prev => !prev);
    }, []);

    const closeModals = useCallback(() => {
        setIsInventoryOpen(false);
        setIsMenuOpen(false);
    }, []);

    useEffect(() => {
        if(!isInventoryInitialized){
            get("/inventory").then(
                (data) => {
                    initInventory(data);
                }
            ).catch(
                (error) => {
                    console.error("인벤토리 초기화 실패",error);
                }
            );
        }
    }, [isInventoryInitialized, initInventory, get]);

    // 키보드 단축키 처리
    useEffect(() => {
        const handleKeyDown = (e: KeyboardEvent) => {
            // 처리할 키만 필터링
            const targetKeys = ['i', 'escape', 'm', 'c'];
            if (!targetKeys.includes(e.key.toLowerCase())) {
                return;
            }

            switch (e.key.toLowerCase()) {
                case 'i':
                    e.preventDefault();
                    e.stopPropagation();
                    toggleInventory();
                    break;
                case 'escape':
                    e.preventDefault();
                    e.stopPropagation();
                    closeModals();
                    break;
                case 'm':
                    e.preventDefault();
                    e.stopPropagation();
                    toggleMenu();
                    break;
                case 'c':
                    e.preventDefault();
                    e.stopPropagation();
                    toggleCraft();
                    break;
            }
        };

        window.addEventListener('keydown', handleKeyDown);
        return () =>{ 
            window.removeEventListener('keydown', handleKeyDown)
        };
    }, [toggleInventory, closeModals, toggleMenu, toggleCraft]);

    return (
        <>
            {/* 네비게이션 버튼들 */}
            <div className={`fixed top-4 right-4 flex flex-col gap-2 z-40 ${className}`}>
                {/* 인벤토리 버튼 */}
                <button
                    onClick={toggleInventory}
                    className="w-12 h-12 bg-blue-600 hover:bg-blue-700 text-white rounded-lg shadow-lg flex items-center justify-center transition-colors"
                    title="인벤토리 (I)"
                >
                    🎒
                </button>

                {/* 메뉴 버튼 */}
                <button
                    onClick={toggleMenu}
                    className="w-12 h-12 bg-gray-600 hover:bg-gray-700 text-white rounded-lg shadow-lg flex items-center justify-center transition-colors"
                    title="메뉴 (M)"
                >
                    ⚙️
                </button>

                {/* 설정 버튼 */}
                <button
                    className="w-12 h-12 bg-purple-600 hover:bg-purple-700 text-white rounded-lg shadow-lg flex items-center justify-center transition-colors"
                    title="설정"
                >
                    🔧
                </button>

                {/* 크래프트 버튼 */}
                <button
                    className="w-12 h-12 bg-yellow-600 hover:bg-yellow-700 text-white rounded-lg shadow-lg flex items-center justify-center transition-colors"
                    title="크래프트"
                >
                    🔨
                </button>
                {/* 도움말 버튼 */}
                <button
                    className="w-12 h-12 bg-green-600 hover:bg-green-700 text-white rounded-lg shadow-lg flex items-center justify-center transition-colors"
                    title="도움말"
                >
                    ❓
                </button>
            </div>

            {/* 인벤토리 모달 */}
            {isInventoryOpen && (<Inventory setIsOpen={setIsInventoryOpen}/>)}
            {/* 크래프트 모달 */}
            {isCraftOpen && (<Craft/>)}

            {/* 메뉴 모달 */}
            {isMenuOpen && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
                    <div className="bg-white rounded-lg p-6 w-80">
                        <div className="flex justify-between items-center mb-4">
                            <h2 className="text-xl font-bold">게임 메뉴</h2>
                            <button
                                onClick={closeModals}
                                className="text-gray-500 hover:text-gray-700"
                            >
                                ✕
                            </button>
                        </div>

                        <div className="space-y-2">
                            <button className="w-full px-4 py-3 bg-blue-500 text-white rounded hover:bg-blue-600 transition-colors">
                                메인 메뉴로 돌아가기
                            </button>
                            <button className="w-full px-4 py-3 bg-gray-500 text-white rounded hover:bg-gray-600 transition-colors">
                                게임 저장
                            </button>
                            <button className="w-full px-4 py-3 bg-red-500 text-white rounded hover:bg-red-600 transition-colors">
                                게임 종료
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
} 