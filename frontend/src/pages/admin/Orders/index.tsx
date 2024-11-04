import { useState, useEffect } from "react";
import { Plus, Search, Filter } from "lucide-react";
import DataTable, { Column } from "../../../components/common/DataTable";
import PageHeader from "../../../components/common/PageHeader";
import { Order, OrderStatus, OrderStatusMap } from "../../../types/order";

const orderStatuses: OrderStatusMap = {
  PENDING: { label: "Chờ xử lý", color: "bg-yellow-100 text-yellow-800" },
  CONSULTING: { label: "Đang tư vấn", color: "bg-blue-100 text-blue-800" },
  DESIGNING: { label: "Đang thiết kế", color: "bg-indigo-100 text-indigo-800" },
  APPROVED: { label: "Đã duyệt", color: "bg-green-100 text-green-800" },
  CONSTRUCTION: {
    label: "Đang thi công",
    color: "bg-purple-100 text-purple-800",
  },
  COMPLETED: { label: "Hoàn thành", color: "bg-gray-100 text-gray-800" },
  CANCELLED: { label: "Đã hủy", color: "bg-red-100 text-red-800" },
};

export default function AdminOrders() {
  const [orders, setOrders] = useState<Order[]>([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  const handleViewOrder = (orderId: number) => {
    console.log("View order:", orderId);
  };

  const handleCancelOrder = async (orderId: number) => {
    console.log("Cancel order:", orderId);
  };

  const columns: Column<Order>[] = [
    { key: "id", title: "Mã đơn" },
    { key: "customerName", title: "Khách hàng" },
    { key: "service", title: "Dịch vụ" },
    {
      key: "status",
      title: "Trạng thái",
      render: (value: OrderStatus) => (
        <span
          className={`${orderStatuses[value].color} px-2 py-1 rounded-full text-xs font-medium`}
        >
          {orderStatuses[value].label}
        </span>
      ),
    },
    { key: "createdAt", title: "Ngày tạo" },
    {
      key: "actions",
      title: "Thao tác",
      render: (_: any, order: Order) => (
        <div className="flex space-x-2">
          <button
            onClick={() => handleViewOrder(order.id)}
            className="text-blue-600 hover:text-blue-800"
          >
            Chi tiết
          </button>
          <button
            onClick={() => handleCancelOrder(order.id)}
            className="text-red-600 hover:text-red-800"
          >
            Hủy
          </button>
        </div>
      ),
    },
  ];

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        setLoading(true);
        // TODO: Implement API call
        // const response = await axios.get<{ orders: Order[]; totalPages: number }>(
        //   `/api/admin/orders?page=${currentPage}`
        // );
        // setOrders(response.data.orders);
        // setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("Error fetching orders:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchOrders();
  }, [currentPage]);

  return (
    <div className="space-y-6">
      <PageHeader
        title="Quản lý đơn hàng"
        actions={
          <button className="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-primary-600 hover:bg-primary-700">
            <Plus className="h-5 w-5 mr-2" />
            Tạo đơn hàng
          </button>
        }
      />

      {/* Filters */}
      <div className="bg-white p-4 rounded-lg shadow flex items-center space-x-4">
        <div className="flex-1">
          <div className="relative">
            <Search className="h-5 w-5 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2" />
            <input
              type="text"
              placeholder="Tìm kiếm đơn hàng..."
              className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-1 focus:ring-primary-500 focus:border-primary-500"
            />
          </div>
        </div>
        <div className="flex items-center space-x-4">
          <select className="form-select rounded-md border-gray-300 focus:outline-none focus:ring-1 focus:ring-primary-500 focus:border-primary-500">
            <option value="">Trạng thái</option>
            {Object.entries(orderStatuses).map(([value, { label }]) => (
              <option key={value} value={value}>
                {label}
              </option>
            ))}
          </select>
          <button className="inline-flex items-center px-3 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50">
            <Filter className="h-5 w-5 mr-2" />
            Bộ lọc
          </button>
        </div>
      </div>

      {/* Orders Table */}
      <DataTable<Order>
        columns={columns}
        data={orders}
        loading={loading}
        pagination={{
          currentPage,
          totalPages,
          onPageChange: setCurrentPage,
        }}
      />
    </div>
  );
}
