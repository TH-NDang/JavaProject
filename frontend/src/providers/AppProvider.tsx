import React from "react";
import { AuthProvider } from "../contexts/AuthContext";

interface AppProviderProps {
  children: React.ReactNode;
}

export const AppProvider: React.FC<AppProviderProps> = ({ children }) => {
  return <AuthProvider>{children}</AuthProvider>;
};
