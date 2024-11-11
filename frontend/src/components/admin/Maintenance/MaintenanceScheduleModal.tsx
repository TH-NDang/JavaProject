import React from "react";
import { useForm } from "react-hook-form";
import { MaintenanceSchedule } from "../../../types/maintenance";

interface MaintenanceScheduleModalProps {
  schedule?: Partial<MaintenanceSchedule>;
  onSubmit: (data: Partial<MaintenanceSchedule>) => void;
  onClose: () => void;
  availableStaff: Array<{ id: number; name: string }>;
}

const MaintenanceScheduleModal: React.FC<MaintenanceScheduleModalProps> = ({
  schedule,
  onSubmit,
  onClose,
  availableStaff,
}) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<MaintenanceSchedule>({
    defaultValues: schedule,
  });

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 max-w-lg w-full">
        <h2 className="text-xl font-semibold mb-4">
          {schedule?.id ? "Edit Schedule" : "New Schedule"}
        </h2>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          {!schedule?.id && (
            <>
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Customer
                </label>
                <select
                  {...register("customerId", {
                    required: "Customer is required",
                  })}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                >
                  <option value="">Select customer</option>
                  {/* Add customer options here */}
                </select>
                {errors.customerId && (
                  <p className="mt-1 text-sm text-red-600">
                    {errors.customerId.message}
                  </p>
                )}
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Pond
                </label>
                <select
                  {...register("pondId", { required: "Pond is required" })}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
                >
                  <option value="">Select pond</option>
                  {/* Add pond options here */}
                </select>
                {errors.pondId && (
                  <p className="mt-1 text-sm text-red-600">
                    {errors.pondId.message}
                  </p>
                )}
              </div>
            </>
          )}

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Assigned Staff
            </label>
            <select
              {...register("assignedStaffId")}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            >
              <option value="">Select staff</option>
              {availableStaff.map((staff) => (
                <option key={staff.id} value={staff.id}>
                  {staff.name}
                </option>
              ))}
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Notes
            </label>
            <textarea
              {...register("notes")}
              rows={3}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            />
          </div>

          <div className="flex justify-end space-x-3 mt-6">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 text-gray-700 hover:text-gray-900"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-primary-600 text-white rounded-md hover:bg-primary-700"
            >
              {schedule?.id ? "Update" : "Create"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default MaintenanceScheduleModal;
