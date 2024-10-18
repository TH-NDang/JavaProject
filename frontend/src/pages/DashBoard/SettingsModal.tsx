import React, { useRef, useEffect } from 'react';
import { X } from 'lucide-react';
import { userRoles } from '../../utils/roles';

interface SettingsModalProps {
  isOpen: boolean;
  onClose: () => void;
  userRole: keyof typeof userRoles;
  handleRoleChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
  onLogout: () => void;
}

const SettingsModal: React.FC<SettingsModalProps> = ({ isOpen, onClose, userRole, handleRoleChange, onLogout }) => {
  const modalRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (modalRef.current && !modalRef.current.contains(event.target as Node)) {
        onClose();
      }
    };

    if (isOpen) {
      document.addEventListener('mousedown', handleClickOutside);
    }

    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [isOpen, onClose]);

  if (!isOpen) return null;

  return (
      <div className="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full" id="my-modal">
        <div className="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white" ref={modalRef}>
          <button
            onClick={onClose}
            className="absolute top-0 right-0 mt-4 mr-4 text-gray-500 hover:text-gray-700"
          >
            <X size={24} />
          </button>
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
        </div>
      </div>
  );
};

export default SettingsModal;
