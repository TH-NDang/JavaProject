import React, { useState, useEffect, useRef } from "react";
import {
  Home,
  Users,
  FileText,
  BarChart,
  Star,
  Settings,
  LogOut,
  ChevronLeft,
  ChevronRight,
  X,
  LucideIcon,
} from "lucide-react";
import { userRoles, rolePermissions } from "../utils/roles";

type UserRole =
  | "customer"
  | "consultingStaff"
  | "designStaff"
  | "constructionStaff"
  | "manager";

interface MenuItem {
  icon: LucideIcon;
  label: string;
}

const allMenuItems: MenuItem[] = [
  { icon: Home, label: "Trang chủ" },
  { icon: Users, label: "Khách hàng" },
  { icon: FileText, label: "Đơn hàng" },
  { icon: BarChart, label: "Thi công" },
  { icon: FileText, label: "Báo cáo" },
  { icon: Star, label: "Đánh giá" },
];

interface SidebarToggleButtonProps {
  isSidebarCollapsed: boolean;
  toggleSidebar: () => void;
}

const SidebarToggleButton: React.FC<SidebarToggleButtonProps> = ({
  isSidebarCollapsed,
  toggleSidebar,
}) => (
  <button
    onClick={toggleSidebar}
    className="text-gray-600 p-2 rounded-full hover:bg-gray-200"
  >
    {isSidebarCollapsed ? <ChevronRight size={24} /> : <ChevronLeft size={24} />}
  </button>
);

interface MenuItemProps {
  icon: LucideIcon;
  label: string;
  isActive: boolean;
  onClick: () => void;
  isCollapsed: boolean;
}

const MenuItem: React.FC<MenuItemProps> = ({
  icon: Icon,
  label,
  isActive,
  onClick,
  isCollapsed,
}) => (
  <a
    href="#"
    className={`flex items-center px-4 py-2 mt-2 text-gray-700 ${
      isActive ? "bg-gray-200" : "hover:bg-gray-100"
    } rounded-lg transition-colors duration-200`}
    onClick={onClick}
  >
    <Icon className="w-5 h-5" />
    {!isCollapsed && <span className="ml-3">{label}</span>}
  </a>
);

interface SidebarHeaderProps {
  isSidebarCollapsed: boolean;
  toggleSidebar: () => void;
}

const SidebarHeader: React.FC<SidebarHeaderProps> = ({
  isSidebarCollapsed,
  toggleSidebar,
}) => (
  <div className="p-4 flex justify-between items-center">
    {!isSidebarCollapsed && (
      <h1 className="text-2xl font-bold text-gray-800">Quản lý Hồ cá Koi</h1>
    )}
    <SidebarToggleButton
      isSidebarCollapsed={isSidebarCollapsed}
      toggleSidebar={toggleSidebar}
    />
  </div>
);

interface SidebarNavigationProps {
  items: MenuItem[];
  activeMenuItem: string;
  setActiveMenuItem: (item: string) => void;
  isSidebarCollapsed: boolean;
}

const SidebarNavigation: React.FC<SidebarNavigationProps> = ({
  items,
  activeMenuItem,
  setActiveMenuItem,
  isSidebarCollapsed,
}) => (
  <nav className="mt-8">
    {items.map((item) => (
      <MenuItem
        key={item.label}
        icon={item.icon}
        label={item.label}
        isActive={activeMenuItem === item.label}
        onClick={() => setActiveMenuItem(item.label)}
        isCollapsed={isSidebarCollapsed}
      />
    ))}
  </nav>
);

interface SidebarProps {
  activeMenuItem: string;
  setActiveMenuItem: (item: string) => void;
  isSidebarCollapsed: boolean;
  toggleSidebar: () => void;
  userRole: UserRole;
  setShowSettings: (show: boolean) => void;
}

const Sidebar: React.FC<SidebarProps> = ({
  activeMenuItem,
  setActiveMenuItem,
  isSidebarCollapsed,
  toggleSidebar,
  userRole,
  setShowSettings,
}) => {
  const allowedMenuItems = allMenuItems.filter((item) =>
    rolePermissions[userRole].includes(item.label)
  );

  return (
    <div
      className={`flex flex-col justify-between bg-white text-gray-800 border-r transition-all duration-300 ${
        isSidebarCollapsed ? "w-16" : "w-64"
      }`}
    >
      <div>
        <SidebarHeader
          isSidebarCollapsed={isSidebarCollapsed}
          toggleSidebar={toggleSidebar}
        />
        <SidebarNavigation
          items={allowedMenuItems}
          activeMenuItem={activeMenuItem}
          setActiveMenuItem={setActiveMenuItem}
          isSidebarCollapsed={isSidebarCollapsed}
        />
      </div>
      <div className="mb-4">
        <MenuItem
          icon={Settings}
          label="Cài đặt"
          isActive={false}
          onClick={() => setShowSettings(true)}
          isCollapsed={isSidebarCollapsed}
        />
      </div>
    </div>
  );
};

interface HeaderProps {
  activeMenuItem: string;
}

const Header: React.FC<HeaderProps> = ({ activeMenuItem }) => (
  <header className="bg-white shadow-sm border-b">
    <div className="max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8">
      <h2 className="text-2xl font-bold leading-7 text-gray-900 sm:truncate">
        {activeMenuItem}
      </h2>
    </div>
  </header>
);

interface StatCardProps {
  icon: LucideIcon;
  title: string;
  value: string;
}

const StatCard: React.FC<StatCardProps> = ({ icon: Icon, title, value }) => (
  <div className="w-full px-6 sm:w-1/2 xl:w-1/3 mt-6 sm:mt-0">
    <div className="flex items-center px-5 py-6 shadow-sm rounded-md bg-white border">
      <div className="p-3 rounded-full bg-blue-100 bg-opacity-75">
        <Icon className="h-8 w-8 text-blue-600" />
      </div>
      <div className="mx-5">
        <h4 className="text-2xl font-semibold text-gray-700">{value}</h4>
        <div className="text-gray-500">{title}</div>
      </div>
    </div>
  </div>
);

interface SettingsModalContentProps {
  userRole: UserRole;
  handleRoleChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
  onLogout: () => void;
}

const SettingsModalContent: React.FC<SettingsModalContentProps> = ({
  userRole,
  handleRoleChange,
  onLogout,
}) => (
  <div className="mt-3 text-center">
    <h3 className="text-lg leading-6 font-medium text-gray-900">Cài đặt</h3>
    <div className="mt-2 px-7 py-3">
      <label
        className="block text-gray-700 text-sm font-bold mb-2"
        htmlFor="role"
      >
        Vai trò người dùng
      </label>
      <select
        id="role"
        value={userRole}
        onChange={handleRoleChange}
        className="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
      >
        {Object.entries(userRoles).map(([value, label]) => (
          <option key={value} value={value}>
            {label}
          </option>
        ))}
      </select>
    </div>
    <div className="items-center px-4 py-3">
      <button
        className="px-4 py-2 bg-red-500 text-white text-base font-medium rounded-md w-full shadow-sm hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-300"
        onClick={onLogout}
      >
        Đăng xuất
      </button>
    </div>
  </div>
);

interface SettingsModalProps {
  isOpen: boolean;
  onClose: () => void;
  userRole: UserRole;
  handleRoleChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
  onLogout: () => void;
}

const SettingsModal: React.FC<SettingsModalProps> = ({
  isOpen,
  onClose,
  userRole,
  handleRoleChange,
  onLogout,
}) => {
  const modalRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (modalRef.current && !modalRef.current.contains(event.target as Node)) {
        onClose();
      }
    };

    if (isOpen) {
      document.addEventListener("mousedown", handleClickOutside);
    }

    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [isOpen, onClose]);

  if (!isOpen) return null;

  return (
    <div
      className="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full"
      id="my-modal"
    >
      <div
        className="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white"
        ref={modalRef}
      >
        <button
          onClick={onClose}
          className="absolute top-0 right-0 mt-4 mr-4 text-gray-500 hover:text-gray-700"
        >
          <X size={24} />
        </button>
        <SettingsModalContent
          userRole={userRole}
          handleRoleChange={handleRoleChange}
          onLogout={onLogout}
        />
      </div>
    </div>
  );
};

const HomePage: React.FC = () => (
  <div className="mt-4">
    <div className="flex flex-wrap -mx-6">
      <StatCard icon={FileText} title="Đơn hàng mới" value="8,282" />
      <StatCard icon={Users} title="Khách hàng" value="200,521" />
      <StatCard icon={Star} title="Đánh giá" value="215,542" />
    </div>
  </div>
);

interface ContentAreaProps {
  activeMenuItem: string;
}

const ContentArea: React.FC<ContentAreaProps> = ({ activeMenuItem }) => {
  switch (activeMenuItem) {
    case "Trang chủ":
      return <HomePage />;
    case "Khách hàng":
      return (
        <div className="bg-white p-6 rounded-lg shadow-sm">
          Nội dung quản lý khách hàng
        </div>
      );
    case "Đơn hàng":
      return (
        <div className="bg-white p-6 rounded-lg shadow-sm">
          Nội dung quản lý đơn hàng
        </div>
      );
    case "Thi công":
      return (
        <div className="bg-white p-6 rounded-lg shadow-sm">
          Nội dung quản lý thi công
        </div>
      );
    case "Báo cáo":
      return (
        <div className="bg-white p-6 rounded-lg shadow-sm">Nội dung báo cáo</div>
      );
    case "Đánh giá":
      return (
        <div className="bg-white p-6 rounded-lg shadow-sm">
          Nội dung quản lý đánh giá
        </div>
      );
    default:
      return (
        <div className="bg-white p-6 rounded-lg shadow-sm">
          Chọn một mục từ menu
        </div>
      );
  }
};

const Dashboard: React.FC = () => {
  const [activeMenuItem, setActiveMenuItem] = useState<string>("Trang chủ");
  const [userRole, setUserRole] = useState<UserRole>("customer");
  const [isSidebarCollapsed, setIsSidebarCollapsed] = useState<boolean>(false);
  const [showSettings, setShowSettings] = useState<boolean>(false);

  const handleRoleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setUserRole(e.target.value as UserRole);
  };

  const toggleSidebar = () => {
    setIsSidebarCollapsed(!isSidebarCollapsed);
  };

  const handleLogout = () => {
    alert("Đã đăng xuất");
    setShowSettings(false);
    // window.location.href = '/login'; // Uncomment và điều chỉnh URL khi cần
  };

  useEffect(() => {
    if (!rolePermissions[userRole].includes(activeMenuItem)) {
      setActiveMenuItem(rolePermissions[userRole][0]);
    }
  }, [userRole, activeMenuItem]);

  return (
    <div className="flex h-screen bg-gray-100">
      <Sidebar
        activeMenuItem={activeMenuItem}
        setActiveMenuItem={setActiveMenuItem}
        isSidebarCollapsed={isSidebarCollapsed}
        toggleSidebar={toggleSidebar}
        userRole={userRole}
        setShowSettings={setShowSettings}
      />

      <div className="flex-1 flex flex-col overflow-hidden">
        <Header activeMenuItem={activeMenuItem} />

        <main className="flex-1 overflow-x-hidden overflow-y-auto bg-gray-50">
          <div className="container mx-auto px-6 py-8">
            <h3 className="text-gray-700 text-3xl font-medium mb-4">
              Chào mừng, {userRoles[userRole]}!
            </h3>
            <ContentArea activeMenuItem={activeMenuItem} />
          </div>
        </main>
      </div>

      <SettingsModal
        isOpen={showSettings}
        onClose={() => setShowSettings(false)}
        userRole={userRole}
        handleRoleChange={handleRoleChange}
        onLogout={handleLogout}
      />
    </div>
  );
};

export default Dashboard;
