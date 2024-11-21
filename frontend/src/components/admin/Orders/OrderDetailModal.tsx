import React, { useState, useEffect, useCallback } from "react";
import { X } from "lucide-react";
import { OrderService } from "../../../services/order.service";
import { OrderStatusBadge } from "./OrderStatusBadge";
import { Toast } from "../../../services/toast.service";
import { Order, OrderHistory, OrderStatus } from "../../../types/order";

interface OrderDetailModalProps {
  order: Order;
  onClose: () => void;
  onUpdate: () => void;
}

const OrderDetailModal: React.FC<OrderDetailModalProps> = ({
  order,
  onClose,
  onUpdate,
}) => {
  const [loading, setLoading] = useState(false);
  const [orderHistory, setOrderHistory] = useState<OrderHistory[]>([]);
  const [newStatus, setNewStatus] = useState<OrderStatus>(order.status);

  const fetchOrderHistory = useCallback(async () => {
    try {
      const history = await OrderService.getOrderHistory(order.id);
      setOrderHistory(history);
    } catch (error) {
      Toast.error("Không thể tải lịch sử đơn hàng");
    }
  }, [order.id]);

  useEffect(() => {
    fetchOrderHistory();
  }, [fetchOrderHistory]);

  const handleUpdateStatus = async () => {
    try {
      setLoading(true);
      await OrderService.updateOrder(order.id, { status: newStatus });
      Toast.success("Cập nhật trạng thái thành công");
      onUpdate();
      onClose();
    } catch (error) {
      Toast.error("Không thể cập nhật trạng thái");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg max-w-4xl w-full max-h-[90vh] overflow-y-auto">
        <div className="p-6">
          <div className="flex justify-between items-start mb-6">
            <h2 className="text-2xl font-bold">Chi tiết đơn hàng #{order.id}</h2>
            <button
              onClick={onClose}
              className="text-gray-400 hover:text-gray-500"
            >
              <X className="h-6 w-6" />
            </button>
          </div>

          {/* Customer Information */}
          <div className="grid grid-cols-2 gap-6 mb-8">
            <div>
              <h3 className="text-lg font-semibold mb-4">
                Thông tin khách hàng
              </h3>
              <dl className="space-y-2">
                <div className="flex justify-between">
                  <dt className="text-gray-600">Khách hàng:</dt>
                  <dd className="font-medium">{order.customerName}</dd>
                </div>
                <div className="flex justify-between">
                  <dt className="text-gray-600">Email:</dt>
                  <dd className="font-medium">{order.customerEmail}</dd>
                </div>
                <div className="flex justify-between">
                  <dt className="text-gray-600">Số điện thoại:</dt>
                  <dd className="font-medium">{order.customerPhone}</dd>
                </div>
                <div className="flex justify-between">
                  <dt className="text-gray-600">Địa chỉ:</dt>
                  <dd className="font-medium">{order.location}</dd>
                </div>
              </dl>
            </div>

            {/* Order Information */}
            <div>
              <h3 className="text-lg font-semibold mb-4">Thông tin đơn hàng</h3>
              <dl className="space-y-2">
                <div className="flex justify-between">
                  <dt className="text-gray-600">Dịch vụ:</dt>
                  <dd className="font-medium">{order.serviceName}</dd>
                </div>
                <div className="flex justify-between">
                  <dt className="text-gray-600">Tổng tiền:</dt>
                  <dd className="font-medium">
                    {new Intl.NumberFormat("vi-VN", {
                      style: "currency",
                      currency: "VND",
                    }).format(order.totalAmount)}
                  </dd>
                </div>
                <div className="flex justify-between">
                  <dt className="text-gray-600">Trạng thái:</dt>
                  <dd>
                    <OrderStatusBadge status={order.status} />
                  </dd>
                </div>
                <div className="flex justify-between">
                  <dt className="text-gray-600">Ngày tạo:</dt>
                  <dd className="font-medium">
                    {new Date(order.createdAt).toLocaleDateString("vi-VN")}
                  </dd>
                </div>
              </dl>
            </div>
          </div>

          {/* Order Requirements */}
          <div className="mb-8">
            <h3 className="text-lg font-semibold mb-4">
              Yêu cầu của khách hàng
            </h3>
            <div className="bg-gray-50 rounded-md p-4">
              <p className="whitespace-pre-wrap">{order.requirements}</p>
            </div>
          </div>

          {/* Update Status */}
          <div className="mb-8">
            <h3 className="text-lg font-semibold mb-4">Cập nhật trạng thái</h3>
            <div className="flex gap-4">
              <select
                value={newStatus}
                onChange={(e) => setNewStatus(e.target.value as OrderStatus)}
                className="flex-1 rounded-md border-gray-300"
                disabled={loading}
              >
                <option value="PENDING">Chờ xử lý</option>
                <option value="CONSULTING">Đang tư vấn</option>
                <option value="DESIGNING">Đang thiết kế</option>
                <option value="APPROVED">Đã duyệt</option>
                <option value="CONSTRUCTION">Đang thi công</option>
                <option value="COMPLETED">Hoàn thành</option>
              </select>
              <button
                onClick={handleUpdateStatus}
                disabled={loading || newStatus === order.status}
                className="px-4 py-2 bg-primary-600 text-white rounded-md hover:bg-primary-700 disabled:opacity-50"
              >
                {loading ? "Đang cập nhật..." : "Cập nhật"}
              </button>
            </div>
          </div>

          {/* Order History */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Lịch sử đơn hàng</h3>
            <div className="space-y-4">
              {orderHistory.map((history, index) => (
                <div
                  key={index}
                  className="flex items-start space-x-4 border-l-2 border-gray-200 pl-4"
                >
                  <div className="flex-1">
                    <div className="flex items-center space-x-2">
                      <OrderStatusBadge status={history.toStatus} />
                      <span className="text-gray-500 text-sm">
                        bởi {history.changedByName}
                      </span>
                    </div>
                    {history.notes && (
                      <p className="mt-1 text-gray-600">{history.notes}</p>
                    )}
                    <p className="text-sm text-gray-500 mt-1">
                      {new Date(history.createdAt).toLocaleString("vi-VN")}
                    </p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderDetailModal;
