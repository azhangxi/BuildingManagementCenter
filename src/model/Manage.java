package model;

public class Manage {
    private String managerID;
    private String address;

    public Manage(String managerID, String address) {
        this.managerID = managerID;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }
}
