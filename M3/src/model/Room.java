package model;

import java.util.ArrayList;

public class Room {
    private int unitNum;
    private String address;
    private String parkingID;

    public Room(int unitNum, String address, String parkingID) {
        this.unitNum = unitNum;
        this.address = address;
        this.parkingID = parkingID;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParkingID() {
        return parkingID;
    }

    public void setParkingID(String parkingID) {
        this.parkingID = parkingID;
    }
}
