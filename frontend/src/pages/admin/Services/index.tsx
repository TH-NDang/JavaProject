import React, { useState, useEffect } from "react";
import { PlusIcon } from "lucide-react";
import { ServicePrice } from "../../../types/service";
import { ServiceService } from "../../../services/service.service";
import { Toast } from "../../../services/toast.service";
import ServicePriceCard from "./ServicePriceCard";
import ServiceFormModal from "./ServiceFormModal";
import PageHeader from "../../../components/common/PageHeader";

export default function ServicesManagement() {
  const [services, setServices] = useState<ServicePrice[]>([]);
  const [loading, setLoading] = useState(true);
  const [selectedService, setSelectedService] = useState<ServicePrice | null>(
    null
  );
  const [showFormModal, setShowFormModal] = useState(false);

  const fetchServices = async () => {
    try {
      setLoading(true);
      const data = await ServiceService.getAllServices();
      setServices(data);
    } catch (error) {
      Toast.error("Không thể tải danh sách dịch vụ");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchServices();
  }, []);

  const handleCreate = async (data: ServicePrice) => {
    try {
      await ServiceService.createService(data);
      Toast.success("Tạo dịch vụ thành công");
      fetchServices();
      setShowFormModal(false);
    } catch (error) {
      Toast.error("Không thể tạo dịch vụ");
    }
  };

  const handleUpdate = async (data: ServicePrice) => {
    try {
      if (!selectedService?.id) {
        throw new Error("Service ID is missing");
      }
      await ServiceService.updateService(selectedService.id, data);
      Toast.success("Cập nhật dịch vụ thành công");
      fetchServices();
      setSelectedService(null);
      setShowFormModal(false);
    } catch (error) {
      Toast.error("Không thể cập nhật dịch vụ");
    }
  };

  const handleDelete = async (id: number) => {
    if (!window.confirm("Bạn có chắc chắn muốn xóa dịch vụ này?")) {
      return;
    }

    try {
      await ServiceService.deleteService(id);
      Toast.success("Xóa dịch vụ thành công");
      fetchServices();
    } catch (error) {
      Toast.error("Không thể xóa dịch vụ");
    }
  };

  const handleTogglePopular = async (id: number, isPopular: boolean) => {
    try {
      await ServiceService.updateService(id, { isPopular });
      Toast.success("Cập nhật thành công");
      fetchServices();
    } catch (error) {
      Toast.error("Không thể cập nhật trạng thái");
    }
  };

  return (
    <div className="space-y-6">
      <PageHeader
        title="Quản lý Dịch vụ"
        description="Quản lý các gói dịch vụ và bảng giá"
        actions={
          <button
            onClick={() => setShowFormModal(true)}
            className="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-primary-600 hover:bg-primary-700"
          >
            <PlusIcon className="h-5 w-5 mr-2" />
            Thêm Dịch vụ
          </button>
        }
      />

      {loading ? (
        <div className="text-center py-12">
          <div className="inline-block animate-spin rounded-full h-8 w-8 border-b-2 border-primary-600" />
        </div>
      ) : (
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {services.map((service) => (
            <ServicePriceCard
              key={service.id}
              service={service}
              onEdit={() => {
                setSelectedService(service);
                setShowFormModal(true);
              }}
              onDelete={handleDelete}
              onTogglePopular={handleTogglePopular}
            />
          ))}
        </div>
      )}

      {showFormModal && (
        <ServiceFormModal
          service={selectedService || undefined}
          onSubmit={selectedService ? handleUpdate : handleCreate}
          onClose={() => {
            setShowFormModal(false);
            setSelectedService(null);
          }}
        />
      )}
    </div>
  );
}
