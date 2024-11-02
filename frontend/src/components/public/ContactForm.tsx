// src/components/public/ContactForm.tsx

import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";
import { ContactService } from "../../services/api/contact.service";
import { ContactFormData } from "../../types/contact";
import { Send } from "lucide-react";

const SUBJECTS = [
  { value: "design", label: "Tư vấn thiết kế" },
  { value: "construction", label: "Thi công hồ cá" },
  { value: "maintenance", label: "Bảo trì định kỳ" },
  { value: "other", label: "Khác" },
];

const ContactForm: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<ContactFormData>();

  const onSubmit = async (data: ContactFormData) => {
    try {
      setLoading(true);
      await ContactService.createContact(data);

      toast.success(
        "Cảm ơn bạn đã liên hệ! Chúng tôi sẽ phản hồi trong thời gian sớm nhất.",
        {
          position: "top-right",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
        }
      );

      reset();
    } catch (error) {
      console.error("Error submitting contact form:", error);
      toast.error(
        "Có lỗi xảy ra khi gửi thông tin. Vui lòng thử lại sau hoặc liên hệ trực tiếp với chúng tôi.",
        {
          position: "top-right",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
        }
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div>
          <label
            htmlFor="name"
            className="block text-sm font-medium text-gray-700"
          >
            Họ và tên<span className="text-red-500">*</span>
          </label>
          <input
            id="name"
            type="text"
            {...register("name", {
              required: "Vui lòng nhập họ tên",
              minLength: {
                value: 2,
                message: "Họ tên phải có ít nhất 2 ký tự",
              },
            })}
            className={`mt-1 block w-full rounded-md shadow-sm
              ${
                errors.name
                  ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                  : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
              }`}
          />
          {errors.name && (
            <p className="mt-1 text-sm text-red-600">{errors.name.message}</p>
          )}
        </div>

        <div>
          <label
            htmlFor="email"
            className="block text-sm font-medium text-gray-700"
          >
            Email<span className="text-red-500">*</span>
          </label>
          <input
            id="email"
            type="email"
            {...register("email", {
              required: "Vui lòng nhập email",
              pattern: {
                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                message: "Email không hợp lệ",
              },
            })}
            className={`mt-1 block w-full rounded-md shadow-sm
              ${
                errors.email
                  ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                  : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
              }`}
          />
          {errors.email && (
            <p className="mt-1 text-sm text-red-600">{errors.email.message}</p>
          )}
        </div>
      </div>

      <div>
        <label
          htmlFor="phone"
          className="block text-sm font-medium text-gray-700"
        >
          Số điện thoại<span className="text-red-500">*</span>
        </label>
        <input
          id="phone"
          type="tel"
          {...register("phone", {
            required: "Vui lòng nhập số điện thoại",
            pattern: {
              value: /(84|0[3|5|7|8|9])+([0-9]{8})\b/,
              message: "Số điện thoại không hợp lệ",
            },
          })}
          className={`mt-1 block w-full rounded-md shadow-sm
            ${
              errors.phone
                ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
            }`}
        />
        {errors.phone && (
          <p className="mt-1 text-sm text-red-600">{errors.phone.message}</p>
        )}
      </div>

      <div>
        <label
          htmlFor="subject"
          className="block text-sm font-medium text-gray-700"
        >
          Chủ đề<span className="text-red-500">*</span>
        </label>
        <select
          id="subject"
          {...register("subject", {
            required: "Vui lòng chọn chủ đề",
          })}
          className={`mt-1 block w-full rounded-md shadow-sm
            ${
              errors.subject
                ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
            }`}
        >
          <option value="">Chọn chủ đề</option>
          {SUBJECTS.map((subject) => (
            <option key={subject.value} value={subject.value}>
              {subject.label}
            </option>
          ))}
        </select>
        {errors.subject && (
          <p className="mt-1 text-sm text-red-600">{errors.subject.message}</p>
        )}
      </div>

      <div>
        <label
          htmlFor="message"
          className="block text-sm font-medium text-gray-700"
        >
          Nội dung<span className="text-red-500">*</span>
        </label>
        <textarea
          id="message"
          rows={4}
          {...register("message", {
            required: "Vui lòng nhập nội dung",
            minLength: {
              value: 10,
              message: "Nội dung phải có ít nhất 10 ký tự",
            },
          })}
          className={`mt-1 block w-full rounded-md shadow-sm
            ${
              errors.message
                ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
            }`}
        />
        {errors.message && (
          <p className="mt-1 text-sm text-red-600">{errors.message.message}</p>
        )}
      </div>

      <div>
        <button
          type="submit"
          disabled={loading}
          className="w-full flex justify-center items-center px-6 py-3 border border-transparent rounded-md shadow-sm text-base font-medium text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500 disabled:opacity-50 disabled:cursor-not-allowed"
        >
          {loading ? (
            <div className="w-6 h-6 border-2 border-white border-t-transparent rounded-full animate-spin" />
          ) : (
            <>
              <Send className="w-5 h-5 mr-2" />
              Gửi tin nhắn
            </>
          )}
        </button>
      </div>

      <div className="text-sm text-gray-500">
        <span className="text-red-500">*</span> Thông tin bắt buộc
      </div>
    </form>
  );
};

export default ContactForm;
