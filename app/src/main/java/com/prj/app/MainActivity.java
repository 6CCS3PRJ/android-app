package com.prj.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.skyfishjy.library.RippleBackground;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static boolean isScanning = false;
    private static Intent wifiScanningIntent;
    private boolean startScanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        handlePermission(Manifest.permission.ACCESS_FINE_LOCATION, 100);
        handlePermission(Manifest.permission.ACCESS_WIFI_STATE, 101);
        handlePermission(Manifest.permission.CHANGE_WIFI_STATE, 102);
        if (wifiScanningIntent == null) {
            wifiScanningIntent = new Intent(this, WifiScanningService.class);
            wifiScanningIntent.putExtra("inputExtra", "Wifi Scanning Service");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (isScanning && !startScanning) {
            stopService(wifiScanningIntent);
            isScanning = false;
        } else if (startScanning) {
            ContextCompat.startForegroundService(this, wifiScanningIntent);
            isScanning = true;
        }

        RippleBackground rippleBackground = findViewById(R.id.rippleBackground);

        if (rippleBackground != null) {
            if (isScanning && !rippleBackground.isRippleAnimationRunning()) {
                rippleBackground.startRippleAnimation();
            } else if (!isScanning && rippleBackground.isRippleAnimationRunning()) {
                rippleBackground.stopRippleAnimation();
            }
        }
    }

    public void handlePermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat
                    .requestPermissions(
                            MainActivity.this,
                            new String[]{permission},
                            requestCode);
        } else {
            startScanning = true;
            if (!isScanning)
                isScanning = true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String @NotNull [] permissions, int @NotNull [] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            startScanning = false;
        }
    }

    public void openDevUtils(View view) {
        startActivity(new Intent(this, DevUtils.class));
    }
}