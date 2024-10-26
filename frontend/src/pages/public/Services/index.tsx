import React from "react";
import { Link } from "react-router-dom";
import {
  PencilRuler,
  Wrench, // Thay thế cho Hammer
  Settings, // Thay thế cho Tool
  ClipboardList,
  Droplet, // Thay thế cho Droplets
  ArrowRight,
  CheckCircle,
} from "lucide-react";

const services = [
  {
    icon: PencilRuler,
    title: "Thiết Kế Hồ Cá",
    description:
      "Thiết kế hồ cá Koi theo phong cách Nhật Bản, tối ưu không gian và đảm bảo tính thẩm mỹ.",
    features: [
      "Tư vấn phong cách thiết kế",
      "Thiết kế 3D trực quan",
      "Tối ưu hóa không gian",
      "Tính toán hệ thống lọc",
    ],
  },
  {
    icon: Wrench,
    title: "Thi Công Hồ Cá",
    description:
      "Thi công trọn gói với đội ngũ thợ lành nghề, đảm bảo chất lượng và tiến độ.",
    features: [
      "Thi công theo tiêu chuẩn",
      "Vật liệu chất lượng cao",
      "Giám sát kỹ thuật",
      "Bảo hành dài hạn",
    ],
  },
  {
    icon: Settings,
    title: "Bảo Trì Định Kỳ",
    description:
      "Dịch vụ bảo trì, vệ sinh hồ định kỳ đảm bảo hồ luôn trong tình trạng tối ưu.",
    features: [
      "Vệ sinh hồ định kỳ",
      "Kiểm tra hệ thống lọc",
      "Xử lý nước",
      "Tư vấn chăm sóc cá",
    ],
  },
  {
    icon: ClipboardList,
    title: "Tư Vấn Chuyên Sâu",
    description: "Tư vấn chi tiết về thiết kế, xây dựng và vận hành hồ cá Koi.",
    features: [
      "Tư vấn phong thủy",
      "Định hướng thiết kế",
      "Tư vấn kỹ thuật",
      "Giải đáp thắc mắc",
    ],
  },
  {
    icon: Droplet,
    title: "Hệ Thống Lọc",
    description: "Tư vấn và lắp đặt hệ thống lọc chuyên nghiệp cho hồ cá Koi.",
    features: [
      "Thiết kế hệ thống lọc",
      "Lắp đặt thiết bị",
      "Bảo trì định kỹ",
      "Sửa chữa, thay thế",
    ],
  },
];

const Services: React.FC = () => {
  return (
    <div className="min-h-screen bg-gray-50">
      {/* Hero Section */}
      <section className="bg-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-4xl font-bold text-gray-900 mb-6">
              Dịch Vụ Của Chúng Tôi
            </h1>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Cung cấp giải pháp toàn diện từ thiết kế, thi công đến bảo trì hồ
              cá Koi
            </p>
          </div>
        </div>
      </section>

      {/* Services Grid */}
      <section className="py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
            {services.map((service, index) => {
              const Icon = service.icon;
              return (
                <div
                  key={index}
                  className="bg-white rounded-lg shadow-md p-8 hover:shadow-lg transition duration-300"
                >
                  <div className="w-12 h-12 bg-primary-100 rounded-lg flex items-center justify-center mb-6">
                    <Icon className="h-6 w-6 text-primary-600" />
                  </div>
                  <h3 className="text-xl font-semibold text-gray-900 mb-4">
                    {service.title}
                  </h3>
                  <p className="text-gray-600 mb-6">{service.description}</p>
                  <ul className="space-y-3 mb-6">
                    {service.features.map((feature, idx) => (
                      <li key={idx} className="flex items-start">
                        <span className="h-5 w-5 text-accent-500 mr-2">•</span>
                        <span className="text-gray-600">{feature}</span>
                      </li>
                    ))}
                  </ul>
                  <Link
                    to="/contact"
                    className="inline-flex items-center text-primary-600 hover:text-primary-700 font-medium"
                  >
                    Tìm hiểu thêm
                    <ArrowRight className="ml-2 h-4 w-4" />
                  </Link>
                </div>
              );
            })}
          </div>
        </div>
      </section>

      {/* Process Section tiếp theo */}
      <section className="bg-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl font-bold text-gray-900 mb-4">
              Quy Trình Làm Việc
            </h2>
            <p className="text-lg text-gray-600 max-w-2xl mx-auto">
              Chúng tôi thực hiện theo quy trình chuyên nghiệp, đảm bảo chất
              lượng và tiến độ cho mọi dự án
            </p>
          </div>

          <div className="relative">
            <div className="absolute left-1/2 transform -translate-x-1/2 h-full w-1 bg-primary-100" />
            <div className="space-y-12">
              {[
                {
                  step: 1,
                  title: "Tư vấn & Khảo sát",
                  description:
                    "Trao đổi ý tưởng, khảo sát thực địa và tư vấn giải pháp phù hợp",
                },
                {
                  step: 2,
                  title: "Thiết kế & Báo giá",
                  description:
                    "Thiết kế 3D, lập dự toán chi tiết và đề xuất phương án tối ưu",
                },
                {
                  step: 3,
                  title: "Ký kết hợp đồng",
                  description:
                    "Thống nhất phương án, ký kết hợp đồng và lập kế hoạch thi công",
                },
                {
                  step: 4,
                  title: "Thi công",
                  description:
                    "Triển khai thi công theo tiêu chuẩn, giám sát chặt chẽ",
                },
                {
                  step: 5,
                  title: "Nghiệm thu & Bàn giao",
                  description: "Kiểm tra, nghiệm thu và bàn giao công trình",
                },
              ].map((item, index) => (
                <div
                  key={index}
                  className={`flex items-start ${
                    index % 2 === 0 ? "flex-row" : "flex-row-reverse"
                  }`}
                >
                  <div className="w-1/2" />
                  <div className="relative w-10 h-10 flex items-center justify-center">
                    <div className="absolute w-10 h-10 bg-primary-600 rounded-full flex items-center justify-center text-white font-bold">
                      {item.step}
                    </div>
                  </div>
                  <div
                    className={`w-1/2 ${index % 2 === 0 ? "pl-10" : "pr-10"}`}
                  >
                    <div className="bg-white p-6 rounded-lg shadow-md">
                      <h3 className="text-xl font-semibold mb-2">
                        {item.title}
                      </h3>
                      <p className="text-gray-600">{item.description}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </section>

      {/* Pricing Section */}
      <section className="bg-gray-50 py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl font-bold text-gray-900 mb-4">
              Bảng Giá Dịch Vụ
            </h2>
            <p className="text-lg text-gray-600 max-w-2xl mx-auto">
              Chúng tôi cung cấp nhiều gói dịch vụ phù hợp với nhu cầu và ngân
              sách của bạn
            </p>
          </div>

          <div className="grid md:grid-cols-3 gap-8">
            {[
              {
                name: "Cơ bản",
                price: "Từ 15 triệu",
                features: [
                  "Thiết kế 2D",
                  "Thi công cơ bản",
                  "Bảo hành 1 năm",
                  "Tư vấn kỹ thuật",
                ],
              },
              {
                name: "Tiêu chuẩn",
                price: "Từ 25 triệu",
                features: [
                  "Thiết kế 3D",
                  "Thi công trọn gói",
                  "Bảo hành 2 năm",
                  "Tư vấn chuyên sâu",
                  "Bảo trì 6 tháng",
                ],
                featured: true,
              },
              {
                name: "Cao cấp",
                price: "Từ 40 triệu",
                features: [
                  "Thiết kế 3D chi tiết",
                  "Thi công cao cấp",
                  "Bảo hành 3 năm",
                  "Tư vấn VIP",
                  "Bảo trì 12 tháng",
                  "Trang thiết bị cao cấp",
                ],
              },
            ].map((plan, index) => (
              <div
                key={index}
                className={`rounded-lg shadow-md overflow-hidden ${
                  plan.featured
                    ? "bg-white ring-2 ring-primary-600 transform scale-105"
                    : "bg-white"
                }`}
              >
                <div className="p-8">
                  <h3 className="text-2xl font-semibold text-gray-900 mb-2">
                    {plan.name}
                  </h3>
                  <p className="text-primary-600 font-bold text-3xl mb-6">
                    {plan.price}
                  </p>
                  <ul className="space-y-4 mb-8">
                    {plan.features.map((feature, idx) => (
                      <li key={idx} className="flex items-center text-gray-600">
                        <CheckCircle className="h-5 w-5 text-accent-500 mr-2" />
                        {feature}
                      </li>
                    ))}
                  </ul>
                  <Link
                    to="/contact"
                    className={`block text-center px-6 py-3 rounded-md font-medium transition duration-300 ${
                      plan.featured
                        ? "bg-primary-600 text-white hover:bg-primary-700"
                        : "bg-gray-100 text-gray-900 hover:bg-gray-200"
                    }`}
                  >
                    Liên hệ ngay
                  </Link>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="bg-primary-600 py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center text-white">
            <h2 className="text-3xl font-bold mb-4">
              Sẵn sàng bắt đầu dự án của bạn?
            </h2>
            <p className="text-primary-100 mb-8 max-w-2xl mx-auto">
              Liên hệ với chúng tôi ngay để được tư vấn miễn phí và báo giá chi
              tiết
            </p>
            <Link
              to="/contact"
              className="inline-flex items-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-primary-600 bg-white hover:bg-primary-50 transition duration-300"
            >
              Liên hệ ngay
              <ArrowRight className="ml-2 h-5 w-5" />
            </Link>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Services;
