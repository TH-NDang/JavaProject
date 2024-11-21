import React, { useState, useEffect, useCallback } from "react";
import { Search, Eye, XCircle } from "lucide-react";
import { OrderService } from "../../../services/order.service";
import { Toast } from "../../../services/toast.service";
import DataTable from "../../common/DataTable";
import OrderDetailModal from "./OrderDetailModal";
import OrderCancelModal from "./OrderCancelModal";
import { OrderStatusBadge } from "./OrderStatusBadge";
import { Order, OrderStatus } from "../../../types/order";
import { Column } from "../../../types/column";

const OrderList: React.FC = () => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState("");
  const [statusFilter, setStatusFilter] = useState<string>("");
  const [selectedOrder, setSelectedOrder] = useState<Order | null>(null);
  const [showDetailModal, setShowDetailModal] = useState(false);
  const [showCancelModal, setShowCancelModal] = useState(false);

  const fetchOrders = useCallback(async () => {
    try {
      setLoading(true);
      const response = await OrderService.getAllOrders({
        page: currentPage,
        size: 10,
        search: searchTerm,
        status: statusFilter,
      });
      setOrders(response.content);
      setTotalPages(response.totalPages);
    } catch (error) {
      Toast.error("Không thể tải danh sách đơn hàng");
    } finally {
      setLoading(false);
    }
  }, [currentPage, searchTerm, statusFilter]);

  useEffect(() => {
    fetchOrders();
  }, [fetchOrders]);

  const handleViewOrder = (order: Order) => {
    setSelectedOrder(order);
    setShowDetailModal(true);
  };

  const handleCancelOrder = (order: Order) => {
    setSelectedOrder(order);
    setShowCancelModal(true);
  };

  const columns: Column<Order>[] = [
    {
      key: "id",
      title: "Mã đơn",
      render: (value: number) => `#${value}`,
    },
    {
      key: "customerName",
      title: "Khách hàng",
    },
    {
      key: "serviceName",
      title: "Dịch vụ",
    },
    {
      key: "status",
      title: "Trạng thái",
      render: (value: OrderStatus) => <OrderStatusBadge status={value} />,
    },
    {
      key: "totalAmount",
      title: "Tổng tiền",
      render: (value: number) =>
        new Intl.NumberFormat("vi-VN", {
          style: "currency",
          currency: "VND",
        }).format(value),
    },
    {
      key: "createdAt",
      title: "Ngày tạo",
      render: (value: string) => new Date(value).toLocaleDateString("vi-VN"),
    },
    {
      key: "actions",
      title: "Thao tác",
      render: (_, order: Order) => (
        <div className="flex space-x-2">
          <button
            onClick={() => handleViewOrder(order)}
            className="p-2 text-blue-600 hover:text-blue-800"
          >
            <Eye className="h-5 w-5" />
          </button>
          {order.status !== OrderStatus.CANCELLED &&
            order.status !== OrderStatus.COMPLETED && (
              <button
                onClick={() => handleCancelOrder(order)}
                className="p-2 text-red-600 hover:text-red-800"
              >
                <XCircle className="h-5 w-5" />
              </button>
            )}
        </div>
      ),
    },
  ];

  return (
    <div className="space-y-6">
      {/* Search and Filter */}
      <div className="flex gap-4 bg-white p-4 rounded-lg shadow">
        <div className="flex-1">
          <div className="relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" />
            <input
              type="text"
              placeholder="Tìm kiếm đơn hàng..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="pl-10 pr-4 py-2 w-full border rounded-md"
            />
          </div>
        </div>
        <select
          value={statusFilter}
          onChange={(e) => setStatusFilter(e.target.value)}
          className="border rounded-md px-3"
        >
          <option value="">Tất cả trạng thái</option>
          {Object.entries(OrderStatus).map(([key, value]) => (
            <option key={value} value={value}>
              {STATUS_LABELS[value as OrderStatus]}
            </option>
          ))}
        </select>
      </div>

      {/* Orders Table */}
      <DataTable<Order>
        columns={columns}
        data={orders}
        loading={loading}
        pagination={{
          currentPage: currentPage + 1,
          totalPages,
          onPageChange: (page: number) => setCurrentPage(page - 1),
        }}
      />

      {/* Modals */}
      {showDetailModal && selectedOrder && (
        <OrderDetailModal
          order={selectedOrder}
          onClose={() => {
            setShowDetailModal(false);
            setSelectedOrder(null);
          }}
          onUpdate={fetchOrders}
        />
      )}

      {showCancelModal && selectedOrder && (
        <OrderCancelModal
          order={selectedOrder}
          onClose={() => {
            setShowCancelModal(false);
            setSelectedOrder(null);
          }}
          onCancel={fetchOrders}
        />
      )}
    </div>
  );
};

// Status labels for the filter dropdown
const STATUS_LABELS: Record<OrderStatus, string> = {
  [OrderStatus.PENDING]: "Chờ xử lý",
  [OrderStatus.CONSULTING]: "Đang tư vấn",
  [OrderStatus.DESIGNING]: "Đang thiết kế",
  [OrderStatus.APPROVED]: "Đã duyệt",
  [OrderStatus.CONSTRUCTION]: "Đang thi công",
  [OrderStatus.COMPLETED]: "Hoàn thành",
  [OrderStatus.CANCELLED]: "Đã hủy",
};

export default OrderList;
