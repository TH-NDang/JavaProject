import React, { useState } from "react";
import { MapPin, Phone, Mail, Clock } from "lucide-react";
import ContactForm from "../../../components/modals/ContactForm";

const Contact: React.FC = () => {
  // !TODO: Thay thế nếu cần
  const contactInfo = [
    {
      icon: MapPin,
      title: "Địa chỉ",
      content: "123 Đường ABC, Quận XYZ, TP.HCM",
    },
    {
      icon: Phone,
      title: "Điện thoại",
      content: "(84) 123-456-789",
    },
    {
      icon: Mail,
      title: "Email",
      content: "info@koipondco.com",
    },
    {
      icon: Clock,
      title: "Giờ làm việc",
      content: "T2-T7: 8:00 - 17:30",
    },
  ];

  return (
    <div className="min-h-screen">
      {/* Hero Section */}
      <section className="bg-primary-600 text-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-4xl font-bold mb-6">Liên Hệ Với Chúng Tôi</h1>
            <p className="text-xl text-primary-100 max-w-2xl mx-auto">
              Hãy để chúng tôi giúp bạn hiện thực hóa ý tưởng về hồ cá Koi trong
              mơ
            </p>
          </div>
        </div>
      </section>

      {/* Contact Info & Form Section */}
      <section className="py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid lg:grid-cols-3 gap-12">
            {/* Contact Info */}
            <div className="lg:col-span-1">
              <h2 className="text-2xl font-bold mb-8">Thông Tin Liên Hệ</h2>
              <div className="space-y-6">
                {contactInfo.map((info, index) => {
                  const Icon = info.icon;
                  return (
                    <div key={index} className="flex items-start">
                      <div className="flex-shrink-0">
                        <div className="w-10 h-10 bg-primary-100 rounded-lg flex items-center justify-center">
                          <Icon className="h-5 w-5 text-primary-600" />
                        </div>
                      </div>
                      <div className="ml-4">
                        <h3 className="text-lg font-medium text-gray-900">
                          {info.title}
                        </h3>
                        <p className="mt-1 text-gray-600">{info.content}</p>
                      </div>
                    </div>
                  );
                })}
              </div>
            </div>

            {/* Contact Form */}
            <div className="lg:col-span-2">
              <div className="bg-white rounded-lg shadow-lg p-8">
                <h2 className="text-2xl font-bold mb-6">Gửi Tin Nhắn</h2>
                <ContactForm />
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Contact;
