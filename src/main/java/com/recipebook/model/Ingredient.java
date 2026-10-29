package com.recipebook.model;

public final class Ingredient {

    private final String name;
    private final double quantity;
    private final String unit;

    public Ingredient(String name, double quantity, String unit) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or blank");
        }
        if (!(quantity >0)) {
            throw new IllegalArgumentException("Ingredient quantity must be greater than zero.");
        }
        this.name = name.trim();
        this.quantity = quantity;
        this.unit = unit == null ? "" : unit.trim();
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        String amount;

        if (quantity == (int) quantity) {
            amount = String.valueOf((int) quantity);
        } else {
            amount = String.valueOf(quantity);
        }

        if (unit.isEmpty()) {
            return amount + " " + name;
        } else {
            return amount + " " + unit + " " + name;
        }
    }

}
