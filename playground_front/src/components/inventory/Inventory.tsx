'use client'

import { useState,useEffect } from "react";

import MaterialInventoryTab from "./MaterialInventoryTab";
import EquipmentInventoryTab from "./EquipmentInventoryTab";
import { InventoryStore } from "@/store/inventoryStore";

interface InventoryProps {
  setIsOpen: (isOpen: boolean) => void;
}

export default function Inventory({setIsOpen}:InventoryProps) {

  const {materialInventory,equipmentInventory} = InventoryStore();
  const [selectedTab,setSelectedTab] = useState("material");

  useEffect(()=>{
    const handleTabChange = (e:KeyboardEvent) => {
      if(e.key === 'Tab'){
        e.preventDefault();
        e.stopPropagation();
        setSelectedTab(prev => prev === 'material' ? 'equipment' : 'material');
      };
    };
    window.addEventListener('keydown',handleTabChange);
    return () => {
      window.removeEventListener('keydown',handleTabChange);
    };
  },[])

  return (
    <div className="inventory-container">
      {/* 탭 버튼들 */}
      <div className="flex inventory-tabs">
        <button 
          onClick={() => setSelectedTab('material')}
          className={`inventory-tab font-bold pixel-button ${
            selectedTab === 'material' 
              ? 'bg-blue-600 text-white' 
              : 'bg-gray-600 text-gray-300'
          }`}
        >
          MATERIALS
        </button>
        <button
          onClick={() => setSelectedTab('equipment')}
          className={`inventory-tab font-bold pixel-button ${
            selectedTab === 'equipment' 
              ? 'bg-blue-600 text-white' 
              : 'bg-gray-600 text-gray-300'
          }`}
        >
          EQUIPMENT
        </button>
      </div>
      
      {/* 탭 내용 */}
      {selectedTab === 'material' ? (
        <MaterialInventoryTab materialInventory={materialInventory} />
      ) : (
        <EquipmentInventoryTab equipmentInventory={equipmentInventory} />
      )}
      
      <button 
        onClick={() => setIsOpen(false)} 
        className="inventory-close w-full pixel-button bg-gray-600 text-white py-2 text-xs font-bold hover:bg-gray-500"
      >
        CLOSE
      </button>
    </div>
  )
}