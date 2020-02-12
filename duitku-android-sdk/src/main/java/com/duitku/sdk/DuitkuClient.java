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

    public void run(Context context) {


        if (code.equals(context.getString(R.string.zero))) {
            onSuccessTransaction(status, reference, amount, code);
        } else if (code.equals(context.getString(R.string.one))) {
            onPendingTransaction(status, reference, amount, code);
        } else if (code.equals(context.getString(R.string.two))) {
            onCancelTransaction(status, reference, amount, code);
        } else if (code.equals(context.getString(R.string.three))) {
            onCancelTransaction(status, reference, amount, code);
        }else {

        }


    }

    public void duitkuFinish(Context context) {


        if (code.equals(context.getString(R.string.zero))) {
            onDone();
        } else if (code.equals(context.getString(R.string.one))) {
            onDone();
        } else if (code.equals(context.getString(R.string.two))) {
            onDone();
        } else if (code.equals(context.getString(R.string.three))) {
            onDone();
        } else {

        }

    }

    public void FinishTopUpNotify(Context context) {

        //CC
        if (topUpNotif.contains(context.getString(R.string.topUp)) || topUpNotif.equals(context.getString(R.string.Notification))) {
            onDone();
        }


    }


    public void startPayment(Context context) {

        context.startActivity(new Intent(context, ListPaymentMethodDuitku.class));

    }

}