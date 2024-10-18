import React from 'react';
import { LucideIcon } from 'lucide-react';

interface MenuItemProps {
    icon: LucideIcon;
    label: string;
    isActive: boolean;
    onClick: () => void;
    isCollapsed: boolean;
}

const MenuItem: React.FC<MenuItemProps> = ({ icon: Icon, label, isActive, onClick, isCollapsed }) => (
    <a
        href="#"
        className={`flex items-center px-4 py-2 mt-2 text-gray-700 ${
            isActive ? 'bg-gray-200' : 'hover:bg-gray-100'
        } rounded-lg transition-colors duration-200`}
        onClick={onClick}
    >
        <Icon className="w-5 h-5" />
        {!isCollapsed && <span className="ml-3">{label}</span>}
    </a>
);

export default MenuItem;
