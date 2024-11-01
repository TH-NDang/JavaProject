// src/components/modals/UpdateUserModal.tsx
import React from "react";
import { useForm } from "react-hook-form";
import { User, UpdateUserRequest } from "../../types/user";

interface UpdateUserModalProps {
  user: User;
  onClose: () => void;
  onSubmit: (data: UpdateUserRequest) => void;
}

export const UpdateUserModal: React.FC<UpdateUserModalProps> = ({
  user,
  onClose,
  onSubmit,
}) => {
  const { register, handleSubmit } = useForm<UpdateUserRequest>({
    defaultValues: {
      email: user.email,
      fullName: user.fullName,
      phone: user.phone,
      address: user.address,
    },
  });

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
      <div className="bg-white rounded-lg max-w-md w-full p-6">
        <h2 className="text-xl font-bold mb-4">Cập nhật người dùng</h2>
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Email
            </label>
            <input
              type="email"
              {...register("email")}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Họ và tên
            </label>
            <input
              type="text"
              {...register("fullName")}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Số điện thoại
            </label>
            <input
              type="text"
              {...register("phone")}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Địa chỉ
            </label>
            <input
              type="text"
              {...register("address")}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            />
          </div>

          <div className="flex justify-end space-x-3 mt-6">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 border rounded-md text-gray-600 hover:bg-gray-50"
            >
              Hủy
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-primary-600 text-white rounded-md hover:bg-primary-700"
            >
              Cập nhật
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
