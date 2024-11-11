import React, { useEffect, useState } from "react";
import { Activity, DollarSign, ShoppingBag, Users } from "lucide-react";
import StatCard from "../../../components/admin/Dashboard/StatCard";
import RecentOrders from "../../../components/admin/Dashboard/RecentOrders";
import {
  RevenueChart,
  OrderChart,
  ServiceChart,
} from "../../../components/admin/Dashboard/DashboardCharts";
import {
  DashboardService,
  DashboardStats,
  RevenueStats,
  OrderStats,
  ServiceStats,
} from "../../../services/api/dashboard.service";
import { OrderService } from "../../../services/api/order.service";
import { Toast } from "../../../services/toast.service";
import { Order } from "../../../types/order";

const Dashboard: React.FC = () => {
  const [loading, setLoading] = useState(true);
  const [stats, setStats] = useState<DashboardStats | null>(null);
  const [revenueData, setRevenueData] = useState<RevenueStats[]>([]);
  const [orderData, setOrderData] = useState<OrderStats[]>([]);
  const [serviceData, setServiceData] = useState<ServiceStats[]>([]);
  const [recentOrders, setRecentOrders] = useState<Order[]>([]);

  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        setLoading(true);
        const [statsData, revenueStats, orderStats, serviceStats, latestOrders] =
          await Promise.all([
            DashboardService.getStats(),
            DashboardService.getRevenueStats(),
            DashboardService.getOrderStats(),
            DashboardService.getServiceStats(),
            OrderService.getAllOrders({
              page: 0,
              size: 5,
              sort: "createdAt,desc",
            }),
          ]);

        setStats(statsData);
        setRevenueData(revenueStats);
        setOrderData(orderStats);
        setServiceData(serviceStats);
        setRecentOrders(latestOrders.content);
      } catch (error) {
        Toast.error("Không thể tải dữ liệu thống kê");
      } finally {
        setLoading(false);
      }
    };

    fetchDashboardData();
  }, []);

  return (
    <div className="space-y-6">
      {/* Page Title */}
      <div>
        <h1 className="text-2xl font-semibold text-gray-900">Tổng quan</h1>
        <p className="mt-1 text-sm text-gray-500">
          Thống kê hoạt động kinh doanh
        </p>
      </div>

      {/* Stat Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <StatCard
          title="Doanh thu tháng"
          value={stats?.totalRevenue || 0}
          growth={stats?.revenueGrowth}
          icon={DollarSign}
          loading={loading}
        />
        <StatCard
          title="Đơn hàng"
          value={stats?.totalOrders || 0}
          growth={stats?.orderGrowth}
          icon={ShoppingBag}
          loading={loading}
        />
        <StatCard
          title="Khách hàng"
          value={stats?.totalCustomers || 0}
          growth={stats?.customerGrowth}
          icon={Users}
          loading={loading}
        />
        <StatCard
          title="Giá trị TB/đơn"
          value={stats?.avgOrderValue || 0}
          growth={stats?.valueGrowth}
          icon={Activity}
          loading={loading}
        />
      </div>

      {/* Charts */}
      <div className="grid lg:grid-cols-2 gap-6">
        <RevenueChart data={revenueData} loading={loading} />
        <OrderChart data={orderData} loading={loading} />
      </div>

      {/* Service Stats and Recent Orders */}
      <div className="grid lg:grid-cols-2 gap-6">
        <ServiceChart data={serviceData} loading={loading} />
        <RecentOrders orders={recentOrders} loading={loading} />
      </div>
    </div>
  );
};

export default Dashboard;
