package it.dib.uniba.counterapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements
    CounterFragment.CounterListener{

    private static final String LOG_TAG = MainActivity.class.getName();

    // private static final String COUNTER_EXTRA = "it.dib.uniba.extra.COUNTER_EXTRA";
    // private int mCounter;
    private TextView mOutput;
    // private CounterThread mCounterThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "ONCREATED INVOKED ON ACTIVITY");
        setContentView(R.layout.activity_main);
        mOutput = findViewById(R.id.output);
        if(savedInstanceState == null){
            Fragment fragment = new CounterFragment(); // creo un'istanza di CounterFragment
            fragment.setRetainInstance(true); // se passiamo true, il fragment non è nel back stack e il sistema non potrà distruggerlo
            getSupportFragmentManager().beginTransaction()  // viene aggiunta attraverso una transazione al contenitore
                    .add(R.id.container, fragment).commit();
        }
        Log.i("BUNDLE", "" + savedInstanceState);
    }

    @Override
    public void count(final int countValue){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mOutput.setText("Counter: " + countValue);
            }
        });
    }
    /*
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_EXTRA, mCounter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mCounterThread = new CounterThread();
        mCounterThread.start();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mCounterThread.stopCounter();
    }

    public class CounterThread extends Thread{
        private boolean mRunner = true;

        @Override
        public void run(){
            super.run();
            Log.d(LOG_TAG, "Counter STARTED from " + mCounter);
            while(mRunner){
                try{
                    Thread.sleep(500L);
                }catch (InterruptedException ie){

                }
                mCounter++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mOutput.setText("Counter: " + mCounter);
                    }
                });
                Log.d(LOG_TAG, "Counter in Fragment is: " + mCounter);
            }
            Log.d(LOG_TAG, "Counter ENDED at " + mCounter);
        }

        public void stopCounter(){
            mRunner = false;
        }
    }*/
}