import React from 'react';
import { MenuItem } from './MenuItem';
import { MenuItem as MenuItemType } from '../../../types';

interface SidebarNavigationProps {
    items: MenuItemType[];
    activeMenuItem: string;
    setActiveMenuItem: (item: string) => void;
    isSidebarCollapsed: boolean;
}

const SidebarNavigation: React.FC<SidebarNavigationProps> = ({
    items,
    activeMenuItem,
    setActiveMenuItem,
    isSidebarCollapsed
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

export default SidebarNavigation;
