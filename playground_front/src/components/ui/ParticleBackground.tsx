'use client'

import { useEffect } from 'react';
import { tsParticles } from "@tsparticles/engine";
import { loadSlim } from "@tsparticles/slim";

export function ParticleBackground() {
  useEffect(() => {
    const initParticles = async () => {
      await loadSlim(tsParticles);
      
      await tsParticles.load({
        id: "tsparticles",
        options: {
          background: {
            color: {
              value: 'transparent',
            },
          },
          fpsLimit: 120,
          particles: {
            color: {
              value: '#ffffff',
            },
            links: {
              color: '#ffffff',
              distance: 150,
              enable: true,
              opacity: 0.2,
              width: 1,
            },
            move: {
              direction: 'none',
              enable: true,
              outModes: {
                default: 'bounce',
              },
              random: false,
              speed: 1,
              straight: false,
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
              value: 0.3,
            },
            shape: {
              type: 'circle',
            },
            size: {
              value: { min: 1, max: 3 },
            },
          },
          detectRetina: true,
        }
      });
    };
    
    initParticles();
  }, []);

  return <div id="tsparticles" className="fixed inset-0 -z-10" />;
} 