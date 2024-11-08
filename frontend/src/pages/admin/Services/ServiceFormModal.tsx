import React from "react";
import ServiceForm from "./ServiceForm";
import { ServicePrice } from "../../../types/service";
import { X } from "lucide-react";

interface ServiceFormModalProps {
  service?: ServicePrice;
  onSubmit: (data: ServicePrice) => void;
  onClose: () => void;
}

const ServiceFormModal: React.FC<ServiceFormModalProps> = ({
  service,
  onSubmit,
  onClose,
}) => {
  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg max-w-2xl w-full max-h-[90vh] overflow-y-auto">
        <div className="p-6">
          <div className="flex justify-between items-center mb-6">
            <h2 className="text-2xl font-bold">
              {service ? "Chỉnh sửa dịch vụ" : "Thêm dịch vụ mới"}
            </h2>
            <button
              onClick={onClose}
              className="text-gray-400 hover:text-gray-500"
            >
              <X className="h-6 w-6" />
            </button>
          </div>

          <ServiceForm
            initialData={service}
            onSubmit={onSubmit}
            onCancel={onClose}
          />
        </div>
      </div>
    </div>
  );
};

export default ServiceFormModal;
