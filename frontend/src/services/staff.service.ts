// src/services/api/staff.service.ts
import axios from "../config/axios";
import { Staff } from "../types/staff";

export class StaffService {
  static async getAllStaff() {
    const response = await axios.get<Staff[]>("/api/staff");
    return response.data;
  }

  static async getStaffById(id: number) {
    const response = await axios.get<Staff>(`/api/staff/${id}`);
    return response.data;
  }

  static async getMaintenanceStaff() {
    // API endpoint để lấy danh sách nhân viên bảo trì
    const response = await axios.get<Staff[]>(
      "/api/staff?department=maintenance"
    );
    return response.data;
  }

  static async updateStaffStatus(id: number, status: string) {
    const response = await axios.put<Staff>(`/api/staff/${id}/status`, {
      status,
    });
    return response.data;
  }

  static async deleteStaff(id: number) {
    await axios.delete(`/api/staff/${id}`);
  }

  static async assignStaffToMaintenance(staffId: number, maintenanceId: number) {
    const response = await axios.post(
      `/api/staff/${staffId}/maintenance/${maintenanceId}`
    );
    return response.data;
  }
}
