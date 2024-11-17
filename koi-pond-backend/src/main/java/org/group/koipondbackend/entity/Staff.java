package org.group.koipondbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Staff extends User {
    @Column(name = "department", columnDefinition = "NVARCHAR(255)")
    private String department;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "status", columnDefinition = "NVARCHAR(50)")
    private String status; // ACTIVE, INACTIVE, ON_LEAVE, etc.
}
