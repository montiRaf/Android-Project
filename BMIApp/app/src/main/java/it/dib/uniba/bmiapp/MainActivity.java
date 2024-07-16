package it.dib.uniba.bmiapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText altezza, peso;
    private TextView risultato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calcola = findViewById(R.id.calcolaButton);
        altezza = findViewById(R.id.altezzaEditText);
        peso = findViewById(R.id.pesoEditText);
        risultato = findViewById(R.id.risultatoTextView);

        calcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcolaBMI();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void calcolaBMI() {
        String altezzaString = altezza.getText().toString();
        String pesoString = peso.getText().toString();

        if (!altezzaString.isEmpty() && !pesoString.isEmpty()) {
            double altezza = Double.parseDouble(altezzaString) / 100;
            double peso = Double.parseDouble(pesoString);
            double bmi = peso / (altezza * altezza);

            risultato.setText(String.format("Il tuo BMI è: %.2f", bmi));
        } else {
            risultato.setText("Per favore, inserisci altezza e peso");
        }
    }
}