package com.duitku.sdk;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.duitku.sdk.DuitkuCallback.DuitkuCallbackTransaction;
import com.duitku.sdk.DuitkuUtility.BaseKitDuitku;
import com.duitku.sdk.DuitkuUtility.DuitkuKit;
import com.duitku.sdk.Mode.PASSPORT;
import com.duitku.sdk.Mode.SANDBOX;
import com.duitku.sdk.Model.Address;
import com.duitku.sdk.Model.CustomerDetails;
import com.duitku.sdk.Model.ResponseCheckTransaction;
import com.duitku.sdk.Network.NetworkService;
import com.duitku.sdk.Network.ServerNetwork;
import com.duitku.sdk.Model.ResponseTransaction;
import com.duitku.sdk.PrefManagerDuitku.LocalPrefManagerDuitku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DuitkuTransaction extends AppCompatActivity {

    private NetworkService API;
    private RelativeLayout pCustomDialog;
    private RelativeLayout pCustomDialog_error;
    private ImageView iv_spinner;
    private ImageView iv_error;
    private TextView stat_error;
    private TextView txt_toolbar_title;
    private TextView txt_toolbar_title_top;
    public  int num = 0 ;
    private DuitkuCallbackTransaction callbackKit ;

    public  Boolean isCheckTransactionDOne = false ;

    private String reference;

    private LocalPrefManagerDuitku prefManager;
    private RelativeLayout view_toolbar ;
    private RelativeLayout view_toolbar_top ;

    private WebView webView;
    private DuitkuKit DuitkuKit;

    public static List<Map<Object, Object>> itemDetails ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        initialiasi();

        //portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ButterKnife.bind(DuitkuTransaction.this);
        API = ServerNetwork.getAPIService();




        Address address = new Address(DuitkuKit.getFirstName(),DuitkuKit.getLastName(),DuitkuKit.getAddress(),DuitkuKit.getCity(),DuitkuKit.getPostalCode(),DuitkuKit.getPhoneNumber(),DuitkuKit.getCountryCode());
        CustomerDetails customerDetails = new CustomerDetails(DuitkuKit.getFirstName(),DuitkuKit.getLastName(),DuitkuKit.getEmail(),DuitkuKit.getPhoneNumber(),address,address) ;

        checkout(DuitkuKit.getPaymentAmount(),
                DuitkuKit.getPaymentMethod(),
                DuitkuKit.getProductDetails(),
                DuitkuKit.getEmail(),
                DuitkuKit.getPhoneNumber(),
                DuitkuKit.getAdditionalParam(),
                DuitkuKit.getMerchantUserInfo(),
                DuitkuKit.getCustomerVaName(),
                DuitkuKit.getCallbackUrl(),
                DuitkuKit.getReturnUrl(),
                DuitkuKit.getExpiryPeriod(),customerDetails);
    }

    private void initialiasi(){

        DuitkuKit = new DuitkuKit();
        prefManager = new LocalPrefManagerDuitku(getApplicationContext());
        callbackKit = new DuitkuCallbackTransaction();
        txt_toolbar_title = findViewById(R.id.toolbar_title);
        txt_toolbar_title.setText(DuitkuKit.getTitlePayment());

        txt_toolbar_title_top = findViewById(R.id.toolbar_title_top);
        txt_toolbar_title_top.setText(DuitkuKit.getTitlePayment());

        view_toolbar = findViewById(R.id.view_toolbar);
        view_toolbar_top = findViewById(R.id.view_toolbar_top);

        pCustomDialog = findViewById(R.id.custom_loading);
        pCustomDialog_error = findViewById(R.id.custom_loading_error);
        stat_error = findViewById(R.id.txt_error);

        iv_spinner = findViewById(R.id.iv_duitku_spinner);
        iv_error = findViewById(R.id.iv_duitku_error);
        Glide.with(this).load(R.mipmap.logoabuloading).into(iv_spinner);
        Glide.with(this).load(R.mipmap.i_error).into(iv_error);
    }

    @Override
    public void onBackPressed() {

        finish();

        super.onBackPressed();
    }


    private void checkout(final int paymentAmount, final String paymentMethod, final String productDetails, final String email, final String phoneNumber, final String additionalParam, final String merchantUserInfo, final String customerVaName, final String callbackUrl, final String returnUrl, final String expiryPeriod, CustomerDetails customerDetails) {
        displayProgreesLoading();
        Call<ResponseTransaction> call=API.checkout(BaseKitDuitku.getUrlRequestTransaction(),new ResponseTransaction(DuitkuKit.getItemDetails(),paymentAmount,paymentMethod,productDetails,email,phoneNumber,additionalParam,merchantUserInfo,customerVaName,callbackUrl,returnUrl,expiryPeriod,customerDetails));
        call.enqueue(new Callback<ResponseTransaction>() {
            @Override
            public void onResponse(Call<ResponseTransaction> call, Response<ResponseTransaction> response) {
                if (response.isSuccessful()){
                    ResponseTransaction responseData = response.body();

                    if(responseData.getStatusMessage().equals(DuitkuTransaction.this.getString(R.string.sukses))){
                        closeProgreesLoading();
                        String d_url = response.body().getPaymentUrl();
                        reference = response.body().getReference();

                        if(d_url.contains(DuitkuTransaction.this.getString(R.string.sandbox))){
                            DuitkuKit.setModePayment(DuitkuTransaction.this.getString(R.string.sandboxLarge));
                        }else{
                            DuitkuKit.setModePayment(DuitkuTransaction.this.getString(R.string.passportLarge));
                        }

                        web_checkout(d_url);
                    }else {
                        closeProgreesLoading();
                        displayError(response.body().getStatusMessage());
                        Toast.makeText(DuitkuTransaction.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }

                }else {
                    closeProgreesLoading();
                    displayError(DuitkuTransaction.this.getString(R.string.errorConnection));
                    Toast.makeText(DuitkuTransaction.this, DuitkuTransaction.this.getString(R.string.errorConnection), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseTransaction> call, Throwable t) {
                closeProgreesLoading();
                displayError(DuitkuTransaction.this.getString(R.string.internalServerError));
                Toast.makeText(DuitkuTransaction.this,DuitkuTransaction.this.getString(R.string.internalServerError), Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void web_checkout(String url){

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setUseWideViewPort(true);
        // webView.getSettings().setLoadsImagesAutomatically(true);
        // webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
// For API level below 18 (This method was deprecated in API level 18)
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= 21) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.setWebViewClient(new myWebclient());


        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                if (progress == 100 && isCheckTransactionDOne) {
                    displayProgreesLoading();
                }
                else if (progress == 100) {
                    //HIDE
                    closeProgreesLoading();
                }else {

                }

            }



        });

        webView.loadUrl(url);

    }


    private class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {

            if (!DuitkuKit.getPaymentMethod().contains("VC")){
                closeProgreesLoading();
            }

            if(DuitkuKit.getPaymentMethod().contains("VC") && callbackKit.isCallbackFromMerchant() && url.contains("TopUpDuitkuCreditCardNotification")){
                displayProgreesLoading();
            }

            super.onPageFinished(view, url);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

           overloading(url);

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //  pCustomDialog.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            overloading(url);
            return true;
        }



        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }


        @Override
        public void onReceivedError(WebView view,WebResourceRequest request, WebResourceError error){

            if (DuitkuKit.getPaymentMethod().equals("MG")){
                checkConnection();

            }else{
                //Your code to do
                closeProgreesLoading();
                //cv_cc_info_transaction.setVisibility(View.VISIBLE);
                displayError(DuitkuTransaction.this.getString(R.string.errorConnection));
                Toast.makeText(DuitkuTransaction.this, DuitkuTransaction.this.getString(R.string.errorConnection)+error.toString(), Toast.LENGTH_LONG).show();
            }


        }


    }

    public void checkTransaction(String reference) {

        displayProgreesLoading();
        Call<ResponseCheckTransaction> call=API.checkTransaction(BaseKitDuitku.getUrlCheckTransaction(),new ResponseCheckTransaction(reference));
        call.enqueue(new Callback<ResponseCheckTransaction>() {
            @Override
            public void onResponse(Call<ResponseCheckTransaction> call, Response<ResponseCheckTransaction> response) {
                //response success
                if (response.isSuccessful()){
                    ResponseCheckTransaction responseData = response.body();

                    if(responseData.getStatusMessage().equals(DuitkuTransaction.this.getString(R.string.sukses))){
                        displayProgreesLoading();
                        modeInformation(responseData.getStatusMessage(),responseData.getReference(),responseData.getAmount(),responseData.getStatusCode());

                    }else {
                        displayProgreesLoading();
                        modeInformation(responseData.getStatusMessage(),responseData.getReference(),responseData.getAmount(),responseData.getStatusCode());

                    }

                }else {
                    closeProgreesLoading();
                    //cv_cc_info_transaction.setVisibility(View.VISIBLE);
                    displayError(DuitkuTransaction.this.getString(R.string.errorConnection));
                    Toast.makeText(DuitkuTransaction.this, DuitkuTransaction.this.getString(R.string.errorConnection), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckTransaction> call, Throwable t) {

                closeProgreesLoading();
                //cv_cc_info_transaction.setVisibility(View.VISIBLE);
                displayError(DuitkuTransaction.this.getString(R.string.internalServerError));
                Toast.makeText(DuitkuTransaction.this,DuitkuTransaction.this.getString(R.string.errorConnection), Toast.LENGTH_SHORT).show();
            }
        });
    }


    //when get url
    private void overloading(String url){

        //detect
        if (url != null){
            SANDBOX sandbox =  new SANDBOX();
            PASSPORT passport =  new PASSPORT();

            if (DuitkuKit.getModePayment() == DuitkuTransaction.this.getString(R.string.sandboxLarge)){
                sandbox.runSandbox(webView,this,DuitkuKit,url,reference);
            }

            if (DuitkuKit.getModePayment() == DuitkuTransaction.this.getString(R.string.passportLarge)){
                passport.runPasport(webView,this,DuitkuKit,url,reference);
            }

        }
    }


    private void modeInformation(String status,String reference, String amount, String Code) {

        finish();
        DuitkuClient duitkuClient = new DuitkuClient();
        duitkuClient.code = Code ;
        duitkuClient.amount= amount;
        duitkuClient.reference = reference;
        duitkuClient.status = status ;

    }



    //display progress bar
    public void displayProgreesLoading(){

        pCustomDialog.setVisibility(View.VISIBLE);

    }

    //close progress bar
    public void closeProgreesLoading(){

        pCustomDialog.setVisibility(View.GONE);
    }


    //display error
    public void displayError(String error){
        pCustomDialog_error.setVisibility(View.VISIBLE);
        stat_error.setText(error);
    }

    //close toolbar
    public void closeToolbar(){
        view_toolbar.setVisibility(View.GONE);
        view_toolbar_top.setVisibility(View.GONE);
    }


    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();

        if (activeInfo != null && activeInfo.isConnected()) {
            if(activeInfo.getType() == ConnectivityManager.TYPE_WIFI) {

            }else
            {

            }

        }
        else{
            //Your code to do
            closeProgreesLoading();
            //cv_cc_info_transaction.setVisibility(View.VISIBLE);
            displayError(DuitkuTransaction.this.getString(R.string.errorConnection));
            Toast.makeText(DuitkuTransaction.this, DuitkuTransaction.this.getString(R.string.errorConnection), Toast.LENGTH_LONG).show();
        }
    }




}
