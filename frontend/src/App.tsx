import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { ROUTES, USER_ROLES } from "./constants/routes";

// Layouts
import PublicLayout from "./components/layouts/PublicLayout";
import AuthLayout from "./components/layouts/AuthLayout";
import DashboardLayout from "./components/layouts/DashboardLayout";

// Public Pages
import Home from "./pages/public/Home";
import Services from "./pages/public/Services";
import Portfolio from "./pages/public/Portfolio";
import About from "./pages/public/About";
import Contact from "./pages/public/Contact";

// Auth Pages
import Login from "./pages/auth/Login";

// Customer Pages
// import CustomerDashboard from "./pages/customer/Dashboard";
// import CustomerOrders from "./pages/customer/Orders";
// import CustomerOrderDetail from "./pages/customer/OrderDetail";
// import CustomerProfile from "./pages/customer/Profile";
// import CustomerMaintenance from "./pages/customer/Maintenance";

// // Staff Pages
// import StaffDashboard from "./pages/staff/Dashboard";
// import StaffOrders from "./pages/staff/Orders";
// import StaffOrderDetail from "./pages/staff/OrderDetail";
// import StaffCustomers from "./pages/staff/Customers";
// import StaffSchedule from "./pages/staff/Schedule";

// Admin Pages
// import AdminDashboard from "./pages/admin/Dashboard";
// import AdminUsers from "./pages/admin/Users";
// import AdminOrders from "./pages/admin/Orders";
// import AdminServices from "./pages/admin/Services";
// import AdminReports from "./pages/admin/Reports";
// import AdminSettings from "./pages/admin/Settings";

import { AuthProvider } from "./contexts/AuthContext";

// Guards
import PrivateRoute from "./components/guards/PrivateRoute";
import RoleRoute from "./components/guards/RoleRoute";

const App: React.FC = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
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
        > */}
          {/* <Route
            path={ROUTES.CUSTOMER.DASHBOARD}
            element={<CustomerDashboard />}
          />
          <Route path={ROUTES.CUSTOMER.ORDERS} element={<CustomerOrders />} />
          <Route
            path={`${ROUTES.CUSTOMER.ORDERS}/:id`}
            element={<CustomerOrderDetail />}
          />
          <Route
            path={ROUTES.CUSTOMER.MAINTENANCE}
            element={<CustomerMaintenance />}
          />
          <Route path={ROUTES.CUSTOMER.PROFILE} element={<CustomerProfile />} />
        </Route> */}

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
          <Route
            path={`${ROUTES.STAFF.ORDERS}/:id`}
            element={<StaffOrderDetail />}
          />
          <Route path={ROUTES.STAFF.CUSTOMERS} element={<StaffCustomers />} />
          <Route path={ROUTES.STAFF.SCHEDULE} element={<StaffSchedule />} />
        </Route> */}

          {/* Admin Routes */}
          {/* <Route
          element={
            <PrivateRoute>
              <RoleRoute roles={[USER_ROLES.ADMIN]}>
                <DashboardLayout userRole={USER_ROLES.ADMIN} />
              </RoleRoute>
            </PrivateRoute>
          }
        >
          <Route path={ROUTES.ADMIN.DASHBOARD} element={<AdminDashboard />} />
          <Route path={ROUTES.ADMIN.USERS} element={<AdminUsers />} />
          <Route path={ROUTES.ADMIN.ORDERS} element={<AdminOrders />} />
          <Route path={ROUTES.ADMIN.SERVICES} element={<AdminServices />} />
          <Route path={ROUTES.ADMIN.REPORTS} element={<AdminReports />} />
          <Route path={ROUTES.ADMIN.SETTINGS} element={<AdminSettings />} />
        </Route> */}

          {/* Fallback Route */}
          <Route path="*" element={<Navigate to={ROUTES.HOME} replace />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;
