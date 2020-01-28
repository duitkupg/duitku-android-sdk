package com.duitku.sdk.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetListPaymentMethod {



    @SerializedName("paymentFee")
    @Expose
    private List<ResponseListPaymentMethod> paymentFee = null;


    @SerializedName("merchantcode")
    private String merchantcode;

    @SerializedName("mode")
    private String mode;

    @SerializedName("request")
    private String request;

    @SerializedName("paymentAmount")
    private int paymentAmount;

    @SerializedName("signature")
    private String signature;

    @SerializedName("reference")
    private String reference;




    public List<ResponseListPaymentMethod> getPaymentFee() {
        return paymentFee;
    }


    public ResponseGetListPaymentMethod(int amount) {
        this.paymentAmount= amount;

    }
}
