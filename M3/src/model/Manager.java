package model;

public class Manager {
    private String name;
    private String id;
    private String contactNum;

    public Manager(String name, String id, String contactNum) {
        this.name = name;
        this.id = id;
        this.contactNum = contactNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}
