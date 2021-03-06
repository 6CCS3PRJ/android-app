package com.prj.app.util;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Scan implements Comparable<Scan> {
    final String bssid;
    final Double distance;
    final Date timestamp;

    public Scan(String bssid, Double distance, Date timestamp) {
        this.bssid = bssid;
        this.distance = distance;
        this.timestamp = timestamp;
    }

    public static List<JSONObject> mapScansToJSON(List<String[]> scans) throws JSONException {
        List<JSONObject> result = new ArrayList<>();
        for (String[] scan : scans) {
            JSONObject object = new JSONObject();
            object.put("t", scan[2]);
            object.put("l", Double.parseDouble(scan[1]));
            object.put("b", scan[0]);
            result.add(object);
        }
        return result;
    }

    public String getBssid() {
        return bssid;
    }

    public Double getDistance() {
        return distance;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    @NotNull
    public String toString() {
        return bssid + " | " + distance + "m";
    }

    @Override
    public int compareTo(Scan o) {
        if (getTimestamp() == null || o.getTimestamp() == null)
            return 0;
        return getTimestamp().compareTo(o.getTimestamp());
    }
}
