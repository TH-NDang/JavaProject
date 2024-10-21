package org.group.koipondbackend.service;

import org.group.koipondbackend.entity.KoiFish;
import org.group.koipondbackend.repository.KoiFishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoiFishService {

    @Autowired
    private KoiFishRepository koiFishRepository;

    public List<KoiFish> getAllKoiFish() {
        return koiFishRepository.findAll();
    }

    public KoiFish getKoiFishById(Long id) {
        return koiFishRepository.findById(id).orElse(null);
    }

    public KoiFish createKoiFish(KoiFish koiFish) {
        return koiFishRepository.save(koiFish);
    }

    public KoiFish updateKoiFish(Long id, KoiFish koiFish) {
        if (!koiFishRepository.existsById(id)) {
            return null;
        }
        koiFish.setId(id);
        return koiFishRepository.save(koiFish);
    }

    public boolean deleteKoiFish(Long id) {
        if (!koiFishRepository.existsById(id)) {
            return false;
        }
        koiFishRepository.deleteById(id);
        return true;
    }

    public List<KoiFish> getKoiFishInPond(Long pondId) {
        return koiFishRepository.findByPondId(pondId);
    }
}
