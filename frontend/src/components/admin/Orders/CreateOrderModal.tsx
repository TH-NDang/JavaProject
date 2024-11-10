import React, { useState } from "react";
import { X } from "lucide-react";
import CreateOrderForm from "./CreateOrderForm";
import { OrderService } from "../../../services/api/order.service";
import { Toast } from "../../../services/toast.service";

interface CreateOrderModalProps {
  onClose: () => void;
  onSuccess: () => void;
}

const CreateOrderModal: React.FC<CreateOrderModalProps> = ({
  onClose,
  onSuccess,
}) => {
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (data: any) => {
    try {
      setLoading(true);
      await OrderService.createOrder(data);
      Toast.success("Tạo đơn hàng thành công");
      onSuccess();
      onClose();
    } catch (error) {
      Toast.error("Không thể tạo đơn hàng");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg max-w-4xl w-full p-6">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-2xl font-bold">Tạo đơn hàng mới</h2>
          <button
            onClick={onClose}
            className="text-gray-400 hover:text-gray-500"
          >
            <X className="h-6 w-6" />
          </button>
        </div>

        <CreateOrderForm onSubmit={handleSubmit} loading={loading} />
      </div>
    </div>
  );
};

export default CreateOrderModal;
