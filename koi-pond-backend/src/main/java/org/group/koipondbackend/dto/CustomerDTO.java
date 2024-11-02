package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;

    // Constructor đầy đủ các thuộc tính
    public CustomerDTO(Long id, String username, String fullName, String email, String phoneNumber, String address) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Constructor không tham số (cần thiết cho Lombok @Data để có thể sử dụng các getter/setter mặc định)
    public CustomerDTO() {
    }
}
