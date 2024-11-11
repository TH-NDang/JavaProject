import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { ContactService } from "../../services/contact.service";
import { ContactFormData } from "../../types/contact";
import {
  Send,
  CheckCircle2,
  AlertCircle,
  Loader2,
  ArrowLeft,
} from "lucide-react";

type FormStatus = "idle" | "submitting" | "success" | "error";

const ContactForm: React.FC = () => {
  const [status, setStatus] = useState<FormStatus>("idle");
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<ContactFormData>();

  const onSubmit = async (data: ContactFormData) => {
    try {
      setStatus("submitting");
      await ContactService.createContact(data);
      setStatus("success");
      reset();
    } catch (error) {
      setStatus("error");
    }
  };

  // Success View
  if (status === "success") {
    return (
      <div className="bg-white p-8 rounded-lg shadow-sm border border-green-100">
        <div className="text-center space-y-4">
          <div className="h-12 w-12 bg-green-100 text-green-600 rounded-full mx-auto flex items-center justify-center">
            <CheckCircle2 className="h-6 w-6" />
          </div>
          <h3 className="text-xl font-semibold text-gray-900">
            Yêu cầu đã được gửi thành công!
          </h3>
          <p className="text-gray-600 max-w-sm mx-auto">
            Cảm ơn bạn đã liên hệ. Chúng tôi sẽ phản hồi trong thời gian sớm
            nhất.
          </p>
          <button
            onClick={() => setStatus("idle")}
            className="inline-flex items-center text-sm font-medium text-green-600 hover:text-green-500"
          >
            <ArrowLeft className="h-4 w-4 mr-1" />
            Gửi yêu cầu khác
          </button>
        </div>
      </div>
    );
  }

  // Error View
  if (status === "error") {
    return (
      <div className="bg-white p-8 rounded-lg shadow-sm border border-red-100">
        <div className="text-center space-y-4">
          <div className="h-12 w-12 bg-red-100 text-red-600 rounded-full mx-auto flex items-center justify-center">
            <AlertCircle className="h-6 w-6" />
          </div>
          <h3 className="text-xl font-semibold text-gray-900">
            Không thể gửi yêu cầu
          </h3>
          <p className="text-gray-600 max-w-sm mx-auto">
            Đã có lỗi xảy ra. Vui lòng thử lại hoặc liên hệ trực tiếp với chúng
            tôi.
          </p>
          <div className="space-x-4">
            <button
              onClick={() => setStatus("idle")}
              className="inline-flex items-center text-sm font-medium text-red-600 hover:text-red-500"
            >
              Thử lại
            </button>
          </div>
        </div>
      </div>
    );
  }

  // Form View
  return (
    <div className="relative">
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
        {/* Name field */}
        <div>
          <label className="block text-sm font-medium text-gray-700">
            Họ và tên <span className="text-red-500">*</span>
          </label>
          <div className="mt-1">
            <input
              {...register("name", {
                required: "Vui lòng nhập họ tên",
                minLength: { value: 2, message: "Tên quá ngắn" },
              })}
              className={`block w-full px-4 py-3 rounded-md shadow-sm text-gray-900 border
                ${
                  errors.name
                    ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                    : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
                }`}
              disabled={status === "submitting"}
            />
            {errors.name && (
              <p className="mt-1 text-sm text-red-600">{errors.name.message}</p>
            )}
          </div>
        </div>

        {/* Email field */}
        <div>
          <label className="block text-sm font-medium text-gray-700">
            Email <span className="text-red-500">*</span>
          </label>
          <div className="mt-1">
            <input
              type="email"
              {...register("email", {
                required: "Vui lòng nhập email",
                pattern: {
                  value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                  message: "Email không hợp lệ",
                },
              })}
              className={`block w-full px-4 py-3 rounded-md shadow-sm text-gray-900 border
                ${
                  errors.email
                    ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                    : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
                }`}
              disabled={status === "submitting"}
            />
            {errors.email && (
              <p className="mt-1 text-sm text-red-600">{errors.email.message}</p>
            )}
          </div>
        </div>

        {/* Phone field */}
        <div>
          <label className="block text-sm font-medium text-gray-700">
            Số điện thoại <span className="text-red-500">*</span>
          </label>
          <div className="mt-1">
            <input
              type="tel"
              {...register("phone", {
                required: "Vui lòng nhập số điện thoại",
                pattern: {
                  value: /^[0-9]{10,11}$/,
                  message: "Số điện thoại không hợp lệ",
                },
              })}
              className={`block w-full px-4 py-3 rounded-md shadow-sm text-gray-900 border
                ${
                  errors.phone
                    ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                    : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
                }`}
              disabled={status === "submitting"}
            />
            {errors.phone && (
              <p className="mt-1 text-sm text-red-600">{errors.phone.message}</p>
            )}
          </div>
        </div>

        {/* Subject field */}
        <div>
          <label className="block text-sm font-medium text-gray-700">
            Chủ đề <span className="text-red-500">*</span>
          </label>
          <div className="mt-1">
            <select
              {...register("subject", { required: "Vui lòng chọn chủ đề" })}
              className={`block w-full px-4 py-3 rounded-md shadow-sm text-gray-900 border
                ${
                  errors.subject
                    ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                    : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
                }`}
              disabled={status === "submitting"}
            >
              <option value="">Chọn chủ đề</option>
              <option value="design">Tư vấn thiết kế</option>
              <option value="construction">Thi công hồ cá</option>
              <option value="maintenance">Bảo trì định kỳ</option>
              <option value="other">Khác</option>
            </select>
            {errors.subject && (
              <p className="mt-1 text-sm text-red-600">
                {errors.subject.message}
              </p>
            )}
          </div>
        </div>

        {/* Message field */}
        <div>
          <label className="block text-sm font-medium text-gray-700">
            Nội dung <span className="text-red-500">*</span>
          </label>
          <div className="mt-1">
            <textarea
              {...register("message", {
                required: "Vui lòng nhập nội dung",
                minLength: { value: 10, message: "Nội dung quá ngắn" },
              })}
              rows={4}
              className={`block w-full px-4 py-3 rounded-md shadow-sm text-gray-900 border
                ${
                  errors.message
                    ? "border-red-300 focus:border-red-500 focus:ring-red-500"
                    : "border-gray-300 focus:border-primary-500 focus:ring-primary-500"
                }`}
              disabled={status === "submitting"}
            />
            {errors.message && (
              <p className="mt-1 text-sm text-red-600">
                {errors.message.message}
              </p>
            )}
          </div>
        </div>

        {/* Submit Button */}
        <button
          type="submit"
          disabled={status === "submitting"}
          className="w-full inline-flex items-center justify-center px-6 py-3 border border-transparent 
            rounded-md shadow-sm text-base font-medium text-white bg-primary-600 hover:bg-primary-700 
            focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500 
            disabled:opacity-50 disabled:cursor-not-allowed"
        >
          {status === "submitting" ? (
            <>
              <Loader2 className="w-5 h-5 mr-2 animate-spin" />
              Đang gửi...
            </>
          ) : (
            <>
              <Send className="w-5 h-5 mr-2" />
              Gửi tin nhắn
            </>
          )}
        </button>

        <p className="text-sm text-gray-500">
          <span className="text-red-500">*</span> Thông tin bắt buộc
        </p>
      </form>
    </div>
  );
};

export default ContactForm;
