export enum OrderStatus {
  PENDING = "PENDING",
  CONSULTING = "CONSULTING",
  DESIGNING = "DESIGNING",
  APPROVED = "APPROVED",
  CONSTRUCTION = "CONSTRUCTION",
  COMPLETED = "COMPLETED",
  CANCELLED = "CANCELLED",
}

export interface Order {
  id: number;
  customerName: string;
  customerEmail: string;
  customerPhone: string;
  serviceName: string;
  status: OrderStatus;
  totalAmount: number;
  requirements?: string;
  location?: string;
  createdAt: string;
  updatedAt: string;
}

export interface OrderHistory {
  id: number;
  orderId: number;
  fromStatus: OrderStatus;
  toStatus: OrderStatus;
  changedById: number;
  changedByName: string;
  notes?: string;
  createdAt: string;
}

export interface OrderListResponse {
  content: Order[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
}

export interface CreateOrderRequest {
  serviceId: number;
  requirements?: string;
  location?: string;
}

export interface UpdateOrderRequest {
  status?: OrderStatus;
  notes?: string;
}

export interface CancelOrderRequest {
  cancellationReason: string;
}
