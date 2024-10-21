package org.group.koipondbackend.service;

import org.group.koipondbackend.dto.KoiPondDTO;
import org.group.koipondbackend.entity.KoiPond;
import org.group.koipondbackend.mapper.KoiPondMapper;
import org.group.koipondbackend.repository.KoiPondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KoiPondService {

    @Autowired
    private KoiPondRepository koiPondRepository;

    @Autowired
    private KoiPondMapper koiPondMapper;

    public List<KoiPondDTO> getAllPonds() {
        return koiPondRepository.findAll().stream()
                .map(koiPondMapper::toDto)
                .collect(Collectors.toList());
    }

    public KoiPondDTO getPondById(Long id) {
        return koiPondRepository.findById(id)
                .map(koiPondMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Pond not found"));
    }

    public KoiPondDTO createPond(KoiPondDTO dto) {
        KoiPond pond = koiPondMapper.toEntity(dto);
        KoiPond savedPond = koiPondRepository.save(pond);
        return koiPondMapper.toDto(savedPond);
    }

    public KoiPondDTO updatePond(Long id, KoiPondDTO dto) {
        KoiPond pond = koiPondRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pond not found"));
        // update pond properties
        pond.setName(dto.getName());
        pond.setSizeInLiters(dto.getSizeInLiters());
        pond.setDepthInMeters(dto.getDepthInMeters());
        // and others...

        KoiPond updatedPond = koiPondRepository.save(pond);
        return koiPondMapper.toDto(updatedPond);
    }

    public void deletePond(Long id) {
        koiPondRepository.deleteById(id);
    }
}
