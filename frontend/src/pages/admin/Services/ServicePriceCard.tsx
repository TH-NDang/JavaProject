// src/pages/admin/Services/ServicePriceCard.tsx
import React from "react";
import { ServicePrice } from "../../../types/service";
import { StarIcon, PencilIcon, TrashIcon, CheckIcon } from "lucide-react";

interface ServicePriceCardProps {
  service: ServicePrice;
  onEdit: (service: ServicePrice) => void;
  onDelete: (id: number) => void;
  onTogglePopular: (id: number, isPopular: boolean) => void;
}

const ServicePriceCard: React.FC<ServicePriceCardProps> = ({
  service,
  onEdit,
  onDelete,
  onTogglePopular,
}) => {
  // Đảm bảo service.id tồn tại trước khi sử dụng
  const handleDelete = () => {
    if (service.id) {
      onDelete(service.id);
    }
  };

  const handleTogglePopular = () => {
    if (service.id) {
      onTogglePopular(service.id, !service.isPopular);
    }
  };

  return (
    <div className="bg-white rounded-lg shadow-md p-6 relative">
      {service.isPopular && (
        <div className="absolute top-4 right-4 bg-yellow-100 text-yellow-800 px-3 py-1 rounded-full text-sm font-medium">
          Popular
        </div>
      )}

      <h3 className="text-xl font-semibold mb-2">{service.name}</h3>
      <p className="text-gray-600 mb-4">{service.description}</p>

      <div className="text-3xl font-bold text-primary-600 mb-4">
        {service.price.toLocaleString("vi-VN")} đ
      </div>

      <ul className="space-y-2 mb-6">
        {service.features.map((feature, index) => (
          <li key={index} className="flex items-center text-gray-700">
            <CheckIcon className="h-5 w-5 text-green-500 mr-2" />
            {feature}
          </li>
        ))}
      </ul>

      <div className="flex justify-end space-x-2">
        <button
          onClick={handleTogglePopular}
          className="p-2 text-gray-600 hover:text-yellow-600"
        >
          <StarIcon
            className={`h-5 w-5 ${service.isPopular ? "fill-yellow-400" : ""}`}
          />
        </button>
        <button
          onClick={() => onEdit(service)}
          className="p-2 text-blue-600 hover:text-blue-800"
        >
          <PencilIcon className="h-5 w-5" />
        </button>
        <button
          onClick={handleDelete}
          className="p-2 text-red-600 hover:text-red-800"
        >
          <TrashIcon className="h-5 w-5" />
        </button>
      </div>
    </div>
  );
};

export default ServicePriceCard;
