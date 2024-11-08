import { ServicePrice } from "../../../types/service";

export const mockServices: ServicePrice[] = [
  {
    id: 1,
    name: "Gói Cơ Bản",
    description:
      "Thiết kế và thi công hồ cá Koi cơ bản với đầy đủ tính năng thiết yếu.",
    price: 15000000,
    features: [
      "Thiết kế 2D",
      "Thi công cơ bản",
      "Bảo hành 1 năm",
      "Tư vấn kỹ thuật",
    ],
    isPopular: false,
  },
  {
    id: 2,
    name: "Gói Tiêu Chuẩn",
    description:
      "Giải pháp hoàn hảo cho những ai muốn một hồ cá Koi chuyên nghiệp.",
    price: 25000000,
    features: [
      "Thiết kế 3D",
      "Thi công trọn gói",
      "Bảo hành 2 năm",
      "Tư vấn chuyên sâu",
      "Bảo trì 6 tháng",
    ],
    isPopular: true,
  },
  {
    id: 3,
    name: "Gói Cao Cấp",
    description:
      "Trải nghiệm đẳng cấp với những tính năng cao cấp và độc quyền.",
    price: 40000000,
    features: [
      "Thiết kế 3D chi tiết",
      "Thi công cao cấp",
      "Bảo hành 3 năm",
      "Tư vấn VIP",
      "Bảo trì 12 tháng",
      "Trang thiết bị cao cấp",
    ],
    isPopular: false,
  },
];
