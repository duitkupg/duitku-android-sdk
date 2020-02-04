package com.duitku.sdk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duitku.sdk.DuitkuCallback.DuitkuCallbackTransaction;
import com.duitku.sdk.DuitkuClient;
import com.duitku.sdk.DuitkuUtility.BaseKitDuitku;
import com.duitku.sdk.DuitkuUtility.DuitkuKit;
import com.duitku.sdk.Model.ItemDetails;

import java.util.ArrayList;
import static com.duitku.sdk.sdkConfig.ENDPOINT_CHECK_TRX;
import static com.duitku.sdk.sdkConfig.ENDPOINT_LIST_PAYMENT;
import static com.duitku.sdk.sdkConfig.ENDPOINT_REQUEST_TRX;
import static com.duitku.sdk.sdkConfig.MERCHANT_BASE_URL;


public class MainActivity extends DuitkuClient {

    DuitkuKit duitku ;
    DuitkuCallbackTransaction callbackKit ;
    EditText etAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_payment = findViewById(R.id.btn_payment);
        duitku = new DuitkuKit();
        callbackKit =new DuitkuCallbackTransaction();
        etAmount = findViewById(R.id.et_amount);

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_amount = etAmount.getText().toString();

                if (s_amount.equals("")){
                    s_amount = "0" ;
                }

                int amount = Integer.parseInt(s_amount);

                if ( amount == 0 ){
                    etAmount.setError("silahkan masukan jumlah pembayaran");
                }else if(amount < 10000){
                    etAmount.setError("Minimal pembayaran Rp 10.000");
                }else {

                    //setting merchant
                    settingMerchant(amount);
                    startPayment(MainActivity.this);

                }

            }
        });

    }


    //REQUIRED
    @Override
    protected void onResume() {
        super.onResume();

        run();

    }


    //REQUIRED
    @Override
    public void onSuccessTransaction(String status, String reference, String amount, String Code) {
        //do nothing
        Toast.makeText(MainActivity.this,"Transaction"+status,Toast.LENGTH_LONG).show();

        clearSdkTask(); //REQUIRED
        super.onSuccessTransaction(status, reference, amount, Code);
    }

    @Override
    public void onPendingTransaction(String status, String reference, String amount, String Code) {
        //do nothing
        Toast.makeText(MainActivity.this,"Transaction"+status,Toast.LENGTH_LONG).show();

        clearSdkTask(); //REQUIRED
        super.onPendingTransaction(status, reference, amount, Code);
    }

    @Override
    public void onCancelTransaction(String status, String reference, String amount, String Code) {
        //do nothing
        Toast.makeText(MainActivity.this,"Transaction :"+status,Toast.LENGTH_LONG).show();

        clearSdkTask(); //REQUIRED
        super.onCancelTransaction(status, reference, amount, Code);
    }


    //REQUIRED
    private void settingMerchant(int amount){
        //set false if callback from duitku
        callbackKit.setCallbackFromMerchant(false);

        //run duitku sdk
        run();
        //set base url merchant
        BaseKitDuitku.setBaseUrlApiDuitku(MERCHANT_BASE_URL); //FROM MERCHANT //SERVER_MERCHANT_BASE_URL
        BaseKitDuitku.setUrlRequestTransaction(ENDPOINT_REQUEST_TRX); //Endpoint RequestTransaction
        BaseKitDuitku.setUrlCheckTransaction(ENDPOINT_CHECK_TRX); //Endpoint checkTransaction
        BaseKitDuitku.setUrlListPayment(ENDPOINT_LIST_PAYMENT); //Endpoint List Payment
        duitku.setPaymentAmount(amount);
        duitku.setEmail("bambangm88@gmail.com");//your merchant email
        duitku.setPhoneNumber("082219278906"); //your merchant phone
        duitku.setAdditionalParam(""); //optional
        duitku.setMerchantUserInfo(""); //optional
        duitku.setCustomerVaName("");
        duitku.setProductDetails("Pembelian Sepatu");
        duitku.setCallbackUrl("http://bambangm.com/callback");//required
        duitku.setReturnUrl("http://bambangm.com/returnUrl");//required
        duitku.setExpiryPeriod("60");

        //set item details
        ItemDetails itemDetails = new ItemDetails(10000,2,"shoes");
        ArrayList<ItemDetails> arrayList = new  ArrayList<ItemDetails> ();
        arrayList.add(itemDetails);
        duitku.setItemDetails(arrayList);
    }



}