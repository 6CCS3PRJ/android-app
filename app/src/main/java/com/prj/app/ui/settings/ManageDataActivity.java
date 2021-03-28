package com.prj.app.ui.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.prj.app.R;
import com.prj.app.api.CustomJsonArrayRequest;
import com.prj.app.api.VolleySingleton;
import com.prj.app.managers.DatabaseManager;
import com.prj.app.managers.PreferencesManager;
import com.prj.app.util.Scan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
@SuppressLint("SetTextI18n")
public class ManageDataActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_manage_data);
        databaseManager = DatabaseManager.getInstance(getApplicationContext());
        TextView resultTextView = findViewById(R.id.manageDataTextView);
        resultTextView.setText("");
    }

    public void deleteData(View view) {
        databaseManager.deleteData();
        TextView resultTextView = findViewById(R.id.manageDataTextView);
        resultTextView.setText("Data Deleted");
    }

    public void deleteCollection(View view) {
        TextView resultTextView = findViewById(R.id.manageDataTextView);

        List<String[]> results = databaseManager.getRawScanData();
        JSONObject jsonBody = new JSONObject();
        List<JSONObject> scans;
        try {
            scans = Scan.mapScansToJSON(results);
            jsonBody.put("scans", new JSONArray(scans));
        } catch (JSONException e) {
            resultTextView.setText("There was an error." + e.getMessage());
            return;
        }

        String URL = VolleySingleton.getApiUrl() + "scans/drop/all";
        sendPOST(URL);
    }

    private void sendPOST(String URL) {
        try {
            CustomJsonArrayRequest customJsonArrayRequest = new CustomJsonArrayRequest(Request.Method.POST, URL, null, this::response, Throwable::printStackTrace);
            VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().add(customJsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void response(JSONArray jsonArray) {
        TextView resultTextView = findViewById(R.id.manageDataTextView);
        resultTextView.setText("Collection has been deleted");
    }

}