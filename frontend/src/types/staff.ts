import { Role } from "./enums/Role";

export interface Staff {
  id: number;
  email: string;
  fullName: string;
  phone?: string;
  address?: string;
  role: Role;
  department: string;
  status: "ACTIVE" | "INACTIVE" | "ON_LEAVE";
  joinDate: string;
}
