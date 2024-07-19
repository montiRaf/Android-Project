package it.dib.uniba.convertitoreditemperatura;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText tempCelsius;
    TextView tempFahre;
    Button btnConverti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Colleghiamo le view con il codice Java
        tempCelsius = findViewById(R.id.etCelsius);
        tempFahre = findViewById(R.id.tvResult);
        btnConverti = findViewById(R.id.btnConverti);
    }

    boolean btnColor = false;

    public void convertiTemp(View view) {
        // Leggere la temperatura in celsius
        String tempCelsiusStr = tempCelsius.getText().toString();

        // Convertire la stringa in un valore double
        double tempCelsius = Double.parseDouble(tempCelsiusStr);

        // Applichiamo la formula che converte la temperatura
        double tempF = tempCelsius * 1.8 + 32;

        // Scrivere il valore ottenuto all'interno della textView
        tempFahre.setText(String.format("%.2f", tempF));

        if(btnColor){
            btnConverti.setBackgroundColor(getColor(R.color.colorPrimaryDark));
            btnColor = false;
        }else{
            btnConverti.setBackgroundColor(getColor(R.color.colorAccent));
            btnColor = true;
        }

    }
}