import React from 'react';
import { userRoles } from '../../utils/roles';

interface SettingsModalContentProps {
  userRole: keyof typeof userRoles;
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

export default SettingsModalContent;
