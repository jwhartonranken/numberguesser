package tech.jameswharton.guess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class guessingRankDisplay extends AppCompatActivity {

    Drawable drawable = null;
    ImageView ivRank;
    TextView tvRankTitle;
    Button btnReturnFromRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_rank_display);

        ivRank = findViewById(R.id.ivRank);
        tvRankTitle = findViewById(R.id.tvRankTitle);
        btnReturnFromRank = findViewById(R.id.btnReturnFromRank);

        String status = "";

        // Get the intent in the target activity
        Intent intent = getIntent();

        // Get the attached bundle from the intent
        Bundle extras = intent.getExtras();

        if (extras != null) {
            if (extras.containsKey("rank")) {
                status = extras.getString("rank", "");
            }
        }

        switch (status) {
            case "Wow!":
                drawable = getResources().getDrawable((R.drawable.wowguess));
                break;
            case "Novice":
                drawable = getResources().getDrawable((R.drawable.noviceguess));
                break;
            case "Average":
                drawable = getResources().getDrawable((R.drawable.averageguess));
                break;
            default:
                drawable = getResources().getDrawable(((R.drawable.jameswhartontechlogo)));
                break;
        }

        ivRank.setImageDrawable(drawable);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        ivRank.startAnimation(animation);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        ivRank.clearAnimation();
                    }
                },
                1000
        );


        tvRankTitle.setText(status);

        btnReturnFromRank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                finish();
            }
        });
    }
}