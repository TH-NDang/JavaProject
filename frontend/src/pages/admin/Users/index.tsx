import { useState, useEffect } from "react";
import {
  UserPlus,
  Search,
  Filter,
  Edit2,
  Trash2,
  MoreHorizontal,
} from "lucide-react";
import DataTable, { Column } from "../../../components/common/DataTable";

interface User {
  id: number;
  fullName: string;
  email: string;
  role: string;
  status: "ACTIVE" | "INACTIVE" | "PENDING";
  joinedDate: string;
}

const userStatuses = {
  ACTIVE: { label: "Hoạt động", color: "bg-green-100 text-green-800" },
  INACTIVE: { label: "Không hoạt động", color: "bg-red-100 text-red-800" },
  PENDING: { label: "Chờ xác nhận", color: "bg-yellow-100 text-yellow-800" },
} as const;

export default function AdminUsers() {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [searchTerm, setSearchTerm] = useState("");
  const [roleFilter, setRoleFilter] = useState("");
  const [statusFilter, setStatusFilter] = useState("");

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const mockUsers: User[] = [
          {
            id: 1,
            fullName: "Nguyễn Văn A",
            email: "nguyenvana@example.com",
            role: "CUSTOMER",
            status: "ACTIVE",
            joinedDate: "2024-01-15",
          },
          {
            id: 2,
            fullName: "Trần Thị B",
            email: "tranthib@example.com",
            role: "STAFF",
            status: "ACTIVE",
            joinedDate: "2024-02-01",
          },
        ];

        setUsers(mockUsers);
        setTotalPages(3);
      } catch (error) {
        console.error("Error fetching users:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchUsers();
  }, [currentPage, searchTerm, roleFilter, statusFilter]);

  const handleEditUser = (userId: number) => {
    console.log("Edit user:", userId);
  };

  const handleDeleteUser = (userId: number) => {
    console.log("Delete user:", userId);
  };

  const columns: Column<User>[] = [
    { key: "id", title: "ID" },
    { key: "fullName", title: "Họ và tên" },
    { key: "email", title: "Email" },
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
      render: (value: keyof typeof userStatuses) => (
        <span
          className={`px-2 py-1 text-xs font-medium rounded-full ${userStatuses[value].color}`}
        >
          {userStatuses[value].label}
        </span>
      ),
    },
    { key: "joinedDate", title: "Ngày tham gia" },
    {
      key: "actions",
      title: "Thao tác",
      render: (_, user: User) => (
        <div className="flex space-x-2">
          <button
            onClick={() => handleEditUser(user.id)}
            className="p-1 text-blue-600 hover:text-blue-800"
          >
            <Edit2 className="h-4 w-4" />
          </button>
          <button
            onClick={() => handleDeleteUser(user.id)}
            className="p-1 text-red-600 hover:text-red-800"
          >
            <Trash2 className="h-4 w-4" />
          </button>
          <button className="p-1 text-gray-600 hover:text-gray-800">
            <MoreHorizontal className="h-4 w-4" />
          </button>
        </div>
      ),
    },
  ];

  return (
    <div className="space-y-6">
      {/* Rest of the component remains the same */}
      <DataTable<User>
        columns={columns}
        data={users}
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
