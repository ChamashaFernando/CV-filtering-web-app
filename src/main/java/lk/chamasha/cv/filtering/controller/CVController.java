package lk.chamasha.cv.filtering.controller;


import lk.chamasha.cv.filtering.controller.response.CVFilterResponse;
import lk.chamasha.cv.filtering.service.CVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/cv")
public class CVController {

    private final CVService cvService;

    public CVController(CVService cvService) {
        this.cvService = cvService;
    }

    @PostMapping("/upload")
    public ResponseEntity<CVFilterResponse> uploadCV(@RequestParam("file") MultipartFile file) {
        CVFilterResponse response = cvService.filterCV(file);
        return ResponseEntity.ok(response);
    }
}
