'use client'

import { useState, useCallback } from 'react';
import { AxiosError, AxiosRequestConfig } from 'axios';
import { axiosInstance } from './axiosInstance';


interface ApiResponse<T> {
    data: T | null;
    loading: boolean;
    error: string | null;
}

interface UseApiOptions<T> {
    onSuccess?: (data: T) => void;
    onError?: (error: AxiosError) => void;
}

export function useApi<T = any>(){
    const [state, setState] = useState<ApiResponse<T>>({
        data: null,
        loading: false,
        error: null,
    });

    const get = useCallback(async (url: string, options?: UseApiOptions<T>) => {
        try{
            setState(prev => ({...prev,loading:true,error:null}));
            const response = await axiosInstance.get<T>(url);
            setState({data:response.data, loading:false, error:null});
            options?.onSuccess?.(response.data);
            return response.data;
        }catch(error){
            const axiosError = error as AxiosError;
            setState({data:null, loading:false, error:axiosError.message});
            options?.onError?.(axiosError);
            throw axiosError;
        }
    },[])

    const post = useCallback(async <D = any>(url: string, data: D, options?: UseApiOptions<T>) => {
        try{
            setState(prev => ({...prev,loading:true,error:null}));
            const response = await axiosInstance.post<T>(url, data);
            setState({data:response.data, loading:false, error:null});
            options?.onSuccess?.(response.data);
            return response.data;
        }catch(error){
            const axiosError = error as AxiosError;
            setState({data:null, loading:false, error:axiosError.message});
            options?.onError?.(axiosError);
            throw axiosError;
        }
    },[])

    const put = useCallback(async <D = any>(url: string, data: D, options?: UseApiOptions<T>) => {
        try{
            setState(prev => ({...prev, loading:true, error:null}));
            const response = await axiosInstance.put<T>(url, data);
            setState({data:response.data, loading:false, error:null});
            options?.onSuccess?.(response.data);
            return response.data;
        }catch(error){
            const axiosError = error as AxiosError;
            setState({data:null, loading:false, error:axiosError.message});
            options?.onError?.(axiosError);
            throw axiosError;
        }
    },[])

    const del = useCallback(async (url: string, options?: UseApiOptions<T>) => {
        try{
            setState(prev => ({...prev, loading:true, error:null}));
            const response = await axiosInstance.delete<T>(url);
            setState({data:response.data, loading:false, error:null});
            options?.onSuccess?.(response.data);
            return response.data;
        }catch(error){
            const axiosError = error as AxiosError;
            setState({data:null, loading:false, error:axiosError.message});
            options?.onError?.(axiosError);
        }
    },[])

    return{
        ...state,
        get,
        post,
        put,
        del,
    }

}
