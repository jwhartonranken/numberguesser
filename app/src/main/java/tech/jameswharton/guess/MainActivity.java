package tech.jameswharton.guess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Public Variables
    public int randomNum = getRandomNum();
    public int totGuesses = 0;
    public int totNovice = 0;
    public int totAverage = 0;
    public int totWow = 0;
    public String status;

    // Public Constants
    public final int MINGUESS = 1;
    public final int MAXGUESS = 100;
    public final String NOENTRY = "No guess entered.";
    public final String BADENTRY = "That guess was outside the valid range. 1 to 100 only, please!";
    public final String TOOLOW = "That guess was too low. Try again!";
    public final String TOOHIGH = "That guess was too high. Try again!";
    public final String SUCCESS1 = "You guessed the Secret Number (";
    public final String SUCCESS2 = ") in ";
    public final String SUCCESS3 = " guesses!";
    public final String NOVICE = "Novice";
    public final String AVERAGE = "Average";
    public final String WOW = "Wow!";

    // Android Widgets
    EditText etGuess;
    TextView tvResult;
    Button btnCalculate;
    Button btnClear;
    Button btnRank;
    Button btnTotals;


    //Attach the widgets to their layout-respective versions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etGuess         = findViewById(R.id.etGuess);
        tvResult        = findViewById(R.id.tvResult);
        btnCalculate    = findViewById(R.id.btnCalculate);
        btnClear        = findViewById(R.id.btnClear);
        btnRank         = findViewById(R.id.btnRank);
        btnTotals       = findViewById(R.id.btnTotals);
        btnRank.setEnabled(false);

        etGuess.requestFocus();


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessDriver();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        btnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Bundle object
                Bundle bundleRank = new Bundle();
                bundleRank.putString("rank", status);

                // Create and initialize an intent
                Intent indIntent = new Intent(MainActivity.this,
                        guessingRankDisplay.class);

                // Attach the bundle to the Intent object
                indIntent.putExtras(bundleRank);

                // Start the activity
                startActivity(indIntent);
            }
        });

        btnTotals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a Bundle object
                Bundle bundleTotals = new Bundle();
                bundleTotals.putInt("wow", totWow);
                bundleTotals.putInt("average", totAverage);
                bundleTotals.putInt("novice", totNovice);

                // Create and initialize an intent
                Intent totIntent = new Intent(MainActivity.this,
                        activityDisplayTotals.class);

                // Attach the bundle to the Intent object
                totIntent.putExtras(bundleTotals);

                // Start the activity
                startActivity(totIntent);
            }
        });
    }

    // Calculate Button calls this
    public void guessDriver() {
        boolean valid = true;
        int guessedNum = 0;

        // This try-catch method allows for checking of empty editText
        try {
            guessedNum = Integer.parseInt(etGuess.getText().toString());
        }
        catch(NumberFormatException e) {
            valid = false;
            callTheToast(NOENTRY);
        }
        if (valid) {

            // Validates entry is between parameters
            if ((guessedNum < MINGUESS) || (guessedNum > MAXGUESS)) {
                callTheToast(BADENTRY);
            }
            else {
                tvResult.setText(checkEntry(guessedNum, randomNum));
            }
        }
    }

    // Clears the fields, resets the number, and resets the total guesses
    public void clear() {
        randomNum = getRandomNum();
        tvResult.setText("");
        totGuesses = 0;
        etGuess.setText("");
        btnRank.setEnabled(false);
        callTheToast(Integer.toString(randomNum));
    }

    // Creates a random number between 1 and 100
    public int getRandomNum () {
        int n = 0;
        Random rand = new Random();
        n = rand.nextInt(99) + 1;
        return n;
    }

    // Toasts whatever string is sent into it
    public void callTheToast(String os) {
        Toast t;
        t = Toast.makeText(this, os, Toast.LENGTH_LONG);
        t.show();
    }

    // Checks to see if the entry is higher than, lower than, or equal to the correct number, then returns the appropriate message
    public String checkEntry(int guess, int correctAnswer) {
        String message = "MESSAGE";
        totGuesses++;

        if (guess == correctAnswer) {
            message = SUCCESS1 + randomNum + SUCCESS2 + totGuesses + SUCCESS3;
            btnRank.setEnabled(true);
            if (totGuesses <= 5) {
                status = WOW;
                totWow++;
            }
            else if (totGuesses <= 10) {
                status = AVERAGE;
                totAverage++;
            }
            else {
                status = NOVICE;
                totNovice++;
            }
        } else if (guess < correctAnswer) {
            message = TOOLOW;
        } else {
            message = TOOHIGH;
        }

        etGuess.setText("");
        return message;
    }
}