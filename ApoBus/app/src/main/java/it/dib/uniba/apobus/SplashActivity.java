package it.dib.uniba.apobus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.ref.WeakReference;

public class SplashActivity extends Activity {

    private static final String TAG_LOG = SplashActivity.class.getName();

    private static final long MIN_WAIT_INTERVAL = 1500L;      // intervallo minimo per passare all'attività successiva
    private static final long MAX_WAIT_INTERVAL = 3000L;      // intervallo max in cui passerà in automatico
    private static final int GO_AHEAD_WHAT = 1;

    private static final String IS_DONE_KEY = "IS_DONE_KEY";
    private static final String START_TIME_KEY = "START_TIME_KEY";

    private long mStartTime = -1L;    // istante della prima visualizzazione
    private boolean mIsDone;    // flag che verifica se il passaggio all'attività seguente sia già stato realizzato
    @SuppressLint("HandlerLeak")

    private UiHandler mHandler;
    private static class UiHandler extends Handler {
        private WeakReference<SplashActivity> mActivityRef;

        public UiHandler(final SplashActivity srcActivity) {
            this.mActivityRef = new WeakReference<SplashActivity>(srcActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final SplashActivity srcActivity = this.mActivityRef.get();
            if (srcActivity == null) {
                Log.d(TAG_LOG, "Reference to SplashActivity lost!");
                return;
            }
            switch (msg.what) {
                case GO_AHEAD_WHAT:
                    long elapsedTime = SystemClock.uptimeMillis() - srcActivity.mStartTime;
                    if (elapsedTime >= MIN_WAIT_INTERVAL && !srcActivity.mIsDone) {
                        srcActivity.mIsDone = true;
                        srcActivity.goAhead();
                    }
                    break;
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();       // otteniamo la view che rappresenta tutto ciò che la finestra contiene
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // flag che permette di impostare l'hide degli elementi di navigazione
        decorView.setSystemUiVisibility(uiOptions);

        // ripristino dello stato dell'activity
        if(savedInstanceState != null){
            this.mStartTime = savedInstanceState.getLong(START_TIME_KEY);
        }

        mHandler = new UiHandler(this);

        final ImageView logoImageView = findViewById(R.id.splash_imageview);
        logoImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
                if(elapsedTime >= MIN_WAIT_INTERVAL && !mIsDone){
                    mIsDone = true;
                    goAhead();
                }else{
                    Log.d(TAG_LOG, "Too early!");
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(mStartTime == -1L){
            mStartTime = SystemClock.uptimeMillis();
        }
        final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
        mHandler.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERVAL);
        Log.d(TAG_LOG, "Handler message sent!");
    }

    // passaggio all'activity successiva, ovvero la Main Activity
    private void goAhead(){
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_DONE_KEY, mIsDone);
        outState.putLong(START_TIME_KEY, mStartTime);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        this.mIsDone = savedInstanceState.getBoolean(IS_DONE_KEY);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}