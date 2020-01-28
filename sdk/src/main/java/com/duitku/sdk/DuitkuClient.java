package com.duitku.sdk;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.duitku.sdk.DuitkuUtility.DuitkuKit;

import java.util.List;


public class DuitkuClient extends AppCompatActivity {

    public static String code = "";
    public static String status = "";
    public static String amount = "";
    public static String reference = "";
    public static String topUpNotif = "";

    public void onSuccessTransaction(String status, String reference, String amount, String Code) {

    }

    public void onPendingTransaction(String status, String reference, String amount, String Code) {

    }

    public void onCancelTransaction(String status, String reference, String amount, String Code) {

    }


    public void onDone() {

    }

    public void clearSdkTask() {
        code = "";
        status = "";
        amount = "";
        reference = "";
        topUpNotif = "";
        //num = 0 ;
    }

    public void run() {


        if (code.equals("00")) {
            onSuccessTransaction(status, reference, amount, code);
        } else if (code.equals("01")) {
            onPendingTransaction(status, reference, amount, code);
        } else if (code.equals("02")) {
            onCancelTransaction(status, reference, amount, code);
        } else if (code.equals("03")) {
            onCancelTransaction(status, reference, amount, code);
        }else {

        }


    }

    public void duitkuFinish() {


        if (code.equals("00")) {
            onDone();
        } else if (code.equals("01")) {
            onDone();
        } else if (code.equals("02")) {
            onDone();
        } else if (code.equals("03")) {
            onDone();
        } else {

        }

    }

    public void FinishTopUpNotify() {

        //CC
        if (topUpNotif.contains("TopUp") || topUpNotif == "Notification") {
            onDone();
        }


    }


    public void startPayment(Context context) {

        context.startActivity(new Intent(context, ListPaymentMethodDuitku.class));

    }

}