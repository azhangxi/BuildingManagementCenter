package model;

public class SINandName {
    private String sin;
    private String ownerName;

    public SINandName(String sin, String ownerName) {
        this.sin = sin;
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }
}
