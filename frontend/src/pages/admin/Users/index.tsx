import React, { useState, useEffect } from "react";
import { UserService } from "../../../services/api/user.service";
import { User, CreateUserRequest, UpdateUserRequest } from "../../../types/user";
import { UserPlus, Search, Filter } from "lucide-react";
import DataTable from "../../../components/common/DataTable";
import { toast } from "react-toastify";
import { CreateUserModal } from "../../../components/modals/CreateUserModal";
import { UpdateUserModal } from "../../../components/modals/UpdateUserModal";
import { ApiError } from "../../../types/error";
import { Column } from "../../../types/column";

export default function AdminUsers() {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [searchTerm, setSearchTerm] = useState("");
  const [roleFilter, setRoleFilter] = useState("");
  const [statusFilter, setStatusFilter] = useState("");
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [isUpdateModalOpen, setIsUpdateModalOpen] = useState(false);
  const [selectedUser, setSelectedUser] = useState<User | null>(null);

  const fetchUsers = async () => {
    try {
      setLoading(true);
      const response = await UserService.getAllUsers({
        page: currentPage,
        size: 10,
        search: searchTerm,
        role: roleFilter,
        status: statusFilter,
      });

      setUsers(response.content);
      setTotalPages(response.totalPages);
    } catch (error) {
      console.error("Error fetching users:", error);
      toast.error("Failed to fetch users");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, [currentPage, searchTerm, roleFilter, statusFilter]);

  const handleCreateUser = async (data: CreateUserRequest) => {
    try {
      await UserService.createUser(data);
      toast.success("User created successfully");
      fetchUsers();
      setIsCreateModalOpen(false);
    } catch (error) {
      console.error("Error creating user:", error);
      toast.error("Failed to create user");
    }
  };

  const handleUpdateUser = async (id: number, data: UpdateUserRequest) => {
    try {
      await UserService.updateUser(id, data);
      toast.success("User updated successfully");
      fetchUsers();
      setIsUpdateModalOpen(false);
      setSelectedUser(null);
    } catch (error) {
      console.error("Error updating user:", error);
      toast.error("Failed to update user");
    }
  };

  const handleUpdateStatus = async (id: number, status: string) => {
    try {
      await UserService.updateUserStatus(id, status);
      toast.success("User status updated successfully");
      fetchUsers();
    } catch (error) {
      console.error("Error updating user status:", error);
      toast.error("Failed to update user status");
    }
  };

  const handleDeleteUser = async (id: number) => {
    try {
      await UserService.deleteUser(id);
      toast.success("User deleted successfully");
      fetchUsers();
    } catch (err) {
      const error = err as ApiError;
      console.error("Error deleting user:", error);
      if (error.response?.status === 400) {
        toast.error(error.response.data.message || "Cannot delete this user");
      } else {
        toast.error("Failed to delete user");
      }
    }
  };

  const columns: Column<User>[] = [
    {
      key: "fullName",
      title: "Họ và tên",
    },
    {
      key: "email",
      title: "Email",
    },
    {
      key: "role",
      title: "Vai trò",
      render: (value: string) => (
        <span className="px-2 py-1 text-xs font-medium rounded-full bg-blue-100 text-blue-800">
          {value === "CUSTOMER"
            ? "Khách hàng"
            : value === "STAFF"
            ? "Nhân viên"
            : "Quản trị viên"}
        </span>
      ),
    },
    {
      key: "status",
      title: "Trạng thái",
      render: (value: string) => (
        <span
          className={`px-2 py-1 text-xs font-medium rounded-full ${
            value === "ACTIVE"
              ? "bg-green-100 text-green-800"
              : "bg-red-100 text-red-800"
          }`}
        >
          {value === "ACTIVE" ? "Hoạt động" : "Không hoạt động"}
        </span>
      ),
    },
    {
      key: "actions",
      title: "Thao tác",
      render: (_, user: User) => (
        <div className="flex space-x-2">
          <button
            onClick={() => {
              setSelectedUser(user);
              setIsUpdateModalOpen(true);
            }}
            className="text-blue-600 hover:text-blue-800"
          >
            Sửa
          </button>
          <button
            onClick={() =>
              handleUpdateStatus(
                user.id,
                user.status === "ACTIVE" ? "INACTIVE" : "ACTIVE"
              )
            }
            className="text-orange-600 hover:text-orange-800"
          >
            {user.status === "ACTIVE" ? "Vô hiệu hóa" : "Kích hoạt"}
          </button>
          <button
            onClick={() => handleDeleteUser(user.id)}
            className="text-red-600 hover:text-red-800"
          >
            Xóa
          </button>
        </div>
      ),
    },
  ];

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h2 className="text-2xl font-bold">Quản lý người dùng</h2>
        <button
          onClick={() => setIsCreateModalOpen(true)}
          className="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-primary-600 hover:bg-primary-700"
        >
          <UserPlus className="h-5 w-5 mr-2" />
          Thêm người dùng
        </button>
      </div>

      {/* Search and Filter */}
      <div className="flex gap-4 bg-white p-4 rounded-lg shadow">
        <div className="flex-1">
          <div className="relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" />
            <input
              type="text"
              placeholder="Tìm kiếm..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="pl-10 pr-4 py-2 w-full border rounded-md"
            />
          </div>
        </div>
        <select
          value={roleFilter}
          onChange={(e) => setRoleFilter(e.target.value)}
          className="border rounded-md px-3"
        >
          <option value="">Tất cả vai trò</option>
          <option value="ADMIN">Quản trị viên</option>
          <option value="STAFF">Nhân viên</option>
          <option value="CUSTOMER">Khách hàng</option>
        </select>
        <select
          value={statusFilter}
          onChange={(e) => setStatusFilter(e.target.value)}
          className="border rounded-md px-3"
        >
          <option value="">Tất cả trạng thái</option>
          <option value="ACTIVE">Hoạt động</option>
          <option value="INACTIVE">Không hoạt động</option>
        </select>
      </div>

      {/* Users Table */}
      <DataTable
        columns={columns}
        data={users}
        loading={loading}
        pagination={{
          currentPage: currentPage + 1,
          totalPages,
          onPageChange: (page) => setCurrentPage(page - 1),
        }}
      />

      {/* Create User Modal */}
      {isCreateModalOpen && (
        <CreateUserModal
          onClose={() => setIsCreateModalOpen(false)}
          onSubmit={handleCreateUser}
        />
      )}

      {/* Update User Modal */}
      {isUpdateModalOpen && selectedUser && (
        <UpdateUserModal
          user={selectedUser}
          onClose={() => {
            setIsUpdateModalOpen(false);
            setSelectedUser(null);
          }}
          onSubmit={(data) => handleUpdateUser(selectedUser.id, data)}
        />
      )}
    </div>
  );
}
