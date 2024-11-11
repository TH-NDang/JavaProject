import axios from "../config/axios";
import { ServicePrice } from "../types/service";

export class ServiceService {
  static async getAllServices() {
    const response = await axios.get<ServicePrice[]>("/api/services");
    return response.data;
  }

  static async getServiceById(id: number) {
    const response = await axios.get<ServicePrice>(`/api/services/${id}`);
    return response.data;
  }

  static async createService(data: Omit<ServicePrice, "id">) {
    const response = await axios.post<ServicePrice>("/api/services", data);
    return response.data;
  }

  static async updateService(id: number, data: Partial<ServicePrice>) {
    const response = await axios.put<ServicePrice>(`/api/services/${id}`, data);
    return response.data;
  }

  static async deleteService(id: number) {
    await axios.delete(`/api/services/${id}`);
  }
}
