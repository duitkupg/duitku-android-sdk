package com.duitku.sdk.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ResponseCheckTransaction {

    @SerializedName("reference")
    String reference;

    @SerializedName("paymentUrl")
    String paymentUrl;

    @SerializedName("amount")
    String amount;

    @SerializedName("statusCode")
    String statusCode;

    @SerializedName("statusMessage")
    String statusMessage;



    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }


    public ResponseCheckTransaction(String reference ) {
        this.reference = reference;
    }




}
