// src/pages/admin/Contacts/index.tsx

import React, { useState, useEffect } from "react";
import { Search, Filter, Eye, Download } from "lucide-react";
import { Toast } from "../../../services/toast.service";
import { ContactService } from "../../../services/api/contact.service";
import { Contact, ContactStatus, CONTACT_STATUS } from "../../../types/contact";
import DataTable from "../../../components/common/DataTable";
import PageHeader from "../../../components/common/PageHeader";
import { Column } from "../../../types/column";
import { ContactDetailModal } from "../../../components/modals/ContactDetailModal";

export default function AdminContacts() {
  const [contacts, setContacts] = useState<Contact[]>([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [searchTerm, setSearchTerm] = useState("");
  const [statusFilter, setStatusFilter] = useState<string>("");
  const [selectedContact, setSelectedContact] = useState<Contact | null>(null);

  const fetchContacts = async () => {
    try {
      setLoading(true);
      const response = await ContactService.getAllContacts({
        page: currentPage,
        size: 10,
        search: searchTerm,
        status: statusFilter,
      });

      setContacts(response.content);
      setTotalPages(response.totalPages);
    } catch (error) {
      console.error("Error fetching contacts:", error);
      Toast.error("Không thể tải danh sách liên hệ");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchContacts();
  }, [currentPage, searchTerm, statusFilter]);

  const handleExportExcel = () => {
    // TODO: Implement export to Excel functionality
    Toast.info("Tính năng đang được phát triển");
  };

  const columns: Column<Contact>[] = [
    {
      key: "name",
      title: "Tên khách hàng",
    },
    {
      key: "email",
      title: "Email",
    },
    {
      key: "phone",
      title: "Số điện thoại",
    },
    {
      key: "subject",
      title: "Chủ đề",
      render: (value: string) => {
        const subjectMap: Record<string, string> = {
          design: "Tư vấn thiết kế",
          construction: "Thi công hồ cá",
          maintenance: "Bảo trì định kỳ",
          other: "Khác",
        };
        return subjectMap[value] || value;
      },
    },
    {
      key: "status",
      title: "Trạng thái",
      render: (value: ContactStatus) => (
        <span
          className={`px-2 py-1 text-xs font-medium rounded-full ${CONTACT_STATUS[value].color}`}
        >
          {CONTACT_STATUS[value].label}
        </span>
      ),
    },
    {
      key: "createdAt",
      title: "Ngày tạo",
      render: (value: string) => new Date(value).toLocaleDateString("vi-VN"),
    },
    {
      key: "actions",
      title: "Thao tác",
      render: (_, contact: Contact) => (
        <div className="flex space-x-2">
          <button
            onClick={() => setSelectedContact(contact)}
            className="p-2 text-blue-600 hover:text-blue-800"
          >
            <Eye className="h-5 w-5" />
          </button>
        </div>
      ),
    },
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Quản lý liên hệ"
        actions={
          <button
            onClick={handleExportExcel}
            className="inline-flex items-center px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-primary-600 hover:bg-primary-700"
          >
            <Download className="h-5 w-5 mr-2" />
            Xuất Excel
          </button>
        }
      />

      {/* Quick Stats */}
      <div className="grid grid-cols-4 gap-4">
        {Object.entries(CONTACT_STATUS).map(([key, { label, color }]) => {
          const count = contacts.filter((c) => c.status === key).length;
          return (
            <div key={key} className="bg-white rounded-lg shadow p-4">
              <div className="flex items-center justify-between">
                <span
                  className={`${color} px-2 py-1 rounded-full text-xs font-medium`}
                >
                  {label}
                </span>
                <span className="text-2xl font-bold">{count}</span>
              </div>
            </div>
          );
        })}
      </div>

      {/* Search and Filter */}
      <div className="flex gap-4 bg-white p-4 rounded-lg shadow">
        <div className="flex-1">
          <div className="relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" />
            <input
              type="text"
              placeholder="Tìm kiếm theo tên, email, số điện thoại..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="pl-10 pr-4 py-2 w-full border rounded-md"
            />
          </div>
        </div>
        <select
          value={statusFilter}
          onChange={(e) => setStatusFilter(e.target.value)}
          className="border rounded-md px-3"
        >
          <option value="">Tất cả trạng thái</option>
          {Object.entries(CONTACT_STATUS).map(([key, { label }]) => (
            <option key={key} value={key}>
              {label}
            </option>
          ))}
        </select>
      </div>

      {/* Contacts Table */}
      <DataTable
        columns={columns}
        data={contacts}
        loading={loading}
        pagination={{
          currentPage: currentPage + 1,
          totalPages,
          onPageChange: (page) => setCurrentPage(page - 1),
        }}
      />

      {/* Contact Detail Modal */}
      {selectedContact && (
        <ContactDetailModal
          contact={selectedContact}
          onClose={() => setSelectedContact(null)}
          onUpdate={fetchContacts}
        />
      )}
    </div>
  );
}
