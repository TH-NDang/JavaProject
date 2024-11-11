import { useState } from "react";
import { Outlet, Link, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../../config/AuthContext";
import {
  LayoutDashboard,
  Users,
  FileText,
  Settings,
  ChevronLeft,
  ChevronRight,
  Bell,
  LogOut,
  Menu,
  Calendar,
  BarChart2,
  Home,
  MessageSquare,
  Wrench,
  Store,
} from "lucide-react";

const menuItems = [
  {
    icon: LayoutDashboard,
    label: "Tổng quan",
    path: "/admin/dashboard",
  },
  {
    icon: Users,
    label: "Quản lý người dùng",
    path: "/admin/users",
  },
  {
    icon: MessageSquare,
    label: "Quản lý liên hệ",
    path: "/admin/contacts",
  },
  {
    icon: FileText,
    label: "Quản lý đơn hàng",
    path: "/admin/orders",
  },
  {
    icon: Store,
    label: "Quản lý dịch vụ",
    path: "/admin/services",
  },
  {
    icon: Wrench,
    label: "Quản lý Bảo trì",
    path: "/admin/maintenance",
  },
  {
    icon: Calendar,
    label: "Lịch công việc",
    path: "/admin/schedule",
  },
  {
    icon: BarChart2,
    label: "Báo cáo",
    path: "/admin/reports",
  },
  {
    icon: Settings,
    label: "Cài đặt",
    path: "/admin/settings",
  },
];

export default function AdminLayout() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const { user, logout } = useAuth();
  const location = useLocation();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <div className="h-screen flex overflow-hidden bg-gray-100">
      {/* Mobile menu backdrop */}
      {isMobileMenuOpen && (
        <div
          className="fixed inset-0 bg-gray-600 bg-opacity-75 lg:hidden"
          onClick={() => setIsMobileMenuOpen(false)}
        />
      )}

      {/* Sidebar */}
      <div
        className={`
          ${isMobileMenuOpen ? "translate-x-0" : "-translate-x-full"}
          ${isSidebarOpen ? "w-64" : "w-20"}
          fixed inset-y-0 left-0 z-30 
          transform lg:translate-x-0 lg:relative
          transition duration-300 ease-in-out
          bg-primary-700 text-white
        `}
      >
        {/* Sidebar Header */}
        <div className="h-16 flex items-center justify-between px-4 bg-primary-800">
          {isSidebarOpen && (
            <Link to="/admin/dashboard" className="flex items-center space-x-3">
              <img className="h-8 w-8" src="/logo.png" alt="Koi Pond Co." />
              <span className="text-xl font-bold">Koi Pond</span>
            </Link>
          )}
          <button
            onClick={() => setIsSidebarOpen(!isSidebarOpen)}
            className="p-2 rounded-md hover:bg-primary-600"
          >
            {isSidebarOpen ? (
              <ChevronLeft size={20} />
            ) : (
              <ChevronRight size={20} />
            )}
          </button>
        </div>

        {/* Navigation Menu */}
        <nav className="mt-4 flex-1 space-y-1 px-2">
          {menuItems.map((item) => {
            const isActive = location.pathname === item.path;
            const Icon = item.icon;

            return (
              <Link
                key={item.path}
                to={item.path}
                className={`
                  flex items-center px-2 py-2 text-sm font-medium rounded-md
                  transition-colors duration-200
                  ${
                    isActive
                      ? "bg-primary-800 text-white"
                      : "text-primary-100 hover:bg-primary-600 hover:text-white"
                  }
                `}
              >
                <Icon className="mr-3 h-6 w-6" />
                {isSidebarOpen && <span>{item.label}</span>}
              </Link>
            );
          })}
        </nav>

        {/* User Profile */}
        <div className="border-t border-primary-600 p-4">
          <div className="flex items-center">
            <img
              src="/api/placeholder/40/40"
              alt="User avatar"
              className="h-10 w-10 rounded-full bg-primary-600"
            />
            {isSidebarOpen && (
              <div className="ml-3">
                <p className="text-sm font-medium">{user?.fullName}</p>
                <p className="text-xs text-primary-300">{user?.email}</p>
              </div>
            )}
          </div>
          <button
            onClick={handleLogout}
            className="mt-4 flex items-center w-full px-2 py-2 text-sm font-medium rounded-md text-primary-100 hover:bg-primary-600 hover:text-white"
          >
            <LogOut className="mr-3 h-6 w-6" />
            {isSidebarOpen && <span>Đăng xuất</span>}
          </button>
        </div>
      </div>

      {/* Main Content */}
      <div className="flex-1 flex flex-col overflow-hidden">
        {/* Top Header */}
        <header className="bg-white shadow">
          <div className="flex items-center justify-between h-16 px-4">
            <button
              onClick={() => setIsMobileMenuOpen(true)}
              className="lg:hidden p-2 rounded-md text-gray-400 hover:text-gray-500 hover:bg-gray-100"
            >
              <Menu className="h-6 w-6" />
            </button>

            <div className="flex items-center">
              <button className="p-2 rounded-full text-gray-400 hover:text-gray-500 hover:bg-gray-100">
                <Bell className="h-6 w-6" />
              </button>
            </div>
          </div>
        </header>

        {/* Main Content Area */}
        <main className="flex-1 overflow-auto">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
            <Outlet />
          </div>
        </main>
      </div>
    </div>
  );
}
