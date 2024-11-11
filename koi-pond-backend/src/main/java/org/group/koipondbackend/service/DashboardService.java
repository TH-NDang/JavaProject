package org.group.koipondbackend.service;

import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.dto.dashboard.*;
import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.entity.Services;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.repository.OrderRepository;
import org.group.koipondbackend.repository.ServicesRepository;
import org.group.koipondbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ServicesRepository servicesRepository;

    @Transactional(readOnly = true)
    public DashboardStatsDTO getStats() {
        // Lấy thời điểm đầu tháng này và tháng trước
        LocalDateTime startOfCurrentMonth = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime startOfLastMonth = YearMonth.now().minusMonths(1).atDay(1).atStartOfDay();
        LocalDateTime now = LocalDateTime.now();

        // Thống kê doanh thu
        double currentMonthRevenue = orderRepository.calculateTotalRevenueBetween(
                startOfCurrentMonth,
                now);
        double lastMonthRevenue = orderRepository.calculateTotalRevenueBetween(
                startOfLastMonth,
                startOfCurrentMonth);

        // Thống kê đơn hàng
        int currentMonthOrders = orderRepository.countOrdersBetween(
                startOfCurrentMonth,
                now);
        int lastMonthOrders = orderRepository.countOrdersBetween(
                startOfLastMonth,
                startOfCurrentMonth);

        // Thống kê khách hàng mới
        int currentMonthCustomers = userRepository.countNewCustomersBetween(
                startOfCurrentMonth,
                now);
        int lastMonthCustomers = userRepository.countNewCustomersBetween(
                startOfLastMonth,
                startOfCurrentMonth);

        // Tính toán tăng trưởng
        double revenueGrowth = calculateGrowth(lastMonthRevenue, currentMonthRevenue);
        double orderGrowth = calculateGrowth(lastMonthOrders, currentMonthOrders);
        double customerGrowth = calculateGrowth(lastMonthCustomers, currentMonthCustomers);

        // Tính giá trị trung bình đơn hàng
        double currentAvgValue = currentMonthOrders > 0 ? currentMonthRevenue / currentMonthOrders : 0;
        double lastAvgValue = lastMonthOrders > 0 ? lastMonthRevenue / lastMonthOrders : 0;
        double valueGrowth = calculateGrowth(lastAvgValue, currentAvgValue);

        // Tính tổng số khách hàng
        int totalCustomers = userRepository.countAllCustomers();

        return DashboardStatsDTO.builder()
                .totalRevenue(currentMonthRevenue)
                .totalOrders(currentMonthOrders)
                .totalCustomers(totalCustomers)
                .avgOrderValue(currentAvgValue)
                .revenueGrowth(revenueGrowth)
                .orderGrowth(orderGrowth)
                .customerGrowth(customerGrowth)
                .valueGrowth(valueGrowth)
                .build();
    }

    @Transactional(readOnly = true)
    public List<RevenueStatsDTO> getRevenueStats(String period) {
        LocalDateTime startDate = getStartDateByPeriod(period);
        List<Order> orders = orderRepository.findAllByCreatedAtAfter(startDate);

        Map<YearMonth, Double> monthlyRevenue = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> YearMonth.from(order.getCreatedAt()),
                        Collectors.summingDouble(Order::getTotalAmount)));

        List<RevenueStatsDTO> stats = new ArrayList<>();
        YearMonth current = YearMonth.from(startDate);
        YearMonth end = YearMonth.now();

        while (!current.isAfter(end)) {
            stats.add(RevenueStatsDTO.builder()
                    .month(current.toString())
                    .revenue(monthlyRevenue.getOrDefault(current, 0.0))
                    .build());
            current = current.plusMonths(1);
        }

        return stats;
    }

    @Transactional(readOnly = true)
    public List<OrderStatsDTO> getOrderStats(String period) {
        LocalDateTime startDate = getStartDateByPeriod(period);
        List<Order> orders = orderRepository.findAllByCreatedAtAfter(startDate);

        Map<YearMonth, Long> monthlyOrders = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> YearMonth.from(order.getCreatedAt()),
                        Collectors.counting()));

        List<OrderStatsDTO> stats = new ArrayList<>();
        YearMonth current = YearMonth.from(startDate);
        YearMonth end = YearMonth.now();

        while (!current.isAfter(end)) {
            stats.add(OrderStatsDTO.builder()
                    .month(current.toString())
                    .orders(monthlyOrders.getOrDefault(current, 0L).intValue())
                    .build());
            current = current.plusMonths(1);
        }

        return stats;
    }

    @Transactional(readOnly = true)
    public List<ServiceStatsDTO> getServiceStats() {
        List<Services> services = servicesRepository.findAll();
        Map<Long, ServiceStatsDTO.ServiceStatsDTOBuilder> statsBuilders = new HashMap<>();

        // Initialize builders for all services
        for (Services service : services) {
            statsBuilders.put(service.getId(), ServiceStatsDTO.builder()
                    .name(service.getName())
                    .count(0)
                    .revenue(0.0));
        }

        // Calculate stats from orders
        List<Order> orders = orderRepository.findCompletedOrCancelledOrders();
        for (Order order : orders) {
            if (order.getService() != null) {
                Long serviceId = order.getService().getId();
                ServiceStatsDTO.ServiceStatsDTOBuilder builder = statsBuilders.get(serviceId);
                if (builder != null) {
                    builder.count(builder.build().getCount() + 1)
                            .revenue(builder.build().getRevenue() + order.getTotalAmount());
                }
            }
        }

        return statsBuilders.values().stream()
                .map(ServiceStatsDTO.ServiceStatsDTOBuilder::build)
                .sorted((a, b) -> Double.compare(b.getRevenue(), a.getRevenue()))
                .collect(Collectors.toList());
    }

    private LocalDateTime getStartDateByPeriod(String period) {
        LocalDateTime now = LocalDateTime.now();
        return switch (period.toLowerCase()) {
            case "year" -> now.minusYears(1);
            case "quarter" -> now.minusMonths(3);
            default -> now.minusMonths(6);
        };
    }

    private double calculateGrowth(double previous, double current) {
        if (previous == 0)
            return current > 0 ? 100.0 : 0.0;
        return ((current - previous) / previous) * 100.0;
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getCustomerLocationStats() {
        List<User> customers = userRepository.findAllCustomers();

        // Group customers by location and count
        Map<String, Long> locationCounts = customers.stream()
                .filter(customer -> customer.getAddress() != null && !customer.getAddress().isEmpty())
                .map(customer -> extractCity(customer.getAddress()))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        location -> location,
                        Collectors.counting()));

        // Convert to Map<String, Integer>
        Map<String, Integer> result = new HashMap<>();
        locationCounts.forEach((location, count) -> result.put(location, count.intValue()));

        return result;
    }

    private String extractCity(String address) {
        // Implement logic to extract city from address
        // This is a simplified version - you might want to implement
        // more sophisticated address parsing
        if (address == null)
            return null;

        // Split address by comma and get the last part as city
        String[] parts = address.split(",");
        if (parts.length > 0) {
            return parts[parts.length - 1].trim();
        }

        return null;
    }

}
