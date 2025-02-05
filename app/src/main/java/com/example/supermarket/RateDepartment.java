package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RateDepartment extends AppCompatActivity {

    private RatingBar rateLiquor, rateProduce, rateMeat, rateCheese,rateEase;
    private Ratings currentRating;
    private int marketID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate_department);
        marketID = getIntent().getIntExtra("marketID", -1);
        backBtn();
        currentRating = new Ratings();
        initRatingBars();
        initSaveRating();
    }

    private void initRatingBars() {

      rateLiquor = findViewById(R.id.rateLiquorDepartment);
      rateProduce = findViewById(R.id.rateProduceDepartment);
      rateMeat = findViewById(R.id.rateMeatDepartment);
      rateCheese = findViewById(R.id.rateCheeseDepartment);
      rateEase = findViewById(R.id.rateEaseOfCheckout);
    }

    private void initSaveRating() {
        Button btnSave = findViewById(R.id.buttonSaveRate);
        btnSave.setOnClickListener(v -> {

            currentRating.setLiquor((float) rateLiquor.getRating());
            currentRating.setProduce((float) rateProduce.getRating());
            currentRating.setMeat((float) rateMeat.getRating());
            currentRating.setCheese((float) rateCheese.getRating());
            currentRating.setEase((float) rateEase.getRating());

            currentRating.setMarketID(marketID);


            SuperMarketDS ds = new SuperMarketDS(this);
            try {
                ds.open();
                if (currentRating.getRatingID() == -1) {
                    boolean wasInserted = ds.insertRating(currentRating);
                    if (wasInserted) {
                        int newId = ds.getLastRatingId();
                        currentRating.setRatingID(newId);
                    }
                }
                ds.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

//            Intent intent = new Intent(RateDepartment.this, MainActivity.class);
//            startActivity(intent);
            finish();
        });
    }

    private void backBtn() {
        Button btnBack = findViewById(R.id.backBtn);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}