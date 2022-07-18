package com.example.realestate.Model;

public class SellerResidentialModel {

    String date,name,building,aptno,area,price,mob1,mob2,type;

    public SellerResidentialModel(String date, String name, String building, String aptno, String area, String price, String mob1, String mob2, String type) {
        this.date = date;
        this.name = name;
        this.building = building;
        this.aptno = aptno;
        this.area = area;
        this.price = price;
        this.mob1 = mob1;
        this.mob2 = mob2;
        this.type = type;
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

    public String getAptno() {
        return aptno;
    }

    public void setAptno(String aptno) {
        this.aptno = aptno;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
