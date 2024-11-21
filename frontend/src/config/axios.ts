import axios from "axios";
import { Toast } from "../services/toast.service";

const API_URL = process.env.REACT_APP_API_URL || "http://localhost:9999";

const axiosInstance = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json;charset=UTF-8",
    "Accept-Charset": "UTF-8",
  },
});

axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("auth_token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    const message = error.response?.data?.message || "Có lỗi xảy ra";
    Toast.error(message);
    return Promise.reject(error);
  }
);

export default axiosInstance;
