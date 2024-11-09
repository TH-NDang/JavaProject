package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.order.*;
import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.entity.OrderHistory;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.fullName")
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "serviceName", source = "service.name")
    @Mapping(target = "assignedStaffId", source = "assignedStaff.id")
    @Mapping(target = "assignedStaffName", source = "assignedStaff.fullName")
    OrderDTO toDto(Order order);

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "changedById", source = "changedBy.id")
    @Mapping(target = "changedByName", source = "changedBy.fullName")
    OrderHistoryDTO toHistoryDto(OrderHistory history);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "assignedStaff", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "cancellationReason", ignore = true)
    @Mapping(target = "cancelledDate", ignore = true)
    @Mapping(target = "completionDate", ignore = true)
    @Mapping(target = "actualStartDate", ignore = true)
    @Mapping(target = "notes", source = "requirements")
    Order createRequestToEntity(CreateOrderRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "cancellationReason", ignore = true)
    @Mapping(target = "cancelledDate", ignore = true)
    void updateEntityFromDto(UpdateOrderRequest request, @MappingTarget Order order);

    @AfterMapping
    default void setDefaultValues(@MappingTarget Order order) {
        if (order.getStatus() == null) {
            order.setStatus(org.group.koipondbackend.entity.enums.OrderStatus.PENDING);
        }
    }
}
