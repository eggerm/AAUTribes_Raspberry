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
            if (castle.isLocationInRange(latitude, longitude)) {
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

    public void addCastle(String owner, double latitude, double longitude) {
        // TODO: check if castle doesn't overlap with other things
        Castle castle = new Castle(owner, latitude, longitude, castleCount++);
        castles.add(castle);
    }

    public Castle getCastleByPlayerName(String owner) {
        for (Castle castle :
                castles) {
            if (castle.owner.equals(owner)) {
                return castle;
            }
        }
        return null;
    }

    public boolean gatherResources(int resourceId, int amount) {
        for (ResourceEvent event : events) {
            if (event.id == resourceId) {
                return event.gatherResources(amount);
            }
        }
        return false;
    }

    // for debug output
    public String printCastles() {
        String output = "CASTLES: \n";
        for (Castle castle : castles) {
            output += "Owner: " + castle.owner +
                    ", Id: " + castle.id;
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
