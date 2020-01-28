package com.duitku.sdk.Network;

import com.duitku.sdk.Model.ResponseCheckTransaction;
import com.duitku.sdk.Model.ResponseGetListPaymentMethod;
import com.duitku.sdk.Model.ResponseTransaction;
import com.duitku.sdk.Model.ResponseListPaymentMethod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface NetworkService {

    @Headers("Content-Type: application/json")
    @POST
    Call<ResponseTransaction> checkout(@Url String endPoint,
                                       @Body ResponseTransaction body);

    @Headers("Content-Type: application/json")
    @POST
    Call<ResponseCheckTransaction> checkTransaction(@Url String endPoint,
                                                    @Body ResponseCheckTransaction body);

    @Headers("Content-Type: application/json")
    @POST
    Call<ResponseGetListPaymentMethod> getAllPaymentMethod(@Url String endPoint,
                                                           @Body ResponseGetListPaymentMethod body);

}
