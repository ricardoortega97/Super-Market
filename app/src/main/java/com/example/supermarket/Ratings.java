package com.example.supermarket;

public class Ratings {

    private int ratingID;
    private float liquor;
    private float meat;
    private float produce;
    private float cheese;
    private float ease;

    private int marketID;

    public int getMarketID() {
        return marketID;
    }

    public void setMarketID(int i) {
       this.marketID = i;
    }

    public Ratings() {
        ratingID = -1;
    }

    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int i) {
        ratingID = i;
    }

    public float getLiquor() {
        return liquor;
    }

    public void setLiquor(float f) {
        this.liquor = f;
    }

    public float getMeat() {
        return meat;
    }

    public void setMeat(float f) {
        this.meat = f;
    }

    public float getProduce() {
        return produce;
    }

    public void setProduce(float f) {
        this.produce = f;
    }


    public float getCheese() {
        return cheese;
    }

    public void setCheese(float f) {
        this.cheese = f;
    }

    public float getEase() {
        return ease;
    }

    public void setEase(float f) {
        this.ease = f;
    }
}
