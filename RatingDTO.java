package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private Long id;
    private int rating;
    private String comment;
    private Long customerId; // ID của khách hàng
}
