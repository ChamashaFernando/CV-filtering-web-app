package lk.chamasha.cv.filtering.service.impl;


import lk.chamasha.cv.filtering.model.Technology;
import lk.chamasha.cv.filtering.repository.TechnologyRepository;
import lk.chamasha.cv.filtering.service.TechnologyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {

    private TechnologyRepository technologyRepository;

    public Technology addTechnology(String name) {
        return technologyRepository.findByNameIgnoreCase(name.trim())
                .orElseGet(() -> technologyRepository.save(new Technology(name.trim())));
    }

    @Override
    public List<String> getAllTechnologies() {
        return technologyRepository.findAll()
                .stream()
                .map(Technology::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTechnology(Long id) {
        if (!technologyRepository.existsById(id)) {
            throw new RuntimeException("Technology not found with ID: " + id);
        }
        technologyRepository.deleteById(id);
    }

    @Override
    public Technology updateTechnology(Long id, String newName) {
        Technology technology = technologyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technology not found with ID: " + id));

        Optional<Technology> existing = technologyRepository.findByNameIgnoreCase(newName.trim());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new RuntimeException("Technology name already exists: " + newName);
        }

        technology.setName(newName.trim());
        return technologyRepository.save(technology);
    }
}