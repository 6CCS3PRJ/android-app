package com.prj.app.ui.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prj.app.R;
import com.prj.app.logic.RiskAnalyser;
import com.prj.app.managers.DatabaseManager;
import com.prj.app.managers.PreferencesManager;

import java.util.Objects;

public class CheckExposureActivity extends AppCompatActivity {
    private RiskAnalyser riskAnalyser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_check_exposure);

        TextView resultTextView = findViewById(R.id.checkExposureResultTextView);
        resultTextView.setText("");

        riskAnalyser = new RiskAnalyser(DatabaseManager.getInstance(getApplicationContext()),
                PreferencesManager.getInstance(getApplicationContext()),
                resultTextView,
                this.getApplicationContext());
    }

    public void checkExposure(View view) {
        riskAnalyser.checkExposure();
    }
}