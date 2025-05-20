package lk.chamasha.cv.filtering.controller;


import lk.chamasha.cv.filtering.controller.request.AddTechnologyRequest;
import lk.chamasha.cv.filtering.model.Technology;
import lk.chamasha.cv.filtering.service.TechnologyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TechnologyController {


    @Autowired
    private TechnologyService technologyService;

    @PostMapping("/technologies")
    public ResponseEntity<Technology> addTechnology(@RequestBody AddTechnologyRequest request) {
        Technology tech = technologyService.addTechnology(request.getName());
        return ResponseEntity.ok(tech);
    }

    @GetMapping("/technologies")
    public ResponseEntity<List<String>> getAllTechnologies() {
        return ResponseEntity.ok(technologyService.getAllTechnologies());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTechnology(@PathVariable Long id) {
        technologyService.deleteTechnology(id);
        return ResponseEntity.ok("Technology deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable Long id,
                                                       @RequestBody AddTechnologyRequest request) {
        Technology updated = technologyService.updateTechnology(id, request.getName());
        return ResponseEntity.ok(updated);
    }
}