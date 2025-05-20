package lk.chamasha.cv.filtering.controller;


import lk.chamasha.cv.filtering.model.Cv;
import lk.chamasha.cv.filtering.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CVController {
    @Autowired
    private CvService cvService;

    @PostMapping("/upload")
    public ResponseEntity<Cv> uploadCv(@RequestParam("file") MultipartFile file) {
        Cv savedCv = cvService.saveCv(file);
        return ResponseEntity.ok(savedCv);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cv> getCvById(@PathVariable Long id) {
        return cvService.getCvById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cvs")
    public ResponseEntity<List<Cv>> getAllCvs() {
        return ResponseEntity.ok(cvService.getAllCvs());
    }
}