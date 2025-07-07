import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/auth';

const apiClient = axios.create({
  baseURL: API_BASE_URL,
});

export const login = async (username, password) => {
    // axios 在遇到 4xx 或 5xx 的响应时会自动抛出错误，这很方便
    const response = await apiClient.post('/login', { username, password });
    return response.data;
};