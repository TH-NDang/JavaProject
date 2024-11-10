package org.group.koipondbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.dto.dashboard.*;
import org.group.koipondbackend.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Dashboard", description = "Dashboard management APIs")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/stats")
    @Operation(summary = "Get dashboard statistics")
    public ResponseEntity<DashboardStatsDTO> getStats() {
        return ResponseEntity.ok(dashboardService.getStats());
    }

    @GetMapping("/revenue-stats")
    @Operation(summary = "Get revenue statistics by period")
    public ResponseEntity<List<RevenueStatsDTO>> getRevenueStats(
            @RequestParam(defaultValue = "month") String period) {
        return ResponseEntity.ok(dashboardService.getRevenueStats(period));
    }

    @GetMapping("/order-stats")
    @Operation(summary = "Get order statistics by period")
    public ResponseEntity<List<OrderStatsDTO>> getOrderStats(
            @RequestParam(defaultValue = "month") String period) {
        return ResponseEntity.ok(dashboardService.getOrderStats(period));
    }

    @GetMapping("/service-stats")
    @Operation(summary = "Get service usage statistics")
    public ResponseEntity<List<ServiceStatsDTO>> getServiceStats() {
        return ResponseEntity.ok(dashboardService.getServiceStats());
    }

    @GetMapping("/customer-locations")
    @Operation(summary = "Get customer location distribution")
    public ResponseEntity<Map<String, Integer>> getCustomerLocationStats() {
        return ResponseEntity.ok(dashboardService.getCustomerLocationStats());
    }
}
