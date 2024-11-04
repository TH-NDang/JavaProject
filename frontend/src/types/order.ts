export type OrderStatus =
  | "PENDING"
  | "CONSULTING"
  | "DESIGNING"
  | "APPROVED"
  | "CONSTRUCTION"
  | "COMPLETED"
  | "CANCELLED";

export interface Order {
  id: number;
  customerName: string;
  service: string;
  status: OrderStatus;
  createdAt: string;
  totalAmount: number;
}

export interface OrderStatusConfig {
  label: string;
  color: string;
}

// Sửa lại định nghĩa OrderStatusMap
export type OrderStatusMap = Record<OrderStatus, OrderStatusConfig>;
