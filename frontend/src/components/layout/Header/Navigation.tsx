import React from "react";
import { Link } from "react-router-dom";

const Navigation: React.FC = () => {
  const navItems = [
    { text: "Trang chủ", url: "/" },
    { text: "Mẫu thiết kế", url: "/designs" },
    { text: "Dự án", url: "/projects" },
    { text: "Blog", url: "/blog" },
    { text: "Liên hệ", url: "/contact" },
  ];

  return (
    <nav>
      <ul className="flex space-x-6">
        {navItems.map((item) => (
          <li key={item.text}>
            <Link
              to={item.url}
              className="text-gray-600 hover:text-blue-600 transition duration-300"
            >
              {item.text}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default Navigation;
