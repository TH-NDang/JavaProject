import React, { useState } from 'react';
import Header from './Header/Header';
import Footer from './Footer/Footer';

interface LayoutProps {
    children: React.ReactNode;
}

const Layout: React.FC<LayoutProps> = ({ children }) => {
    const [activeMenuItem, setActiveMenuItem] = useState<string>('Trang chủ');

    return (
        <div className="flex flex-col min-h-screen">
            <Header activeMenuItem={activeMenuItem} setActiveMenuItem={setActiveMenuItem} />
            <main className="flex-grow">
                {children}
            </main>
            <Footer />
        </div>
    );
};

export default Layout;
