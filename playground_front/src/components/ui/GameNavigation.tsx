'use client'

import React, { useState, useEffect, useCallback } from 'react';
import { usePathname } from 'next/navigation';
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
    const pathname = usePathname();
    const isCodexPage = pathname?.startsWith('/codex');

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

    // codex 페이지에서는 아무것도 렌더링하지 않음
    if (isCodexPage) {
        return null;
    }

    return (
        <>
        <div className="floating-nav">
            {/* 네비게이션 버튼들  - 버튼 svg 변경필요*/}
            <div className="flex flex-col gap-3">
                <div className="relative">
                    <button 
                        className="nav-button bg-gradient-to-r from-blue-600 to-blue-700 text-white w-14 h-14 flex items-center justify-center hover:from-blue-500 hover:to-blue-600"
                        onClick={toggleInventory}
                    >
                        <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 20 20">
                            <path d="M3 4a1 1 0 011-1h12a1 1 0 011 1v2a1 1 0 01-1 1H4a1 1 0 01-1-1V4zM3 10a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H4a1 1 0 01-1-1v-6zM14 9a1 1 0 00-1 1v6a1 1 0 001 1h2a1 1 0 001-1v-6a1 1 0 00-1-1h-2z"/>
                        </svg>
                        <div className="nav-tooltip">INVENTORY</div>
                    </button>
                    <div className="notification-badge">3</div>
                </div>
                <div className="relative">
                    <button className="nav-button bg-gradient-to-r from-orange-600 to-red-600 text-white w-14 h-14 flex items-center justify-center hover:from-orange-500 hover:to-red-500">
                        <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 20 20">
                            <path d="M7 3a1 1 0 000 2h6a1 1 0 100-2H7zM4 7a1 1 0 011-1h10a1 1 0 110 2H5a1 1 0 01-1-1zM2 11a2 2 0 012-2h12a2 2 0 012 2v4a2 2 0 01-2 2H4a2 2 0 01-2-2v-4z"/>
                        </svg>
                        <div className="nav-tooltip">CRAFT</div>
                    </button>
                    <div className="notification-badge">1</div>
                </div>
                <div className="relative">
                    <button className="nav-button bg-gradient-to-r from-amber-600 to-amber-700 text-white w-14 h-14 flex items-center justify-center hover:from-amber-500 hover:to-amber-600">
                        <svg className="w-6 h-6" fill="currentColor" viewBox="0 0 20 20">
                            <path d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
                        </svg>
                        <div className="nav-tooltip">CODEX</div>
                    </button>
                    <div className="notification-badge">1</div>
                </div>
            </div>
        </div>

            {/* 인벤토리 모달 */}
            {isInventoryOpen && (<Inventory setIsOpen={setIsInventoryOpen}/>)}
            {/* 크래프트 모달 */}
            {isCraftOpen && (<Craft setIsOpen={setIsCraftOpen}/>)}

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