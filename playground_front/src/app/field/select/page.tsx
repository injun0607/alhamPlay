'use client'

import React, { useEffect } from 'react';
import { useApi } from '@/hooks/common/useApi';
import { FieldDataDTO } from '@/types/map';
import { Map } from '@/components/map/Map';
import { CommonResponse } from '@/types/response';
import { useFieldStore } from '@/store/fieldStore';
import '../../globals.css'

export default function SelectedFieldPage() {    
    const { data: fieldData, loading, error, get } = useApi<CommonResponse<FieldDataDTO>>();
    const { setSelectedTile } = useFieldStore();
    
    useEffect(() => {
        const fetchFieldData = async () => {
            await get(`/field/tiles`);
        }
        fetchFieldData();
    }, [get]);

    // fieldData가 로드되면 초기 selectedTile 설정
    useEffect(() => {
        if (fieldData?.data?.dailyTileInfo.selectedTileFlag) {
            const { selectedTileX, selectedTileY, selectedTileRarity, availableUpdateCount } = fieldData.data.dailyTileInfo;
            setSelectedTile({ 
                selectedTileFlag: true,
                selectedTileX: selectedTileX,
                selectedTileY: selectedTileY,
                selectedTileRarity: fieldData.data.dailyTileInfo.selectedTileRarity,
                availableUpdateCount: fieldData.data.dailyTileInfo.availableUpdateCount
            });
        }
    }, [fieldData, setSelectedTile]);

    if (loading) return <div>Loading... </div>;
    if (error) return <div>Error: {error}</div>;
    if (!fieldData || !(fieldData.status == 'OK' || fieldData.status == 'NO_CONTENT')) return null;

    return (
        <div>
            <Map fieldData={fieldData.data!} />
        </div>
    );
}
