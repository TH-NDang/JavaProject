import React from "react";
import { Link } from "react-router-dom";
import {
  Facebook,
  Twitter,
  Instagram,
  Linkedin,
  Mail,
  Phone,
  MapPin,
} from "lucide-react";

const Footer: React.FC = () => {
  const navigation = {
    company: [
      { name: "Về chúng tôi", href: "/about" },
      { name: "Dự án", href: "/portfolio" },
      { name: "Đội ngũ", href: "/about#team" },
      { name: "Tuyển dụng", href: "/careers" },
    ],
    services: [
      { name: "Thiết kế hồ cá", href: "/services#design" },
      { name: "Thi công hồ cá", href: "/services#construction" },
      { name: "Bảo trì định kỳ", href: "/services#maintenance" },
      { name: "Tư vấn chuyên sâu", href: "/services#consulting" },
    ],
    support: [
      { name: "Liên hệ", href: "/contact" },
      { name: "FAQ", href: "/faq" },
      { name: "Hỗ trợ kỹ thuật", href: "/support" },
      { name: "Chính sách bảo hành", href: "/warranty" },
    ],
  };

  const social = [
    {
      name: "Facebook",
      href: "#",
      icon: Facebook,
    },
    {
      name: "Twitter",
      href: "#",
      icon: Twitter,
    },
    {
      name: "Instagram",
      href: "#",
      icon: Instagram,
    },
    {
      name: "LinkedIn",
      href: "#",
      icon: Linkedin,
    },
  ];

  const contact = [
    {
      icon: MapPin,
      text: "123 Đường ABC, Quận XYZ, TP.HCM",
    },
    {
      icon: Phone,
      text: "(84) 123-456-789",
    },
    {
      icon: Mail,
      text: "info@koipondco.com",
    },
  ];

  return (
    <footer className="bg-gray-900">
      <div className="max-w-7xl mx-auto py-12 px-4 sm:px-6 lg:py-16 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-12">
          {/* Company Info */}
          <div>
            <div className="flex items-center space-x-2 mb-6">
              <img src={`logo.png`} alt="Logo" className="h-8 w-auto"/>
              <span className="text-white text-lg font-bold">Koi Pond Co.</span>
            </div>
            <ul className="mt-4 space-y-4">
              {contact.map((item, index) => {
                const Icon = item.icon;
                return (
                  <li key={index} className="flex items-start">
                    <Icon className="h-6 w-6 text-gray-400 mt-1" />
                    <span className="ml-3 text-gray-300">{item.text}</span>
                  </li>
                );
              })}
            </ul>
          </div>

          {/* Navigation Links */}
          <div>
            <h3 className="text-sm font-semibold text-gray-400 tracking-wider uppercase mb-4">
              Công ty
            </h3>
            <ul className="space-y-4">
              {navigation.company.map((item) => (
                <li key={item.name}>
                  <Link
                    to={item.href}
                    className="text-gray-300 hover:text-white transition duration-300"
                  >
                    {item.name}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          <div>
            <h3 className="text-sm font-semibold text-gray-400 tracking-wider uppercase mb-4">
              Dịch vụ
            </h3>
            <ul className="space-y-4">
              {navigation.services.map((item) => (
                <li key={item.name}>
                  <Link
                    to={item.href}
                    className="text-gray-300 hover:text-white transition duration-300"
                  >
                    {item.name}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          <div>
            <h3 className="text-sm font-semibold text-gray-400 tracking-wider uppercase mb-4">
              Hỗ trợ
            </h3>
            <ul className="space-y-4">
              {navigation.support.map((item) => (
                <li key={item.name}>
                  <Link
                    to={item.href}
                    className="text-gray-300 hover:text-white transition duration-300"
                  >
                    {item.name}
                  </Link>
                </li>
              ))}
            </ul>
          </div>
        </div>

        {/* Bottom Section */}
        <div className="mt-12 border-t border-gray-800 pt-8">
          <div className="flex flex-col md:flex-row justify-between items-center">
            <div className="flex space-x-6">
              {social.map((item) => {
                const Icon = item.icon;
                return (
                  <a
                    key={item.name}
                    href={item.href}
                    className="text-gray-400 hover:text-gray-300 transition duration-300"
                  >
                    <span className="sr-only">{item.name}</span>
                    <Icon className="h-6 w-6" />
                  </a>
                );
              })}
            </div>
            <p className="mt-8 md:mt-0 text-base text-gray-400">
              &copy; {new Date().getFullYear()} Koi Pond Co. All rights reserved.
            </p>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
