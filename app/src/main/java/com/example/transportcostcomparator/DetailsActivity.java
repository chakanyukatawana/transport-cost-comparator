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

public class DetailsActivity extends AppCompatActivity {

    TextView txtDetails;
    TextView navHome;
    TextView navDetails;
    TextView navReport;
    TextView navHelp;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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

        txtDetails = findViewById(R.id.txtDetails);

        navHome = findViewById(R.id.navHome);
        navDetails = findViewById(R.id.navDetails);
        navReport = findViewById(R.id.navReport);
        navHelp = findViewById(R.id.navHelp);

        databaseHelper = new DatabaseHelper(this);

        displaySavedDetails();

        navHome.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DetailsActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();
        });

        navDetails.setOnClickListener(v -> {

            Toast.makeText(
                    DetailsActivity.this,
                    "You are already on the Details screen",
                    Toast.LENGTH_SHORT
            ).show();
        });

        navReport.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DetailsActivity.this,
                    ReportsActivity.class
            );

            startActivity(intent);
        });

        navHelp.setOnClickListener(v -> {

            Intent intent = new Intent(
                    DetailsActivity.this,
                    HelpActivity.class
            );

            startActivity(intent);
        });
    }

    private void displaySavedDetails() {

        Cursor cursor = databaseHelper.getAllReports();

        if (cursor.getCount() == 0) {

            txtDetails.setText(
                    R.string.no_details_msg
            );

            cursor.close();
            return;
        }

        StringBuilder builder = new StringBuilder();

        while (cursor.moveToNext()) {

            String mode =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_MODE
                            )
                    );

            String type =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_TYPE
                            )
                    );

            double distance =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_DISTANCE
                            )
                    );

            double costPerKm =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_COST_PER_KM
                            )
                    );

            int travelDays =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_TRAVEL_DAYS
                            )
                    );

            double dailyCost =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_DAILY_COST
                            )
                    );

            double monthlyCost =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_MONTHLY_COST
                            )
                    );

            double monthlyDistance =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_MONTHLY_DISTANCE
                            )
                    );

            String category =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_CATEGORY
                            )
                    );

            String recommendation =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_RECOMMENDATION
                            )
                    );

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
                    "\n------------------------------\n\n"
            );
        }

        txtDetails.setText(
                builder.toString()
        );

        cursor.close();
    }
}