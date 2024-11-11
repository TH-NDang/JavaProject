// src/pages/admin/Services/ServiceForm.tsx
import React from "react";
import { useForm } from "react-hook-form";
import { ServicePrice } from "../../../types/service";
import { PlusIcon, XIcon } from "lucide-react";

interface ServiceFormProps {
  initialData?: ServicePrice;
  onSubmit: (data: ServicePrice) => void;
  onCancel: () => void;
}

const defaultValues: ServicePrice = {
  name: "",
  description: "",
  price: 0,
  features: [""],
  isPopular: false,
};

const ServiceForm: React.FC<ServiceFormProps> = ({
  initialData,
  onSubmit,
  onCancel,
}) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
    setValue,
  } = useForm<ServicePrice>({
    defaultValues: initialData || defaultValues,
  });

  // Thay useFieldArray bằng cách watch features array trực tiếp
  const features = watch("features", [""]);

  const addFeature = () => {
    setValue("features", [...features, ""]);
  };

  const removeFeature = (index: number) => {
    const newFeatures = features.filter((_, i) => i !== index);
    setValue("features", newFeatures);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
      <div>
        <label className="block text-sm font-medium text-gray-700">
          Tên dịch vụ
        </label>
        <input
          type="text"
          {...register("name", { required: "Tên dịch vụ là bắt buộc" })}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
        />
        {errors.name && (
          <p className="mt-1 text-sm text-red-600">{errors.name.message}</p>
        )}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700">Mô tả</label>
        <textarea
          {...register("description", { required: "Mô tả là bắt buộc" })}
          rows={3}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
        />
        {errors.description && (
          <p className="mt-1 text-sm text-red-600">
            {errors.description.message}
          </p>
        )}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700">Giá</label>
        <div className="mt-1 relative rounded-md shadow-sm">
          <input
            type="number"
            {...register("price", {
              required: "Giá là bắt buộc",
              min: { value: 0, message: "Giá không thể âm" },
            })}
            className="block w-full pr-12 rounded-md border-gray-300"
            placeholder="0"
          />
          <div className="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none">
            <span className="text-gray-500 sm:text-sm">VND</span>
          </div>
        </div>
        {errors.price && (
          <p className="mt-1 text-sm text-red-600">{errors.price.message}</p>
        )}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Tính năng
        </label>
        <div className="space-y-2">
          {features.map((_, index) => (
            <div key={index} className="flex gap-2">
              <input
                {...register(`features.${index}`)}
                className="flex-1 rounded-md border-gray-300 shadow-sm"
                placeholder="Nhập tính năng..."
              />
              {features.length > 1 && (
                <button
                  type="button"
                  onClick={() => removeFeature(index)}
                  className="p-2 text-gray-400 hover:text-red-600"
                >
                  <XIcon className="h-5 w-5" />
                </button>
              )}
            </div>
          ))}
          <button
            type="button"
            onClick={addFeature}
            className="inline-flex items-center px-3 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50"
          >
            <PlusIcon className="h-5 w-5 mr-2" />
            Thêm tính năng
          </button>
        </div>
      </div>

      <div>
        <label className="flex items-center">
          <input
            type="checkbox"
            {...register("isPopular")}
            className="rounded border-gray-300 text-primary-600 shadow-sm"
          />
          <span className="ml-2 text-sm text-gray-600">
            Đánh dấu là gói phổ biến
          </span>
        </label>
      </div>

      <div className="flex justify-end space-x-3">
        <button
          type="button"
          onClick={onCancel}
          className="px-4 py-2 border rounded-md text-gray-600 hover:bg-gray-50"
        >
          Hủy
        </button>
        <button
          type="submit"
          className="px-4 py-2 bg-primary-600 text-white rounded-md hover:bg-primary-700"
        >
          {initialData ? "Cập nhật" : "Tạo mới"}
        </button>
      </div>
    </form>
  );
};

export default ServiceForm;
