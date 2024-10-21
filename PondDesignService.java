package org.group.koipondbackend.service;

import org.group.koipondbackend.dto.PondDesignDTO;
import org.group.koipondbackend.entity.KoiPond;
import org.group.koipondbackend.entity.PondDesign;
import org.group.koipondbackend.mapper.PondDesignMapper;
import org.group.koipondbackend.repository.KoiPondRepository;
import org.group.koipondbackend.repository.PondDesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PondDesignService {

    @Autowired
    private PondDesignRepository pondDesignRepository;

    @Autowired
    private PondDesignMapper pondDesignMapper;

    @Autowired
    private KoiPondRepository koiPondRepository; // Thêm repository cho KoiPond

    public List<PondDesignDTO> getAllPondDesigns() {
        List<PondDesign> designs = pondDesignRepository.findAll();
        return pondDesignMapper.toDtoList(designs);
    }

    public PondDesignDTO getPondDesignById(Long id) {
        Optional<PondDesign> design = pondDesignRepository.findById(id);
        return design.map(pondDesignMapper::toDto).orElse(null);
    }

    public PondDesignDTO createPondDesign(PondDesignDTO dto) {
        PondDesign entity = pondDesignMapper.toEntity(dto);
        PondDesign savedDesign = pondDesignRepository.save(entity);
        return pondDesignMapper.toDto(savedDesign);
    }

    public PondDesignDTO updatePondDesign(Long id, PondDesignDTO dto) {
        if (!pondDesignRepository.existsById(id)) {
            return null; // Có thể ném ra ngoại lệ
        }

        PondDesign entity = pondDesignMapper.toEntity(dto);
        entity.setId(id); // Đảm bảo ID được đặt đúng
        PondDesign updatedDesign = pondDesignRepository.save(entity);
        return pondDesignMapper.toDto(updatedDesign);
    }

    public boolean deletePondDesign(Long id) {
        if (pondDesignRepository.existsById(id)) {
            pondDesignRepository.deleteById(id);
            return true;
        }
        return false; // Có thể ném ra ngoại lệ
    }

    public Double estimateCost(Long id) {
        Optional<PondDesign> design = pondDesignRepository.findById(id);
        return design.map(PondDesign::getCostEstimate).orElse(null);
    }

    // Hàm áp dụng thiết kế cho hồ cá Koi
    public void applyDesignToPond(Long pondId, PondDesignDTO designDto) {
        KoiPond koiPond = koiPondRepository.findById(pondId)
                .orElseThrow(() -> new RuntimeException("Koi Pond not found"));

        PondDesign pondDesign = pondDesignRepository.findById(designDto.getId())
                .orElseThrow(() -> new RuntimeException("Pond Design not found"));

        koiPond.setPondDesign(pondDesign);  // Thiết lập mẫu thiết kế cho hồ cá
        koiPondRepository.save(koiPond);  // Lưu hồ cá với thiết kế đã được áp dụng
    }
}
