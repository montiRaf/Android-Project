package it.dib.uniba.apobus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


public class BusStopActivity extends AppCompatActivity {

    private static final String TAG = BusStopActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop);

        final Intent srcIntent = getIntent();
        if(srcIntent != null){
            BusStop busStop = BusStop.fromIntent(srcIntent);
            Log.d(TAG, "Received: " + busStop);
        }
    }
}