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
    int lvl;
    int wood;
    int stone;
    int food;

    public Castle(String owner, double latitude, double longitude, int id) {
        this.owner = owner;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        enteringRange = 10;
        spottingRange = 20;
        lvl = 1;
        wood = 100;
        stone = 100;
        food = 100;
    }

    public String getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public int getLvl() {
        return lvl;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getFood() {
        return food;
    }
    
    /*
    public void addWood(int amount) {
        wood += amount;
    }
    
    public void addStone(int amount) {
        stone += amount;
    }
    
    public void addFood(int amount) {
        food += amount;
    }*/
    
    public void addResource(String type, int amount) {
        switch (type) {
            case "wood":
                wood += amount;
                break;
            case "stone":
                stone += amount;
                break;
            case "food":
                food += amount;
                break;
            default:
                System.out.println("Resource not available");
        }
    }

    public String toEnterCastleJson() {
        JSONObject newResponse = new JSONObject();
        newResponse.put("player", owner);
        newResponse.put("action", "CastleArrived");
        newResponse.put("latitude", latitude);
        newResponse.put("longitude", longitude);
        newResponse.put("castleId", id);
        newResponse.put("wood", wood);
        newResponse.put("stone", stone);
        newResponse.put("food", food);
        newResponse.put("level", lvl);
        System.out.println(newResponse.toString());
        return newResponse.toString();
    }

    public boolean isLocationInRange(double latitude, double longitude) {
        return CoordinateMath.distance(latitude, this.latitude, longitude, this.longitude) < enteringRange;
    }
    
    public int upgradeCastle() {
        // if (wood > lvl*lvl*10 && stone > lvl*lvl*10 && food > lvl*lvl*10) {
        if (true) {
            //wood -= lvl*lvl*10;
            //stone -= lvl*lvl*10;
            //food -= lvl*lvl*10;
            
            return ++lvl;
        }
        return -1;
    }
}
