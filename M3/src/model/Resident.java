package model;

import java.time.LocalDate;
import java.util.Date;

public class Resident {
    private String sin;
    private String name;
    private String birthday;
    private int unitNum;

    public Resident(String sin, String name, String birthday, int unitNum) {
        this.sin = sin;
        this.name = name;
        this.birthday = birthday;
        this.unitNum = unitNum;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }
}
