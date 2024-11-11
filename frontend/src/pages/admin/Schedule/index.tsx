import { Calendar } from "lucide-react";
import ComingSoonPage from "../../../components/common/ComingSoonPage";

const AdminSchedule = () => {
  return (
    <ComingSoonPage
      title="Quản lý Lịch Công Việc"
      description="Hệ thống quản lý lịch biểu và điều phối công việc sẽ sớm ra mắt."
      icon={Calendar}
      expectedDate="Quý 4/2024"
    />
  );
};

export default AdminSchedule;
