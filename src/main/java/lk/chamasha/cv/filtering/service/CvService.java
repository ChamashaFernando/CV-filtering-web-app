package lk.chamasha.cv.filtering.service;


import lk.chamasha.cv.filtering.controller.response.CvUploadResponse;
import lk.chamasha.cv.filtering.model.Cv;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CvService {
    Cv saveCv(MultipartFile file);
    Optional<Cv> getCvById(Long id);
    List<Cv> getAllCvs();
}
