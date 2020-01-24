/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aau_tribes;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author manuelegger
 */
public class AAUMap {
    List<ResourceEvent> events;
    List<Castle> castles = Collections.synchronizedList(new ArrayList<>());
    int castleCount;
    String eventNowhere;

    public AAUMap() {
        events = new ArrayList<>();
        //castles = new ArrayList<>();
        castleCount = 0;
        
        events.add(new ResourceEvent("wood", 1, 46.811471, 14.363499));
        events.add(new ResourceEvent("stone", 2, 46.813471, 14.363499));
        events.add(new ResourceEvent("food", 3, 46.815471, 14.363499));
        events.add(new ResourceEvent("wood", 4, 46.817471, 14.363499));
        JSONObject noWhereEvent = new JSONObject();
        noWhereEvent.put("action", "Nowhere");
        eventNowhere = noWhereEvent.toString();
    }
    
    public MovementResponse checkEvent(double latitude, double longitude) {
        for (Castle castle : castles) {
            if (castle.isLocationInRange(latitude,longitude)) {
                return new MovementResponse(castle.toEnterCastleJson(), castle.getId(), 1);
            }
        }
        for (ResourceEvent event : events) {
            if (event.isLocationInRange(latitude, longitude)) {
                return new MovementResponse(event.toAvailableResourceJson(), event.getId(), 2);
            }
        }
        
        return new MovementResponse(eventNowhere, 0, 0);
    }
    
    public boolean addEvent() {
        //TODO: check if new event doesn't overlap with other things
        return false;
    }
    
    public String addCastle(String owner, double latitude, double longitude) {
        // TODO: check if castle doesn't overlap with other things
        if (true) {
            Castle castle = new Castle(owner, latitude, longitude, castleCount++);
            castles.add(castle);
            JSONObject newResponse = new JSONObject();
            newResponse.put("action","CastleBuilt");
            newResponse.put("playerName", owner);
            newResponse.put("baseLatitude", latitude);
            newResponse.put("baseLongitude", longitude);
            newResponse.put("success", true);
            newResponse.put("castleId", castle.getId());
            newResponse.put("wood", castle.getWood());
            newResponse.put("stone", castle.getStone());
            newResponse.put("food", castle.getFood());
            newResponse.put("level", castle.getLvl());
            return newResponse.toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("player", owner);
        jsonObject.put("success", false);
        return jsonObject.toString();
    }
    
    public String upgradeCastle(int castleId) {
        for(Castle castle: castles) {
            if (castle.id == castleId) {
                castle.upgradeCastle();
                return castle.toEnterCastleJson();
            }
        }
        return "";
    }
    
    public boolean gatherResources(int resourceId, int amount) {
        for (ResourceEvent event: events) {
            if (event.id == resourceId) {
                return event.gatherResources(amount);
            }
        }
        return false;
    }
    
    public void addRessourcesToCastle(int castleId, int wood, int stone, int food) {
        Iterator<Castle> iterator = castles.iterator();
            while (iterator.hasNext()) {
                Castle castle = iterator.next();
                if (castle.id == castleId) {
                    castle.addResource("wood", wood);
                    castle.addResource("stone", stone);
                    castle.addResource("food", food);
                }
            }
    }
    
    // for debug output
    public String printCastles() {
        String output = "CASTLES: \n";
        for (Castle castle: castles) {
            output += "Owner: " + castle.owner + ", Lvl: " + castle.lvl +
                    ", Id: " + castle.id + ", Resources: W:" + castle.wood +
                    ", S:" + castle.stone + ", F:" + castle.food + "\n";
        }
        return output;
    }
    public String printEvents() {
        String output = "EVENTS: \n";
        /*for (ResourceEvent event: events) {
            output += "Id:" + event.id +
                    ", Type: " + event.type +
                    ", Available Resources: " + event.amount + 
                    ", Range: X:" + event.xStart + " - " + event.xEnd +
                    ", Y:" + event.yStart + " - " + event.yEnd + "\n";
        }*/
        return output;
    }
}
