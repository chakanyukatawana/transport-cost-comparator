package com.example.transportcostcomparator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class ReportsActivity extends AppCompatActivity {

    TextView txtReports;

    TextView navHome;
    TextView navDetails;
    TextView navReport;
    TextView navHelp;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

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
        txtReports = findViewById(R.id.txtReports);

        navHome = findViewById(R.id.navHome);
        navDetails = findViewById(R.id.navDetails);
        navReport = findViewById(R.id.navReport);
        navHelp = findViewById(R.id.navHelp);

        // Connect database
        databaseHelper = new DatabaseHelper(this);

        // Display saved reports
        displayReports();

        // HOME
        navHome.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ReportsActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();
        });

        // DETAILS
        navDetails.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ReportsActivity.this,
                    DetailsActivity.class
            );

            startActivity(intent);
        });

        // REPORT
        navReport.setOnClickListener(v -> Toast.makeText(
                ReportsActivity.this,
                "You are already on the Report screen",
                Toast.LENGTH_SHORT
        ).show());

        // HELP
        navHelp.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ReportsActivity.this,
                    HelpActivity.class
            );

            startActivity(intent);
        });
    }

    // DISPLAY ALL SAVED REPORTS
    private void displayReports() {

        Cursor cursor = databaseHelper.getAllReports();

        // Check if database is empty
        if (cursor.getCount() == 0) {

            txtReports.setText(
                    R.string.no_reports_msg
            );

            cursor.close();
            return;
        }

        StringBuilder builder = new StringBuilder();

        while (cursor.moveToNext()) {

            // Get ID
            int id =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_ID
                            )
                    );

            // Get mode of transport
            String mode =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_MODE
                            )
                    );

            // Get transport type
            String type =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_TYPE
                            )
                    );

            // Get distance per day
            double distance =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_DISTANCE
                            )
                    );

            // Get cost per kilometre
            double costPerKm =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_COST_PER_KM
                            )
                    );

            // Get travel days
            int travelDays =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_TRAVEL_DAYS
                            )
                    );

            // Get daily cost
            double dailyCost =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_DAILY_COST
                            )
                    );

            // Get monthly cost
            double monthlyCost =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_MONTHLY_COST
                            )
                    );

            // Get monthly distance
            double monthlyDistance =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_MONTHLY_DISTANCE
                            )
                    );

            // Get category
            String category =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_CATEGORY
                            )
                    );

            // Get recommendation
            String recommendation =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_RECOMMENDATION
                            )
                    );

            // Build report
            builder.append("REPORT #")
                    .append(id)
                    .append("\n\n");

            builder.append("Mode of Transport: ")
                    .append(mode)
                    .append("\n");

            builder.append("Transport Type: ")
                    .append(type)
                    .append("\n");

            builder.append(
                    String.format(
                            Locale.getDefault(),
                            "Distance per Day: %.2f km\n",
                            distance
                    )
            );

            builder.append(
                    String.format(
                            Locale.getDefault(),
                            "Cost per Kilometre: R%.2f\n",
                            costPerKm
                    )
            );

            builder.append("Travel Days: ")
                    .append(travelDays)
                    .append("\n");

            builder.append(
                    String.format(
                            Locale.getDefault(),
                            "Daily Cost: R%.2f\n",
                            dailyCost
                    )
            );

            builder.append(
                    String.format(
                            Locale.getDefault(),
                            "Monthly Cost: R%.2f\n",
                            monthlyCost
                    )
            );

            builder.append(
                    String.format(
                            Locale.getDefault(),
                            "Monthly Distance: %.2f km\n",
                            monthlyDistance
                    )
            );

            builder.append("Category: ")
                    .append(category)
                    .append("\n");

            builder.append("Recommendation: ")
                    .append(recommendation)
                    .append("\n");

            builder.append(
                    "\n--------------------------------\n\n"
            );
        }

        // Display reports
        txtReports.setText(
                builder.toString()
        );

        cursor.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh reports when returning to this screen
        if (databaseHelper != null &&
                txtReports != null) {

            displayReports();
        }
    }
}
