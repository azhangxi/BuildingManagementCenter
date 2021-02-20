package model;

public class Postman {
    private String company;
    private String id;
    private String name;

    public Postman(String company, String id, String name) {
        this.company = company;
        this.id = id;
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
