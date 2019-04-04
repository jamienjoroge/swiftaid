package com.njoroge.jamie.swiftaid.Common;

import com.njoroge.jamie.swiftaid.Remote.FCMClient;
import com.njoroge.jamie.swiftaid.Remote.IFCMService;
import com.njoroge.jamie.swiftaid.Remote.IGoogleApi;
import com.njoroge.jamie.swiftaid.Remote.RetrofitClient;

public class Common {

    public static final String driver_tbl = "Drivers";
    public static final String user_driver_tbl = "DriversInformation";
    public static final String user_victim_table = "VictimsInformation";
    public static final String request_pickup_tbl = "PickupRequest";
    public static final String token_tbl = "Tokens";

    public static final String baseURL = "https://maps.googleapis.com";
    public static final String fcmURL = "https://fcm.googleapis.com/";

    public static IGoogleApi getGoogleApi()
    {
        return RetrofitClient.getClient(baseURL).create(IGoogleApi.class);
    }
    public static IFCMService getFCMService()
    {
        return FCMClient.getClient(fcmURL).create(IFCMService.class);
    }
}
