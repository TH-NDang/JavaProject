package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.order.OrderDTO;
import org.group.koipondbackend.dto.order.OrderHistoryDTO;
import org.group.koipondbackend.dto.order.CreateOrderRequest;
import org.group.koipondbackend.dto.order.UpdateOrderRequest;
import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.entity.OrderHistory;
import org.group.koipondbackend.entity.enums.OrderStatus;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class OrderMapper {

        public OrderDTO toDto(Order order) {
                if (order == null) {
                        return null;
                }

                return OrderDTO.builder()
                                .id(order.getId())
                                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : null)
                                .customerName(order.getCustomer() != null ? order.getCustomer().getFullName() : null)
                                .serviceId(order.getService() != null ? order.getService().getId() : null)
                                .serviceName(order.getService() != null ? order.getService().getName() : null)
                                .assignedStaffId(order.getAssignedStaff() != null ? order.getAssignedStaff().getId()
                                                : null)
                                .assignedStaffName(order.getAssignedStaff() != null
                                                ? order.getAssignedStaff().getFullName()
                                                : null)
                                .status(order.getStatus())
                                .totalAmount(order.getTotalAmount())
                                .requirements(order.getRequirements())
                                .customRequests(order.getCustomRequests() != null
                                                ? new ArrayList<>(order.getCustomRequests())
                                                : new ArrayList<>())
                                .location(order.getLocation())
                                .locationNotes(order.getLocationNotes())
                                .preferredStartDate(order.getPreferredStartDate())
                                .actualStartDate(order.getActualStartDate())
                                .completionDate(order.getCompletionDate())
                                .cancelledDate(order.getCancelledDate())
                                .cancellationReason(order.getCancellationReason())
                                .notes(order.getNotes())
                                .createdAt(order.getCreatedAt())
                                .updatedAt(order.getUpdatedAt())
                                .build();
        }

        public OrderHistoryDTO toHistoryDto(OrderHistory history) {
                if (history == null) {
                        return null;
                }

                return OrderHistoryDTO.builder()
                                .id(history.getId())
                                .orderId(history.getOrder() != null ? history.getOrder().getId() : null)
                                .fromStatus(history.getFromStatus())
                                .toStatus(history.getToStatus())
                                .changedById(history.getChangedBy() != null ? history.getChangedBy().getId() : null)
                                .changedByName(history.getChangedBy() != null ? history.getChangedBy().getFullName()
                                                : null)
                                .notes(history.getNotes())
                                .createdAt(history.getCreatedAt())
                                .build();
        }

        public Order createRequestToEntity(CreateOrderRequest request) {
                if (request == null) {
                        return null;
                }

                return Order.builder()
                                .requirements(request.getRequirements())
                                .customRequests(request.getCustomRequests() != null
                                                ? new ArrayList<>(request.getCustomRequests())
                                                : new ArrayList<>())
                                .location(request.getLocation())
                                .locationNotes(request.getLocationNotes())
                                .preferredStartDate(request.getPreferredStartDate())
                                .status(OrderStatus.PENDING)
                                .build();
        }

        public void updateEntityFromDto(UpdateOrderRequest request, Order order) {
                if (request == null) {
                        return;
                }

                if (request.getRequirements() != null) {
                        order.setRequirements(request.getRequirements());
                }
                if (request.getCustomRequests() != null) {
                        order.setCustomRequests(new ArrayList<>(request.getCustomRequests()));
                }
                if (request.getLocation() != null) {
                        order.setLocation(request.getLocation());
                }
                if (request.getLocationNotes() != null) {
                        order.setLocationNotes(request.getLocationNotes());
                }
                if (request.getPreferredStartDate() != null) {
                        order.setPreferredStartDate(request.getPreferredStartDate());
                }
                if (request.getStatus() != null) {
                        order.setStatus(request.getStatus());
                }
                if (request.getActualStartDate() != null) {
                        order.setActualStartDate(request.getActualStartDate());
                }
                if (request.getCompletionDate() != null) {
                        order.setCompletionDate(request.getCompletionDate());
                }
                if (request.getNotes() != null) {
                        order.setNotes(request.getNotes());
                }
        }
}
