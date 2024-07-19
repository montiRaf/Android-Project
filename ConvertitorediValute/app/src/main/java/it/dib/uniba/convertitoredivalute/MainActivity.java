package it.dib.uniba.convertitoredivalute;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

   EditText valutaConv;
   TextView risultato;
   TextView valuta1;
   TextView valuta2;
   TextView subTitle;

   boolean euroToDollaro;

   static final double TASSO = 1.18213;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valutaConv = findViewById(R.id.valutaConvET);
        risultato = findViewById(R.id.risultato);
        valuta1 = findViewById(R.id.valuta1);
        valuta2 = findViewById(R.id.valuta2);
        euroToDollaro = true;   // conversione da euro a dollaro
        subTitle = findViewById(R.id.subTitle);
    }

    @SuppressLint("SetTextI18n")
    public void convert(View view) {
        conversione();
    }

    // restituisce true se la stringa è vuota
    private boolean isEmpty(EditText euro) {
        String input = euro.getText().toString().trim();
        return input.length() == 0;
    }

    // scambia euro e dollaro
    public void scambia(View view) {
        if(euroToDollaro == true){
            valuta1.setText("Dollaro");
            valuta2.setText("Euro");
            euroToDollaro = false;
            subTitle.setText("Da dollaro a euro");
            conversione();
        }else{
            valuta1.setText("Euro");
            valuta2.setText("Dollaro");
            euroToDollaro = true;
            subTitle.setText("Da euro a dollaro");
            conversione();
        }
    }

    private void conversione(){
        if(!isEmpty(valutaConv)){
            String strInput = valutaConv.getText().toString();
            double valuta = Double.parseDouble(strInput);

            if(euroToDollaro == true){
                double risultatoConv = valuta * TASSO;  // da euro a dollaro
                //string format permette di formattare il numero in modo da visualizzare solo due cifre dopo la virgola
                risultato.setText(String.format("%.2f", risultatoConv));
            }else {
                double risultatoConv = valuta / TASSO;  // da dollaro a euro
                //string format permette di formattare il numero in modo da visualizzare solo due cifre dopo la virgola
                risultato.setText(String.format("%.2f", risultatoConv));
            }
        }else{
            Toast.makeText(getApplicationContext(), "Il campo da convertire è vuoto", Toast.LENGTH_LONG).show();
        }
    }
}