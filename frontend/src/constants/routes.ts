export const ROUTES = {
  // Public routes
  HOME: "/",
  ABOUT: "/about",
  SERVICES: "/services",
  PORTFOLIO: "/portfolio",
  CONTACT: "/contact",

  // Auth routes
  LOGIN: "/login",
  REGISTER: "/register",
  FORGOT_PASSWORD: "/forgot-password",

  // Admin routes
  ADMIN: {
    ROOT: "/admin",
    DASHBOARD: "/admin/dashboard",
    USERS: "/admin/users",
    ORDERS: "/admin/orders",
    SERVICES: "/admin/services",
    SCHEDULE: "/admin/schedule",
    REPORTS: "/admin/reports",
    SETTINGS: "/admin/settings",
  },

  // Customer routes
  CUSTOMER: {
    DASHBOARD: "/customer/dashboard",
    ORDERS: "/customer/orders",
    PROFILE: "/customer/profile",
    MAINTENANCE: "/customer/maintenance",
  },

  // Staff routes
  STAFF: {
    DASHBOARD: "/staff/dashboard",
    ORDERS: "/staff/orders",
    SCHEDULE: "/staff/schedule",
    CUSTOMERS: "/staff/customers",
  },
} as const;

export const USER_ROLES = {
  ADMIN: "ADMIN",
  STAFF: "STAFF",
  CUSTOMER: "CUSTOMER",
} as const;

export type UserRole = keyof typeof USER_ROLES;

export const ORDER_STATUS = {
  PENDING: "PENDING",
  CONSULTING: "CONSULTING",
  DESIGNING: "DESIGNING",
  APPROVED: "APPROVED",
  CONSTRUCTION: "CONSTRUCTION",
  COMPLETED: "COMPLETED",
  CANCELLED: "CANCELLED",
} as const;

export const MAINTENANCE_STATUS = {
  SCHEDULED: "SCHEDULED",
  IN_PROGRESS: "IN_PROGRESS",
  COMPLETED: "COMPLETED",
  CANCELLED: "CANCELLED",
} as const;
