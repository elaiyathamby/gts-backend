import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { ToDo } from '@/model/todo';

export async function getAllToDos(): Promise<ToDo[]>   { 
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/todo', config);
        return response.data;
    } catch (error) {
        return error;   
    }
}

export async function getFilterToDos(query: string): Promise<ToDo[]>   { 
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/todo/filter?'+query, config);
        return response.data;
    } catch (error) {
        return error;   
    }
}

export async function getTodayToDos(): Promise<ToDo[]>   { 
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/todo/today', config);
        return response.data;
    } catch (error) {
        return error;   
    }
}

export async function addNewToDo(newToDo: ToDo): Promise<any>   {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.post(API_ROOT + '/api/todo', newToDo, config);
        return response.data;
    } catch (error) {
        return error;   
    }
}

export async function updateToDo(toDo: ToDo): Promise<any>   {
    const config = {        
        withCredentials: true
    }
    try {
        const response = await axios.put(API_ROOT + '/api/todo', toDo, config);
        return response.data;
    } catch (error) {
        return error;   
    }
}