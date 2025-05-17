package lk.chamasha.cv.filtering.service.impl;


import lk.chamasha.cv.filtering.controller.response.CVFilterResponse;
import lk.chamasha.cv.filtering.model.CV;
import lk.chamasha.cv.filtering.repository.CVRepository;
import lk.chamasha.cv.filtering.service.CVService;
import lk.chamasha.cv.filtering.util.PDFTextExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.stream.Collectors;

@Service

public class CVServiceImpl implements CVService {

    private final CVRepository repository;

    private final List<String> requiredTechnologies = List.of(
            "Spring Data JPA",
            "Hibernate",
            "Spring Data JDBC",
            "Spring Data MongoDB",
            "Spring Data Redis",
            "Flyway",
            "Liquibase",
            "Docker",
            "JUnit 5",
            "Mockito",
            "Testcontainers",
            "Kubernetes"
    );

    public CVServiceImpl(CVRepository repository) {
        this.repository = repository;
    }

    @Override
    public CVFilterResponse filterCV(MultipartFile file) {
        try {
            String text = PDFTextExtractor.extractText(file).toLowerCase();
            List<String> matchedTechnologies = requiredTechnologies.stream()
                    .filter(tech -> text.contains(tech.toLowerCase()))
                    .collect(Collectors.toList());

            List<String> missingTechnologies = requiredTechnologies.stream()
                    .filter(tech -> !matchedTechnologies.contains(tech))
                    .collect(Collectors.toList());

            repository.save(new CV(file.getOriginalFilename(), text));

            boolean accepted = matchedTechnologies.size() == requiredTechnologies.size();
            boolean partiallyAccepted = matchedTechnologies.size() > 0 && !accepted;

            if (accepted) {
                return new CVFilterResponse(true, List.of(), matchedTechnologies);
            } else if (partiallyAccepted) {
                return new CVFilterResponse(false, missingTechnologies, matchedTechnologies);
            } else {
                return new CVFilterResponse(false, requiredTechnologies, List.of());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error processing CV", e);
        }
    }
}
