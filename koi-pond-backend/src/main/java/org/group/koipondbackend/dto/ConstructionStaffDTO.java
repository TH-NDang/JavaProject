package org.group.koipondbackend.dto;

import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CONSTRUCTION")
@Data
@NoArgsConstructor
public class ConstructionStaffDTO extends StaffDTO {
    private String constructionSkills;
}
