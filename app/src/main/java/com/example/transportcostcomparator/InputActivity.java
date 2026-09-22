package com.example.transportcostcomparator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InputActivity extends AppCompatActivity {

    EditText edtModeTransport;
    EditText edtDistance;
    EditText edtCostPerKm;
    EditText edtTravelDays;

    Spinner spinnerTransportType;

    Button btnCalculate;
    Button btnClear;

    TextView navHome;
    TextView navDetails;
    TextView navReport;
    TextView navHelp;

    DatabaseHelper databaseHelper;

    Calendar startDate;
    Calendar endDate;

    int travelDays = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

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

        // Database
        databaseHelper = new DatabaseHelper(this);

        // Input fields
        edtModeTransport = findViewById(R.id.edtModeTransport);
        edtDistance = findViewById(R.id.edtDistance);
        edtCostPerKm = findViewById(R.id.edtCostPerKm);
        edtTravelDays = findViewById(R.id.edtTravelDays);

        spinnerTransportType = findViewById(R.id.spinnerTransportType);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);

        // Navigation
        navHome = findViewById(R.id.navHome);
        navDetails = findViewById(R.id.navDetails);
        navReport = findViewById(R.id.navReport);
        navHelp = findViewById(R.id.navHelp);

        // Spinner options
        String[] transportTypes = {
                "Private Vehicle",
                "Taxi",
                "Bus",
                "Train",
                "Motorcycle"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                transportTypes
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerTransportType.setAdapter(adapter);

        // DATE PICKER
        edtTravelDays.setOnClickListener(v ->
                selectStartDate()
        );

        // CALCULATE
        btnCalculate.setOnClickListener(v ->
                calculateTransportCost()
        );

        // CLEAR
        btnClear.setOnClickListener(v ->
                clearFields()
        );

        // HOME
        navHome.setOnClickListener(v -> {

            Intent intent = new Intent(
                    InputActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();
        });

        // DETAILS
        navDetails.setOnClickListener(v -> {

            Intent intent = new Intent(
                    InputActivity.this,
                    DetailsActivity.class
            );

            startActivity(intent);
        });

        // REPORT
        navReport.setOnClickListener(v -> {

            Intent intent = new Intent(
                    InputActivity.this,
                    ReportsActivity.class
            );

            startActivity(intent);
        });

        // HELP
        navHelp.setOnClickListener(v -> {

            Intent intent = new Intent(
                    InputActivity.this,
                    HelpActivity.class
            );

            startActivity(intent);
        });
    }

    // SELECT START DATE
    private void selectStartDate() {

        Calendar today = Calendar.getInstance();

        DatePickerDialog startDialog =
                new DatePickerDialog(
                        this,
                        (view, year, month, dayOfMonth) -> {

                            startDate = Calendar.getInstance();

                            startDate.set(
                                    year,
                                    month,
                                    dayOfMonth,
                                    0,
                                    0,
                                    0
                            );

                            startDate.set(
                                    Calendar.MILLISECOND,
                                    0
                            );

                            selectEndDate();

                        },
                        today.get(Calendar.YEAR),
                        today.get(Calendar.MONTH),
                        today.get(Calendar.DAY_OF_MONTH)
                );

        startDialog.setTitle(
                "Select Start Travel Date"
        );

        startDialog.show();
    }

    // SELECT END DATE
    private void selectEndDate() {

        Calendar selectedStart = startDate;

        DatePickerDialog endDialog =
                new DatePickerDialog(
                        this,
                        (view, year, month, dayOfMonth) -> {

                            endDate = Calendar.getInstance();

                            endDate.set(
                                    year,
                                    month,
                                    dayOfMonth,
                                    0,
                                    0,
                                    0
                            );

                            endDate.set(
                                    Calendar.MILLISECOND,
                                    0
                            );

                            if (endDate.before(startDate)) {

                                Toast.makeText(
                                        this,
                                        "End date cannot be before start date",
                                        Toast.LENGTH_SHORT
                                ).show();

                                travelDays = 0;
                                edtTravelDays.setText("");

                                return;
                            }

                            calculateTravelDays();

                        },
                        selectedStart.get(Calendar.YEAR),
                        selectedStart.get(Calendar.MONTH),
                        selectedStart.get(Calendar.DAY_OF_MONTH)
                );

        endDialog.setTitle(
                "Select End Travel Date"
        );

        endDialog.getDatePicker().setMinDate(
                startDate.getTimeInMillis()
        );

        endDialog.show();
    }

    // CALCULATE NUMBER OF TRAVEL DAYS
    private void calculateTravelDays() {

        long difference =
                endDate.getTimeInMillis()
                        - startDate.getTimeInMillis();

        travelDays =
                (int) TimeUnit.MILLISECONDS
                        .toDays(difference) + 1;

        SimpleDateFormat dateFormat =
                new SimpleDateFormat(
                        "dd MMM yyyy",
                        Locale.getDefault()
                );

        String start =
                dateFormat.format(
                        startDate.getTime()
                );

        String end =
                dateFormat.format(
                        endDate.getTime()
                );

        edtTravelDays.setText(
                getString(
                        R.string.travel_dates_format,
                        start,
                        end,
                        travelDays
                )
        );
    }

    // CALCULATE TRANSPORT COST
    private void calculateTransportCost() {

        String mode =
                edtModeTransport
                        .getText()
                        .toString()
                        .trim();

        String distanceText =
                edtDistance
                        .getText()
                        .toString()
                        .trim();

        String costText =
                edtCostPerKm
                        .getText()
                        .toString()
                        .trim();

        // MODE VALIDATION
        if (mode.isEmpty()) {

            edtModeTransport.setError(
                    "Enter mode of transport"
            );

            edtModeTransport.requestFocus();
            return;
        }

        // DISTANCE VALIDATION
        if (distanceText.isEmpty()) {

            edtDistance.setError(
                    "Enter distance travelled"
            );

            edtDistance.requestFocus();
            return;
        }

        // COST VALIDATION
        if (costText.isEmpty()) {

            edtCostPerKm.setError(
                    "Enter cost per kilometre"
            );

            edtCostPerKm.requestFocus();
            return;
        }

        // DATE VALIDATION
        if (travelDays <= 0) {

            Toast.makeText(
                    this,
                    "Please select your travel dates",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        try {

            double distance =
                    Double.parseDouble(
                            distanceText
                    );

            double costPerKm =
                    Double.parseDouble(
                            costText
                    );

            if (distance <= 0) {

                edtDistance.setError(
                        "Distance must be greater than 0"
                );

                edtDistance.requestFocus();
                return;
            }

            if (costPerKm <= 0) {

                edtCostPerKm.setError(
                        "Cost must be greater than 0"
                );

                edtCostPerKm.requestFocus();
                return;
            }

            String transportType =
                    spinnerTransportType
                            .getSelectedItem()
                            .toString();

            // CALCULATIONS
            TransportCalculator calculator =
                    new TransportCalculator();

            double dailyCost =
                    calculator.calculateDailyCost(
                            distance,
                            costPerKm
                    );

            double monthlyCost =
                    calculator.calculateMonthlyCost(
                            dailyCost,
                            travelDays
                    );

            double monthlyDistance =
                    calculator.calculateMonthlyDistance(
                            distance,
                            travelDays
                    );

            // CATEGORY AND RECOMMENDATION
            Recommendation recommendation =
                    new Recommendation();

            String category =
                    recommendation.getCostCategory(
                            monthlyCost
                    );

            String advice =
                    recommendation.getRecommendation(
                            monthlyCost
                    );

            // SAVE TO SQLITE
            boolean saved =
                    databaseHelper.insertReport(
                            mode,
                            transportType,
                            distance,
                            costPerKm,
                            travelDays,
                            dailyCost,
                            monthlyCost,
                            monthlyDistance,
                            category,
                            advice
                    );

            if (!saved) {

                Toast.makeText(
                        this,
                        "Failed to save calculation",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Toast.makeText(
                    this,
                    "Calculation saved successfully",
                    Toast.LENGTH_SHORT
            ).show();

            // OPEN RESULTS
            Intent intent =
                    new Intent(
                            InputActivity.this,
                            ResultsActivity.class
                    );

            intent.putExtra(
                    "mode",
                    mode
            );

            intent.putExtra(
                    "transportType",
                    transportType
            );

            intent.putExtra(
                    "dailyCost",
                    dailyCost
            );

            intent.putExtra(
                    "monthlyCost",
                    monthlyCost
            );

            intent.putExtra(
                    "monthlyDistance",
                    monthlyDistance
            );

            intent.putExtra(
                    "category",
                    category
            );

            intent.putExtra(
                    "recommendation",
                    advice
            );

            intent.putExtra(
                    "travelDays",
                    travelDays
            );

            startActivity(intent);

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "Distance and cost must contain numbers only",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // CLEAR EVERYTHING
    private void clearFields() {

        edtModeTransport.setText("");
        edtDistance.setText("");
        edtCostPerKm.setText("");
        edtTravelDays.setText("");

        spinnerTransportType.setSelection(0);

        startDate = null;
        endDate = null;
        travelDays = 0;

        edtModeTransport.requestFocus();
    }
}