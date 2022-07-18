package com.example.realestate.Model;

public class LicenseeCommercialModel {
    String date;
    String name;
    String mob1;
    String mob2;
    String type;
    String remarks;

    public LicenseeCommercialModel(String date, String name, String mob1, String mob2, String type, String remarks) {
        this.date = date;
        this.name = name;
        this.mob1 = mob1;
        this.mob2 = mob2;
        this.type = type;
        this.remarks = remarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob1() {
        return mob1;
    }

    public void setMob1(String mob1) {
        this.mob1 = mob1;
    }

    public String getMob2() {
        return mob2;
    }

    public void setMob2(String mob2) {
        this.mob2 = mob2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
