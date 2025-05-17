package lk.chamasha.cv.filtering.controller.response;

import lk.chamasha.cv.filtering.model.CVStatus;

import java.util.List;

public class CVFilterResponse {
    private boolean accepted;
    private CVStatus status;
    private List<String> missingTechnologies;
    private List<String> matchedTechnologies;

    public CVFilterResponse(boolean accepted, List<String> missingTechnologies, List<String> matchedTechnologies) {
        this.accepted = accepted;
        this.missingTechnologies = missingTechnologies;
        this.matchedTechnologies = matchedTechnologies;
        this.status = accepted
                ? CVStatus.ACCEPTED
                : matchedTechnologies.isEmpty() ? CVStatus.REJECTED : CVStatus.PARTIALLY_ACCEPTED;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public CVStatus getStatus() {
        return status;
    }

    public List<String> getMissingTechnologies() {
        return missingTechnologies;
    }

    public List<String> getMatchedTechnologies() {
        return matchedTechnologies;
    }
}