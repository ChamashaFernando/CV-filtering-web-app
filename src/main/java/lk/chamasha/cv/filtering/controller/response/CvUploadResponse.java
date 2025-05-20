package lk.chamasha.cv.filtering.controller.response;

import java.util.List;

public class CvUploadResponse {
    private boolean approved;
    private List<String> matchedTechnologies;
    private String message;

    public CvUploadResponse() {}

    public CvUploadResponse(boolean approved, List<String> matchedTechnologies, String message) {
        this.approved = approved;
        this.matchedTechnologies = matchedTechnologies;
        this.message = message;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public List<String> getMatchedTechnologies() {
        return matchedTechnologies;
    }

    public void setMatchedTechnologies(List<String> matchedTechnologies) {
        this.matchedTechnologies = matchedTechnologies;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
