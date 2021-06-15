package com.example.moviealarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Alarm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Button MakeTimerBt = findViewById(R.id.MakeTimerBt);
        MakeTimeLintener lintener = new MakeTimeLintener();
        MakeTimerBt.setOnClickListener(lintener);
    }

    public void clickTimer2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clickAlarm2(View view) {

    }

    private class MakeTimeLintener implements View.OnClickListener{//
        @Override
        public void onClick(View view) {
            MakeTimeDialogFragment dialogFragment = new MakeTimeDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"MakeTimeDialogFragment");
        }
    }
}