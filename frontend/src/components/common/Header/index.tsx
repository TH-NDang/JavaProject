import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Menu, X } from 'lucide-react';

const Header: React.FC = () => {
  const [isScrolled, setIsScrolled] = useState(false);
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const location = useLocation();
  
  // Kiểm tra xem có phải trang chủ không
  const isHomePage = location.pathname === '/';

  useEffect(() => {
    const handleScroll = () => {
      if (window.scrollY > 0) {
        setIsScrolled(true);
      } else {
        setIsScrolled(false);
      }
    };

    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const navigation = [
    { name: 'Trang chủ', href: '/' },
    { name: 'Dịch vụ', href: '/services' },
    { name: 'Dự án', href: '/portfolio' },
    { name: 'Về chúng tôi', href: '/about' },
    { name: 'Liên hệ', href: '/contact' },
  ];

  // Xác định màu sắc dựa trên vị trí và trạng thái scroll
  const getHeaderStyle = () => {
    if (isScrolled) {
      return 'bg-white shadow-md';
    }
    return isHomePage ? 'bg-transparent' : 'bg-primary-600';
  };

  const getLinkStyle = (isActive: boolean) => {
    if (isScrolled) {
      return isActive ? 'text-primary-600' : 'text-gray-600 hover:text-primary-600';
    }
    return isHomePage 
      ? (isActive ? 'text-white' : 'text-gray-100 hover:text-white')
      : (isActive ? 'text-white' : 'text-white/80 hover:text-white');
  };

  const getLogoStyle = () => {
    if (isScrolled) {
      return 'text-gray-900';
    }
    return 'text-white';
  };

  const getLoginButtonStyle = () => {
    if (isScrolled) {
      return 'bg-primary-600 text-white hover:bg-primary-700';
    }
    return isHomePage
      ? 'bg-white text-primary-600 hover:bg-gray-100'
      : 'bg-white text-primary-600 hover:bg-gray-100';
  };

  return (
    <header
      className={`fixed w-full z-50 transition-all duration-300 ${getHeaderStyle()}`}
    >
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center py-4">
          {/* Logo */}
          <Link to="/" className="flex items-center space-x-2">
            <img src={`/images/logo.svg`} alt="Logo" className="h-10 w-auto" />logo dau trang
            <span className={`text-xl font-bold ${getLogoStyle()}`}>
              Koi Pond Co.
            </span>
          </Link>

          {/* Desktop Navigation */}
          <nav className="hidden md:flex space-x-8">
            {navigation.map((item) => (
              <Link
                key={item.name}
                to={item.href}
                className={`text-sm font-medium transition-colors duration-300 ${getLinkStyle(
                  location.pathname === item.href
                )}`}
              >
                {item.name}
              </Link>
            ))}
          </nav>

          {/* Login Button */}
          <div className="hidden md:flex items-center space-x-4">
            <Link
              to="/login"
              className={`px-4 py-2 rounded-md text-sm font-medium transition-colors duration-300 ${getLoginButtonStyle()}`}
            >
              Đăng nhập
            </Link>
          </div>

          {/* Mobile menu button */}
          <button
            onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
            className="md:hidden rounded-md p-2 inline-flex items-center justify-center transition-colors duration-300"
          >
            {isMobileMenuOpen ? (
              <X className={isScrolled ? 'text-gray-900' : 'text-white'} />
            ) : (
              <Menu className={isScrolled ? 'text-gray-900' : 'text-white'} />
            )}
          </button>
        </div>

        {/* Mobile menu */}
        {isMobileMenuOpen && (
          <div className="md:hidden bg-white rounded-lg shadow-lg mt-2 absolute left-4 right-4">
            <div className="px-2 pt-2 pb-3 space-y-1">
              {navigation.map((item) => (
                <Link
                  key={item.name}
                  to={item.href}
                  className={`block px-3 py-2 rounded-md text-base font-medium ${
                    location.pathname === item.href
                      ? 'text-primary-600 bg-primary-50'
                      : 'text-gray-900 hover:text-primary-600 hover:bg-primary-50'
                  }`}
                  onClick={() => setIsMobileMenuOpen(false)}
                >
                  {item.name}
                </Link>
              ))}
              <Link
                to="/login"
                className="block px-3 py-2 rounded-md text-base font-medium text-primary-600 hover:text-primary-700 hover:bg-primary-50"
                onClick={() => setIsMobileMenuOpen(false)}
              >
                Đăng nhập
              </Link>
            </div>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;
