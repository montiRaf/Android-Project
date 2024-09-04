package it.dib.uniba.counterapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CounterFragment extends Fragment {

    public interface CounterListener{
        void count(int countValue);
    }

    private int mCounter;
    private CounterThread mCounterThread;
    private CounterListener mCounterListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCounterThread = new CounterThread();
        mCounterThread.start();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mCounterThread.stopCounter();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(activity instanceof CounterListener){
            mCounterListener = (CounterListener) activity;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mCounterListener = null;
    }

    public class CounterThread extends Thread{
        private boolean mRunner = true;

        @Override
        public void run(){
            super.run();
            while(mRunner){
                try{
                    Thread.sleep(500L);
                }catch (InterruptedException ie){

                }
                mCounter++;
                if(mCounterListener != null){
                    mCounterListener.count(mCounter);
                }
            }
        }

        public void stopCounter(){
            mRunner = false;
        }
    }
}