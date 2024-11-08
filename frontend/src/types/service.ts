export interface ServicePrice {
  id?: number;
  name: string;
  description: string;
  price: number;
  features: string[];
  isPopular?: boolean;
}

export type ServiceFormData = Omit<ServicePrice, "id">;
