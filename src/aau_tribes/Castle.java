/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aau_tribes;

import org.json.JSONObject;

/**
 *
 * @author manuelegger
 */
public class Castle {
    String owner;
    int id;
    double latitude;
    double longitude;
    double enteringRange;
    double spottingRange;


    public Castle(String owner, double latitude, double longitude, int id) {
        this.owner = owner;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        enteringRange = 10;
        spottingRange = 20;
    }

    public String getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public String toEnterCastleJson() {
        JSONObject newResponse = new JSONObject();
        newResponse.put("player", owner);
        newResponse.put("action", "CastleArrived");
        newResponse.put("latitude", latitude);
        newResponse.put("longitude", longitude);
        newResponse.put("castleId", id);
        System.out.println(newResponse.toString());
        return newResponse.toString();
    }

    public boolean isLocationInRange(double latitude, double longitude) {
        return CoordinateMath.distance(latitude, this.latitude, longitude, this.longitude) < enteringRange;
    }
}
