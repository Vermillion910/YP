package com.example.vermillion.Service;

import com.example.vermillion.DTO.DeveloperDto;
import com.example.vermillion.Model.Developer;
import com.example.vermillion.Repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public Optional<Developer> findById(Long id) {
        return developerRepository.findById(id);
    }

    @Transactional
    public Developer create(DeveloperDto dto) {
        Developer developer = new Developer();
        developer.setFirstName(dto.getFirstName());
        developer.setLastName(dto.getLastName());
        developer.setEmail(dto.getEmail());
        developer.setPhoneNumber(dto.getPhoneNumber());
        developer.setSpecialization(dto.getSpecialization());
        return developerRepository.save(developer);
    }

    @Transactional
    public Optional<Developer> update(Long id, DeveloperDto dto) {
        return developerRepository.findById(id).map(existing -> {
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            existing.setEmail(dto.getEmail());
            existing.setPhoneNumber(dto.getPhoneNumber());
            existing.setSpecialization(dto.getSpecialization());
            return developerRepository.save(existing);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (developerRepository.existsById(id)) {
            developerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
