package model;

import java.util.Objects;

public class ParkingSpot {
    private String availability;
    private String id;
    private String ownerName;
    private String address;
    private int unitNum;
    private String sin;

    public ParkingSpot(String availability, String id, String ownerName, String address, int unitNum, String sin) {
        this.availability = availability;
        this.id = id;
        this.address = address;
        this.availability = availability;
        this.ownerName = ownerName;
        this.unitNum = unitNum;
        this.sin = sin;
    }

    public String isAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
