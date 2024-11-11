import React from "react";
import { Link } from "react-router-dom";
import { Users, Award, Clock, Target, ArrowRight } from "lucide-react";

const stats = [
  {
    icon: Users,
    label: "Khách hàng hài lòng",
    value: "500+",
  },
  {
    icon: Award,
    label: "Năm kinh nghiệm",
    value: "10+",
  },
  {
    icon: Clock,
    label: "Dự án hoàn thành",
    value: "300+",
  },
  {
    icon: Target,
    label: "Đội ngũ chuyên gia",
    value: "50+",
  },
];

const team = [
  {
    name: "Nguyễn Ngọc Đặng",
    role: "Giám đốc điều hành",
    image: "/api/placeholder/400/400",
    bio: "Có một siếu kinh nghiệm.",
  },
  {
    name: "Nguyễn Hữu Văn Lâm",
    role: "Trưởng phòng thiết kế",
    image: "/api/placeholder/400/400",
    bio: "Chuyên gia thiết kế với nhiều dự án lớn trong và ngoài nước.",
  },
  {
    name: "Vũ Quốc Anh",
    role: "Trưởng phòng kỹ thuật",
    image: "/api/placeholder/400/400",
    bio: "Chuyên gia về hệ thống lọc và xử lý nước cho hồ Koi.",
  },
  {
    name: "Hồ Thủy Như Trúc",
    role: "Quản lý dự án",
    image: "/api/placeholder/400/400",
    bio: "10 năm kinh nghiệm quản lý các dự án quy mô lớn.",
  },
  {
    name: "Trần Thị Mỹ Ngân",
    role: "Quản lý dự án",
    image: "/api/placeholder/400/400",
    bio: "10 năm kinh nghiệm quản lý các dự án quy mô lớn.",
  },
];

const values = [
  {
    title: "Chất lượng hàng đầu",
    description:
      "Cam kết mang đến những sản phẩm và dịch vụ chất lượng cao nhất cho khách hàng.",
  },
  {
    title: "Sáng tạo không ngừng",
    description: "Luôn đổi mới và cập nhật những xu hướng thiết kế mới nhất.",
  },
  {
    title: "Trách nhiệm cao",
    description: "Đảm bảo tiến độ và chất lượng cho mọi dự án được giao.",
  },
  {
    title: "Khách hàng là trọng tâm",
    description: "Lắng nghe và đáp ứng mọi nhu cầu của khách hàng.",
  },
];

const About: React.FC = () => {
  return (
    <div className="min-h-screen">
      {/* Hero Section */}
      <section className="bg-primary-600 text-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-4xl font-bold mb-6">Về Chúng Tôi</h1>
            <p className="text-xl text-primary-100 max-w-2xl mx-auto">
              Chúng tôi là đơn vị hàng đầu trong lĩnh vực thiết kế và thi công hồ
              cá Koi tại Việt Nam
            </p>
          </div>
        </div>
      </section>

      {/* Story Section */}
      <section className="py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid lg:grid-cols-2 gap-12 items-center">
            <div>
              <h2 className="text-3xl font-bold mb-6">
                Câu chuyện của chúng tôi
              </h2>
              <p className="text-gray-600 mb-6">
                Được thành lập từ năm 2014, Koi Pond Co. đã không ngừng phát
                triển và khẳng định vị thế là đơn vị tiên phong trong lĩnh vực
                thiết kế và thi công hồ cá Koi tại Việt Nam.
              </p>
              <p className="text-gray-600 mb-6">
                Với đội ngũ chuyên gia giàu kinh nghiệm và tâm huyết, chúng tôi
                tự hào đã thực hiện hàng trăm dự án cho khách hàng trên khắp cả
                nước, từ những hồ cá nhỏ đến những dự án quy mô lớn cho resort và
                khu nghỉ dưỡng.
              </p>
              <p className="text-gray-600">
                Sứ mệnh của chúng tôi là mang đến những không gian sống đẳng cấp
                và độc đáo, nơi con người có thể tận hưởng vẻ đẹp của thiên nhiên
                ngay trong chính ngôi nhà của mình.
              </p>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <img
                src="/images/about/image1.png"
                alt="About us 1"
                className="rounded-lg object-cover w-full h-full"
              />
              <div className="grid gap-4">
                <img
                  src="/images/about/image2.png"
                  alt="About us 2"
                  className="rounded-lg object-cover w-full h-full"
                />
                <img
                  src="/images/about/image3.png"
                  alt="About us 3"
                  className="rounded-lg object-cover w-full h-full"
                />
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Stats Section */}
      <section className="bg-gray-50 py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid md:grid-cols-4 gap-8">
            {stats.map((stat, index) => {
              const Icon = stat.icon;
              return (
                <div
                  key={index}
                  className="bg-white p-6 rounded-lg shadow-md text-center"
                >
                  <div className="inline-flex items-center justify-center w-12 h-12 bg-primary-100 rounded-lg mb-4">
                    <Icon className="h-6 w-6 text-primary-600" />
                  </div>
                  <div className="text-3xl font-bold text-gray-900 mb-2">
                    {stat.value}
                  </div>
                  <div className="text-gray-600">{stat.label}</div>
                </div>
              );
            })}
          </div>
        </div>
      </section>

      {/* Team Section */}
      <section className="py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-12">
            <h2 className="text-3xl font-bold mb-4">Đội ngũ chuyên gia</h2>
            <p className="text-gray-600 max-w-2xl mx-auto">
              Gặp gỡ những chuyên gia giàu kinh nghiệm và tâm huyết của chúng tôi
            </p>
          </div>

          <div className="grid md:grid-cols-4 gap-8">
            {team.map((member, index) => (
              <div key={index} className="text-center">
                <div className="relative mb-4">
                  <img
                    src={member.image}
                    alt={member.name}
                    className="w-full h-64 object-cover rounded-lg"
                  />
                  <div className="absolute inset-0 bg-gradient-to-t from-black/50 to-transparent rounded-lg" />
                </div>
                <h3 className="text-xl font-semibold mb-1">{member.name}</h3>
                <p className="text-primary-600 mb-2">{member.role}</p>
                <p className="text-gray-600 text-sm">{member.bio}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Values Section */}
      <section className="bg-gray-50 py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-12">
            <h2 className="text-3xl font-bold mb-4">Giá trị cốt lõi</h2>
            <p className="text-gray-600 max-w-2xl mx-auto">
              Những giá trị định hình nên thành công của chúng tôi
            </p>
          </div>

          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
            {values.map((value, index) => (
              <div key={index} className="bg-white p-6 rounded-lg shadow-md">
                <h3 className="text-xl font-semibold mb-4">{value.title}</h3>
                <p className="text-gray-600">{value.description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="bg-primary-600 text-white py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h2 className="text-3xl font-bold mb-4">
              Sẵn sàng bắt đầu dự án của bạn?
            </h2>
            <p className="text-primary-100 mb-8 max-w-2xl mx-auto">
              Hãy để chúng tôi giúp bạn tạo nên không gian sống đẳng cấp với hồ
              cá Koi
            </p>
            <Link
              to="/contact"
              className="inline-flex items-center px-8 py-3 bg-white text-primary-600 rounded-md hover:bg-primary-50 transition duration-300"
            >
              Liên hệ ngay
              <ArrowRight className="ml-2 h-5 w-5" />
            </Link>
          </div>
        </div>
      </section>

      {/* Certifications Section */}
      <section className="py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-12">
            <h2 className="text-3xl font-bold mb-4">Chứng nhận & Giải thưởng</h2>
            <p className="text-gray-600 max-w-2xl mx-auto">
              Những thành tựu ghi nhận chất lượng và uy tín của chúng tôi
            </p>
          </div>

          <div className="grid md:grid-cols-3 gap-8">
            {[1, 2, 3].map((cert) => (
              <div
                key={cert}
                className="bg-white p-6 rounded-lg shadow-md text-center"
              >
                <img
                  src="/api/placeholder/200/200"
                  alt={`Certification ${cert}`}
                  className="mx-auto mb-4 h-24 w-auto"
                />
                <h3 className="text-lg font-semibold mb-2">Chứng nhận {cert}</h3>
                <p className="text-gray-600">
                  Mô tả về chứng nhận và ý nghĩa của nó đối với công ty
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>
    </div>
  );
};

export default About;
