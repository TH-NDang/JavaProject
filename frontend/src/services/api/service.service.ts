// import axios from "../../config/axios";
// import { ServicePrice } from "../../types/service";

// export class ServiceService {
//   static async getAllServices() {
//     const response = await axios.get<ServicePrice[]>("/api/services");
//     return response.data;
//   }

//   static async getServiceById(id: number) {
//     const response = await axios.get<ServicePrice>(`/api/services/${id}`);
//     return response.data;
//   }

//   static async createService(data: Omit<ServicePrice, "id">) {
//     const response = await axios.post<ServicePrice>("/api/services", data);
//     return response.data;
//   }

//   static async updateService(id: number, data: Partial<ServicePrice>) {
//     const response = await axios.put<ServicePrice>(`/api/services/${id}`, data);
//     return response.data;
//   }

//   static async deleteService(id: number) {
//     await axios.delete(`/api/services/${id}`);
//   }
// }

import { ServicePrice } from "../../types/service";
import { mockServices } from "../../pages/admin/Services/mockData";

let services = [...mockServices];

export class ServiceService {
  static async getAllServices(): Promise<ServicePrice[]> {
    // Giả lập delay API
    await new Promise((resolve) => setTimeout(resolve, 500));
    return services;
  }

  static async getServiceById(id: number): Promise<ServicePrice> {
    await new Promise((resolve) => setTimeout(resolve, 300));
    const service = services.find((s) => s.id === id);
    if (!service) throw new Error("Service not found");
    return service;
  }

  static async createService(
    data: Omit<ServicePrice, "id">
  ): Promise<ServicePrice> {
    await new Promise((resolve) => setTimeout(resolve, 500));
    const newService: ServicePrice = {
      ...data,
      id: Math.max(...services.map((s) => s.id || 0)) + 1,
    };
    services.push(newService);
    return newService;
  }

  static async updateService(
    id: number,
    data: Partial<ServicePrice>
  ): Promise<ServicePrice> {
    await new Promise((resolve) => setTimeout(resolve, 500));
    const index = services.findIndex((s) => s.id === id);
    if (index === -1) throw new Error("Service not found");

    services[index] = {
      ...services[index],
      ...data,
    };
    return services[index];
  }

  static async deleteService(id: number): Promise<void> {
    await new Promise((resolve) => setTimeout(resolve, 500));
    const index = services.findIndex((s) => s.id === id);
    if (index === -1) throw new Error("Service not found");
    services = services.filter((s) => s.id !== id);
  }
}
