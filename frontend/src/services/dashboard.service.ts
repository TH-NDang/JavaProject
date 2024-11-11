import api from "../config/axios";

export interface DashboardStats {
  totalRevenue: number;
  totalOrders: number;
  totalCustomers: number;
  avgOrderValue: number;
  revenueGrowth: number;
  orderGrowth: number;
  customerGrowth: number;
  valueGrowth: number;
}

export interface RevenueStats {
  month: string;
  revenue: number;
}

export interface OrderStats {
  month: string;
  orders: number;
}

export interface ServiceStats {
  name: string;
  count: number;
  revenue: number;
}

export const DashboardService = {
  async getStats(): Promise<DashboardStats> {
    const response = await api.get("/api/dashboard/stats");
    return response.data;
  },

  async getRevenueStats(
    period: "month" | "year" = "month"
  ): Promise<RevenueStats[]> {
    const response = await api.get(
      `/api/dashboard/revenue-stats?period=${period}`
    );
    return response.data;
  },

  async getOrderStats(
    period: "month" | "year" = "month"
  ): Promise<OrderStats[]> {
    const response = await api.get(
      `/api/dashboard/order-stats?period=${period}`
    );
    return response.data;
  },

  async getServiceStats(): Promise<ServiceStats[]> {
    const response = await api.get("/api/dashboard/service-stats");
    return response.data;
  },
};
