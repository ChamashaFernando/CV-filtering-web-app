package lk.chamasha.cv.filtering.controller.request;
public class AddTechnologyRequest {
    private String name;

    public AddTechnologyRequest() {}

    public AddTechnologyRequest(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

