package com.example.transportcostcomparator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class ResultsActivity extends AppCompatActivity {

    TextView txtModeResult;
    TextView txtTypeResult;
    TextView txtDailyCost;
    TextView txtMonthlyCost;
    TextView txtMonthlyDistance;
    TextView txtCategory;
    TextView txtRecommendation;

    Button btnCalculateAgain;
    Button btnExit;

    LinearLayout navBar;
    TextView navHome, navDetails, navReport, navHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        navBar = findViewById(R.id.navBar);
        ViewCompat.setOnApplyWindowInsetsListener(navBar, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), insets.top, v.getPaddingRight(), v.getPaddingBottom());
            return windowInsets;
        });

        txtModeResult = findViewById(R.id.txtModeResult);
        txtTypeResult = findViewById(R.id.txtTypeResult);
        txtDailyCost = findViewById(R.id.txtDailyCost);
        txtMonthlyCost = findViewById(R.id.txtMonthlyCost);
        txtMonthlyDistance = findViewById(R.id.txtMonthlyDistance);
        txtCategory = findViewById(R.id.txtCategory);
        txtRecommendation = findViewById(R.id.txtRecommendation);

        btnCalculateAgain =
                findViewById(R.id.btnCalculateAgain);

        btnExit =
                findViewById(R.id.btnExit);

        navHome = findViewById(R.id.navHome);
        navDetails = findViewById(R.id.navDetails);
        navReport = findViewById(R.id.navReport);
        navHelp = findViewById(R.id.navHelp);

        navHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        navReport.setOnClickListener(v -> startActivity(new Intent(this, ReportsActivity.class)));

        navHelp.setOnClickListener(v -> startActivity(new Intent(this, HelpActivity.class)));

        Intent intent = getIntent();

        String mode =
                intent.getStringExtra("mode");

        String transportType =
                intent.getStringExtra("transportType");

        double dailyCost =
                intent.getDoubleExtra(
                        "dailyCost",
                        0
                );

        double monthlyCost =
                intent.getDoubleExtra(
                        "monthlyCost",
                        0
                );

        double monthlyDistance =
                intent.getDoubleExtra(
                        "monthlyDistance",
                        0
                );

        String category =
                intent.getStringExtra("category");

        String recommendation =
                intent.getStringExtra(
                        "recommendation"
                );


        txtModeResult.setText(getString(R.string.mode_result, mode));
        txtTypeResult.setText(getString(R.string.type_result, transportType));

        txtDailyCost.setText(String.format(
                Locale.getDefault(),
                getString(R.string.daily_cost_result),
                dailyCost
        ));

        txtMonthlyCost.setText(String.format(
                Locale.getDefault(),
                getString(R.string.monthly_cost_result),
                monthlyCost
        ));

        txtMonthlyDistance.setText(String.format(
                Locale.getDefault(),
                getString(R.string.monthly_distance_result),
                monthlyDistance
        ));

        txtCategory.setText(getString(R.string.category_result, category));
        txtRecommendation.setText(getString(R.string.recommendation_result, recommendation));


        btnCalculateAgain.setOnClickListener(v -> {

            Intent newCalculation =
                    new Intent(
                            ResultsActivity.this,
                            InputActivity.class
                    );

            startActivity(newCalculation);

            finish();
        });


        btnExit.setOnClickListener(v ->
                finishAffinity()
        );
    }
}