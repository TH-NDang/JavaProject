import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { ServiceService } from "../../../services/api/service.service";
import { UserService } from "../../../services/api/user.service";
import { ServicePrice } from "../../../types/service";
import { User } from "../../../types/auth";
import { Toast } from "../../../services/toast.service";

interface CreateOrderFormData {
  customerId: string;
  serviceId: string;
  requirements: string;
  location: string;
  locationNotes?: string;
  preferredStartDate?: string;
}

interface CreateOrderFormProps {
  onSubmit: (data: CreateOrderFormData) => Promise<void>;
  loading?: boolean;
}

const CreateOrderForm: React.FC<CreateOrderFormProps> = ({
  onSubmit,
  loading,
}) => {
  const [services, setServices] = useState<ServicePrice[]>([]);
  const [customers, setCustomers] = useState<User[]>([]);
  const [loadingData, setLoadingData] = useState(true);

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<CreateOrderFormData>();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [servicesData, customersData] = await Promise.all([
          ServiceService.getAllServices(),
          UserService.getAllUsers({ page: 0, size: 100, role: "CUSTOMER" }),
        ]);
        setServices(servicesData);
        setCustomers(customersData.content);
      } catch (error) {
        Toast.error("Không thể tải dữ liệu");
      } finally {
        setLoadingData(false);
      }
    };

    fetchData();
  }, []);

  // Watch for selected service ID and find corresponding service
  const selectedServiceId = watch("serviceId");
  const selectedService = services.find(
    (s) => s.id && s.id.toString() === selectedServiceId
  );

  if (loadingData) {
    return (
      <div className="space-y-4">
        <div className="animate-pulse h-10 bg-gray-200 rounded" />
        <div className="animate-pulse h-10 bg-gray-200 rounded" />
        <div className="animate-pulse h-32 bg-gray-200 rounded" />
      </div>
    );
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
      {/* Customer Selection */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Khách hàng <span className="text-red-500">*</span>
        </label>
        <select
          {...register("customerId", { required: "Vui lòng chọn khách hàng" })}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
          disabled={loading}
        >
          <option value="">Chọn khách hàng</option>
          {customers.map((customer) => (
            <option key={customer.id} value={customer.id}>
              {customer.fullName} ({customer.email})
            </option>
          ))}
        </select>
        {errors.customerId && (
          <p className="mt-1 text-sm text-red-600">
            {errors.customerId.message}
          </p>
        )}
      </div>

      {/* Service Selection */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Dịch vụ <span className="text-red-500">*</span>
        </label>
        <select
          {...register("serviceId", { required: "Vui lòng chọn dịch vụ" })}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
          disabled={loading}
        >
          <option value="">Chọn dịch vụ</option>
          {services.map((service) => (
            <option key={service.id} value={service.id?.toString()}>
              {service.name} -{" "}
              {new Intl.NumberFormat("vi-VN", {
                style: "currency",
                currency: "VND",
              }).format(service.price || 0)}
            </option>
          ))}
        </select>
        {errors.serviceId && (
          <p className="mt-1 text-sm text-red-600">{errors.serviceId.message}</p>
        )}
      </div>

      {/* Requirements */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Yêu cầu cụ thể <span className="text-red-500">*</span>
        </label>
        <textarea
          {...register("requirements", {
            required: "Vui lòng nhập yêu cầu cụ thể",
            minLength: {
              value: 10,
              message: "Yêu cầu phải có ít nhất 10 ký tự",
            },
          })}
          rows={4}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
          placeholder="Nhập yêu cầu cụ thể về dịch vụ..."
          disabled={loading}
        />
        {errors.requirements && (
          <p className="mt-1 text-sm text-red-600">
            {errors.requirements.message}
          </p>
        )}
      </div>

      {/* Location */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Địa điểm <span className="text-red-500">*</span>
        </label>
        <input
          type="text"
          {...register("location", { required: "Vui lòng nhập địa điểm" })}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
          placeholder="Nhập địa chỉ thực hiện dịch vụ"
          disabled={loading}
        />
        {errors.location && (
          <p className="mt-1 text-sm text-red-600">{errors.location.message}</p>
        )}
      </div>

      {/* Location Notes */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Ghi chú về địa điểm
        </label>
        <input
          type="text"
          {...register("locationNotes")}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
          placeholder="Ghi chú thêm về địa điểm (nếu có)"
          disabled={loading}
        />
      </div>

      {/* Preferred Start Date */}
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Ngày mong muốn bắt đầu
        </label>
        <input
          type="date"
          {...register("preferredStartDate")}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
          min={new Date().toISOString().split("T")[0]}
          disabled={loading}
        />
      </div>

      {/* Summary */}
      {selectedService && (
        <div className="rounded-lg bg-gray-50 p-4 border border-gray-200">
          <h4 className="font-medium text-gray-900 mb-2">Tóm tắt đơn hàng</h4>
          <div className="space-y-2 text-sm text-gray-600">
            <p>Dịch vụ: {selectedService.name}</p>
            <p>
              Giá dịch vụ:{" "}
              {new Intl.NumberFormat("vi-VN", {
                style: "currency",
                currency: "VND",
              }).format(selectedService.price || 0)}
            </p>
          </div>
        </div>
      )}

      {/* Submit Button */}
      <div className="flex justify-end">
        <button
          type="submit"
          className="px-4 py-2 bg-primary-600 text-white rounded-md hover:bg-primary-700 disabled:opacity-50"
          disabled={loading}
        >
          {loading ? "Đang xử lý..." : "Tạo đơn hàng"}
        </button>
      </div>
    </form>
  );
};

export default CreateOrderForm;
