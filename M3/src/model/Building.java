package model;

import java.util.HashMap;

public class Building {
    private String address;
    private String garbageRoomID;

    public Building(String garbageRoomID, String address) {
        this.address = address;
        this.garbageRoomID = garbageRoomID;
    }

    public String getGarbageRoomID() {
        return garbageRoomID;
    }

    public void setGarbageRoomID(String garbageRoomID) {
        this.garbageRoomID = garbageRoomID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
