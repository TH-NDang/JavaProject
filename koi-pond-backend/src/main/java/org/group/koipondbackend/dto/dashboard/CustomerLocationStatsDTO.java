package org.group.koipondbackend.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLocationStatsDTO {
    private String location;
    private int customerCount;
    private double percentage;
}
