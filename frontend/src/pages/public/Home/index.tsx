import React from "react";
import { Link } from "react-router-dom";
import { ArrowRight, CheckCircle } from "lucide-react";

const Home: React.FC = () => {
  const features = [
    "Thiết kế hồ cá theo phong cách Nhật Bản",
    "Thi công trọn gói chuyên nghiệp",
    "Đội ngũ kỹ thuật viên giàu kinh nghiệm",
    "Dịch vụ bảo trì định kỳ",
    "Tư vấn chăm sóc cá Koi",
    "Bảo hành dài hạn",
  ];

  const testimonials = [
    {
      content: "Chất lượng thi công và dịch vụ chăm sóc khách hàng tuyệt vời!",
      author: "Anh Nguyễn Văn A",
      role: "Chủ biệt thự tại Q.7",
    },
    {
      content: "Đội ngũ tư vấn rất nhiệt tình và chuyên nghiệp.",
      author: "Chị Trần Thị B",
      role: "Khách hàng tại Thủ Đức",
    },
  ];

  return (
    <div className="flex flex-col min-h-screen">
      {/* Hero Section */}
      <section className="relative bg-gradient-to-r from-primary-600 to-primary-800 text-white">
        <div className="absolute inset-0">
          <img
            src={`/images/6.jpg`}
            alt="Hero background"
            className="w-full h-full object-cover opacity-20"
          />
        </div>
        <div className="relative max-w-7xl mx-auto px-4 py-24 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold tracking-tight mb-8">
              Thiết Kế & Thi Công Hồ Cá Koi Chuyên Nghiệp
            </h1>
            <p className="max-w-2xl mx-auto text-xl text-primary-100 mb-10">
              Mang đến không gian sống đẳng cấp với hồ cá Koi được thiết kế và
              thi công theo tiêu chuẩn Nhật Bản
            </p>
            <div className="flex flex-col sm:flex-row justify-center gap-4">
              <Link
                to="/contact"
                className="inline-flex items-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-white bg-accent-600 hover:bg-accent-700 transition duration-300"
              >
                Tư Vấn Ngay
                <ArrowRight className="ml-2 h-5 w-5" />
              </Link>
              <Link
                to="/portfolio"
                className="inline-flex items-center px-8 py-3 border-2 border-white text-base font-medium rounded-md text-white hover:bg-white hover:text-primary-600 transition duration-300"
              >
                Xem Dự Án
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl font-bold text-gray-900 mb-4">
              Tại Sao Chọn Chúng Tôi?
            </h2>
            <p className="text-lg text-gray-600 max-w-2xl mx-auto">
              Chúng tôi cam kết mang đến cho bạn những dịch vụ tốt nhất với đội
              ngũ chuyên nghiệp và quy trình làm việc chuẩn mực
            </p>
          </div>
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
            {features.map((feature, index) => (
              <div key={index} className="flex items-start">
                <CheckCircle className="h-6 w-6 text-accent-500 mt-1" />
                <p className="ml-3 text-lg text-gray-700">{feature}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Featured Projects */}
      {/* !TODO: thực hiện lấy các thông số qua gọi API */}
      <section className="py-20 bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h2 className="text-3xl font-bold text-gray-900 text-center mb-12">
            Dự Án Tiêu Biểu
          </h2>
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
            {[1, 2, 3].map((item) => (
              <div
                key={item}
                className="bg-white rounded-lg overflow-hidden shadow-md"
              >
                <img
                  src={`/images/${item}.jpg`}
                  alt={`Project ${item}`}
                  className="w-full h-48 object-cover"
                />
                <div className="p-6">
                  <h3 className="text-xl font-semibold mb-2">Dự án {item}</h3>
                  <p className="text-gray-600 mb-4">
                    Thiết kế và thi công hồ cá Koi theo phong cách Nhật Bản
                  </p>
                  <Link
                    to={`/portfolio/${item}`}
                    className="text-primary-600 hover:text-primary-700 font-medium inline-flex items-center"
                  >
                    Xem chi tiết
                    <ArrowRight className="ml-2 h-4 w-4" />
                  </Link>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Testimonials */}
      {/* !TODO: thực hiện thêm hiển thị ảnh người đánh giá */}
      <section className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h2 className="text-3xl font-bold text-gray-900 text-center mb-12">
            Khách Hàng Nói Gì Về Chúng Tôi
          </h2>
          <div className="grid md:grid-cols-2 gap-8">
            {testimonials.map((testimonial, index) => (
              <div key={index} className="bg-gray-50 rounded-lg p-8">
                <p className="text-gray-600 italic mb-4">
                  {testimonial.content}
                </p>
                <div>
                  <p className="font-medium text-gray-900">
                    {testimonial.author}
                  </p>
                  <p className="text-gray-500">{testimonial.role}</p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="bg-primary-700 text-white py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h2 className="text-3xl font-bold mb-4">
            Sẵn Sàng Để Bắt Đầu Dự Án Của Bạn?
          </h2>
          <p className="text-primary-100 mb-8 max-w-2xl mx-auto">
            Liên hệ với chúng tôi ngay hôm nay để được tư vấn chi tiết và báo giá
            miễn phí
          </p>
          <Link
            to="/contact"
            className="inline-flex items-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-primary-700 bg-white hover:bg-primary-50 transition duration-300"
          >
            Liên Hệ Ngay
            <ArrowRight className="ml-2 h-5 w-5" />
          </Link>
        </div>
      </section>
    </div>
  );
};

export default Home;
