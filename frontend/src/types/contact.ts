export type ContactStatus = 'PENDING' | 'PROCESSING' | 'COMPLETED' | 'REJECTED';

export interface ContactFormData {
  name: string;
  email: string;
  phone: string;
  subject: string;
  message: string;
}

export interface Contact extends ContactFormData {
  id: number;
  status: ContactStatus;
  createdAt: string;
  updatedAt: string;
  notes?: string;
}

export interface CreateContactRequest extends ContactFormData {}

export interface UpdateContactRequest {
  status: ContactStatus;
  notes?: string;
}

export interface ContactResponse {
  content: Contact[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
}

export const CONTACT_STATUS: Record<ContactStatus, {label: string, color: string}> = {
  PENDING: {
    label: 'Chờ xử lý',
    color: 'bg-yellow-100 text-yellow-800'
  },
  PROCESSING: {
    label: 'Đang xử lý', 
    color: 'bg-blue-100 text-blue-800'
  },
  COMPLETED: {
    label: 'Đã hoàn thành',
    color: 'bg-green-100 text-green-800'
  },
  REJECTED: {
    label: 'Từ chối',
    color: 'bg-red-100 text-red-800'
  }
};
