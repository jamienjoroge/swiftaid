package com.njoroge.jamie.swiftaid.Remote;

import com.njoroge.jamie.swiftaid.Model.FCMResponse;
import com.njoroge.jamie.swiftaid.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAS7BSOVE:APA91bFP6XWs0DNR4SsXS7Ocxf80FPoynK3Yx_8MPMvvQEIIv8uZmH5-LSQP1aIDqXJG5LDYdZ5Ri6DpmwR8c3SZJkc6mxVVgwIb0FEdYxGq8py4A5pVd6XevXGiZ9lMqQHg_ct-QQqG"
    })

    @POST("fcm/send")
    Call<FCMResponse> sendMessage(@Body Sender body);
}

