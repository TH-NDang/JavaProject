import React from 'react';
import { SidebarToggleButton } from './SidebarToggleButton';

interface SidebarHeaderProps {
    isSidebarCollapsed: boolean;
    toggleSidebar: () => void;
}

export const SidebarHeader: React.FC<SidebarHeaderProps> = ({ isSidebarCollapsed, toggleSidebar }) => (
    <div className="p-4 flex justify-between items-center">
        {!isSidebarCollapsed && <h1 className="text-2xl font-bold text-gray-800">Quản lý Hồ cá Koi</h1>}
        <SidebarToggleButton isSidebarCollapsed={isSidebarCollapsed} toggleSidebar={toggleSidebar} />
    </div>
);
