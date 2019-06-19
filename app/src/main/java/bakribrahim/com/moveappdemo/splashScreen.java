package bakribrahim.com.moveappdemo;

import androidx.appcompat.app.AppCompatActivity;
import bakribrahim.com.moveappdemo.MovieList.UI.MainActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class splashScreen extends AppCompatActivity {
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
         animationView=findViewById(R.id.animation_view);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f)
                .setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationView.setProgress((Float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(splashScreen.this, MainActivity.class));
                   finish();
                super.onAnimationEnd(animation);
            }
        });
         animator.start();

        //  animationView.cancelAnimation();

//custom color
        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.LIGHTEN);
         // animationView.clearColorFilters();

//        animationView.setSpeed(0.9f);
//        animationView.playAnimation();
//        animationView.addAnimatorListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                Log.e("Animation:","start");
//                Log.e("duartion", ""+animationView.getDuration());
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.e("Animation:","end");
//                //Your code for remove the fragment
//                try {
//                   startActivity(new Intent(splashScreen.this, MainActivity.class));
//                   finish();
//                } catch(Exception ex) {
//                    ex.toString();
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                Log.e("Animation:","cancel");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                Log.e("Animation:","repeat");
//            }
//        });
    }
}
