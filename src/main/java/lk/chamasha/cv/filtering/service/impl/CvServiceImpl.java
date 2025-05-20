package lk.chamasha.cv.filtering.service.impl;


import lk.chamasha.cv.filtering.model.Cv;
import lk.chamasha.cv.filtering.repository.CvRepository;
import lk.chamasha.cv.filtering.service.CvService;
import lk.chamasha.cv.filtering.service.TechnologyService;
import lk.chamasha.cv.filtering.util.CvTextExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CvServiceImpl implements CvService {
    @Autowired
    private CvRepository cvRepository;

    @Autowired
    private TechnologyService technologyService;

    @Override
    public Cv saveCv(MultipartFile file) {
        String cvText = "";

        try {
            String contentType = file.getContentType();
            if ("application/pdf".equals(contentType)) {
                cvText = CvTextExtractor.extractTextFromPdf(file);
            } else if ("text/plain".equals(contentType)) {
                cvText = CvTextExtractor.extractTextFromTxt(file);
            } else {
                throw new RuntimeException("Unsupported file type");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract text from CV", e);
        }

        List<String> requiredTechs = technologyService.getAllTechnologies();
        List<String> matchedTechs = new ArrayList<>();
        String lowerCvText = cvText.toLowerCase();

        for (String tech : requiredTechs) {
            if (lowerCvText.contains(tech.toLowerCase())) {
                matchedTechs.add(tech);
            }
        }

        String status;
        if (matchedTechs.size() == requiredTechs.size() && !requiredTechs.isEmpty()) {
            status = "approved";
        } else if (!matchedTechs.isEmpty()) {
            status = "partial";
        } else {
            status = "rejected";
        }

        Cv cv = new Cv();
        cv.setFileName(file.getOriginalFilename());
        cv.setExtractedText(cvText);
        cv.setMatchedTechnologies(matchedTechs);
        cv.setStatus(status);

        return cvRepository.save(cv);
    }

    @Override
    public Optional<Cv> getCvById(Long id) {
        return cvRepository.findById(id);
    }

    @Override
    public List<Cv> getAllCvs() {
        return cvRepository.findAll();
    }
}
