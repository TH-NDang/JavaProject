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

        // Thống kê doanh thu
        double currentMonthRevenue = orderRepository.calculateTotalRevenueBetween(
                startOfCurrentMonth,
                LocalDateTime.now());
        double lastMonthRevenue = orderRepository.calculateTotalRevenueBetween(
                startOfLastMonth,
                startOfCurrentMonth);

        // Thống kê đơn hàng
        int currentMonthOrders = orderRepository.countOrdersBetween(
                startOfCurrentMonth,
                LocalDateTime.now());
        int lastMonthOrders = orderRepository.countOrdersBetween(
                startOfLastMonth,
                startOfCurrentMonth);

        // Thống kê khách hàng mới
        int currentMonthCustomers = userRepository.countNewCustomersBetween(
                startOfCurrentMonth,
                LocalDateTime.now());
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

        return DashboardStatsDTO.builder()
                .totalRevenue(currentMonthRevenue)
                .totalOrders(currentMonthOrders)
                .totalCustomers(userRepository.countAllCustomers())
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

        return monthlyRevenue.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> RevenueStatsDTO.builder()
                        .month(entry.getKey().toString())
                        .revenue(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderStatsDTO> getOrderStats(String period) {
        LocalDateTime startDate = getStartDateByPeriod(period);
        List<Order> orders = orderRepository.findAllByCreatedAtAfter(startDate);

        Map<YearMonth, Long> monthlyOrders = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> YearMonth.from(order.getCreatedAt()),
                        Collectors.counting()));

        return monthlyOrders.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> OrderStatsDTO.builder()
                        .month(entry.getKey().toString())
                        .orders(entry.getValue().intValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ServiceStatsDTO> getServiceStats() {
        List<Services> services = servicesRepository.findAll();
        List<Order> orders = orderRepository.findAll();

        Map<Long, ServiceStats> serviceStats = new HashMap<>();

        // Initialize stats for all services
        services.forEach(service -> serviceStats.put(service.getId(), new ServiceStats(service.getName())));

        // Calculate stats from orders
        orders.forEach(order -> {
            ServiceStats stats = serviceStats.get(order.getService().getId());
            if (stats != null) {
                stats.addOrder(order.getTotalAmount());
            }
        });

        return serviceStats.values().stream()
                .map(stats -> ServiceStatsDTO.builder()
                        .name(stats.getName())
                        .count(stats.getOrderCount())
                        .revenue(stats.getTotalRevenue())
                        .build())
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
            return 100;
        return ((current - previous) / previous) * 100;
    }

    // Helper class for service statistics
    private static class ServiceStats {
        private final String name;
        private int orderCount;
        private double totalRevenue;

        public ServiceStats(String name) {
            this.name = name;
            this.orderCount = 0;
            this.totalRevenue = 0;
        }

        public void addOrder(double amount) {
            orderCount++;
            totalRevenue += amount;
        }

        public String getName() {
            return name;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public double getTotalRevenue() {
            return totalRevenue;
        }
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
