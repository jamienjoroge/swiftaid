package com.njoroge.jamie.swiftaid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.njoroge.jamie.swiftaid.Common.Common;
import com.njoroge.jamie.swiftaid.Remote.IGoogleApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VictimCall extends AppCompatActivity {

    TextView txt_time, txt_address,txt_distance;

    MediaPlayer mediaPlayer;

    IGoogleApi mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim_call);

        mService = Common.getGoogleApi();

        //initiate view
        txt_time = (TextView)findViewById(R.id.txt_time);
        txt_address = (TextView)findViewById(R.id.txt_address);
        txt_distance = (TextView)findViewById(R.id.txt_distance);

        mediaPlayer = MediaPlayer.create(this, R.raw.tone);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        if (getIntent() != null)
        {
            double lat = getIntent().getDoubleExtra("lat", -1.0);
            double lng = getIntent().getDoubleExtra("lng", -1.0);

            getDirection(lat, lng);
        }
    }
    private void getDirection(double lat, double lng) {

        String requestApi;
        try
        {
            requestApi = "https://maps.googleapis.com/maps/api/directions/json?"+
                    "mode=driving&"+
                    "transit_routing_preference= less_driving&"+
                    "origin="+ Common.mLastLocation.getLatitude()+","+Common.mLastLocation.getLongitude()+"&"+
                    "destination="+lat+","+lng+"&"+
                    "key="+getResources().getString(R.string.google_direction_Api);

            Log.d("JAMIE", requestApi);//prints URL for debugging
            mService.getPath(requestApi)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body());


                                JSONArray routes = jsonObject.getJSONArray("routes");

                                //gets the first object (index 0)
                                JSONObject object = routes.getJSONObject(0);

                                //now getting into array named legs
                                JSONArray legs = object.getJSONArray("legs");

                                //here again i need the first object
                                JSONObject legsObject = legs.getJSONObject(0);

                                //Get distance from legs
                                JSONObject distance = legsObject.getJSONObject("distance");
                                txt_distance.setText(distance.getString("text"));

                                //Get the Duration/ time
                                JSONObject time = legsObject.getJSONObject("duration");
                                txt_time.setText(time.getString("text"));

                                //Get the address
                                String address = legsObject.getString("end_address");
                                txt_address.setText(time.getString("text"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(VictimCall.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        mediaPlayer.release();
        super.onStop();
    }

    @Override
    protected void onPause() {
        mediaPlayer.release();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }
}
