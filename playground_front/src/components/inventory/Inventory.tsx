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

  

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-96 max-h-96">
        {/* 탭 버튼들 */}
        <div className="flex gap-2 mb-4">
          <button 
            onClick={() => setSelectedTab('material')}
            className={`px-4 py-2 rounded ${selectedTab === 'material' ? 'bg-blue-500 text-white' : 'bg-gray-200'}`}
          >
            재료
          </button>
          <button 
            onClick={() => setSelectedTab('equipment')}
            className={`px-4 py-2 rounded ${selectedTab === 'equipment' ? 'bg-blue-500 text-white' : 'bg-gray-200'}`}
          >
            장비
          </button>
        </div>
        
        {/* 탭 내용 */}
        {selectedTab === 'material' ? <MaterialInventoryTab materialInventory={materialInventory} /> : <EquipmentInventoryTab equipmentInventory={equipmentInventory} />}
        <button onClick={() => setIsOpen(false)} className="mt-4 px-4 py-2 bg-gray-300 rounded">
          닫기
        </button>
      </div>
    </div>
  )
}