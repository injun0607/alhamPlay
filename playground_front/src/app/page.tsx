'use client'

import { MapView } from '@/components/map/MapView';
import { ActionMenu } from '@/components/map/ActionMenu';
import { StartScreen } from '@/components/start/StartScreen';
import { CharacterCreation } from '@/components/character/CharacterCreation';
import { useCharacterStore } from '@/store/characterStore';

export default function Home() {
  const { character, isCreating } = useCharacterStore();

  if (!character) {
    return isCreating ? <CharacterCreation /> : <StartScreen />;
  }

  return (
    <main className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">RPG Game</h1>
      <MapView />
      <ActionMenu />
    </main>
  );
}
