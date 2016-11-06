package com.example.atoms.atoms;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewAnimator;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);
        final AtomGameView gameView = (AtomGameView) findViewById(R.id.gameview);
        gameView.findTextView();
        Bundle b = getIntent().getExtras();
        int level = 0;
        if (b != null)
            level = b.getInt("level");
        gameView.generateMapWithParam(level);
        final ImageView checkButton = (ImageView) findViewById(R.id.button_check);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.checkResults(checkButton);
            }
        });

        final ImageView resetButton = (ImageView) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                          gameView.resetGame(checkButton, resetButton);
                        }
        });

        /*final Button incMapSizeBtn = (Button) findViewById(R.id.button_increase_map_size);
        incMapSizeBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                gameView.incMapSize();
           }
        });*/
    }
}
