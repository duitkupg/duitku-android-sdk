package com.duitku.sdk.Model;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ItemDetails {
    private int price;
    private int quantity;
    private String name;

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ItemDetails( int price, int quantity, String name) {
        this.name = name;
        this.price= price;
        this.quantity = quantity;
    }



}
