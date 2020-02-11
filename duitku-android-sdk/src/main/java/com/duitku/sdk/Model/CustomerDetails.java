package com.duitku.sdk.Model;

import java.util.ArrayList;

public class CustomerDetails {


    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private ArrayList<Address> billingAddress ;
    private ArrayList<Address> shippingAddress ;

    public CustomerDetails(String firstName, String lastName, String email, String phoneNumber , ArrayList<Address> billingAddress , ArrayList<Address> shippingAddress ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }



}
