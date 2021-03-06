package com.example.moviealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class YoutubeApi extends YouTubeBaseActivity {
    private static final long START_TIME=10000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button getmButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimerLeftInMillis=START_TIME;

    //youtube api
    private static final String API_KEY = "AIzaSyB3tzUfgk2LPorDBrkl4Pfz-zGb_p2JfFA";
    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_api);

        mTextViewCountDown=findViewById(R.id.text_view_countdown);
        mYouTubePlayerView=findViewById(R.id.youtube_view);

        //youtube
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

    }
}