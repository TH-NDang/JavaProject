import axios from "../../config/axios";
import { MaintenanceSchedule } from "../../types/maintenance";

export class MaintenanceService {
  static async getAllSchedules() {
    const response = await axios.get<MaintenanceSchedule[]>(
      "/api/maintenance/schedules"
    );
    return response.data;
  }

  static async getScheduleById(id: number) {
    const response = await axios.get<MaintenanceSchedule>(
      `/api/maintenance/schedules/${id}`
    );
    return response.data;
  }

  static async createSchedule(data: MaintenanceSchedule) {
    const response = await axios.post<MaintenanceSchedule>(
      "/api/maintenance/schedules",
      data
    );
    return response.data;
  }

  static async updateSchedule(id: number, data: Partial<MaintenanceSchedule>) {
    const response = await axios.put<MaintenanceSchedule>(
      `/api/maintenance/schedules/${id}`,
      data
    );
    return response.data;
  }

  static async completeSchedule(
    id: number,
    report: MaintenanceSchedule["completionReport"]
  ) {
    const response = await axios.post<MaintenanceSchedule>(
      `/api/maintenance/schedules/${id}/complete`,
      report
    );
    return response.data;
  }

  static async cancelSchedule(id: number) {
    const response = await axios.post<MaintenanceSchedule>(
      `/api/maintenance/schedules/${id}/cancel`
    );
    return response.data;
  }
}
