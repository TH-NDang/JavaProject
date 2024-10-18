import React from 'react';
import { Settings } from 'lucide-react';
import { SidebarHeader } from './SidebarHeader';
import { SidebarNavigation } from './SidebarNavigation';
import { MenuItem } from './MenuItem';
import { UserRole } from '../../../types';
import { allMenuItems, rolePermissions } from '../../../constants';

interface SidebarProps {
    activeMenuItem: string;
    setActiveMenuItem: (item: string) => void;
    isSidebarCollapsed: boolean;
    toggleSidebar: () => void;
    userRole: UserRole;
    setShowSettings: (show: boolean) => void;
}

export const Sidebar: React.FC<SidebarProps> = ({
                                                    activeMenuItem,
                                                    setActiveMenuItem,
                                                    isSidebarCollapsed,
                                                    toggleSidebar,
                                                    userRole,
                                                    setShowSettings
                                                }) => {
    const allowedMenuItems = allMenuItems.filter(item => rolePermissions[userRole].includes(item.label));

    return (
        <div className={`flex flex-col justify-between bg-white text-gray-800 border-r transition-all duration-300 ${isSidebarCollapsed ? 'w-16' : 'w-64'}`}>
            <div>
                <SidebarHeader isSidebarCollapsed={isSidebarCollapsed} toggleSidebar={toggleSidebar} />
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
