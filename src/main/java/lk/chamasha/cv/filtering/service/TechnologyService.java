package lk.chamasha.cv.filtering.service;



import lk.chamasha.cv.filtering.model.Technology;

import java.util.List;

public interface TechnologyService {
    Technology addTechnology(String name);
    List<String> getAllTechnologies();
    void deleteTechnology(Long id);
    Technology updateTechnology(Long id, String newName);
}