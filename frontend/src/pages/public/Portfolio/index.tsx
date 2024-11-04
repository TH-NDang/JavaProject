import React, { useState } from "react";
import { ArrowRight, CheckCircle, X } from "lucide-react";
import { Link } from "react-router-dom";

interface Project {
  id: number;
  title: string;
  category: string;
  description: string;
  location: string;
  completionDate: string;
  images: string[];
  features: string[];
}

// !TODO: Get projects from API
const projects: Project[] = [
  {
    id: 1,
    title: "Hồ Koi Biệt Thự Hiện Đại",
    category: "Biệt thự",
    description:
      "Thiết kế và thi công hồ Koi phong cách hiện đại kết hợp cảnh quan sân vườn",
    location: "Quận 2, TP.HCM",
    completionDate: "2024",
    images: ["/api/placeholder/800/600", "/api/placeholder/800/600"],
    features: [
      "Diện tích: 50m²",
      "Độ sâu: 1.8m",
      "Hệ thống lọc: Nexus 320",
      "Đèn LED âm nước",
    ],
  },
  {
    id: 2,
    title: "Hồ Koi Resort Sang Trọng",
    category: "Resort",
    description: "Hồ Koi trung tâm kết hợp cảnh quan cho khu nghỉ dưỡng cao cấp",
    location: "Đà Lạt, Lâm Đồng",
    completionDate: "2023",
    images: ["/api/placeholder/800/600", "/api/placeholder/800/600"],
    features: [
      "Diện tích: 120m²",
      "Độ sâu: 2.2m",
      "Hệ thống lọc: Nexus 450",
      "Thác nước trang trí",
    ],
  },
  // Thêm các dự án khác
];

const categories = ["Tất cả", "Biệt thự", "Resort", "Nhà phố", "Penthouse"];

const Portfolio: React.FC = () => {
  const [selectedCategory, setSelectedCategory] = useState("Tất cả");
  const [selectedProject, setSelectedProject] = useState<Project | null>(null);

  const filteredProjects =
    selectedCategory === "Tất cả"
      ? projects
      : projects.filter((project) => project.category === selectedCategory);

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Hero Section */}
      <section className="bg-primary-600 text-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-4xl font-bold mb-6">Dự Án Tiêu Biểu</h1>
            <p className="text-xl text-primary-100 max-w-2xl mx-auto">
              Khám phá các dự án hồ cá Koi đẳng cấp đã được chúng tôi thực hiện
            </p>
          </div>
        </div>
      </section>

      {/* Filter Section */}
      <section className="py-8 bg-white border-b">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex flex-wrap justify-center gap-4">
            {categories.map((category) => (
              <button
                key={category}
                onClick={() => setSelectedCategory(category)}
                className={`px-6 py-2 rounded-full transition duration-300 ${
                  selectedCategory === category
                    ? "bg-primary-600 text-white"
                    : "bg-gray-100 text-gray-700 hover:bg-gray-200"
                }`}
              >
                {category}
              </button>
            ))}
          </div>
        </div>
      </section>

      {/* Projects Grid */}
      <section className="py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
            {filteredProjects.map((project) => (
              <div
                key={project.id}
                className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition duration-300"
              >
                <div className="relative group">
                  <img
                    src={project.images[0]}
                    alt={project.title}
                    className="w-full h-64 object-cover"
                  />
                  <div className="absolute inset-0 bg-black bg-opacity-0 group-hover:bg-opacity-50 transition-opacity duration-300 flex items-center justify-center opacity-0 group-hover:opacity-100">
                    <button
                      onClick={() => setSelectedProject(project)}
                      className="bg-white text-gray-900 px-6 py-2 rounded-full font-medium hover:bg-gray-100 transition duration-300"
                    >
                      Xem chi tiết
                    </button>
                  </div>
                </div>
                <div className="p-6">
                  <h3 className="text-xl font-semibold mb-2">{project.title}</h3>
                  <p className="text-gray-600 mb-4">{project.description}</p>
                  <div className="flex justify-between items-center">
                    <span className="text-sm text-gray-500">
                      {project.location}
                    </span>
                    <span className="text-sm font-medium text-primary-600">
                      {project.completionDate}
                    </span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Project Modal */}
      {selectedProject && (
        <div className="fixed inset-0 bg-black bg-opacity-50 z-50 flex items-center justify-center p-4">
          <div className="bg-white rounded-lg max-w-4xl w-full max-h-[90vh] overflow-y-auto">
            <div className="p-6">
              <div className="flex justify-between items-start mb-6">
                <div>
                  <h2 className="text-2xl font-bold mb-2">
                    {selectedProject.title}
                  </h2>
                  <p className="text-gray-600">{selectedProject.description}</p>
                </div>
                <button
                  onClick={() => setSelectedProject(null)}
                  className="text-gray-400 hover:text-gray-500"
                >
                  <span className="sr-only">Close</span>
                  <X className="h-6 w-6" />
                </button>
              </div>
            </div>

            {/* Image Gallery */}
            <div className="mb-8">
              <div className="grid grid-cols-2 gap-4">
                {selectedProject.images.map((image, index) => (
                  <img
                    key={index}
                    src={image}
                    alt={`${selectedProject.title} - ${index + 1}`}
                    className="w-full h-64 object-cover rounded-lg"
                  />
                ))}
              </div>
            </div>

            {/* Project Details */}
            <div className="grid md:grid-cols-2 gap-8">
              <div>
                <h3 className="text-lg font-semibold mb-4">Thông tin dự án</h3>
                <dl className="space-y-2">
                  <div className="flex justify-between">
                    <dt className="text-gray-600">Địa điểm:</dt>
                    <dd className="font-medium">{selectedProject.location}</dd>
                  </div>
                  <div className="flex justify-between">
                    <dt className="text-gray-600">Hoàn thành:</dt>
                    <dd className="font-medium">
                      {selectedProject.completionDate}
                    </dd>
                  </div>
                  <div className="flex justify-between">
                    <dt className="text-gray-600">Loại hình:</dt>
                    <dd className="font-medium">{selectedProject.category}</dd>
                  </div>
                </dl>
              </div>

              <div>
                <h3 className="text-lg font-semibold mb-4">Đặc điểm nổi bật</h3>
                <ul className="space-y-2">
                  {selectedProject.features.map((feature, index) => (
                    <li key={index} className="flex items-center">
                      <CheckCircle className="h-5 w-5 text-accent-500 mr-2" />
                      <span>{feature}</span>
                    </li>
                  ))}
                </ul>
              </div>
            </div>

            {/* CTA */}
            <div className="mt-8 pt-6 border-t">
              <div className="flex justify-end">
                <Link
                  to="/contact"
                  className="inline-flex items-center px-6 py-3 bg-primary-600 text-white rounded-md hover:bg-primary-700 transition duration-300"
                >
                  Tư vấn dự án tương tự
                  <ArrowRight className="ml-2 h-5 w-5" />
                </Link>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* CTA Section */}
      <section className="bg-gray-900 text-white py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h2 className="text-3xl font-bold mb-4">
              Bạn muốn tạo nên không gian sống đẳng cấp?
            </h2>
            <p className="text-gray-300 mb-8 max-w-2xl mx-auto">
              Hãy để chúng tôi giúp bạn hiện thực hóa ý tưởng với hồ cá Koi độc
              đáo
            </p>
            <Link
              to="/contact"
              className="inline-flex items-center px-8 py-3 bg-white text-gray-900 rounded-md hover:bg-gray-100 transition duration-300"
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

export default Portfolio;
