import axios from "../../config/axios";
import { ProjectDTO } from "../../types/project"; // Adjust the import path as necessary
import { OrderDTO } from "../../types/order"; // Adjust the import path as necessary

export class ProjectService {
    static async getAllProjects(): Promise<ProjectDTO[]> {
        try {
            const response = await axios.get<ProjectDTO[]>("/api/projects");
            return response.data;
        } catch (error) {
            console.error("Failed to fetch all projects", error);
            throw error; // Rethrow or handle the error as needed
        }
    }

    static async getProjectById(id: number): Promise<ProjectDTO> {
        try {
            const response = await axios.get<ProjectDTO>(`/api/projects/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Failed to fetch project with id ${id}`, error);
            throw error;
        }
    }

    static async createProject(projectDTO: ProjectDTO): Promise<ProjectDTO> {
        try {
            const response = await axios.post<ProjectDTO>("/api/projects", projectDTO);
            return response.data;
        } catch (error) {
            console.error("Failed to create project", error);
            throw error;
        }
    }

    static async updateProject(id: number, projectDTO: ProjectDTO): Promise<ProjectDTO> {
        try {
            const response = await axios.put<ProjectDTO>(`/api/projects/${id}`, projectDTO);
            return response.data;
        } catch (error) {
            console.error(`Failed to update project with id ${id}`, error);
            throw error;
        }
    }

    static async deleteProject(id: number): Promise<void> {
        try {
            await axios.delete(`/api/projects/${id}`);
        } catch (error) {
            console.error(`Failed to delete project with id ${id}`, error);
            throw error;
        }
    }

    static async getOrdersByProjectId(projectId: number): Promise<OrderDTO[]> {
        try {
            const response = await axios.get<OrderDTO[]>(`/api/projects/${projectId}/orders`);
            return response.data;
        } catch (error) {
            console.error(`Failed to fetch orders for project with id ${projectId}`, error);
            throw error;
        }
    }
}