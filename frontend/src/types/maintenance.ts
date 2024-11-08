export interface CompletionReport {
  waterQuality: {
    pH: number;
    temperature: number;
    ammonia: number;
    nitrite: number;
  };
  filterSystem: {
    status: "GOOD" | "FAIR" | "POOR";
    cleaningNeeded: boolean;
    replacementNeeded: boolean;
  };
  recommendations: string[];
}

export interface MaintenanceSchedule {
  id: number;
  customerId: number;
  customerName: string;
  pondId: number;
  pondName: string;
  scheduleDate: string;
  timeSlot: string;
  status: "PENDING" | "CONFIRMED" | "IN_PROGRESS" | "COMPLETED" | "CANCELLED";
  notes?: string;
  assignedStaffId?: number;
  assignedStaffName?: string;
  completionReport?: CompletionReport;
}
