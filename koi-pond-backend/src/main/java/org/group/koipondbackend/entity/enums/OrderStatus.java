package org.group.koipondbackend.entity.enums;

public enum OrderStatus {
    PENDING("Chờ xử lý"),
    CONSULTING("Đang tư vấn"),
    DESIGNING("Đang thiết kế"),
    APPROVED("Đã duyệt"),
    CONSTRUCTION("Đang thi công"),
    COMPLETED("Hoàn thành"),
    CANCELLED("Đã hủy");

    private final String vietnameseName;

    OrderStatus(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }
}
