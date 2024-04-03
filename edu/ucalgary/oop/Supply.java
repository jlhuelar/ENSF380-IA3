package edu.ucalgary.oop;

public class Supply {
    private String type;
    private int quantity;

    public Supply(String type, int quantity) {
        setType(type); // Use the setter to apply validation
        setQuantity(quantity); // Use the setter to apply validation
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }
}