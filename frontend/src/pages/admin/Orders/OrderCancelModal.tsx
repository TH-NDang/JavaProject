import React, { useState } from "react";
import { X } from "lucide-react";
import { OrderService } from "../../../services/api/order.service";
import { Toast } from "../../../services/toast.service";
import { Order } from "../../../types/order";

interface OrderCancelModalProps {
  order: Order;
  onClose: () => void;
  onCancel: () => void;
}

const OrderCancelModal: React.FC<OrderCancelModalProps> = ({
  order,
  onClose,
  onCancel,
}) => {
  const [reason, setReason] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!reason.trim()) {
      Toast.error("Vui lòng nhập lý do hủy đơn");
      return;
    }

    try {
      setLoading(true);
      await OrderService.cancelOrder(order.id, reason);
      Toast.success("Hủy đơn hàng thành công");
      onCancel();
      onClose();
    } catch (error) {
      Toast.error("Không thể hủy đơn hàng");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg max-w-md w-full p-6">
        <div className="flex justify-between items-start mb-6">
          <h2 className="text-2xl font-bold">Hủy đơn hàng #{order.id}</h2>
          <button
            onClick={onClose}
            className="text-gray-400 hover:text-gray-500"
          >
            <X className="h-6 w-6" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Lý do hủy đơn
            </label>
            <textarea
              value={reason}
              onChange={(e) => setReason(e.target.value)}
              rows={4}
              className="w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500"
              placeholder="Nhập lý do hủy đơn hàng..."
            />
          </div>

          <div className="flex justify-end space-x-3">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 text-gray-700 hover:text-gray-900"
              disabled={loading}
            >
              Hủy
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 disabled:opacity-50"
              disabled={loading}
            >
              {loading ? "Đang xử lý..." : "Xác nhận hủy đơn"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default OrderCancelModal;
