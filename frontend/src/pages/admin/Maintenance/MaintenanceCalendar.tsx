import React from "react";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import { MaintenanceSchedule } from "../../../types/maintenance";

const localizer = momentLocalizer(moment);

interface MaintenanceEvent
  extends Omit<MaintenanceSchedule, "scheduleDate" | "timeSlot"> {
  start: Date;
  end: Date;
  title: string;
}

interface MaintenanceCalendarProps {
  schedules: MaintenanceSchedule[];
  onSelectEvent: (schedule: MaintenanceSchedule) => void;
  onSelectSlot: (slotInfo: { start: Date; end: Date }) => void;
}

const MaintenanceCalendar: React.FC<MaintenanceCalendarProps> = ({
  schedules,
  onSelectEvent,
  onSelectSlot,
}) => {
  const events: MaintenanceEvent[] = schedules.map((schedule) => {
    const [hours, minutes] = schedule.timeSlot.split(":");
    const start = moment(schedule.scheduleDate)
      .hours(parseInt(hours))
      .minutes(parseInt(minutes))
      .toDate();
    const end = moment(start).add(2, "hours").toDate();

    return {
      ...schedule,
      start,
      end,
      title: `${schedule.customerName} - ${schedule.pondName}`,
    };
  });

  const eventStyleGetter = (event: MaintenanceEvent) => {
    let backgroundColor = "";
    switch (event.status) {
      case "PENDING":
        backgroundColor = "#FEF3C7";
        break;
      case "CONFIRMED":
        backgroundColor = "#DBEAFE";
        break;
      case "IN_PROGRESS":
        backgroundColor = "#C7D2FE";
        break;
      case "COMPLETED":
        backgroundColor = "#D1FAE5";
        break;
      case "CANCELLED":
        backgroundColor = "#FEE2E2";
        break;
      default:
        backgroundColor = "#F3F4F6";
    }

    return {
      style: {
        backgroundColor,
        borderRadius: "4px",
        opacity: 0.8,
        color: "#1F2937",
        border: "0",
        display: "block",
      },
    };
  };

  return (
    <div className="h-[600px]">
      <Calendar
        localizer={localizer}
        events={events}
        startAccessor="start"
        endAccessor="end"
        style={{ height: "100%" }}
        onSelectEvent={(event: MaintenanceEvent) => {
          const schedule: MaintenanceSchedule = {
            ...event,
            scheduleDate: moment(event.start).format("YYYY-MM-DD"),
            timeSlot: moment(event.start).format("HH:mm"),
          };
          onSelectEvent(schedule);
        }}
        onSelectSlot={onSelectSlot}
        selectable
        eventPropGetter={eventStyleGetter}
        defaultView="week"
        views={["month", "week", "day"]}
      />
    </div>
  );
};

export default MaintenanceCalendar;
