package it.dib.uniba.chitarra;

import android.media.SoundPool;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

//TODO implementare l'interfaccia View.OnTouchListener
public class MainActivity extends AppCompatActivity {


    // Costanti
    private final int NR_OF_SIMULTANEOUS_SOUND = 6;

    // TODO: Aggiungere member variables
    private int mSE;
    private int mSA;
    private int mSD;
    private int mSG;
    private int mSB;
    private int mSe6;


    private SoundPool mStringsSound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Impostare l'orientation dell'activity su Landscape



        // TODO: Impostare la visualizzazione dell'activity fullscreen. Supportare le versioni precedenti ad Android 4.4


        // TODO: Collegare le ImageView sfruttando il loro ID



        // TODO: Gestione del costruttore SoundPool che è un API deprecata dalla versione API 21
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mStringsSound = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(NR_OF_SIMULTANEOUS_SOUND).build();
        }else{
            mStringsSound = new SoundPool(NR_OF_SIMULTANEOUS_SOUND, AudioManager.STREAM_MUSIC, 0);
        }


        // TODO: Caricare i suoni



    }

    // TODO: facoltativo Ascoltare l'evento click, commentando le parti relative alla gestion dell'evento touch per capire la differenza


    // TODO: Aggiungere il metodo per la gestione dell'evento touch sulle corde

}
