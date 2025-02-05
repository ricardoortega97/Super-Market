package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Market currentMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        currentMarket = new Market();
        initSave();
        initTextChangeField();
        initRateBtn();
        initSave();

    }

    private void initRateBtn() {
        Button btnRate = findViewById(R.id.buttonRate);
        btnRate.setOnClickListener(v -> {
            SuperMarketDS ds = new SuperMarketDS(this);
            try {
                ds.open();
                if (currentMarket.getMarketID() == -1) {
                    boolean wasInserted = ds.insertMarket(currentMarket);
                    if (wasInserted) {
                        int newId = ds.getLastMarketId();
                        currentMarket.setMarketID(newId);
                    }
                } else {
                    ds.updateMarket(currentMarket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ds.close();
            }
            Intent intent = new Intent(MainActivity.this, RateDepartment.class);
            intent.putExtra("marketID", currentMarket.getMarketID());
            startActivity(intent);
        });
    }

    private void initSave() {
        Button btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(v -> {
            SuperMarketDS ds = new SuperMarketDS(this);
            try {
                ds.open();
                if (currentMarket.getMarketID() == -1) {
                    boolean wasInserted = ds.insertMarket(currentMarket);
                    if (wasInserted) {
                        int newId = ds.getLastMarketId();
                        currentMarket.setMarketID(newId);
                    }
                } else {
                    ds.updateMarket(currentMarket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ds.close();
            }
            setForEditing(false);
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
    }

    private void initTextChangeField() {
        EditText editName = findViewById(R.id.editName);
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentMarket.setName(editName.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        EditText editAddress = findViewById(R.id.editAddress);
        editAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                currentMarket.setAddress(editAddress.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

}