package lk.chamasha.cv.filtering.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private LocalDateTime uploadedAt;

    @Lob
    private String content;

    public CV() {}

    public CV(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.uploadedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }
}