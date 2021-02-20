package model;

public class Serve {
    private String company;
    private String id;
    private String address;

    public Serve(String company, String id, String address) {
        this.company = company;
        this.id = id;
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPostmanID() {
        return id;
    }

    public void setPostmanID(String postmanID) {
        this.id = postmanID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
