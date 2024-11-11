import React, { useState } from "react";
import { Plus } from "lucide-react";
import OrderList from "./OrderList";
import CreateOrderModal from "../../../components/admin/Orders/CreateOrderModal";
import PageHeader from "../../../components/common/PageHeader";

const Orders: React.FC = () => {
  const [showCreateModal, setShowCreateModal] = useState(false);
  const [refreshKey, setRefreshKey] = useState(0);

  const handleCreateSuccess = () => {
    // Trigger a refresh of the order list
    setRefreshKey((prev) => prev + 1);
  };

  return (
    <div className="space-y-6">
      <PageHeader
        title="Quản lý đơn hàng"
        description="Quản lý và theo dõi các đơn đặt hàng"
        actions={
          <button
            className="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-primary-600 hover:bg-primary-700"
            onClick={() => setShowCreateModal(true)}
          >
            <Plus className="h-5 w-5 mr-2" />
            Tạo đơn hàng
          </button>
        }
      />

      {/* Orders List Component */}
      <OrderList key={refreshKey} />

      {/* Create Order Modal */}
      {showCreateModal && (
        <CreateOrderModal
          onClose={() => setShowCreateModal(false)}
          onSuccess={handleCreateSuccess}
        />
      )}
    </div>
  );
};

export default Orders;
