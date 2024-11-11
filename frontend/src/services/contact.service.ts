import axios from "../config/axios";
import {
  Contact,
  ContactResponse,
  CreateContactRequest,
  UpdateContactRequest,
} from "../types/contact";

export class ContactService {
  static async getAllContacts(params: {
    page: number;
    size: number;
    search?: string;
    status?: string;
  }) {
    const { page, size, search, status } = params;
    const response = await axios.get<ContactResponse>("/api/contacts", {
      params: {
        page,
        size,
        search,
        status,
      },
    });
    return response.data;
  }

  static async getContactById(id: number) {
    const response = await axios.get<Contact>(`/api/contacts/${id}`);
    return response.data;
  }

  static async createContact(data: CreateContactRequest) {
    const response = await axios.post<Contact>("/api/contacts", data);
    return response.data;
  }

  static async updateContact(id: number, data: UpdateContactRequest) {
    const response = await axios.put<Contact>(`/api/contacts/${id}`, data);
    return response.data;
  }

  static async deleteContact(id: number) {
    const response = await axios.delete(`/api/contacts/${id}`);
    return response.data;
  }
}
