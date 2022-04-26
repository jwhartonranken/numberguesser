package tech.jameswharton.guess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class whartonTechSplash extends AppCompatActivity {
    Handler mHandler;
    Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wharton_tech_splash);

        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(whartonTechSplash.this, MainActivity.class);

                startActivity(intent);
                finish();
            }
        };

        mHandler.postDelayed(mRunnable, 3000);
    }
}