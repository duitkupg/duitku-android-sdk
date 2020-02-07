package com.duitku.sdk;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duitku.sdk.DuitkuAdapter.DuitkuAdapterPayment;
import com.duitku.sdk.DuitkuCallback.DuitkuCallbackTransaction;
import com.duitku.sdk.DuitkuUtility.BaseKitDuitku;
import com.duitku.sdk.DuitkuUtility.DuitkuKit;
import com.duitku.sdk.Model.ResponseGetListPaymentMethod;
import com.duitku.sdk.Network.NetworkService;
import com.duitku.sdk.Network.ServerNetwork;
import com.duitku.sdk.Model.ResponseListPaymentMethod;
import com.duitku.sdk.PrefManagerDuitku.LocalPrefManagerDuitku;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListPaymentMethodDuitku extends AppCompatActivity  {

    private com.duitku.sdk.DuitkuUtility.DuitkuKit DuitkuKit;
    private TextView txt_amount;
    private Toolbar toolbar ;
    private DuitkuCallbackTransaction callbackKit ;
    private LocalPrefManagerDuitku prefManager;
    private NetworkService API;
    private RelativeLayout pCustomDialog;
    private RelativeLayout pCustomDialog_error;
    private LinearLayout pBar_total;
    private RecyclerView rvPayment;
    private TextView stat_error;
    private ImageView iv_spinner;
    private ImageView iv_error;

    private Context mContext;
    private List<ResponseListPaymentMethod> AllPaymentList = new ArrayList<>();
    private DuitkuAdapterPayment Adapter;
    private DuitkuClient duitkuClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_payment_method);

        //portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DuitkuKit = new DuitkuKit();
        duitkuClient = new DuitkuClient();

        Toolbar toolbar = findViewById(R.id.toolbar_pay);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stat_error = findViewById(R.id.txt_error);
        pCustomDialog = findViewById(R.id.custom_loading_checkout);
        pCustomDialog_error = findViewById(R.id.custom_loading_error_checkout);
        pBar_total = findViewById(R.id.bar_amount);

        iv_spinner = findViewById(R.id.iv_duitku_spinner);
        iv_error = findViewById(R.id.iv_duitku_error);
        Glide.with(this).load(R.mipmap.logoabuloading).into(iv_spinner);
        Glide.with(this).load(R.mipmap.i_error).into(iv_error);

        callbackKit = new DuitkuCallbackTransaction();

        prefManager = new LocalPrefManagerDuitku(getApplicationContext());

        txt_amount = findViewById(R.id.txt_amount);

        //payment amount null

        if (DuitkuKit.getPaymentAmount() == 0 || DuitkuKit.getReturnUrl().equals("")|| DuitkuKit.getExpiryPeriod().equals("") || DuitkuKit.getEmail().equals("") || DuitkuKit.getPhoneNumber().equals("") || DuitkuKit.getProductDetails().equals("") ||  DuitkuKit.getReturnUrl() == null|| DuitkuKit.getExpiryPeriod() == null || DuitkuKit.getEmail() == null || DuitkuKit.getPhoneNumber() == null|| DuitkuKit.getProductDetails() == null || DuitkuKit.getAdditionalParam() == null || DuitkuKit.getMerchantUserInfo() == null|| DuitkuKit.getCustomerVaName() == null || DuitkuKit.getCallbackUrl() == null){
            displayError("Internal response error, Check parameter");
        }else {
            txt_amount.setText("Rp"+conversiRupiah(""+DuitkuKit.getPaymentAmount() ));
            initialisasi(DuitkuKit.getPaymentAmount());
        }

    }

    private String conversiRupiah( String payment){
        //conversi currency
        int number = Integer.parseInt(payment);
        String currency = NumberFormat.getNumberInstance(Locale.US).format(number);
        return currency;
    }

    private void initialisasi(int amount ){
        rvPayment = findViewById(R.id.rvPayment);
        ButterKnife.bind(this);
        mContext = this;
        API = ServerNetwork.getAPIService();

        Adapter = new DuitkuAdapterPayment(this, AllPaymentList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvPayment.setLayoutManager(mLayoutManager);
        rvPayment.setItemAnimator(new DefaultItemAnimator());


        getListCheckout(amount) ;

        d_finish d_finish =new d_finish(this);
        d_finish.duitkuFinish();



        if(!callbackKit.isCallbackFromMerchant()){

            d_finish.FinishTopUpNotify();
        }
    }

    public class d_finish extends DuitkuClient {

        Context mcontext ;

        public d_finish(Context context){
            mcontext = context ;
        }

        @Override
        public void onDone() {

            ListPaymentMethodDuitku activity = (ListPaymentMethodDuitku) mcontext;
            activity.finish();

            super.onDone();
        }
    }

    @Override
    public void onResume(){

        d_finish d_finish =new d_finish(this);
        d_finish.duitkuFinish();

        //finishcallbackfrommerchant
        if(!callbackKit.isCallbackFromMerchant()){

            d_finish.FinishTopUpNotify();
            d_finish.clearSdkTask();

        }


        super.onResume();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //homeback
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void getListCheckout(int amount){

        displayProgreesLoading();

        Call<ResponseGetListPaymentMethod> call = API.getAllPaymentMethod(BaseKitDuitku.getUrlListPayment(),new ResponseGetListPaymentMethod(amount));
        call.enqueue(new Callback<ResponseGetListPaymentMethod>() {
            @Override
            public void onResponse(Call<ResponseGetListPaymentMethod> call, Response<ResponseGetListPaymentMethod>  response) {
                if(response.isSuccessful()) {
                    if (response.body().getPaymentFee() != null) {
                        closeProgreesLoading();
                        AllPaymentList.addAll(response.body().getPaymentFee());
                        rvPayment.setAdapter(new DuitkuAdapterPayment(mContext, AllPaymentList));
                        Adapter.notifyDataSetChanged();
                    }else{
                        closeProgreesLoading();
                        displayError("Error Response Data");
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    closeProgreesLoading();
                    displayError("Error Response Data");
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetListPaymentMethod> call, Throwable t) {
                closeProgreesLoading();
                displayError("Internal server error / check your connection");
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }



    private void displayProgreesLoading(){
        pCustomDialog.setVisibility(View.VISIBLE);
    }

    private void closeProgreesLoading(){
        pCustomDialog.setVisibility(View.GONE);
    }

    private void displayError(String error){
        pCustomDialog_error.setVisibility(View.VISIBLE);
        pBar_total.setVisibility(View.GONE);
        stat_error.setText(error);
    }




}
