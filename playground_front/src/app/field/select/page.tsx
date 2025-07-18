'use client'

import React, { useEffect } from 'react';
import { useParams } from 'next/navigation';
import { useApi } from '@/hooks/common/useApi';
import { FieldDataDTO } from '@/types/map';
import { Map } from '@/components/map/Map';
import { CommonResponse } from '@/types/response';
import '../../globals.css'

export default function SelectedFieldPage() {    
    const { data: fieldData, loading, error, get } = useApi<CommonResponse<FieldDataDTO>>();
    useEffect(() => {
        const fetchFieldData = async () => {
            await get(`/field/select`);
        }
        fetchFieldData();
    }, [get]);

    if (loading) return <div>Loading... </div>;
    if (error) return <div>Error: {error}</div>;
    if (!fieldData || fieldData.status !== 'FOUND') return null;

    return (
        <div>
            <Map fieldData={fieldData.data!} />
        </div>
    );
}
