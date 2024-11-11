import axios from "../config/axios";
import { User, CreateUserRequest, UpdateUserRequest } from "../types/user";

export class UserService {
  static async getAllUsers(params: {
    page: number;
    size: number;
    search?: string;
    role?: string;
    status?: string;
  }) {
    const { page, size, search, role, status } = params;
    const response = await axios.get("/api/users", {
      params: {
        page,
        size,
        search,
        role,
        status,
      },
    });
    return response.data;
  }

  static async getUserById(id: number) {
    const response = await axios.get(`/api/users/${id}`);
    return response.data;
  }

  static async createUser(data: CreateUserRequest) {
    const response = await axios.post("/api/users", data);
    return response.data;
  }

  static async updateUser(id: number, data: UpdateUserRequest) {
    const response = await axios.put(`/api/users/${id}`, data);
    return response.data;
  }

  static async updateUserStatus(id: number, status: string) {
    const response = await axios.put(`/api/users/${id}/status`, { status });
    return response.data;
  }

  static async deleteUser(id: number) {
    const response = await axios.delete(`/api/users/${id}`);
    return response.data;
  }
}
