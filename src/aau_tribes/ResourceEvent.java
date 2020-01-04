/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aau_tribes;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author manuelegger
 */
public class ResourceEvent {
    String type;
    int id;
    int amount;
    double latitude;
    double longitude;
    double gatheringRange;
    double spottingRange;

    public ResourceEvent(String type, int id, double latitude, double longitude) {
        this.type = type;
        this.id = id;
        amount = 10;
        gatheringRange = 10;
        spottingRange = 20;
        this.latitude = latitude;
        this.longitude = longitude;

        TimerTask gainResource = new TimerTask() {
            public void run() {
                if (amount <= 50) {
                    amount++;
                }
            }
        };

        Timer timer = new Timer("Timer");

        timer.scheduleAtFixedRate(gainResource, 5000L, 5000L);
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isLocationInRange(double latitude, double longitude) {
        return CoordinateMath.distance(latitude, this.latitude, longitude, this.longitude) < gatheringRange;
    }

    public boolean gatherResources(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
            return true;
        }
        return false;
    }

    public String toAvailableResourceJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "AvailableResources");
        jsonObject.put("latitude", latitude);
        jsonObject.put("longitude", longitude);
        jsonObject.put("resourceType", type);
        jsonObject.put("resourceAmount", amount);
        jsonObject.put("resourceId", id);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }


}
