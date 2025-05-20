package lk.chamasha.cv.filtering.controller.request;

import org.springframework.web.multipart.MultipartFile;

public class CvUploadRequest {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
