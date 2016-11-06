package com.example.atoms.atoms;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ViewAnimator;

import java.util.Locale;

public class MainMenu extends AppCompatActivity {

    ViewAnimator mContainer;
    int flag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initContainer();
        ImageView logo = (ImageView)findViewById(R.id.imageView2);
        TranslateAnimation anim1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0f);
        anim1.setDuration(1100);
        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation inAnim = new AlphaAnimation(0,1);
                mContainer.setVisibility(View.VISIBLE);
                inAnim.setDuration(2500);
                mContainer.setAnimation(inAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim1.setFillAfter(true);
        logo.startAnimation(anim1);
        ImageView view = (ImageView)findViewById(R.id.imageView3);
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationY", 0.0f,360f);
        anim.setDuration(15000);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && (flag==0 || flag==2)) {
            ImageView r;
            r = (ImageView)findViewById(R.id.imageView3);
            AlphaAnimation alpha = new AlphaAnimation(0.1f, 1.0f);
            alpha.setDuration(400);
            alpha.setFillAfter(true);
            r.startAnimation(alpha);
            if (flag == 0)
                mContainer.showPrevious();
            else mContainer.showNext();
            flag = 1;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void initContainer(){
        mContainer = (ViewAnimator)findViewById(R.id.viewAnimator1);

        Animation inAnim = new AlphaAnimation(0,1);
        inAnim.setDuration(400);
        Animation outAnim = new AlphaAnimation(1,0);
        outAnim.setDuration(400);

        mContainer.setInAnimation(inAnim);
        mContainer.setOutAnimation(outAnim);
    }
    public void btnExit_click(View v)
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void btnStart_click(View v){
        mContainer.showNext();
        flag = 0;
    }
    public void btnHelp_click(View v){
        mContainer.showPrevious();
        flag=2;
    }

    public void btnLow(View v){
        Intent intent = new Intent(MainMenu.this, Main2Activity.class);
        Bundle b = new Bundle();
        b.putInt("level", 0);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void btnEpic(View v){
        Intent intent = new Intent(MainMenu.this, Main2Activity.class);
        Bundle b = new Bundle();
        b.putInt("level", 1);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void btnLegendary(View v){
        Intent intent = new Intent(MainMenu.this, Main2Activity.class);
        Bundle b = new Bundle();
        b.putInt("level", 2);
        intent.putExtras(b);
        startActivity(intent);
    }
}
