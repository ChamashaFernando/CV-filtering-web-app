package lk.chamasha.cv.filtering.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cvs")
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private String extractedText;

    @ElementCollection
    @CollectionTable(name = "cv_technologies", joinColumns = @JoinColumn(name = "cv_id"))
    @Column(name = "technology")
    private List<String> matchedTechnologies;

    private String status;

    public Cv() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getExtractedText() { return extractedText; }
    public void setExtractedText(String extractedText) { this.extractedText = extractedText; }

    public List<String> getMatchedTechnologies() { return matchedTechnologies; }
    public void setMatchedTechnologies(List<String> matchedTechnologies) { this.matchedTechnologies = matchedTechnologies; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
