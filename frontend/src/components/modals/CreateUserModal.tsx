import React from "react";
import { useForm } from "react-hook-form";
import { CreateUserRequest } from "../../types/user";

interface CreateUserModalProps {
  onClose: () => void;
  onSubmit: (data: CreateUserRequest) => void;
}

export const CreateUserModal: React.FC<CreateUserModalProps> = ({
  onClose,
  onSubmit,
}) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CreateUserRequest>();

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
      <div className="bg-white rounded-lg max-w-md w-full p-6">
        <h2 className="text-xl font-bold mb-4">Thêm người dùng mới</h2>
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Email
            </label>
            <input
              type="email"
              {...register("email", { required: "Email is required" })}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            />
            {errors.email && (
              <p className="mt-1 text-sm text-red-600">{errors.email.message}</p>
            )}
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Mật khẩu
            </label>
            <input
              type="password"
              {...register("password", { required: "Password is required" })}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            />
            {errors.password && (
              <p className="mt-1 text-sm text-red-600">
                {errors.password.message}
              </p>
            )}
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Họ và tên
            </label>
            <input
              type="text"
              {...register("fullName", { required: "Full name is required" })}
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

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Vai trò
            </label>
            <select
              {...register("role", { required: "Role is required" })}
              className="mt-1 block w-full rounded-md border-gray-300 shadow-sm"
            >
              <option value="CUSTOMER">Khách hàng</option>
              <option value="STAFF">Nhân viên</option>
              <option value="ADMIN">Quản trị viên</option>
            </select>
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
              Thêm
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
