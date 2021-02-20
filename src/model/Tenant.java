package model;

import java.util.Date;

public class Tenant {
    private String sin;
    private int monthlyRent;

    public Tenant(String sin, int monthlyRent) {
        this.sin = sin;
        this.monthlyRent = monthlyRent;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(int monthlyRent) {
        this.monthlyRent = monthlyRent;
    }
}
