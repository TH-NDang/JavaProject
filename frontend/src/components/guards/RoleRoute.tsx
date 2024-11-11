import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../../config/AuthContext";
import { USER_ROLES, ROUTES } from "../../types/routes";

interface RoleRouteProps {
  children: React.ReactNode;
  roles: Array<keyof typeof USER_ROLES>;
}

const RoleRoute: React.FC<RoleRouteProps> = ({ children, roles }) => {
  const { user } = useAuth();
  if (!user || !roles.includes(user.role)) {
    // Redirect based on user role
    if (user?.role === USER_ROLES.CUSTOMER) {
      return <Navigate to={ROUTES.CUSTOMER.DASHBOARD} replace />;
    }
    if (user?.role === USER_ROLES.STAFF) {
      return <Navigate to={ROUTES.STAFF.DASHBOARD} replace />;
    }
    if (user?.role === USER_ROLES.ADMIN) {
      return <Navigate to={ROUTES.ADMIN.DASHBOARD} replace />;
    }
    return <Navigate to={ROUTES.HOME} replace />;
  }
  return <>{children}</>;
};

export default RoleRoute;
