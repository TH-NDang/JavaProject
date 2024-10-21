import React from "react";
import FooterColumn from "./FooterColumn";
import SocialLinks from "./SocialLinks";

const Footer: React.FC = () => {
  return (
    <footer className="bg-gray-800 text-white py-10">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          <FooterColumn title="Về chúng tôi">
            <p className="text-sm">
              Chúng tôi chuyên thiết kế và thi công hồ cá Koi đẳng cấp, mang đến
              không gian sống tuyệt vời cho cả bạn và những chú cá Koi quý giá.
            </p>
          </FooterColumn>

          <FooterColumn title="Dịch vụ">
            <ul className="space-y-2">
              <li>
                <a
                  href="#"
                  className="hover:text-blue-400 transition duration-300"
                >
                  Thiết kế hồ cá
                </a>
              </li>
              <li>
                <a
                  href="/construction"
                  className="hover:text-blue-400 transition duration-300"
                >
                  Thi công hồ cá
                </a>
              </li>
              <li>
                <a
                  href="/maintenance"
                  className="hover:text-blue-400 transition duration-300"
                >
                  Bảo dưỡng định kỳ
                </a>
              </li>
              <li>
                <a
                  href="#"
                  className="hover:text-blue-400 transition duration-300"
                >
                  Tư vấn chăm sóc cá Koi
                </a>
              </li>
            </ul>
          </FooterColumn>

          <FooterColumn title="Liên hệ">
            <ul className="space-y-2">
              <li>123 Đường ABC, Quận XYZ</li>
              <li>Thành phố HCM, Việt Nam</li>
              <li>Email: info@koipondco.com</li>
              <li>Điện thoại: (84) 123-456-789</li>
            </ul>
          </FooterColumn>

          <FooterColumn title="Kết nối">
            <SocialLinks />
          </FooterColumn>
        </div>

        <div className="mt-8 pt-8 border-t border-gray-700 text-center text-sm">
          <p>
            &copy; {new Date().getFullYear()} Koi Pond Co. All rights reserved.
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
