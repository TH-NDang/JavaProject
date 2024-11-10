package org.group.koipondbackend.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    private double totalRevenue;
    private int totalOrders;
    private int totalCustomers;
    private double avgOrderValue;
    private double revenueGrowth;
    private double orderGrowth;
    private double customerGrowth;
    private double valueGrowth;
}
