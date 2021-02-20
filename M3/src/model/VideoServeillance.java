package model;

public class VideoServeillance {
    private String id;
    private int floor;
    private String address;

    public VideoServeillance(String id, int floor, String address) {
        this.id = id;
        this.floor = floor;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

//3588 Crowley Drive, Vancouver, BC, V5R 6H3