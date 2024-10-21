package org.group.koipondbackend.service;

import org.group.koipondbackend.dto.MaintenanceServiceDTO;
import org.group.koipondbackend.entity.KoiPond;
import org.group.koipondbackend.entity.MaintenanceService;
import org.group.koipondbackend.mapper.MaintenanceServiceMapper;
import org.group.koipondbackend.repository.KoiPondRepository;
import org.group.koipondbackend.repository.MaintenanceServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceService {

    @Autowired
    private MaintenanceServiceRepository maintenanceServiceRepository;

    @Autowired
    private MaintenanceServiceMapper maintenanceServiceMapper;

    @Autowired
    private KoiPondRepository koiPondRepository; // Thêm repository cho KoiPond

    public List<MaintenanceServiceDTO> getAllMaintenanceServices() {
        List<MaintenanceService> services = maintenanceServiceRepository.findAll();
        return maintenanceServiceMapper.toDtoList(services);
    }

    public MaintenanceServiceDTO getMaintenanceServiceById(Long id) {
        Optional<MaintenanceService> service = maintenanceServiceRepository.findById(id);
        return service.map(maintenanceServiceMapper::toDto).orElse(null);
    }

    public MaintenanceServiceDTO createMaintenanceService(MaintenanceServiceDTO dto) {
        MaintenanceService entity = maintenanceServiceMapper.toEntity(dto);
        MaintenanceService savedService = maintenanceServiceRepository.save(entity);
        return maintenanceServiceMapper.toDto(savedService);
    }

    public MaintenanceServiceDTO updateMaintenanceService(Long id, MaintenanceServiceDTO dto) {
        if (!maintenanceServiceRepository.existsById(id)) {
            return null; // Có thể ném ra ngoại lệ
        }

        MaintenanceService entity = maintenanceServiceMapper.toEntity(dto);
        entity.setId(id); // Đảm bảo ID được đặt đúng
        MaintenanceService updatedService = maintenanceServiceRepository.save(entity);
        return maintenanceServiceMapper.toDto(updatedService);
    }

    public boolean deleteMaintenanceService(Long id) {
        if (maintenanceServiceRepository.existsById(id)) {
            maintenanceServiceRepository.deleteById(id);
            return true;
        }
        return false; // Có thể ném ra ngoại lệ
    }

    // Hàm đặt lịch cho dịch vụ bảo dưỡng
    public void scheduleMaintenance(Long id, String scheduleDetails) {
        // Logic để lưu trữ lịch bảo dưỡng, có thể sử dụng một entity khác để quản lý lịch
        // Đây là một ví dụ đơn giản. Có thể thêm logic cụ thể hơn tùy theo yêu cầu.
    }

    // Hàm thêm dịch vụ bảo dưỡng vào hồ cá Koi
    public MaintenanceServiceDTO addServiceToPond(Long pondId, MaintenanceServiceDTO dto) {
        KoiPond koiPond = koiPondRepository.findById(pondId)
                .orElseThrow(() -> new RuntimeException("Koi Pond not found"));

        MaintenanceService maintenanceService = maintenanceServiceMapper.toEntity(dto);
        maintenanceService.setKoiPond(koiPond);  // Thêm quan hệ với hồ cá

        MaintenanceService savedService = maintenanceServiceRepository.save(maintenanceService);

        return maintenanceServiceMapper.toDto(savedService);
    }
}
