export type UserRole = "ADMIN" | "STAFF" | "CUSTOMER";

export interface User {
  id: number;
  email: string;
  fullName: string;
  role: UserRole;
  status?: string;
}

export interface AuthUser extends User {
  token?: string;
}
