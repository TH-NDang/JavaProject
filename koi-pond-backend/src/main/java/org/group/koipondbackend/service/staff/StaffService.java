package org.group.koipondbackend.service.staff;

import lombok.RequiredArgsConstructor;

import org.group.koipondbackend.dto.CreateStaffRequest;
import org.group.koipondbackend.dto.StaffDto;
import org.group.koipondbackend.dto.UpdateStaffRequest;
import org.group.koipondbackend.entity.Staff;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.repository.StaffRepository;
import org.group.koipondbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public StaffDto createStaff(CreateStaffRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Staff staff = Staff.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(Role.STAFF)
                .department(request.getDepartment())
                .status("ACTIVE")
                .joinDate(LocalDateTime.now())
                .build();

        Staff savedStaff = staffRepository.save(staff);
        return mapToDto(savedStaff);
    }

    @Transactional(readOnly = true)
    public List<StaffDto> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StaffDto getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        return mapToDto(staff);
    }

    @Transactional
    public StaffDto updateStaff(Long id, UpdateStaffRequest request) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));

        staff.setFullName(request.getFullName());
        staff.setPhone(request.getPhone());
        staff.setAddress(request.getAddress());
        staff.setDepartment(request.getDepartment());

        Staff updatedStaff = staffRepository.save(staff);
        return mapToDto(updatedStaff);
    }

    @Transactional
    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new ResourceNotFoundException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }

    private StaffDto mapToDto(Staff staff) {
        return StaffDto.builder()
                .id(staff.getId())
                .email(staff.getEmail())
                .fullName(staff.getFullName())
                .phone(staff.getPhone())
                .address(staff.getAddress())
                .role(staff.getRole())
                .department(staff.getDepartment())
                .status(staff.getStatus())
                .joinDate(staff.getJoinDate())
                .build();
    }
}
