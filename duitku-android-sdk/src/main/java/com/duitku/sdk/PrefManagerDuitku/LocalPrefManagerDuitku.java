package com.duitku.sdk.PrefManagerDuitku;

import android.content.Context;
import android.content.SharedPreferences;


public class LocalPrefManagerDuitku {

    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor setPref;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "paymentSDK";

    private static final String TAG_TRANSACTION = "N";
    // All Shared Preferences Keys
    private static final String TAG_STATUS = "statusMessage";
    private static final String TAG_REFERENCE = "reference";
    private static final String TAG_AMOUNT = "amount";
    private static final String TAG_CODE = "statusCode";
    private static final String TAG_TOTAL = "total";


    // Constructor
    public LocalPrefManagerDuitku(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        setPref = pref.edit();
    }


    public void createLocalData( String status,String reference, String amount, String Code){

        setPref.putBoolean(TAG_TRANSACTION, true);
        setPref.putString(TAG_STATUS, status);
        setPref.putString(TAG_REFERENCE, reference);
        setPref.putString(TAG_AMOUNT, amount);
        setPref.putString(TAG_CODE, Code);
        setPref.commit();

    }


    public void createTotal( String total ){

        setPref.putString(TAG_TOTAL, total);
        setPref.commit();

    }

    public String getTagTotalAmount() {

        return pref.getString(TAG_TOTAL, "");
    }



    public String getStatusMessage() {

        return pref.getString(TAG_STATUS, "");
    }

    public String getStatusCode() {

        return pref.getString(TAG_CODE, "");
    }

    public String getReference() {

        return pref.getString(TAG_REFERENCE, "");
    }

    public String getAmount() {

        return pref.getString(TAG_AMOUNT, "");
    }



    public void onfinish(){
        // Clearing all data from Shared Preferences
        setPref.clear();
        setPref.commit();
    }

    public boolean isTransaction(){
        return pref.getBoolean(TAG_TRANSACTION, false);
    }


}
