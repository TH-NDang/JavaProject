import React from 'react';
import Logo from './Logo';
import Navigation from './Navigation';
import SearchBar from './SearchBar';
import LoginButton from './LoginButton';

interface HeaderProps {
    activeMenuItem: string;
    setActiveMenuItem: (item: string) => void;
}

const Header: React.FC<HeaderProps> = ({ activeMenuItem, setActiveMenuItem }) => {
    return (
        <header className="bg-white shadow-md">
            <div className="container mx-auto px-4 py-3 flex items-center justify-between">
                <Logo />
                <Navigation activeMenuItem={activeMenuItem} setActiveMenuItem={setActiveMenuItem} />
                <div className="flex items-center space-x-4">
                    <SearchBar />
                    <LoginButton />
                </div>
            </div>
        </header>
    );
};

export default Header;
