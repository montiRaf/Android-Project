package it.dib.uniba.apobus;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();       // otteniamo la view che rappresenta tutto ciò che la finestra contiene
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // flag che permette di impostare l'hide degli elementi di navigazione
        decorView.setSystemUiVisibility(uiOptions);
    }
}