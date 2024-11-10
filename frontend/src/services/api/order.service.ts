import api from "../../config/axios";

export const OrderService = {
  async getAllOrders(params: {
    page?: number;
    size?: number;
    search?: string;
    status?: string;
  }) {
    const response = await api.get("/api/orders", { params });
    return response.data;
  },

  async getOrderById(id: number) {
    const response = await api.get(`/api/orders/${id}`);
    return response.data;
  },

  async createOrder(data: any) {
    const response = await api.post("/api/orders", data);
    return response.data;
  },

  async updateOrder(id: number, data: any) {
    const response = await api.put(`/api/orders/${id}`, data);
    return response.data;
  },

  async cancelOrder(id: number, reason: string) {
    const response = await api.post(`/api/orders/${id}/cancel`, {
      cancellationReason: reason,
    });
    return response.data;
  },

  async getOrderHistory(id: number) {
    const response = await api.get(`/api/orders/${id}/history`);
    return response.data;
  },
};
