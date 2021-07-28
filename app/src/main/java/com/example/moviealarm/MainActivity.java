package com.example.moviealarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button MakeTimerBt = findViewById(R.id.MakeTimerBt);
        //MakeTimeLintener lintener = new MakeTimeLintener();
        //MakeTimerBt.setOnClickListener(lintener);

    }

    public void clickTimer(View view) {

    }

    public void clickAlarm(View view) {
        Intent intent = new Intent(this, Alarm.class);
        startActivity(intent);
    }

    private class MakeTimeLintener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            MakeTimeDialogFragment dialogFragment = new MakeTimeDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"MakeTimeDialogFragment");
        }
    }

}
