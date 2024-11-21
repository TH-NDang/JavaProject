import { Routes, Route, Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";
import { ROUTES } from "../types/routes";

import PublicLayout from "../components/layouts/PublicLayout";
import AuthLayout from "../components/layouts/AuthLayout";
import AdminLayout from "../components/layouts/AdminLayout";

import Home from "../pages/public/Home";
import About from "../pages/public/About";
import Services from "../pages/public/Services";
import Contact from "../pages/public/Contact";
import Portfolio from "../pages/public/Portfolio";
import Login from "../pages/auth/Login";

import AdminDashboard from "../pages/admin/Dashboard";
import AdminUsers from "../pages/admin/Users";
import AdminOrders from "../pages/admin/Orders";
import AdminServices from "../pages/admin/Services";
import AdminSchedule from "../pages/admin/Schedule";
import MaintenanceManagement from "../pages/admin/Maintenance";
import AdminReports from "../pages/admin/Reports";
import AdminSettings from "../pages/admin/Settings";
import AdminContacts from "../pages/admin/Contacts";

import PrivateRoute from "../components/guards/PrivateRoute";
import RoleRoute from "../components/guards/RoleRoute";

const AppRoutes = () => {
  const { loading } = useAuth();

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <Routes>
      <Route element={<PublicLayout />}>
        <Route path={ROUTES.HOME} element={<Home />} />
        <Route path={ROUTES.SERVICES} element={<Services />} />
        <Route path={ROUTES.PORTFOLIO} element={<Portfolio />} />
        <Route path={ROUTES.ABOUT} element={<About />} />
        <Route path={ROUTES.CONTACT} element={<Contact />} />
      </Route>

      <Route element={<AuthLayout />}>
        <Route path={ROUTES.LOGIN} element={<Login />} />
      </Route>

      <Route
        path="/admin"
        element={
          <PrivateRoute>
            <RoleRoute roles={["ADMIN"]}>
              <AdminLayout />
            </RoleRoute>
          </PrivateRoute>
        }
      >
        <Route index element={<Navigate to="dashboard" replace />} />
        <Route path="dashboard" element={<AdminDashboard />} />
        <Route path="users" element={<AdminUsers />} />
        <Route path="contacts" element={<AdminContacts />} />
        <Route path="orders" element={<AdminOrders />} />
        <Route path="schedule" element={<AdminSchedule />} />
        <Route path="reports" element={<AdminReports />} />
        <Route path="settings" element={<AdminSettings />} />
        <Route path="maintenance" element={<MaintenanceManagement />} />
        <Route path="services" element={<AdminServices />} />
      </Route>

      <Route path="*" element={<Navigate to={ROUTES.HOME} replace />} />
    </Routes>
  );
};

export default AppRoutes;
