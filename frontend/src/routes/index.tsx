import { Routes, Route, Navigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import { ROUTES, USER_ROLES } from "../constants/routes";

// Layouts
import PublicLayout from "../components/layouts/PublicLayout";
import AuthLayout from "../components/layouts/AuthLayout";
import AdminLayout from "../components/layouts/AdminLayout";

// Public Pages
import Home from "../pages/public/Home";
import About from "../pages/public/About";
import Services from "../pages/public/Services";
import Contact from "../pages/public/Contact";
import Portfolio from "../pages/public/Portfolio";
import Login from "../pages/auth/Login";

// Admin Pages
import AdminDashboard from "../pages/admin/Dashboard";
import AdminUsers from "../pages/admin/Users";
import AdminOrders from "../pages/admin/Orders";
import AdminSchedule from "../pages/admin/Schedule";
import AdminReports from "../pages/admin/Reports";
import AdminSettings from "../pages/admin/Settings";
import AdminContacts from "../pages/admin/Contacts"; // Added AdminContacts import
// Guards
import PrivateRoute from "../components/guards/PrivateRoute";
import RoleRoute from "../components/guards/RoleRoute";

const AppRoutes = () => {
  const { user, loading } = useAuth();

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <Routes>
      {/* Public Routes */}
      <Route element={<PublicLayout />}>
        <Route path={ROUTES.HOME} element={<Home />} />
        <Route path={ROUTES.SERVICES} element={<Services />} />
        <Route path={ROUTES.PORTFOLIO} element={<Portfolio />} />
        <Route path={ROUTES.ABOUT} element={<About />} />
        <Route path={ROUTES.CONTACT} element={<Contact />} />
      </Route>

      {/* Auth Routes */}
      <Route element={<AuthLayout />}>
        <Route path={ROUTES.LOGIN} element={<Login />} />
      </Route>

      {/* Customer Routes */}
      {/* <Route
            element={
              <PrivateRoute>
                <RoleRoute roles={[USER_ROLES.CUSTOMER]}>
                  <DashboardLayout userRole={USER_ROLES.CUSTOMER} />
                </RoleRoute>
              </PrivateRoute>
            }
          >
            <Route
              path={ROUTES.CUSTOMER.DASHBOARD}
              element={<CustomerDashboard />}
            />
            <Route path={ROUTES.CUSTOMER.ORDERS} element={<CustomerOrders />} />
            {/* Thêm các routes khác cho customer */}
      {/* </Route> */}

      {/* Staff Routes */}
      {/* <Route
            element={
              <PrivateRoute>
                <RoleRoute roles={[USER_ROLES.STAFF]}>
                  <DashboardLayout userRole={USER_ROLES.STAFF} />
                </RoleRoute>
              </PrivateRoute>
            }
          >
            <Route path={ROUTES.STAFF.DASHBOARD} element={<StaffDashboard />} />
            <Route path={ROUTES.STAFF.ORDERS} element={<StaffOrders />} />
            <Route path={ROUTES.STAFF.CUSTOMERS} element={<StaffCustomers />} />
            <Route path={ROUTES.STAFF.SCHEDULE} element={<StaffSchedule />} />
          </Route> */}

      {/* Admin Routes */}
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
      </Route>

      {/* Fallback Route */}
      <Route path="*" element={<Navigate to={ROUTES.HOME} replace />} />
    </Routes>
  );
};

export default AppRoutes;
