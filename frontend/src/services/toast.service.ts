import { toast, ToasterProps } from "sonner";

const defaultConfig = {
  duration: 2000,
  position: "top-center" as ToasterProps["position"],
};

export const Toast = {
  success: (message: string, showToast = true) => {
    if (!showToast) return;
    toast.success(message, defaultConfig);
  },

  error: (message: string, showToast = true) => {
    if (!showToast) return;
    toast.error(message, defaultConfig);
  },

  warning: (message: string, showToast = true) => {
    if (!showToast) return;
    toast.warning(message, defaultConfig);
  },

  info: (message: string, showToast = true) => {
    if (!showToast) return;
    toast.info(message, defaultConfig);
  },

  // Phương thức dismiss để tắt toast nếu cần
  dismiss: (toastId?: string) => {
    toast.dismiss(toastId);
  },
};
