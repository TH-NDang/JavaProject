import React from "react";
import { ArrowUp, ArrowDown, LucideIcon } from "lucide-react";

interface StatCardProps {
  title: string;
  value: string | number;
  growth?: number;
  icon: LucideIcon;
  loading?: boolean;
}

const formatValue = (value: string | number): string => {
  if (typeof value === "number") {
    return new Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
      maximumFractionDigits: 0,
    }).format(value);
  }
  return value.toString();
};

const StatCard: React.FC<StatCardProps> = ({
  title,
  value,
  growth,
  icon: Icon,
  loading = false,
}) => {
  if (loading) {
    return (
      <div className="bg-white p-6 rounded-lg shadow animate-pulse">
        <div className="flex justify-between">
          <div className="space-y-3">
            <div className="h-4 bg-gray-200 rounded w-24"></div>
            <div className="h-6 bg-gray-200 rounded w-32"></div>
          </div>
          <div className="w-12 h-12 bg-gray-200 rounded-full"></div>
        </div>
      </div>
    );
  }

  return (
    <div className="bg-white p-6 rounded-lg shadow">
      <div className="flex justify-between">
        <div>
          <p className="text-sm font-medium text-gray-600">{title}</p>
          <h3 className="text-2xl font-semibold mt-2">{formatValue(value)}</h3>
          {growth !== undefined && (
            <div className="flex items-center mt-2">
              {growth > 0 ? (
                <ArrowUp className="w-4 h-4 text-green-500" />
              ) : (
                <ArrowDown className="w-4 h-4 text-red-500" />
              )}
              <span
                className={`ml-1 text-sm ${
                  growth > 0 ? "text-green-500" : "text-red-500"
                }`}
              >
                {Math.abs(growth)}%
              </span>
              <span className="ml-1 text-sm text-gray-500">
                so với tháng trước
              </span>
            </div>
          )}
        </div>
        <div className="p-3 bg-primary-100 rounded-full">
          <Icon className="w-6 h-6 text-primary-600" />
        </div>
      </div>
    </div>
  );
};

export default StatCard;
