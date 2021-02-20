package model;

import java.util.Date;

public class GarbageRoom {
    private String clearDate;
    private String id;
    private String address;

    public GarbageRoom(String clearDate, String id, String address) {
        this.clearDate = clearDate;
        this.id = id;
        this.address = address;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
