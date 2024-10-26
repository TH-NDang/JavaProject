export const ROUTES = {
  // Public routes
  HOME: "/",
  ABOUT: "/about",
  SERVICES: "/services",
  PORTFOLIO: "/portfolio",
  CONTACT: "/contact",
  BLOG: "/blog",

  // Auth routes
  LOGIN: "/login",
  REGISTER: "/register",
  FORGOT_PASSWORD: "/forgot-password",

  // Customer routes
  CUSTOMER: {
    DASHBOARD: "/customer/dashboard",
    ORDERS: "/customer/orders",
    MAINTENANCE: "/customer/maintenance",
    PROFILE: "/customer/profile",
  },

  // Staff routes
  STAFF: {
    DASHBOARD: "/staff/dashboard",
    ORDERS: "/staff/orders",
    CUSTOMERS: "/staff/customers",
    SCHEDULE: "/staff/schedule",
  },

  // Admin routes
  ADMIN: {
    DASHBOARD: "/admin/dashboard",
    USERS: "/admin/users",
    ORDERS: "/admin/orders",
    SERVICES: "/admin/services",
    REPORTS: "/admin/reports",
    SETTINGS: "/admin/settings",
  },
} as const;

export const USER_ROLES = {
  ADMIN: "ADMIN",
  STAFF: "STAFF",
  CUSTOMER: "CUSTOMER",
  GUEST: "GUEST",
} as const;

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
