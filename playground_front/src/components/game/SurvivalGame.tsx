'use client'

import { useEffect, useRef, useState, useCallback } from 'react';
import { useGameStore } from '@/store/gameStore';

interface GameObject {
  x: number;
  y: number;
  width: number;
  height: number;
  speed: number;
  health: number;
  maxHealth: number;
}

interface Player extends GameObject {
  experience: number;
  level: number;
  weapons: Weapon[];
  passives: Passive[];
}

interface Enemy extends GameObject {
  type: 'slime' | 'skeleton' | 'ghost';
  damage: number;
  lastAttack: number;
}

interface Projectile extends GameObject {
  damage: number;
  speed: number;
  direction: { x: number; y: number };
  lifetime: number;
}

interface Weapon {
  id: string;
  name: string;
  damage: number;
  cooldown: number;
  lastFired: number;
  range: number;
  projectileSpeed: number;
}

interface Passive {
  id: string;
  name: string;
  effect: string;
  value: number;
}

interface GameState {
  player: Player;
  enemies: Enemy[];
  projectiles: Projectile[];
  experience: number;
  level: number;
  gameTime: number;
  isPaused: boolean;
  isGameOver: boolean;
}

export default function SurvivalGame() {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const [gameState, setGameState] = useState<GameState>({
    player: {
      x: 400,
      y: 300,
      width: 32,
      height: 32,
      speed: 3,
      health: 100,
      maxHealth: 100,
      experience: 0,
      level: 1,
      weapons: [
        {
          id: 'basic_sword',
          name: 'Basic Sword',
          damage: 10,
          cooldown: 500,
          lastFired: 0,
          range: 50,
          projectileSpeed: 5
        }
      ],
      passives: []
    },
    enemies: [],
    projectiles: [],
    experience: 0,
    level: 1,
    gameTime: 0,
    isPaused: false,
    isGameOver: false
  });

  const [lastTime, setLastTime] = useState(0);
  const animationRef = useRef<number>(0);

  // 게임 초기화
  const initGame = useCallback(() => {
    setGameState(prev => ({
      ...prev,
      enemies: [],
      projectiles: [],
      gameTime: 0,
      isGameOver: false
    }));
  }, []);

  // 적 생성
  const spawnEnemy = useCallback(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const side = Math.floor(Math.random() * 4); // 0: top, 1: right, 2: bottom, 3: left
    let x = 0;
    let y = 0;

    switch (side) {
      case 0: // top
        x = Math.random() * canvas.width;
        y = -50;
        break;
      case 1: // right
        x = canvas.width + 50;
        y = Math.random() * canvas.height;
        break;
      case 2: // bottom
        x = Math.random() * canvas.width;
        y = canvas.height + 50;
        break;
      case 3: // left
        x = -50;
        y = Math.random() * canvas.height;
        break;
    }

    const enemyTypes: Enemy['type'][] = ['slime', 'skeleton', 'ghost'];
    const type = enemyTypes[Math.floor(Math.random() * enemyTypes.length)];

    const enemy: Enemy = {
      x: x,
      y: y,
      width: 24,
      height: 24,
      speed: 1 + Math.random() * 2,
      health: 20 + Math.floor(gameState.level * 5),
      maxHealth: 20 + Math.floor(gameState.level * 5),
      type,
      damage: 10 + Math.floor(gameState.level * 2),
      lastAttack: 0
    };

    setGameState(prev => ({
      ...prev,
      enemies: [...prev.enemies, enemy]
    }));
  }, [gameState.level]);

  // 플레이어 이동
  const movePlayer = useCallback((dx: number, dy: number) => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    setGameState(prev => ({
      ...prev,
      player: {
        ...prev.player,
        x: Math.max(0, Math.min(canvas.width - prev.player.width, prev.player.x + dx)),
        y: Math.max(0, Math.min(canvas.height - prev.player.height, prev.player.y + dy))
      }
    }));
  }, []);

  // 무기 발사
  const fireWeapon = useCallback((weapon: Weapon) => {
    const now = Date.now();
    if (now - weapon.lastFired < weapon.cooldown) return;

    // 가장 가까운 적 찾기
    const nearestEnemy = gameState.enemies.reduce((nearest, enemy) => {
      const distance = Math.sqrt(
        Math.pow(enemy.x - gameState.player.x, 2) + 
        Math.pow(enemy.y - gameState.player.y, 2)
      );
      return distance < nearest.distance ? { enemy, distance } : nearest;
    }, { enemy: null as Enemy | null, distance: Infinity });

    if (!nearestEnemy.enemy) return;

    // 방향 계산
    const dx = nearestEnemy.enemy.x - gameState.player.x;
    const dy = nearestEnemy.enemy.y - gameState.player.y;
    const distance = Math.sqrt(dx * dx + dy * dy);
    const direction = { x: dx / distance, y: dy / distance };

    const projectile: Projectile = {
      x: gameState.player.x + gameState.player.width / 2,
      y: gameState.player.y + gameState.player.height / 2,
      width: 8,
      height: 8,
      speed: weapon.projectileSpeed,
      health: 1,
      maxHealth: 1,
      damage: weapon.damage,
      direction,
      lifetime: 3000
    };

    setGameState(prev => ({
      ...prev,
      projectiles: [...prev.projectiles, projectile],
      player: {
        ...prev.player,
        weapons: prev.player.weapons.map(w => 
          w.id === weapon.id ? { ...w, lastFired: now } : w
        )
      }
    }));
  }, [gameState.player, gameState.enemies]);

  // 게임 업데이트
  const updateGame = useCallback((deltaTime: number) => {
    if (gameState.isPaused || gameState.isGameOver) return;

    setGameState(prev => {
      const newState = { ...prev };
      newState.gameTime += deltaTime;

      // 적 이동
      newState.enemies = prev.enemies.map(enemy => {
        const dx = prev.player.x - enemy.x;
        const dy = prev.player.y - enemy.y;
        const distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance > 0) {
          enemy.x += (dx / distance) * enemy.speed;
          enemy.y += (dy / distance) * enemy.speed;
        }

        // 플레이어와 충돌 체크
        if (distance < 30) {
          const now = Date.now();
          if (now - enemy.lastAttack > 1000) {
            newState.player.health -= enemy.damage;
            enemy.lastAttack = now;
          }
        }

        return enemy;
      });

      // 투사체 이동
      newState.projectiles = prev.projectiles
        .map(projectile => {
          projectile.x += projectile.direction.x * projectile.speed;
          projectile.y += projectile.direction.y * projectile.speed;
          projectile.lifetime -= deltaTime;
          return projectile;
        })
        .filter(projectile => projectile.lifetime > 0);

      // 투사체와 적 충돌 체크
      newState.projectiles = newState.projectiles.filter(projectile => {
        let hit = false;
        newState.enemies = newState.enemies.filter(enemy => {
          const distance = Math.sqrt(
            Math.pow(enemy.x - projectile.x, 2) + 
            Math.pow(enemy.y - projectile.y, 2)
          );
          
          if (distance < 20) {
            enemy.health -= projectile.damage;
            hit = true;
            return enemy.health > 0;
          }
          return true;
        });
        return !hit;
      });

      // 경험치 획득
      const killedEnemies = prev.enemies.length - newState.enemies.length;
      if (killedEnemies > 0) {
        newState.experience += killedEnemies * 10;
        newState.player.experience += killedEnemies * 10;
        
        // 레벨업 체크
        const expNeeded = newState.level * 100;
        if (newState.player.experience >= expNeeded) {
          newState.level++;
          newState.player.level++;
          newState.player.experience -= expNeeded;
          newState.player.maxHealth += 20;
          newState.player.health = newState.player.maxHealth;
        }
      }

      // 게임오버 체크
      if (newState.player.health <= 0) {
        newState.isGameOver = true;
      }

      return newState;
    });
  }, [gameState.isPaused, gameState.isGameOver]);

  // 렌더링
  const render = useCallback(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    // 캔버스 클리어
    ctx.fillStyle = '#1a1a2e';
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // 플레이어 렌더링
    ctx.fillStyle = '#4a90e2';
    ctx.fillRect(gameState.player.x, gameState.player.y, gameState.player.width, gameState.player.height);

    // 체력바
    const healthBarWidth = 50;
    const healthBarHeight = 6;
    const healthPercentage = gameState.player.health / gameState.player.maxHealth;
    
    ctx.fillStyle = '#333';
    ctx.fillRect(gameState.player.x - 9, gameState.player.y - 15, healthBarWidth, healthBarHeight);
    ctx.fillStyle = '#ff4444';
    ctx.fillRect(gameState.player.x - 9, gameState.player.y - 15, healthBarWidth * healthPercentage, healthBarHeight);

    // 적 렌더링
    gameState.enemies.forEach(enemy => {
      switch (enemy.type) {
        case 'slime':
          ctx.fillStyle = '#4caf50';
          break;
        case 'skeleton':
          ctx.fillStyle = '#f5f5f5';
          break;
        case 'ghost':
          ctx.fillStyle = '#9c27b0';
          break;
      }
      ctx.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);

      // 적 체력바
      const enemyHealthPercentage = enemy.health / enemy.maxHealth;
      ctx.fillStyle = '#333';
      ctx.fillRect(enemy.x - 2, enemy.y - 10, 28, 4);
      ctx.fillStyle = '#ff4444';
      ctx.fillRect(enemy.x - 2, enemy.y - 10, 28 * enemyHealthPercentage, 4);
    });

    // 투사체 렌더링
    ctx.fillStyle = '#ffeb3b';
    gameState.projectiles.forEach(projectile => {
      ctx.fillRect(projectile.x, projectile.y, projectile.width, projectile.height);
    });

    // UI 렌더링
    ctx.fillStyle = '#ffffff';
    ctx.font = '16px Arial';
    ctx.fillText(`Level: ${gameState.level}`, 10, 30);
    ctx.fillText(`Health: ${gameState.player.health}/${gameState.player.maxHealth}`, 10, 50);
    ctx.fillText(`Experience: ${gameState.player.experience}`, 10, 70);
    ctx.fillText(`Time: ${Math.floor(gameState.gameTime / 1000)}s`, 10, 90);
    ctx.fillText(`Enemies: ${gameState.enemies.length}`, 10, 110);

    if (gameState.isGameOver) {
      ctx.fillStyle = 'rgba(0, 0, 0, 0.8)';
      ctx.fillRect(0, 0, canvas.width, canvas.height);
      ctx.fillStyle = '#ffffff';
      ctx.font = '48px Arial';
      ctx.textAlign = 'center';
      ctx.fillText('GAME OVER', canvas.width / 2, canvas.height / 2);
      ctx.font = '24px Arial';
      ctx.fillText(`Survived for ${Math.floor(gameState.gameTime / 1000)} seconds`, canvas.width / 2, canvas.height / 2 + 40);
      ctx.fillText(`Level: ${gameState.level}`, canvas.width / 2, canvas.height / 2 + 70);
    }
  }, [gameState]);

  // 게임 루프
  const gameLoop = useCallback((currentTime: number) => {
    const deltaTime = currentTime - lastTime;
    setLastTime(currentTime);

    updateGame(deltaTime);
    render();

    // 적 스폰 (시간에 따라 증가)
    if (Math.random() < 0.02 + (gameState.gameTime / 100000)) {
      spawnEnemy();
    }

    // 무기 자동 발사
    gameState.player.weapons.forEach(weapon => {
      fireWeapon(weapon);
    });

    animationRef.current = requestAnimationFrame(gameLoop);
  }, [lastTime, updateGame, render, spawnEnemy, fireWeapon, gameState.gameTime, gameState.player.weapons]);

  // 키보드 입력 처리
  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if (gameState.isGameOver) return;

      const speed = gameState.player.speed;
      switch (e.key) {
        case 'w':
        case 'ArrowUp':
          movePlayer(0, -speed);
          break;
        case 's':
        case 'ArrowDown':
          movePlayer(0, speed);
          break;
        case 'a':
        case 'ArrowLeft':
          movePlayer(-speed, 0);
          break;
        case 'd':
        case 'ArrowRight':
          movePlayer(speed, 0);
          break;
        case ' ':
          e.preventDefault();
          gameState.player.weapons.forEach(weapon => fireWeapon(weapon));
          break;
        case 'r':
          if (gameState.isGameOver) {
            initGame();
          }
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [gameState.isGameOver, gameState.player.speed, gameState.player.weapons, movePlayer, fireWeapon, initGame]);

  // 게임 시작/정지
  useEffect(() => {
    if (!gameState.isPaused && !gameState.isGameOver) {
      animationRef.current = requestAnimationFrame(gameLoop);
    }

    return () => {
      if (animationRef.current) {
        cancelAnimationFrame(animationRef.current);
      }
    };
  }, [gameLoop, gameState.isPaused, gameState.isGameOver]);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-900">
      <div className="mb-4 text-white">
        <h1 className="text-2xl font-bold mb-2">Survival Game</h1>
        <p className="text-sm text-gray-400">
          WASD/Arrow Keys: Move | Space: Attack | R: Restart (when game over)
        </p>
      </div>
      
      <canvas
        ref={canvasRef}
        width={800}
        height={600}
        className="border-2 border-gray-600 bg-gray-800"
      />
      
      <div className="mt-4 text-white text-center">
        <button
          onClick={() => setGameState(prev => ({ ...prev, isPaused: !prev.isPaused }))}
          className="px-4 py-2 bg-blue-600 hover:bg-blue-700 rounded mr-2"
        >
          {gameState.isPaused ? 'Resume' : 'Pause'}
        </button>
        
        {gameState.isGameOver && (
          <button
            onClick={initGame}
            className="px-4 py-2 bg-green-600 hover:bg-green-700 rounded"
          >
            Restart
          </button>
        )}
      </div>
    </div>
  );
} 