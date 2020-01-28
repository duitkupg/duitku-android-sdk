package com.duitku.sdk.Model;

import com.google.gson.annotations.SerializedName;


public class ResponseListPaymentMethod {


    @SerializedName("paymentImage")
    private String paymentImage;

    @SerializedName("paymentName")
    private String paymentName;

    @SerializedName("paymentMethod")
    private String paymentMethod;

    @SerializedName("totalFee")
    private String totalFee;






    public String getPaymentImage() {
        return paymentImage;
    }

    public void setPaymentImage(String paymentImage) {
        this.paymentImage = paymentImage;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }









}
