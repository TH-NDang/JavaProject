import React, { useState } from "react";
import { X } from "lucide-react";
import { Contact, ContactStatus, CONTACT_STATUS } from "../../types/contact";
import { ContactService } from "../../services/api/contact.service";
import { Toast } from "../../services/toast.service";

interface ContactDetailModalProps {
  contact: Contact;
  onClose: () => void;
  onUpdate: () => void;
}

export const ContactDetailModal: React.FC<ContactDetailModalProps> = ({
  contact,
  onClose,
  onUpdate,
}) => {
  const [notes, setNotes] = useState(contact.notes || "");
  const [status, setStatus] = useState(contact.status);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setLoading(true);
      await ContactService.updateContact(contact.id, {
        status,
        notes,
      });
      Toast.success("Cập nhật thông tin liên hệ thành công");
      onUpdate();
      onClose();
    } catch (error) {
      console.error("Error updating contact:", error);
      Toast.error("Không thể cập nhật thông tin liên hệ");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg max-w-3xl w-full max-h-[90vh] overflow-y-auto">
        <div className="p-6">
          <div className="flex justify-between items-start mb-6">
            <h2 className="text-2xl font-bold">Chi tiết liên hệ</h2>
            <button
              onClick={onClose}
              className="text-gray-400 hover:text-gray-500"
            >
              <X className="h-6 w-6" />
            </button>
          </div>

          <div className="space-y-6">
            {/* Thông tin khách hàng */}
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Tên khách hàng
                </label>
                <p className="mt-1 text-gray-900">{contact.name}</p>
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Email
                </label>
                <p className="mt-1 text-gray-900">{contact.email}</p>
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Số điện thoại
                </label>
                <p className="mt-1 text-gray-900">{contact.phone}</p>
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Chủ đề
                </label>
                <p className="mt-1 text-gray-900">{contact.subject}</p>
              </div>
            </div>

            {/* Nội dung tin nhắn */}
            <div>
              <label className="block text-sm font-medium text-gray-700">
                Nội dung tin nhắn
              </label>
              <div className="mt-1 p-4 bg-gray-50 rounded-md">
                <p className="text-gray-900 whitespace-pre-wrap">
                  {contact.message}
                </p>
              </div>
            </div>

            <form onSubmit={handleSubmit} className="space-y-4">
              {/* Trạng thái */}
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Trạng thái
                </label>
                <select
                  value={status}
                  onChange={(e) => setStatus(e.target.value as ContactStatus)}
                  className="mt-1 block w-full rounded-md border-gray-300"
                >
                  {Object.entries(CONTACT_STATUS).map(([key, { label }]) => (
                    <option key={key} value={key}>
                      {label}
                    </option>
                  ))}
                </select>
              </div>

              {/* Ghi chú */}
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Ghi chú xử lý
                </label>
                <textarea
                  value={notes}
                  onChange={(e) => setNotes(e.target.value)}
                  rows={4}
                  className="mt-1 block w-full rounded-md border-gray-300"
                  placeholder="Nhập ghi chú xử lý..."
                />
              </div>

              {/* Buttons */}
              <div className="flex justify-end space-x-3">
                <button
                  type="button"
                  onClick={onClose}
                  className="px-4 py-2 border rounded-md text-gray-600 hover:bg-gray-50"
                >
                  Đóng
                </button>
                <button
                  type="submit"
                  disabled={loading}
                  className="px-4 py-2 bg-primary-600 text-white rounded-md hover:bg-primary-700 disabled:opacity-50"
                >
                  {loading ? "Đang xử lý..." : "Cập nhật"}
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
