import React, { createContext, useContext, useState, useEffect } from "react";
import api from "../config/axios";

interface User {
  id: number;
  email: string;
  fullName: string;
  role: "ADMIN" | "STAFF" | "CUSTOMER";
}

interface AuthContextType {
  user: User | null;
  loading: boolean;
  error: string | null;
  login: (email: string, password: string) => Promise<User>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const checkAuth = async () => {
      const token = localStorage.getItem("token");
      if (token) {
        try {
          const response = await api.get<User>("/auth/me");
          setUser(response.data);
        } catch (error) {
          localStorage.removeItem("token");
        }
      }
      setLoading(false);
    };

    checkAuth();
  }, []);

  const login = async (email: string, password: string): Promise<User> => {
    try {
      setError(null);
      setLoading(true);

      const response = await api.post<{
        token: string;
        user: User;
      }>("/auth/login", {
        email,
        password,
      });

      const { token, user } = response.data;
      localStorage.setItem("token", token);
      setUser(user);

      return user;
    } catch (err: any) {
      const errorMessage = err.response?.data?.message || "Đăng nhập thất bại";
      setError(errorMessage);
      throw new Error(errorMessage);
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    setUser(null);
  };

  const value = {
    user,
    loading,
    error,
    login,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};
