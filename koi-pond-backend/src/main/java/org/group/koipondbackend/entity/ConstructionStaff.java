package org.group.koipondbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Entity
@Table(name = "construction_staff")
@PrimaryKeyJoinColumn(name = "staff_id")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ConstructionStaff extends Staff {

    @Column(name = "certification")
    private String certification;

    @Column(name = "equipment_expertise")
    private String equipmentExpertise;

    @Column(name = "safety_training")
    private LocalDate safetyTrainingExpiry;
}
