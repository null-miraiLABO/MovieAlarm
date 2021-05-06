package com.example.moviealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Button;
import java.util.Locale;

public class MainActivity extends YouTubeBaseActivity {
    private static final long START_TIME=10000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button getmButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private long mTimerLeftInMillis=START_TIME;

    //
    private static final String API_KEY = "AIzaSyB3tzUfgk2LPorDBrkl4Pfz-zGb_p2JfFA";
    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown=findViewById(R.id.text_view_countdown);
        mButtonStartPause=findViewById(R.id.button_start);
        getmButtonReset=findViewById(R.id.button_reset);

        mYouTubePlayerView=findViewById(R.id.youtube_view);
        mOnInitializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("QkeY6HgBALs");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        mYouTubePlayerView.initialize(API_KEY,mOnInitializedListener);
        updateCountDownText();//
    }

    public void setClick(View view) {
        //
        if (mTimerRunning) {
            pauseTimer();
        }else{
            startTime();
        }
        //
    }


    public void resetClick(View view) {
        resetTime();//
    }

//
    private void startTime(){
        mCountDownTimer=new CountDownTimer(mTimerLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning=false;
                mButtonStartPause.setText("スタート");
                getmButtonReset.setVisibility(View.INVISIBLE);

            }
        }.start();

        mTimerRunning=true;
        mButtonStartPause.setText("一時停止");
        getmButtonReset.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning=false;
        mButtonStartPause.setText("一時停止");
        getmButtonReset.setVisibility(View.INVISIBLE);

    }

    private void resetTime(){
        mTimerLeftInMillis=START_TIME;
        updateCountDownText();
        mButtonStartPause.setVisibility(View.VISIBLE);
        getmButtonReset.setVisibility(View.INVISIBLE);

    }

    private void updateCountDownText(){
        int minutes = (int)(mTimerLeftInMillis/1000)/60;
        int second  = (int)(mTimerLeftInMillis/1000)%60;
        String timerLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,second);
        mTextViewCountDown.setText(timerLeftFormatted);
    }
}
