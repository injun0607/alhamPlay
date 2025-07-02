'use client'

import { useEffect, useRef, useState } from 'react';

interface GameStats {
  level: number;
  experience: number;
  health: number;
  maxHealth: number;
  gameTime: number;
  enemiesKilled: number;
}

export default function SurvivalGame() {
  const gameRef = useRef<any>(null);
  const [gameStats, setGameStats] = useState<GameStats>({
    level: 1,
    experience: 0,
    health: 100,
    maxHealth: 100,
    gameTime: 0,
    enemiesKilled: 0
  });
  const [isGameOver, setIsGameOver] = useState(false);
  const [isPaused, setIsPaused] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (gameRef.current) return;

    const initGame = async () => {
      try {
        const Phaser = await import('phaser');
        
        // ===== 게임 설정 =====
        const config = {
          type: Phaser.AUTO,
          width: 1600,
          height: 1600,
          parent: 'game-container',
          backgroundColor: '#1a1a2e',
          physics: {
            default: 'arcade',
            arcade: {
              gravity: { x: 0, y: 0 },
              debug: false
            }
          },
          scene: {
            preload: preload,
            create: create,
            update: update
          }
        };

        // ===== 게임 인스턴스 생성 =====
        gameRef.current = new Phaser.Game(config);

        // ===== 게임 오브젝트 변수들 =====
        let player: any;
        let enemies: any;
        let projectiles: any;
        let experienceOrbs: any;
        let cursors: any;
        let wasdKeys: any;
        let gameTime = 0;
        let playerStats = {
          level: 1,
          experience: 0,
          health: 100,
          maxHealth: 100,
          speed: 200,
          damage: 20,
          attackSpeed: 500,
          lastAttack: 0
        };

        // ===== 에셋 로드 함수 =====
        function preload(this: any) {
          // 플레이어 스프라이트 생성 (파란색 사각형)
          this.add.graphics()
            .fillStyle(0x4a90e2)
            .fillRect(0, 0, 32, 32)
            .generateTexture('player', 32, 32);
          
          // 적 스프라이트 생성 (빨간색 사각형)
          this.add.graphics()
            .fillStyle(0xff4444)
            .fillRect(0, 0, 24, 24)
            .generateTexture('enemy', 24, 24);
          
          // 투사체 스프라이트 생성 (노란색 원)
          this.add.graphics()
            .fillStyle(0xffeb3b)
            .fillCircle(4, 4, 4)
            .generateTexture('projectile', 8, 8);
          
          // 경험치 오브 스프라이트 생성 (초록색 원)
          this.add.graphics()
            .fillStyle(0x00ff00)
            .fillCircle(8, 8, 8)
            .generateTexture('experience', 16, 16);
        }

        // ===== 게임 초기화 함수 =====
        function create(this: any) {
          // 플레이어 생성 및 설정
          player = this.physics.add.sprite(400, 300, 'player');
          player.setCollideWorldBounds(true);

          // 적 그룹 생성
          enemies = this.physics.add.group({
            classType: Phaser.Physics.Arcade.Sprite,
            runChildUpdate: true
          });

          // 투사체 그룹 생성
          projectiles = this.physics.add.group({
            classType: Phaser.Physics.Arcade.Sprite,
            runChildUpdate: true
          });

          // 경험치 오브 그룹 생성
          experienceOrbs = this.physics.add.group({
            classType: Phaser.Physics.Arcade.Sprite,
            runChildUpdate: true
          });

          // ===== 키보드 입력 설정 =====
          if (this.input.keyboard) {
            cursors = this.input.keyboard.createCursorKeys();
            wasdKeys = this.input.keyboard.addKeys('W,S,A,D');
          }

          // ===== 충돌 감지 설정 =====
          this.physics.add.overlap(player, enemies, playerHit, undefined, this);
          this.physics.add.overlap(projectiles, enemies, projectileHit, undefined, this);
          this.physics.add.overlap(player, experienceOrbs, collectExperience, undefined, this);

          // ===== UI 텍스트 생성 =====
          const uiText = this.add.text(16, 16, '', {
            fontSize: '18px',
            color: '#ffffff',
            fontFamily: 'Arial'
          });
          uiText.setScrollFactor(0);

          // ===== 게임 오버 텍스트 생성 =====
          const gameOverText = this.add.text(400, 300, 'GAME OVER\nPress R to restart', {
            fontSize: '48px',
            color: '#ffffff',
            fontFamily: 'Arial',
            align: 'center'
          });
          gameOverText.setOrigin(0.5);
          gameOverText.setScrollFactor(0);
          gameOverText.setVisible(false);

          // ===== 이벤트 리스너 설정 =====
          this.events.on('updateStats', (stats: any) => {
            setGameStats(stats);
            uiText.setText([
              `Level: ${stats.level}`,
              `Health: ${stats.health}/${stats.maxHealth}`,
              `Experience: ${stats.experience}`,
              `Time: ${Math.floor(stats.gameTime / 1000)}s`,
              `Enemies Killed: ${stats.enemiesKilled}`,
              `Enemies: ${enemies.getChildren().length}/100`
            ]);
          });

          this.events.on('gameOver', () => {
            setIsGameOver(true);
            gameOverText.setVisible(true);
          });

          this.events.on('restart', () => {
            setIsGameOver(false);
            gameOverText.setVisible(false);
            restartGame(this);
          });
        }

        // ===== 게임 업데이트 함수 (매 프레임 실행) =====
        function update(this: any, time: number, delta: number) {
          if (isGameOver || isPaused) return;

          gameTime += delta;

          // ===== 플레이어 이동 처리 =====
          const speed = playerStats.speed;
          player.setVelocity(0);

          if (cursors && wasdKeys) {
            if (cursors.left.isDown || wasdKeys.A.isDown) {
              player.setVelocityX(-speed);
            } else if (cursors.right.isDown || wasdKeys.D.isDown) {
              player.setVelocityX(speed);
            }

            if (cursors.up.isDown || wasdKeys.W.isDown) {
              player.setVelocityY(-speed);
            } else if (cursors.down.isDown || wasdKeys.S.isDown) {
              player.setVelocityY(speed);
            }
          }

          // ===== 자동 공격 처리 =====
          if (time - playerStats.lastAttack > playerStats.attackSpeed) {
            spawnProjectile(this);
            playerStats.lastAttack = time;
          }

          // ===== 적 스폰 처리 =====
          const currentEnemyCount = enemies.getChildren().length;
          const maxEnemies = 100; // 최대 적 수 제한
          
          // 적 수가 제한보다 적을 때만 스폰
          if (currentEnemyCount < maxEnemies) {
            const spawnRate = Math.min(0.05 + (gameTime / 50000), 0.2);
            if (Math.random() < spawnRate) {
              spawnEnemy(this);
            }
          }

          // ===== 적 이동 처리 =====
          enemies.getChildren().forEach((enemy: any) => {
            const speed = enemy.getData('speed');
            const targetX = player.x;
            const targetY = player.y;
            
            // 적이 플레이어를 향해 이동
            const angle = Phaser.Math.Angle.Between(enemy.x, enemy.y, targetX, targetY);
            enemy.setVelocity(
              Math.cos(angle) * speed,
              Math.sin(angle) * speed
            );
          });

          // ===== 통계 업데이트 =====
          this.events.emit('updateStats', {
            level: playerStats.level,
            experience: playerStats.experience,
            health: playerStats.health,
            maxHealth: playerStats.maxHealth,
            gameTime,
            enemiesKilled: gameStats.enemiesKilled
          });
        }

        // ===== 적 스폰 함수 =====
        function spawnEnemy(scene: any) {
          // 화면 가장자리에서 랜덤하게 스폰
          const side = Math.floor(Math.random() * 4);
          let x = 0;
          let y = 0;

          switch (side) {
            case 0: // 위쪽
              x = Math.random() * 800;
              y = -50;
              break;
            case 1: // 오른쪽
              x = 850;
              y = Math.random() * 600;
              break;
            case 2: // 아래쪽
              x = Math.random() * 800;
              y = 650;
              break;
            case 3: // 왼쪽
              x = -50;
              y = Math.random() * 600;
              break;
          }

          // 적 생성 및 속성 설정
          const enemy = enemies.create(x, y, 'enemy');
          enemy.setData('health', 100 + playerStats.level * 5);
          enemy.setData('damage', 10 + playerStats.level * 2);
          enemy.setData('speed', 100 + Math.random() * 50);
          
          // 적이 플레이어를 향해 이동하도록 설정
          enemy.setData('targetX', player.x);
          enemy.setData('targetY', player.y);
          
          // 디버깅: 적 스폰 확인
          console.log('Enemy spawned at:', x, y, 'Total enemies:', enemies.getChildren().length);
        }

        // ===== 투사체 스폰 함수 =====
        function spawnProjectile(scene: any) {
          // 가장 가까운 적 찾기
          const nearestEnemy = findNearestEnemy();
          if (!nearestEnemy) return;

          // 투사체 생성
          const projectile = projectiles.create(player.x, player.y, 'projectile');
          
          // 투사체 방향 계산
          const angle = Phaser.Math.Angle.Between(player.x, player.y, nearestEnemy.x, nearestEnemy.y);
          const speed = 400;
          
          // 투사체 속도 설정
          projectile.setVelocity(
            Math.cos(angle) * speed,
            Math.sin(angle) * speed
          );

          // 투사체 수명 설정 (3초 후 자동 제거)
          scene.time.delayedCall(3000, () => {
            if (projectile.active) {
              projectile.destroy();
            }
          });
        }

        // ===== 가장 가까운 적 찾기 함수 =====
        function findNearestEnemy(): any {
          let nearest: any = null;
          let minDistance = Infinity;

          enemies.getChildren().forEach((enemy: any) => {
            const distance = Phaser.Math.Distance.Between(player.x, player.y, enemy.x, enemy.y);
            if (distance < minDistance) {
              minDistance = distance;
              nearest = enemy;
            }
          });

          return nearest;
        }

        // ===== 플레이어 피격 처리 함수 =====
        function playerHit(player: any, enemy: any) {
          const damage = enemy.getData('damage');
          playerStats.health -= damage;

          if (playerStats.health <= 0) {
            playerStats.health = 0;
            if (gameRef.current && gameRef.current.scene.scenes[0]) {
              gameRef.current.scene.scenes[0].events.emit('gameOver');
            }
          }
        }

        // ===== 투사체 히트 처리 함수 =====
        function projectileHit(projectile: any, enemy: any) {
          const damage = playerStats.damage;
          const currentHealth = enemy.getData('health');
          const newHealth = currentHealth - damage;

          projectile.destroy();

          if (newHealth <= 0) {
            // 적 처치 시 경험치 오브 생성
            const orb = experienceOrbs.create(enemy.x, enemy.y, 'experience');
            orb.setData('value', 10);
            
            enemy.destroy();
            setGameStats(prev => ({ ...prev, enemiesKilled: prev.enemiesKilled + 1 }));
          } else {
            enemy.setData('health', newHealth);
          }
        }

        // ===== 경험치 수집 함수 =====
        function collectExperience(player: any, orb: any) {
          const value = orb.getData('value');
          playerStats.experience += value;
          orb.destroy();

          // 레벨업 체크
          const expNeeded = playerStats.level * 100;
          if (playerStats.experience >= expNeeded) {
            playerStats.level++;
            playerStats.experience -= expNeeded;
            playerStats.maxHealth += 20;
            playerStats.health = playerStats.maxHealth;
            playerStats.damage += 5;
            playerStats.attackSpeed = Math.max(200, playerStats.attackSpeed - 50);
          }
        }

        // ===== 게임 재시작 함수 =====
        function restartGame(scene: any) {
          // 모든 오브젝트 제거
          enemies.clear(true, true);
          projectiles.clear(true, true);
          experienceOrbs.clear(true, true);

          // 플레이어 위치 리셋
          player.setPosition(400, 300);
          
          // 플레이어 스탯 리셋
          playerStats = {
            level: 1,
            experience: 0,
            health: 100,
            maxHealth: 100,
            speed: 200,
            damage: 20,
            attackSpeed: 500,
            lastAttack: 0
          };

          gameTime = 0;
        }

        setIsLoading(false);
      } catch (error) {
        console.error('Failed to load Phaser:', error);
        setIsLoading(false);
      }
    };

    initGame();

    // ===== 컴포넌트 정리 함수 =====
    return () => {
      if (gameRef.current) {
        gameRef.current.destroy(true);
        gameRef.current = null;
      }
    };
  }, []);

  // ===== 일시정지/재개 함수 =====
  const togglePause = () => {
    setIsPaused(!isPaused);
    if (gameRef.current) {
      const scene = gameRef.current.scene.scenes[0];
      if (scene) {
        if (isPaused) {
          scene.scene.resume();
        } else {
          scene.scene.pause();
        }
      }
    }
  };

  // ===== 게임 재시작 함수 =====
  const restartGame = () => {
    if (gameRef.current) {
      const scene = gameRef.current.scene.scenes[0];
      if (scene) {
        scene.events.emit('restart');
      }
    }
  };

  // ===== 로딩 화면 =====
  if (isLoading) {
    return (
      <div className="flex flex-col items-center justify-center min-h-screen bg-gray-900">
        <div className="text-white text-xl">게임 로딩 중...</div>
      </div>
    );
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-900">
      <div className="mb-4 text-white">
        <h1 className="text-2xl font-bold mb-2">Vampire Survivors Style Game</h1>
        <p className="text-sm text-gray-400">
          WASD/Arrow Keys: Move | Auto Attack | R: Restart
        </p>
      </div>
      
      <div id="game-container" className="border-2 border-gray-600" />
      
      <div className="mt-4 text-white text-center">
        <button
          onClick={togglePause}
          className="px-4 py-2 bg-blue-600 hover:bg-blue-700 rounded mr-2"
        >
          {isPaused ? 'Resume' : 'Pause'}
        </button>
        
        {isGameOver && (
          <button
            onClick={restartGame}
            className="px-4 py-2 bg-green-600 hover:bg-green-700 rounded"
          >
            Restart
          </button>
        )}
      </div>

      <div className="mt-4 text-white text-sm">
        <p>Level: {gameStats.level}</p>
        <p>Health: {gameStats.health}/{gameStats.maxHealth}</p>
        <p>Experience: {gameStats.experience}</p>
        <p>Time: {Math.floor(gameStats.gameTime / 1000)}s</p>
        <p>Enemies Killed: {gameStats.enemiesKilled}</p>
      </div>
    </div>
  );
} 