package it.dib.uniba.lovecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    ImageView cuoreBtn;
    TextView mResult;
    EditText et1;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        cuoreBtn = findViewById(R.id.imageView);
        mResult = findViewById(R.id.result);
        et1 = findViewById(R.id.editTextText1);
        et2 = findViewById(R.id.editTextText2);

    }

    public void calcolaLoveOnClick(View view){
        Random rand = new Random();
        String str1 = et1.getText().toString();
        String str2 = et2.getText().toString();

        if(str1.isEmpty() || str2.isEmpty()){
            Toast.makeText(this, "Devi inserire i nomi!", Toast.LENGTH_LONG).show();
        }else{
            int loveMeter = rand.nextInt(100);
            mResult.setText(Integer.toString(loveMeter));
            if(loveMeter >= 80){
                Toast.makeText(this, "C'è molta affinità", Toast.LENGTH_LONG).show();
            }else if(loveMeter > 50){
                Toast.makeText(this, "C'è poca affinità", Toast.LENGTH_LONG).show();
            }else{
                    Toast.makeText(this, "Non c'è speranza!", Toast.LENGTH_LONG).show();
            }
        }
    }

}