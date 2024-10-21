package org.group.koipondbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends UserDTO {
    private String address;
    private BigDecimal totalSpent;  // Thêm trường này

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getPhone() {
        return "";
    }
}
