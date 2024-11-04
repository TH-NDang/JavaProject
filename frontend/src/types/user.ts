export interface User {
  id: number;
  email: string;
  fullName: string;
  phone: string;
  address: string;
  role: string;
  status: string;
  createdAt: string;
  updatedAt: string;
}

export interface CreateUserRequest {
  email: string;
  password: string;
  fullName: string;
  phone: string;
  address: string;
  role: string;
}

export interface UpdateUserRequest {
  email?: string;
  fullName?: string;
  phone?: string;
  address?: string;
}

export interface UsersResponse {
  content: User[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
}

