import React from 'react'



interface GatherProgressBarProps {
    isGathering: boolean;
    gatheringProgress: number;
}



export default function GatherProgressBar({isGathering, gatheringProgress}: GatherProgressBarProps) {
  
    if (!isGathering) {
        return null;
    }

    return (
        (
        <div className="w-64">
          <div className="w-full h-4 bg-gray-800 border-2 border-gray-600 rounded-full overflow-hidden">
            <div 
              className="h-full bg-gradient-to-r from-blue-400 to-blue-600 transition-all duration-300"
              style={{ width: `${gatheringProgress}%` }}
            />
          </div>
          <div className="text-center text-sm mt-1 text-gray-600">
            채집 진행률: {gatheringProgress}%
          </div>
        </div>
      )
  )
}