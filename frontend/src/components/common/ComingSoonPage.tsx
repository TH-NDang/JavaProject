import React from "react";
import { Rocket, CalendarClock } from "lucide-react";

interface ComingSoonPageProps {
  title: string;
  description?: string;
  icon?: React.FC<any>;
  expectedDate?: string;
}

const ComingSoonPage: React.FC<ComingSoonPageProps> = ({
  title,
  description,
  icon: Icon = Rocket,
  expectedDate,
}) => {
  return (
    <div className="min-h-[60vh] flex flex-col items-center justify-center text-center p-4">
      <div className="w-full max-w-md mx-auto space-y-8">
        {/* Icon */}
        <div className="flex justify-center">
          <div className="p-4 bg-primary-100 rounded-full">
            <Icon className="w-16 h-16 text-primary-600" />
          </div>
        </div>

        {/* Title and Description */}
        <div className="space-y-4">
          <h1 className="text-4xl font-bold text-gray-900">{title}</h1>
          <p className="text-xl text-gray-600">
            {description ||
              "Tính năng này đang được phát triển và sẽ sớm ra mắt."}
          </p>
        </div>

        {/* Expected Date */}
        {expectedDate && (
          <div className="flex items-center justify-center gap-2 text-primary-600">
            <CalendarClock className="w-5 h-5" />
            <span className="font-medium">Dự kiến ra mắt: {expectedDate}</span>
          </div>
        )}

        {/* Progress Bar */}
        <div className="relative w-full h-4 bg-gray-200 rounded-full overflow-hidden">
          <div
            className="absolute top-0 left-0 h-full bg-primary-600 rounded-full animate-pulse"
            style={{ width: "60%" }}
          />
        </div>

        {/* Features Coming */}
        <div className="mt-8 bg-white p-6 rounded-lg shadow-sm border">
          <h3 className="text-lg font-semibold mb-4">
            Các tính năng sắp ra mắt:
          </h3>
          <ul className="space-y-3 text-left">
            <li className="flex items-center">
              <div className="h-2 w-2 bg-primary-600 rounded-full mr-3"></div>
              Quản lý và theo dõi tiến độ
            </li>
            <li className="flex items-center">
              <div className="h-2 w-2 bg-primary-600 rounded-full mr-3"></div>
              Báo cáo chi tiết và phân tích
            </li>
            <li className="flex items-center">
              <div className="h-2 w-2 bg-primary-600 rounded-full mr-3"></div>
              Tích hợp thông báo thời gian thực
            </li>
          </ul>
        </div>

        {/* Stay Updated Section */}
        <div className="mt-8">
          <p className="text-sm text-gray-500">
            Theo dõi các cập nhật của chúng tôi để biết thêm thông tin mới nhất.
          </p>
        </div>
      </div>
    </div>
  );
};

export default ComingSoonPage;
