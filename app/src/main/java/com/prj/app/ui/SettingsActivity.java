package com.prj.app.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.prj.app.R;
import com.prj.app.logic.BSSIDMatcher;
import com.prj.app.managers.DatabaseManager;

import java.util.Objects;

@SuppressLint({"UseSwitchCompatOrMaterialCode", "SetTextI18n", "SimpleDateFormat"})
public class SettingsActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private BSSIDMatcher bssidMatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_settings);


//        databaseManager = DatabaseManager.getInstance(getApplicationContext());
//        bssidMatcher = new BSSIDMatcher(databaseManager,
//                findViewById(R.id.resultTextView),
//                this.getApplicationContext());
//        initialiseSettings();
    }


    public void openPreferences(View view) {
        startActivity(new Intent(this, PreferencesActivity.class));
    }

    public void openUploadScans(View view) {
        startActivity(new Intent(this, UploadScansActivity.class));
    }

    public void openManageData(View view) {
        startActivity(new Intent(this, ManageDataActivity.class));
    }

//    public void toggleLocationSwitch(View view) {
//        Switch switchView = findViewById(R.id.locationSwitch);
//        boolean newValue = !databaseManager.canSaveHotspotLocation();
//        databaseManager.setSaveHotspotLocation(newValue);
//        switchView.setChecked(newValue);
//    }
//
//    private void initialiseSettings() {
//        Switch switchView = findViewById(R.id.locationSwitch);
//        switchView.setChecked(databaseManager.canSaveHotspotLocation());
//
//        EditText maxDistEditText = findViewById(R.id.maxDistEditText);
//        EditText maxTimeEditText = findViewById(R.id.maxTimeEditText);
//        EditText minHotspotsEditText = findViewById(R.id.minHotspotsEditExt);
//        EditText minTimestampsEditText = findViewById(R.id.minTimestampsEditText);
//        EditText apiUrlEditText = findViewById(R.id.apiUrlEditText);
//
//        TextView resultTextView = findViewById(R.id.resultTextView);
//        resultTextView.setMovementMethod(new ScrollingMovementMethod());
//
//        maxDistEditText.setHint("DIST " + bssidMatcher.getMAX_DISTANCE_DIFFERENCE() + "m");
//        maxTimeEditText.setHint("TIME " + bssidMatcher.getMAX_TIME_DIFFERENCE() + "s");
//        minHotspotsEditText.setHint("HOTSPOTS " + bssidMatcher.getMIN_NUMBER_OF_NEAR_HOTSPOTS());
//        minTimestampsEditText.setHint("TIMESTAMPS " + bssidMatcher.getMIN_NUMBER_OF_CONSECUTIVE_TIMESTAMPS());
//        apiUrlEditText.setText(VolleySingleton.API_URL.split("/")[2]);
//    }
//
//    public void updateSettings(View view) {
//        EditText maxDistEditText = findViewById(R.id.maxDistEditText);
//        EditText maxTimeEditText = findViewById(R.id.maxTimeEditText);
//        EditText minHotspotsEditText = findViewById(R.id.minHotspotsEditExt);
//        EditText minTimestampsEditText = findViewById(R.id.minTimestampsEditText);
//        EditText apiUrlEditText = findViewById(R.id.apiUrlEditText);
//
//        if (maxDistEditText.getText().toString().length() > 0) {
//            double newMaxDist = Double.parseDouble(maxDistEditText.getText().toString());
//            bssidMatcher.setMAX_DISTANCE_DIFFERENCE(newMaxDist);
//            maxDistEditText.setText("");
//        }
//        if (maxTimeEditText.getText().toString().length() > 0) {
//            double newMaxTime = Double.parseDouble(maxTimeEditText.getText().toString());
//            bssidMatcher.setMAX_TIME_DIFFERENCE(newMaxTime);
//            maxTimeEditText.setText("");//        databaseManager.deleteData();
//        TextView resultTextView = findViewById(R.id.resultTextView);
//        resultTextView.setText("Data Deleted");
//
//    }
//        }
//        if (minHotspotsEditText.getText().toString().length() > 0) {
//            double newMinHotspots = Double.parseDouble(minHotspotsEditText.getText().toString());
//            bssidMatcher.setMIN_NUMBER_OF_NEAR_HOTSPOTS(newMinHotspots);
//            minHotspotsEditText.setText("");
//        }
//        if (minTimestampsEditText.getText().toString().length() > 0) {
//            double newMinTimestamps = Double.parseDouble(minTimestampsEditText.getText().toString());
//            bssidMatcher.setMIN_NUMBER_OF_CONSECUTIVE_TIMESTAMPS(newMinTimestamps);
//            minTimestampsEditText.setText("");
//        }
//        if (apiUrlEditText.getText().toString().length() > 0) {
//            VolleySingleton.setAPI_URL(apiUrlEditText.getText().toString());
//            apiUrlEditText.setText("");
//        }
//        initialiseSettings();
//        InputMethodManager inputMethodManager =
//                (InputMethodManager) this.getSystemService(
//                        Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(
//                Objects.requireNonNull(this.getCurrentFocus()).getWindowToken(), 0);
//    }
//
//    public void getMatchingBSSIDs(View view) {
//        bssidMatcher.getMatchingBSSIDs();
//    }
//
//    public void deleteData(View view) {
//        databaseManager.deleteData();
//        TextView resultTextView = findViewById(R.id.resultTextView);
//        resultTextView.setText("Data Deleted");
//
//    }
//
//    public void uploadScans(View view) {
//        Log.d("debug", "Uploading scans");
//        TextView resultTextView = findViewById(R.id.resultTextView);
//
//        List<String[]> results = databaseManager.getRawScanData();
//        JSONObject jsonBody = new JSONObject();
//        List<JSONObject> scans = new ArrayList<>();
//        JSONArray locationData = databaseManager.getRawLocationData();
//
//        try {
//            scans = results.size() > 0 ? Scan.mapScansToJSON(results) : scans;
//            jsonBody.put("scans", new JSONArray(scans));
//            jsonBody.put("wifis", locationData);
//            jsonBody.put("token", "Jt(I9}SFd~|.}c^ZN?(4y8m?aI0~-b");
//        } catch (JSONException e) {
//            resultTextView.setText("There was an error." + e.getMessage());
//            return;
//        }
//
//        String URL = VolleySingleton.API_URL + "scans/insert/new";
//        sendPOST(URL, jsonBody);
//
//        StringBuilder out = new StringBuilder();
//        List<Scan> uploadedScans = null;
//        try {
//            uploadedScans = bssidMatcher.parseScans(new JSONArray(scans));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        assert uploadedScans != null;
//        uploadedScans.sort(Comparator.comparing(Scan::getTimestamp));
//        for (Scan scan : uploadedScans) {
//            out.append("\n").append(scan.toString());
//        }
//        resultTextView.setText("Uploading " + results.size() + " scans\n" + out.toString());
//    }
//
//    public void deleteCollection(View view) {
//        TextView resultTextView = findViewById(R.id.resultTextView);
//
//        List<String[]> results = databaseManager.getRawScanData();
//        JSONObject jsonBody = new JSONObject();
//        List<JSONObject> scans;
//        try {
//            scans = Scan.mapScansToJSON(results);
//            jsonBody.put("scans", new JSONArray(scans));
//        } catch (JSONException e) {
//            resultTextView.setText("There was an error." + e.getMessage());
//            return;
//        }
//
//        String URL = VolleySingleton.API_URL + "scans/drop/all";
//        sendPOST(URL, null);
//
//        resultTextView.setText("Deleted scans.");
//    }
//
//    private void sendPOST(String URL, JSONObject jsonBody) {
//        try {
//            CustomJsonArrayRequest customJsonArrayRequest = new CustomJsonArrayRequest(Request.Method.POST, URL, jsonBody, this::response, Throwable::printStackTrace);
//            VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().add(customJsonArrayRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void response(JSONArray jsonArray) {
//        TextView resultTextView = findViewById(R.id.resultTextView);
//        resultTextView.setText("Uploaded!");
//    }

    public void closeActivity(View view) {
        finish();
    }
}