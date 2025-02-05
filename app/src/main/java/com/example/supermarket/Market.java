package com.example.supermarket;

public class Market {

    private int marketID;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;

    public Market() {
        marketID = -1;
    }

    public int getMarketID() {
        return marketID;
    }

    public void setMarketID(int i) {
        this.marketID = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String s) {
        this.address = s;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String s) {
        this.city = s;
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        this.state = s;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String s) {
        this.zip = s;
    }
}
