'use client'

import { useMapStore } from '@/store/mapStore';
import { MapTile } from '@/types/map';
import { useEffect } from 'react';
import { Map } from './Map';
import { MainMenu } from '../menu/MainMenu';
import { MenuPanel } from '../menu/MenuPanel';

export const MapView = () => {
  const { movePlayer } = useMapStore();

  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      // 방향키인 경우에만 기본 동작 방지
      if (['ArrowUp', 'ArrowDown', 'ArrowLeft', 'ArrowRight'].includes(e.key)) {
        e.preventDefault();
      }

      switch (e.key) {
        case 'ArrowUp':
          movePlayer('NORTH');
          break;
        case 'ArrowDown':
          movePlayer('SOUTH');
          break;
        case 'ArrowLeft':
          movePlayer('WEST');
          break;
        case 'ArrowRight':
          movePlayer('EAST');
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [movePlayer]);

  return (
    <div className="relative w-full max-w-2xl mx-auto">
      <Map />
      <MainMenu />
      <MenuPanel />
    </div>
  );
}; 