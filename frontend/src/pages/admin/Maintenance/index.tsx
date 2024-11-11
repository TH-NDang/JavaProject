import React, { useState, useEffect } from "react";
import { MaintenanceSchedule } from "../../../types/maintenance";
import { MaintenanceService } from "../../../services/maintenance.service";
import { Toast } from "../../../services/toast.service";
import MaintenanceCalendar from "./MaintenanceCalendar";
import MaintenanceScheduleModal from "./MaintenanceScheduleModal";
import MaintenanceCompletionModal from "./MaintenanceCompletionModal";
import PageHeader from "../../../components/common/PageHeader";
import { StaffService } from "../../../services/staff.service";
import { generateMockSchedules, mockStaff } from "./mockData";

export default function MaintenanceManagement() {
  const [schedules, setSchedules] = useState<MaintenanceSchedule[]>([]);
  const [loading, setLoading] = useState(true);
  const [selectedSchedule, setSelectedSchedule] =
    useState<MaintenanceSchedule | null>(null);
  const [showScheduleModal, setShowScheduleModal] = useState(false);
  const [showCompletionModal, setShowCompletionModal] = useState(false);
  const [availableStaff, setAvailableStaff] = useState<
    Array<{ id: number; name: string }>
  >([]);

  const fetchSchedules = async () => {
    try {
      setLoading(true);
      // Tạm thời sử dụng mock data
      const mockData = generateMockSchedules();
      setSchedules(mockData);
    } catch (error) {
      Toast.error("Không thể tải lịch bảo trì");
    } finally {
      setLoading(false);
    }
  };

  const fetchStaff = async () => {
    try {
      // Tạm thời sử dụng mock data
      setAvailableStaff(mockStaff);
    } catch (error) {
      Toast.error("Không thể tải danh sách nhân viên");
    }
  };

  // const fetchSchedules = async () => {
  //   try {
  //     setLoading(true);
  //     const data = await MaintenanceService.getAllSchedules();
  //     setSchedules(data);
  //   } catch (error) {
  //     Toast.error("Không thể tải lịch bảo trì");
  //   } finally {
  //     setLoading(false);
  //   }
  // };

  // const fetchStaff = async () => {
  //   try {
  //     const staff = await StaffService.getMaintenanceStaff();
  //     setAvailableStaff(staff.map((s) => ({ id: s.id, name: s.fullName })));
  //   } catch (error) {
  //     Toast.error("Không thể tải danh sách nhân viên");
  //   }
  // };

  useEffect(() => {
    fetchSchedules();
    fetchStaff();
  }, []);

  const handleCreateSchedule = async (data: Partial<MaintenanceSchedule>) => {
    try {
      await MaintenanceService.createSchedule(data as MaintenanceSchedule);
      Toast.success("Tạo lịch bảo trì thành công");
      fetchSchedules();
      setShowScheduleModal(false);
    } catch (error) {
      Toast.error("Không thể tạo lịch bảo trì");
    }
  };

  const handleUpdateSchedule = async (data: Partial<MaintenanceSchedule>) => {
    try {
      await MaintenanceService.updateSchedule(selectedSchedule!.id, data);
      Toast.success("Cập nhật lịch bảo trì thành công");
      fetchSchedules();
      setShowScheduleModal(false);
      setSelectedSchedule(null);
    } catch (error) {
      Toast.error("Không thể cập nhật lịch bảo trì");
    }
  };

  const handleCompleteSchedule = async (
    data: MaintenanceSchedule["completionReport"]
  ) => {
    try {
      await MaintenanceService.completeSchedule(selectedSchedule!.id, data);
      Toast.success("Hoàn thành bảo trì thành công");
      fetchSchedules();
      setShowCompletionModal(false);
      setSelectedSchedule(null);
    } catch (error) {
      Toast.error("Không thể cập nhật kết quả bảo trì");
    }
  };

  const handleSelectEvent = (schedule: MaintenanceSchedule) => {
    setSelectedSchedule(schedule);
    if (schedule.status === "IN_PROGRESS") {
      setShowCompletionModal(true);
    } else {
      setShowScheduleModal(true);
    }
  };

  const handleSelectSlot = ({ start }: { start: Date }) => {
    setSelectedSchedule({
      scheduleDate: start.toISOString().split("T")[0],
      timeSlot: start.toTimeString().slice(0, 5),
    } as MaintenanceSchedule);
    setShowScheduleModal(true);
  };

  return (
    <div className="space-y-6">
      <PageHeader
        title="Quản lý Lịch Bảo trì"
        description="Lên lịch và theo dõi các hoạt động bảo trì hồ cá"
      />

      {loading ? (
        <div className="text-center py-12">
          <div className="inline-block animate-spin rounded-full h-8 w-8 border-b-2 border-primary-600" />
        </div>
      ) : (
        <MaintenanceCalendar
          schedules={schedules}
          onSelectEvent={handleSelectEvent}
          onSelectSlot={handleSelectSlot}
        />
      )}

      {showScheduleModal && (
        <MaintenanceScheduleModal
          schedule={selectedSchedule || undefined}
          onSubmit={
            selectedSchedule?.id ? handleUpdateSchedule : handleCreateSchedule
          }
          onClose={() => {
            setShowScheduleModal(false);
            setSelectedSchedule(null);
          }}
          availableStaff={availableStaff}
        />
      )}

      {showCompletionModal && selectedSchedule && (
        <MaintenanceCompletionModal
          schedule={selectedSchedule}
          onSubmit={handleCompleteSchedule}
          onClose={() => {
            setShowCompletionModal(false);
            setSelectedSchedule(null);
          }}
        />
      )}
    </div>
  );
}
