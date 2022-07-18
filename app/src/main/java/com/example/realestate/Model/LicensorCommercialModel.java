package com.example.realestate.Model;

public class LicensorCommercialModel {
    String date;
    String name;
    String building;
    String type;
    String area;
    String location;
    String mob1;
    String mob2;
    String remarks;

    public LicensorCommercialModel(String date, String name, String building, String type, String area, String location, String mob1, String mob2, String remarks) {
        this.date = date;
        this.name = name;
        this.building = building;
        this.type = type;
        this.area = area;
        this.location = location;
        this.mob1 = mob1;
        this.mob2 = mob2;
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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
