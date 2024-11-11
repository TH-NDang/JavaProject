import React from "react";
import { useForm } from "react-hook-form";
import {
  MaintenanceSchedule,
  CompletionReport,
} from "../../../types/maintenance";
import { PlusIcon, XIcon } from "lucide-react";

interface MaintenanceCompletionModalProps {
  schedule: MaintenanceSchedule;
  onSubmit: (data: CompletionReport) => void;
  onClose: () => void;
}

const defaultValues: CompletionReport = {
  waterQuality: {
    pH: 7.0,
    temperature: 25,
    ammonia: 0,
    nitrite: 0,
  },
  filterSystem: {
    status: "GOOD",
    cleaningNeeded: false,
    replacementNeeded: false,
  },
  recommendations: [],
};

const MaintenanceCompletionModal: React.FC<MaintenanceCompletionModalProps> = ({
  schedule,
  onSubmit,
  onClose,
}) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    watch,
  } = useForm<CompletionReport>({
    defaultValues: schedule.completionReport || defaultValues,
  });

  const recommendations = watch("recommendations") || [];

  const addRecommendation = () => {
    setValue("recommendations", [...recommendations, ""]);
  };

  const removeRecommendation = (index: number) => {
    const newRecommendations = recommendations.filter((_, i) => i !== index);
    setValue("recommendations", newRecommendations);
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 max-w-2xl w-full">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-xl font-semibold">Hoàn thành Bảo trì</h2>
          <button
            onClick={onClose}
            className="text-gray-400 hover:text-gray-500"
          >
            <XIcon className="h-6 w-6" />
          </button>
        </div>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
          {/* Water Quality Section */}
          <div>
            <h3 className="text-lg font-medium mb-4">Chất lượng nước</h3>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  pH
                </label>
                <input
                  type="number"
                  step="0.1"
                  {...register("waterQuality.pH", {
                    required: "Yêu cầu nhập pH",
                    min: { value: 0, message: "pH không thể âm" },
                    max: { value: 14, message: "pH không thể vượt quá 14" },
                  })}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                />
                {errors.waterQuality?.pH && (
                  <p className="mt-1 text-sm text-red-600">
                    {errors.waterQuality.pH.message}
                  </p>
                )}
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Nhiệt độ (°C)
                </label>
                <input
                  type="number"
                  step="0.1"
                  {...register("waterQuality.temperature", {
                    required: "Yêu cầu nhập nhiệt độ",
                  })}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Amoniac (ppm)
                </label>
                <input
                  type="number"
                  step="0.01"
                  {...register("waterQuality.ammonia", {
                    required: "Yêu cầu nhập amoniac",
                  })}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Nitrit (ppm)
                </label>
                <input
                  type="number"
                  step="0.01"
                  {...register("waterQuality.nitrite", {
                    required: "Yêu cầu nhập nitrit",
                  })}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                />
              </div>
            </div>
          </div>

          {/* Filter System Section */}
          <div>
            <h3 className="text-lg font-medium mb-4">Hệ thống lọc</h3>
            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Trạng thái
                </label>
                <select
                  {...register("filterSystem.status")}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                >
                  <option value="GOOD">Tốt</option>
                  <option value="FAIR">Trung bình</option>
                  <option value="POOR">Kém</option>
                </select>
              </div>

              <div className="flex items-center space-x-4">
                <label className="flex items-center">
                  <input
                    type="checkbox"
                    {...register("filterSystem.cleaningNeeded")}
                    className="rounded border-gray-300 text-primary-600"
                  />
                  <span className="ml-2 text-sm text-gray-600">Cần vệ sinh</span>
                </label>

                <label className="flex items-center">
                  <input
                    type="checkbox"
                    {...register("filterSystem.replacementNeeded")}
                    className="rounded border-gray-300 text-primary-600"
                  />
                  <span className="ml-2 text-sm text-gray-600">
                    Cần thay thế
                  </span>
                </label>
              </div>
            </div>
          </div>

          {/* Recommendations Section */}
          <div>
            <div className="flex justify-between items-center mb-4">
              <h3 className="text-lg font-medium">Khuyến nghị</h3>
              <button
                type="button"
                onClick={addRecommendation}
                className="inline-flex items-center px-3 py-1 border border-transparent text-sm font-medium rounded-md text-primary-600 bg-primary-100 hover:bg-primary-200"
              >
                <PlusIcon className="h-4 w-4 mr-1" />
                Thêm khuyến nghị
              </button>
            </div>

            <div className="space-y-2">
              {recommendations.map((_, index) => (
                <div key={index} className="flex gap-2">
                  <input
                    {...register(`recommendations.${index}` as const)}
                    className="flex-1 rounded-md border-gray-300 shadow-sm"
                    placeholder="Nhập khuyến nghị..."
                  />
                  <button
                    type="button"
                    onClick={() => removeRecommendation(index)}
                    className="p-2 text-gray-400 hover:text-red-600"
                  >
                    <XIcon className="h-5 w-5" />
                  </button>
                </div>
              ))}
            </div>
          </div>

          <div className="flex justify-end space-x-3 pt-6">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 text-gray-700 hover:text-gray-900"
            >
              Hủy
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-primary-600 text-white rounded-md hover:bg-primary-700"
            >
              Hoàn thành bảo trì
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default MaintenanceCompletionModal;
