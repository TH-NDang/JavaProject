package org.group.koipondbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "design_staff")
@PrimaryKeyJoinColumn(name = "staff_id")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("DESIGN")
public class DesignStaff extends Staff {

    @Column(name = "design_specialization")
    private String designSpecialization;

    @Column(name = "software_skills")
    private String softwareSkills;

    @Column(name = "portfolio_url")
    private String portfolioUrl;
}
