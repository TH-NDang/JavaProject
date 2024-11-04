import { useState, useEffect } from "react";
import {
  Users,
  FileText,
  BarChart2,
  Wrench,
  TrendingUp,
  TrendingDown,
} from "lucide-react";

interface StatCardProps {
  title: string;
  value: string | number;
  icon: React.ElementType;
  trend?: {
    value: number;
    isPositive: boolean;
  };
  bgColor?: string;
}

const StatCard: React.FC<StatCardProps> = ({
  title,
  value,
  icon: Icon,
  trend,
  bgColor = "bg-white",
}) => (
  <div className={`${bgColor} rounded-lg shadow p-6`}>
    <div className="flex items-center justify-between">
      <div>
        <p className="text-sm font-medium text-gray-600">{title}</p>
        <p className="mt-2 text-3xl font-semibold text-gray-900">{value}</p>
        {trend && (
          <div className="mt-2 flex items-center">
            {trend.isPositive ? (
              <TrendingUp className="h-4 w-4 text-green-500" />
            ) : (
              <TrendingDown className="h-4 w-4 text-red-500" />
            )}
            <span
              className={`ml-2 text-sm ${
                trend.isPositive ? "text-green-500" : "text-red-500"
              }`}
            >
              {trend.value}%
            </span>
          </div>
        )}
      </div>
      <div className="p-3 bg-primary-100 rounded-full">
        <Icon className="h-6 w-6 text-primary-600" />
      </div>
    </div>
  </div>
);

export default function AdminDashboard() {
  const [recentOrders, setRecentOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // TODO: Fetch dashboard data
    const fetchDashboardData = async () => {
      try {
        // const response = await api.get('/admin/dashboard');
        // setRecentOrders(response.data.orders);
      } catch (error) {
        console.error("Error fetching dashboard data:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchDashboardData();
  }, []);

  const stats = [
    {
      title: "Tổng đơn hàng",
      value: "156",
      icon: FileText,
      trend: { value: 12, isPositive: true },
    },
    {
      title: "Khách hàng mới",
      value: "32",
      icon: Users,
      trend: { value: 8.2, isPositive: true },
    },
    {
      title: "Doanh thu tháng",
      value: "258.2M",
      icon: BarChart2,
      trend: { value: 5.1, isPositive: true },
    },
    {
      title: "Đơn hàng đang thực hiện",
      value: "12",
      icon: Wrench,
      trend: { value: 2.3, isPositive: false },
    },
  ];

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-2xl font-semibold text-gray-900">Dashboard</h1>
        <p className="mt-1 text-sm text-gray-500">
          Tổng quan hoạt động kinh doanh
        </p>
      </div>

      {/* Stats Grid */}
      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-4">
        {stats.map((stat, index) => (
          <StatCard key={index} {...stat} />
        ))}
      </div>

      {/* Recent Orders */}
      <div className="bg-white shadow rounded-lg">
        <div className="px-4 py-5 sm:px-6 border-b border-gray-200">
          <h3 className="text-lg leading-6 font-medium text-gray-900">
            Đơn hàng gần đây
          </h3>
        </div>
        <div className="px-4 py-5 sm:p-6">
          {loading ? (
            <div className="text-center">
              <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-primary-600 mx-auto" />
            </div>
          ) : recentOrders.length > 0 ? (
            <div className="overflow-x-auto">
              <table className="min-w-full divide-y divide-gray-200">
                <thead className="bg-gray-50">
                  <tr>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Mã đơn
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Khách hàng
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Trạng thái
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                      Ngày tạo
                    </th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  {/* TODO: Add order rows */}
                </tbody>
              </table>
            </div>
          ) : (
            <p className="text-gray-500 text-center">Chưa có đơn hàng nào</p>
          )}
        </div>
      </div>
    </div>
  );
}
