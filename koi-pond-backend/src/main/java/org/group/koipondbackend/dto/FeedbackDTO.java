package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
    private Long id;           // ID của phản hồi
    private Long customerId;    // ID của khách hàng (không liên quan tới User)
    private String content;     // Nội dung phản hồi
    private int rating;         // Đánh giá
}
