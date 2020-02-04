package com.duitku.sdk.Network;
import com.duitku.sdk.DuitkuUtility.BaseKitDuitku;

public class ServerNetwork {
    public static NetworkService getAPIService() {

        return ClientNetwork.getClientNetworkDuitku(BaseKitDuitku.BASE_URL_API_DUITKU).create(NetworkService.class);
    }



}
