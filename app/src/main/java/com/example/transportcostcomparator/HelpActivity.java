package com.example.transportcostcomparator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HelpActivity extends AppCompatActivity {

    TextView navHome;
    TextView navDetails;
    TextView navReport;
    TextView navHelp;

    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Fix Android 15 status bar overlap
        View mainView = findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {

            Insets systemBars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
            );

            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });

        // Connect XML controls
        navHome = findViewById(R.id.navHome);
        navDetails = findViewById(R.id.navDetails);
        navReport = findViewById(R.id.navReport);
        navHelp = findViewById(R.id.navHelp);

        btnExit = findViewById(R.id.btnExit);

        // HOME
        navHome.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HelpActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();
        });

        // DETAILS
        navDetails.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HelpActivity.this,
                    DetailsActivity.class
            );

            startActivity(intent);
        });

        // REPORT
        navReport.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HelpActivity.this,
                    ReportsActivity.class
            );

            startActivity(intent);
        });

        // HELP
        navHelp.setOnClickListener(v -> Toast.makeText(
                HelpActivity.this,
                "You are already on the Help screen",
                Toast.LENGTH_SHORT
        ).show());

        // EXIT
        btnExit.setOnClickListener(v ->
                finishAffinity()
        );
    }
}