import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
  LineChart,
  Line,
  PieChart,
  Pie,
  Cell,
} from "recharts";
import {
  RevenueStats,
  OrderStats,
  ServiceStats,
} from "../../../services/api/dashboard.service";

interface ChartContainerProps {
  title: string;
  children: React.ReactNode;
  loading?: boolean;
}

const ChartContainer: React.FC<ChartContainerProps> = ({
  title,
  children,
  loading,
}) => {
  if (loading) {
    return (
      <div className="bg-white p-6 rounded-lg shadow">
        <div className="h-4 bg-gray-200 rounded w-1/3 mb-8 animate-pulse"></div>
        <div className="h-64 bg-gray-100 rounded animate-pulse"></div>
      </div>
    );
  }

  return (
    <div className="bg-white p-6 rounded-lg shadow">
      <h3 className="text-lg font-semibold mb-4">{title}</h3>
      {children}
    </div>
  );
};

interface RevenueChartProps {
  data: RevenueStats[];
  loading?: boolean;
}

export const RevenueChart: React.FC<RevenueChartProps> = ({ data, loading }) => {
  return (
    <ChartContainer title="Doanh thu theo tháng" loading={loading}>
      <div className="h-80">
        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={data}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="month" />
            <YAxis
              tickFormatter={(value) =>
                new Intl.NumberFormat("vi-VN", {
                  notation: "compact",
                  compactDisplay: "short",
                }).format(value)
              }
            />
            <Tooltip
              formatter={(value: number) =>
                new Intl.NumberFormat("vi-VN", {
                  style: "currency",
                  currency: "VND",
                }).format(value)
              }
            />
            <Legend />
            <Bar dataKey="revenue" fill="#3b82f6" name="Doanh thu" />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </ChartContainer>
  );
};

interface OrderChartProps {
  data: OrderStats[];
  loading?: boolean;
}

export const OrderChart: React.FC<OrderChartProps> = ({ data, loading }) => {
  return (
    <ChartContainer title="Số lượng đơn hàng" loading={loading}>
      <div className="h-80">
        <ResponsiveContainer width="100%" height="100%">
          <LineChart data={data}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="month" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line
              type="monotone"
              dataKey="orders"
              stroke="#3b82f6"
              name="Đơn hàng"
            />
          </LineChart>
        </ResponsiveContainer>
      </div>
    </ChartContainer>
  );
};

interface ServiceChartProps {
  data: ServiceStats[];
  loading?: boolean;
}

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042", "#8884d8"];

export const ServiceChart: React.FC<ServiceChartProps> = ({ data, loading }) => {
  const totalRevenue = data.reduce((sum, item) => sum + item.revenue, 0);

  return (
    <ChartContainer title="Thống kê theo dịch vụ" loading={loading}>
      <div className="h-80">
        <ResponsiveContainer width="100%" height="100%">
          <PieChart>
            <Pie
              data={data}
              dataKey="revenue"
              nameKey="name"
              cx="50%"
              cy="50%"
              outerRadius={80}
              label={({
                cx,
                cy,
                midAngle,
                innerRadius,
                outerRadius,
                percent,
                name,
              }) => {
                const radius = innerRadius + (outerRadius - innerRadius) * 1.4;
                const x = cx + radius * Math.cos(-midAngle * (Math.PI / 180));
                const y = cy + radius * Math.sin(-midAngle * (Math.PI / 180));

                return (
                  <text
                    x={x}
                    y={y}
                    fill="black"
                    textAnchor={x > cx ? "start" : "end"}
                    dominantBaseline="central"
                  >
                    {`${name} (${(percent * 100).toFixed(0)}%)`}
                  </text>
                );
              }}
            >
              {data.map((entry, index) => (
                <Cell key={entry.name} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip
              formatter={(value: number) =>
                new Intl.NumberFormat("vi-VN", {
                  style: "currency",
                  currency: "VND",
                }).format(value)
              }
            />
          </PieChart>
        </ResponsiveContainer>
      </div>
      <div className="mt-4">
        <table className="min-w-full">
          <thead>
            <tr>
              <th className="text-left">Dịch vụ</th>
              <th className="text-right">Số lượng</th>
              <th className="text-right">Doanh thu</th>
              <th className="text-right">Tỷ lệ</th>
            </tr>
          </thead>
          <tbody>
            {data.map((service, index) => (
              <tr key={service.name}>
                <td className="py-2">
                  <span
                    className="inline-block w-3 h-3 rounded-full mr-2"
                    style={{ backgroundColor: COLORS[index % COLORS.length] }}
                  ></span>
                  {service.name}
                </td>
                <td className="text-right">{service.count}</td>
                <td className="text-right">
                  {new Intl.NumberFormat("vi-VN", {
                    style: "currency",
                    currency: "VND",
                  }).format(service.revenue)}
                </td>
                <td className="text-right">
                  {((service.revenue / totalRevenue) * 100).toFixed(1)}%
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </ChartContainer>
  );
};
