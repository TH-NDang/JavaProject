import React from 'react';
import { ChevronLeft, ChevronRight } from 'lucide-react';

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

export default SidebarToggleButton;
