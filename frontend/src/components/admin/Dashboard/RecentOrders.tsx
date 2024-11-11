import React from "react";
import { ArrowRight } from "lucide-react";
import { Link } from "react-router-dom";
import { OrderStatusBadge } from "../../../pages/admin/Orders/OrderStatusBadge";
import { Order } from "../../../types/order";

interface RecentOrdersProps {
  orders: Order[];
  loading?: boolean;
}

const RecentOrders: React.FC<RecentOrdersProps> = ({ orders, loading }) => {
  if (loading) {
    return (
      <div className="bg-white rounded-lg shadow">
        <div className="p-6 animate-pulse">
          <div className="h-6 bg-gray-200 rounded w-1/4 mb-8"></div>
          <div className="space-y-4">
            {[1, 2, 3, 4, 5].map((i) => (
              <div key={i} className="flex justify-between items-center">
                <div className="space-y-2">
                  <div className="h-4 bg-gray-200 rounded w-32"></div>
                  <div className="h-4 bg-gray-200 rounded w-24"></div>
                </div>
                <div className="h-6 bg-gray-200 rounded w-20"></div>
              </div>
            ))}
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="bg-white rounded-lg shadow">
      <div className="p-6">
        <div className="flex justify-between items-center mb-6">
          <h3 className="text-lg font-semibold">Đơn hàng gần đây</h3>
          <Link
            to="/admin/orders"
            className="text-sm text-primary-600 hover:text-primary-700 font-medium inline-flex items-center"
          >
            Xem tất cả
            <ArrowRight className="ml-1 h-4 w-4" />
          </Link>
        </div>

        <div className="space-y-4">
          {orders.length === 0 ? (
            <p className="text-gray-500 text-center py-4">
              Chưa có đơn hàng nào
            </p>
          ) : (
            orders.map((order) => (
              <div
                key={order.id}
                className="flex justify-between items-center py-3 border-b border-gray-200 last:border-0"
              >
                <div>
                  <p className="font-medium">
                    #{order.id} - {order.customerName}
                  </p>
                  <p className="text-sm text-gray-500">
                    {new Intl.NumberFormat("vi-VN", {
                      style: "currency",
                      currency: "VND",
                    }).format(order.totalAmount)}
                  </p>
                </div>
                <OrderStatusBadge status={order.status} />
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
};

export default RecentOrders;
