import React from "react";
import { OrderStatus } from "../../../types/order";

const STATUS_STYLES: Record<OrderStatus, { label: string; className: string }> =
  {
    [OrderStatus.PENDING]: {
      label: "Chờ xử lý",
      className: "bg-yellow-100 text-yellow-800",
    },
    [OrderStatus.CONSULTING]: {
      label: "Đang tư vấn",
      className: "bg-blue-100 text-blue-800",
    },
    [OrderStatus.DESIGNING]: {
      label: "Đang thiết kế",
      className: "bg-indigo-100 text-indigo-800",
    },
    [OrderStatus.APPROVED]: {
      label: "Đã duyệt",
      className: "bg-green-100 text-green-800",
    },
    [OrderStatus.CONSTRUCTION]: {
      label: "Đang thi công",
      className: "bg-purple-100 text-purple-800",
    },
    [OrderStatus.COMPLETED]: {
      label: "Hoàn thành",
      className: "bg-gray-100 text-gray-800",
    },
    [OrderStatus.CANCELLED]: {
      label: "Đã hủy",
      className: "bg-red-100 text-red-800",
    },
  };

interface OrderStatusBadgeProps {
  status: OrderStatus;
}

export const OrderStatusBadge: React.FC<OrderStatusBadgeProps> = ({
  status,
}) => {
  const style = STATUS_STYLES[status];

  return (
    <span
      className={`px-2 py-1 rounded-full text-xs font-medium ${style.className}`}
    >
      {style.label}
    </span>
  );
};
