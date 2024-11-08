// src/pages/admin/Maintenance/mockData.ts
import { MaintenanceSchedule } from "../../../types/maintenance";

// Tạo một mảng lịch bảo trì mẫu cho 2 tuần
export const generateMockSchedules = (): MaintenanceSchedule[] => {
  const startDate = new Date();
  startDate.setDate(startDate.getDate() - 7); // Bắt đầu từ 1 tuần trước

  const schedules: MaintenanceSchedule[] = [];

  for (let i = 0; i < 14; i++) {
    // 14 ngày
    const date = new Date(startDate);
    date.setDate(date.getDate() + i);

    // Tạo 2-3 lịch mỗi ngày
    const numSchedules = Math.floor(Math.random() * 2) + 2;

    for (let j = 0; j < numSchedules; j++) {
      const hour = 8 + Math.floor(Math.random() * 8); // Giờ từ 8h-16h
      const status: MaintenanceSchedule["status"] =
        date < new Date()
          ? "COMPLETED"
          : date.toDateString() === new Date().toDateString()
          ? "IN_PROGRESS"
          : "PENDING";

      schedules.push({
        id: schedules.length + 1,
        customerId: Math.floor(Math.random() * 100) + 1,
        customerName: `Khách hàng ${schedules.length + 1}`,
        pondId: Math.floor(Math.random() * 50) + 1,
        pondName: `Hồ ${Math.floor(Math.random() * 50) + 1}`,
        scheduleDate: date.toISOString().split("T")[0],
        timeSlot: `${hour}:00`,
        status,
        assignedStaffId: Math.floor(Math.random() * 5) + 1,
        assignedStaffName: `Nhân viên ${Math.floor(Math.random() * 5) + 1}`,
        ...(status === "COMPLETED" && {
          completionReport: {
            waterQuality: {
              pH: 7.0 + Math.random() * 0.5,
              temperature: 25 + Math.random() * 2,
              ammonia: Math.random() * 0.5,
              nitrite: Math.random() * 0.2,
            },
            filterSystem: {
              status: "GOOD",
              cleaningNeeded: Math.random() > 0.7,
              replacementNeeded: Math.random() > 0.9,
            },
            recommendations: [
              "Kiểm tra định kỳ mỗi tuần",
              "Thay nước 20% mỗi 2 tuần",
            ],
          },
        }),
      });
    }
  }

  return schedules;
};

export const mockStaff = [
  { id: 1, name: "Nguyễn Văn A" },
  { id: 2, name: "Trần Thị B" },
  { id: 3, name: "Lê Văn C" },
  { id: 4, name: "Phạm Thị D" },
  { id: 5, name: "Hoàng Văn E" },
];
