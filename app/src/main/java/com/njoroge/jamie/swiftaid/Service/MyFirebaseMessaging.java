package com.njoroge.jamie.swiftaid.Service;

import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.njoroge.jamie.swiftaid.VictimCall;

public class MyFirebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //now since i will receive lat and long from victims app


        LatLng customerLocation = new Gson().fromJson(remoteMessage.getNotification().getBody(),LatLng.class);

        Intent intent = new Intent(getBaseContext(), VictimCall.class);
        intent.putExtra("lat", customerLocation.latitude);
        intent.putExtra("lng", customerLocation.longitude);

        startActivity(intent);
    }
}
