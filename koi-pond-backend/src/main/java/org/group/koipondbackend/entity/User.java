package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    @Column(name = "full_name")
    String fullName;
    String password;

    @Column(unique = true)
    String email;

    @Column(name = "phone_number")
    String phoneNumber;
}
