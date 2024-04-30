package com.lim.sarisarisaya;

public class Duck {
    // Fields to store duck image resource ID, prize image resource ID, and duck value
    private int duckImage;
    private int priceImage;
    private int duckValue;

    // Constructor to initialize a Duck object with only duck image
    public Duck(int duckImage) {
        this.duckImage = duckImage;
    }

    // Constructor to initialize a Duck object with both duck image and duck value
    public Duck(int duckImage, int duckValue) {
        this.duckImage = duckImage;
        this.duckValue = duckValue;
    }

    // Getter method to retrieve duck image resource ID
    public int getDuckImage() {
        return duckImage;
    }

    // Setter method to set duck image resource ID
    public void setDuckImage(int duckImage) {
        this.duckImage = duckImage;
    }

    // Getter method to retrieve prize image resource ID
    public int getPriceImage() {
        return priceImage;
    }

    // Setter method to set prize image resource ID
    public void setPriceImage(int priceImage) {
        this.priceImage = priceImage;
    }

    // Getter method to retrieve duck value
    public int getDuckValue() {
        return duckValue;
    }

    // Setter method to set duck value
    public void setDuckValue(int duckValue) {
        this.duckValue = duckValue;
    }
}
