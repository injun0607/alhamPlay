'use client'

import React, { useEffect } from 'react';
import { useParams } from 'next/navigation';
import { useApi } from '@/hook/common/useApi';
import { FieldData } from '@/types/map';
import { Map } from '@/components/map/Map';
import { ActionMenu } from '@/components/map/ActionMenu';

export default function FieldPage() {
    const params = useParams();
    const id = Number(params.id);
    
    const { data: fieldData, loading, error, get } = useApi<FieldData>();

    useEffect(() => {
        get(`/field/${id}`);
    }, [id]);

    if (loading) return <div>Loading... </div>;
    if (error) return <div>Error: {error}</div>;
    if (!fieldData) return null;

    return (
        <div>
            <Map id={id} fieldData={fieldData} />
        </div>
    );
}
