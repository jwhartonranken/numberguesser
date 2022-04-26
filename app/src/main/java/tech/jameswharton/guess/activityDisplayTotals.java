package tech.jameswharton.guess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class activityDisplayTotals extends AppCompatActivity {
    Button btnReturnFromTotals;
    TextView tvTotalWow;
    TextView tvTotalNovice;
    TextView tvTotalAverage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_totals);

        tvTotalWow = findViewById(R.id.tvTotalWow);
        tvTotalAverage = findViewById(R.id.tvTotalAverage);
        tvTotalNovice = findViewById(R.id.tvTotalNovice);
        btnReturnFromTotals = findViewById(R.id.btnReturnFromTotals);

        // Get the intent in the target activity
        Intent intent = getIntent();

        // Get the attached bundle from the intent
        Bundle extras = intent.getExtras();

        if (extras != null) {
            if (extras.containsKey("wow")) {
                tvTotalWow.append(" " + Integer.toString(extras.getInt("wow")));
            }
            if (extras.containsKey("average")) {
                tvTotalAverage.append(" " + Integer.toString(extras.getInt("average")));
            }
            if (extras.containsKey("novice")) {
                tvTotalNovice.append(" " + Integer.toString(extras.getInt("novice")));
            }
        }

        btnReturnFromTotals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                finish();
            }
        });
    }
}