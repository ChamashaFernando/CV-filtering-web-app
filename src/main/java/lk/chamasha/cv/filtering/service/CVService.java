package lk.chamasha.cv.filtering.service;


import lk.chamasha.cv.filtering.controller.response.CVFilterResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CVService {
    CVFilterResponse filterCV(MultipartFile file);
}
