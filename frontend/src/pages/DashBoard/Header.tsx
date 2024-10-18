import React from 'react';

interface HeaderProps {
    activeMenuItem: string;
}

const Header: React.FC<HeaderProps> = ({ activeMenuItem }) => {
    return (
        <header className="bg-white shadow-sm border-b">
            <div className="max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8">
                <h2 className="text-2xl font-bold leading-7 text-gray-900 sm:truncate">
                    {activeMenuItem}
                </h2>
            </div>
        </header>
    );
};

export default Header;
