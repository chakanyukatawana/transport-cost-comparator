package com.example.transportcostcomparator;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Button btnListen;
    Button btnExit;

    TextView navHome;
    TextView navDetails;
    TextView navReport;
    TextView navHelp;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Connect buttons
        btnStart = findViewById(R.id.btnStart);
        btnListen = findViewById(R.id.btnListen);
        btnExit = findViewById(R.id.btnExit);

        // Connect navigation
        navHome = findViewById(R.id.navHome);
        navDetails = findViewById(R.id.navDetails);
        navReport = findViewById(R.id.navReport);
        navHelp = findViewById(R.id.navHelp);

        // START CALCULATION
        btnStart.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    InputActivity.class
            );

            startActivity(intent);
        });

        // LISTEN TO ME
        btnListen.setOnClickListener(v -> {

            if (mediaPlayer == null) {

                mediaPlayer = MediaPlayer.create(
                        MainActivity.this,
                        R.raw.transport_audio
                );
            }

            if (mediaPlayer != null) {

                if (!mediaPlayer.isPlaying()) {

                    mediaPlayer.start();

                    btnListen.setText(R.string.stop_audio);

                    Toast.makeText(
                            MainActivity.this,
                            "Playing transport audio",
                            Toast.LENGTH_SHORT
                    ).show();

                } else {

                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);

                    btnListen.setText(R.string.listen_to_me);

                    Toast.makeText(
                            MainActivity.this,
                            "Audio stopped",
                            Toast.LENGTH_SHORT
                    ).show();
                }

                mediaPlayer.setOnCompletionListener(mp -> {

                    btnListen.setText(R.string.listen_to_me);

                    mp.seekTo(0);
                });
            }
        });

        // EXIT
        btnExit.setOnClickListener(v -> {

            stopAudio();

            finishAffinity();
        });

        // HOME
        navHome.setOnClickListener(v -> {

            Toast.makeText(
                    MainActivity.this,
                    "You are already on the Home screen",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // DETAILS
        navDetails.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    DetailsActivity.class
            );

            startActivity(intent);
        });

        // REPORT
        navReport.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ReportsActivity.class
            );

            startActivity(intent);
        });

        // HELP
        navHelp.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    HelpActivity.class
            );

            startActivity(intent);
        });
    }

    private void stopAudio() {

        if (mediaPlayer != null) {

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }

            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopAudio();

        if (btnListen != null) {
            btnListen.setText(R.string.listen_to_me);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopAudio();
    }
}