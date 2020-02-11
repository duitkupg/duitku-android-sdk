package com.duitku.sdk.DuitkuUtility;


import com.duitku.sdk.Model.CustomerDetails;
import com.duitku.sdk.Model.ItemDetails;

import java.util.ArrayList;

public class DuitkuKit {

    private static  int paymentAmount = 0   ;
    private static  String  paymentMethod = "" ;
    private static String  productDetails = "" ;
    private static String  additionalParam = "";
    private static  String  merchantUserInfo = "";
    private static String  phoneNumber = "" ;
    private static  String  customerVaName = "";
    private static  String  callbackUrl = "";
    private static  String  returnUrl = "";
    private static  String  expiryPeriod = "";
    private static String  titlePayment = "";
    private static String  modePayment = "";
    private static  String  requestTransaction= "";
    private static String  checkTransaction = "";
    private static String  email = "";
    private static String  firstName = "";
    private static String  lastName = "";
    private static String  address = "";
    private static String  city = "";
    private static String  postalCode = "";
    private static String  phone = "";
    private static String  countryCode = "";


    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        DuitkuKit.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        DuitkuKit.lastName = lastName;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        DuitkuKit.address = address;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        DuitkuKit.city = city;
    }

    public static String getPostalCode() {
        return postalCode;
    }

    public static void setPostalCode(String postalCode) {
        DuitkuKit.postalCode = postalCode;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        DuitkuKit.phone = phone;
    }

    public static String getCountryCode() {
        return countryCode;
    }

    public static void setCountryCode(String countryCode) {
        DuitkuKit.countryCode = countryCode;
    }

    private static ArrayList<ItemDetails> itemDetails ;


    public ArrayList<ItemDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ArrayList<ItemDetails> itemDetails) {
        DuitkuKit.itemDetails = itemDetails;
    }

    public  String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        DuitkuKit.email = email;
    }

    public  String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        DuitkuKit.phoneNumber = phoneNumber;
    }

    public String getCustomerVaName() {
        return customerVaName;
    }

    public  void setCustomerVaName(String customerVaName) {
        DuitkuKit.customerVaName = customerVaName;
    }

    public  String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        DuitkuKit.callbackUrl = callbackUrl;
    }

    public  String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        DuitkuKit.returnUrl = returnUrl;
    }

    public  String getExpiryPeriod() {
        return expiryPeriod;
    }

    public void setExpiryPeriod(String expiryPeriod) {
        DuitkuKit.expiryPeriod = expiryPeriod;
    }

    public  String getModePayment() {
        return modePayment;
    }

    public void setModePayment(String modePayment) {
        DuitkuKit.modePayment = modePayment;
    }


    public String getRequestTransaction() {
        return requestTransaction;
    }

    public  void setRequestTransaction(String requestTransaction) {
        DuitkuKit.requestTransaction = requestTransaction;
    }

    public String getCheckTransaction() {
        return checkTransaction;
    }

    public  void setCheckTransaction(String checkTransaction) {
        DuitkuKit.checkTransaction = checkTransaction;
    }
    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getAdditionalParam() {
        return additionalParam;
    }

    public void setAdditionalParam(String additionalParam) {
        this.additionalParam = additionalParam;
    }

    public String getMerchantUserInfo() {
        return merchantUserInfo;
    }

    public void setMerchantUserInfo(String merchantUserInfo) {
        this.merchantUserInfo = merchantUserInfo;
    }

    public String getTitlePayment() {
        return titlePayment;
    }

    public void setTitlePayment(String titlePayment) {
        this.titlePayment = titlePayment;
    }


}
