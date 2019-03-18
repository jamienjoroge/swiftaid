package com.njoroge.jamie.swiftaid.Common;

import com.njoroge.jamie.swiftaid.Remote.IGoogleApi;
import com.njoroge.jamie.swiftaid.Remote.RetrofitClient;

public class Common {
    public static final String baseURL = "https://maps.googleapis.com";
    public static IGoogleApi getGoogleApi()
    {
        return RetrofitClient.getClient(baseURL).create(IGoogleApi.class);
    }
}
