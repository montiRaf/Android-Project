package it.dib.uniba.apobus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final int PICK_BUS_STOP_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();       // otteniamo la view che rappresenta tutto ciò che la finestra contiene
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // flag che permette di impostare l'hide degli elementi di navigazione
        decorView.setSystemUiVisibility(uiOptions);

        final Button sendBusStopButton = findViewById(R.id.send_bus_stop_button);
    }

    public void sendBusStop(View view){
        // Invio dei dati relativi alla fermata del bus
        sendBusStopData(view);
        Toast.makeText(getApplicationContext(), "Send bus stop data", Toast.LENGTH_LONG).show();
    }

    public void sendBusStopData(final View view){
        final BusStop busStop =  BusStop.Builder.create("myId", "myName")
                .withDirection("myDirection")
                .withLocation(51.5085300f, -0.1257400f)
                .build();

        // utilizzo di un intent esplicito per il passaggio dei dati all'attività successiva
        final Intent intent = new Intent(this, BusStopActivity.class);
        busStop.toIntent(intent);
        startActivity(intent);
    }

    public void pickBusStop(final View view){
        final Intent pickBusStopIntent = new Intent(BusStopListActivity.PICK_BUS_STOP_ACTION);
        pickBusStopIntent.putExtra(BusStop.Keys.LATITUDE, 51.5085300f);
        pickBusStopIntent.putExtra(BusStop.Keys.LONGITUDE, -0.1257400f);
        startActivityForResult(pickBusStopIntent, PICK_BUS_STOP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_BUS_STOP_REQUEST_CODE){
            switch(resultCode){
                case RESULT_OK:
                    final BusStop busStop = data.getParcelableExtra(BusStop.Keys.ID);
                    Toast.makeText(this, "BusStop: " + busStop, Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, R.string.cancelled_operation, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, R.string.custom_operation, Toast.LENGTH_SHORT).show();
            }
        }
    }
}