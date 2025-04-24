"use client";

import { useState,useEffect } from "react";
import { motion, AnimatePresence } from "framer-motion";
import { Button } from "@/components/ui/button";

export default function FantasyGate() {
    const [dialogue, setDialogue] = useState<string>("");
    const dialogues = [
      "...이 밤에도 별들은 잠들지 않는군.",
      "낯선 이여, 여긴 '달그림자 마을'이라네.",
      "조용히 하게. 이곳엔 오래된 것이 잠들어 있지...",
      "들어왔는가, 운명에 이끌려서..."
    ];
  
    useEffect(() => {
      const random = Math.floor(Math.random() * dialogues.length);
      setDialogue(dialogues[random]);
    }, []);
  
    return (
      <div className="relative h-screen w-screen bg-black text-white flex items-center justify-center">
        <div className="absolute inset-0 bg-[url('/village-night.jpg')] bg-cover bg-center opacity-30" />
  
        <motion.div
          className="z-10 max-w-xl text-center p-6 backdrop-blur-md bg-black/40 rounded-xl shadow-xl"
          initial={{ opacity: 0, y: 10 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 1 }}
        >
          <h1 className="text-4xl font-bold font-serif mb-4">달그림자 마을</h1>
          <p className="text-lg italic mb-6">{dialogue}</p>
          <Button className="bg-indigo-600 hover:bg-indigo-700 px-5 py-2 text-lg rounded-xl text-white">
            여관으로 들어간다
          </Button>
        </motion.div>
  
        <audio autoPlay loop className="hidden">
          <source src="/sounds/night-wind.mp3" type="audio/mpeg" />
        </audio>
      </div>
    );
}
