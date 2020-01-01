/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aau_tribes;

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

    public AAUMap() {
        events = new ArrayList<>();
        //castles = new ArrayList<>();
        castleCount = 0;
        
        events.add(new ResourceEvent("wood", 1, 80, 80));
        events.add(new ResourceEvent("stone", 2, 50, 50));
        events.add(new ResourceEvent("food", 3, 110, 110));
        events.add(new ResourceEvent("wood", 4, 180, 180));
    }
    
    public String checkEvent(int latitude, int longitude) {
        for (Castle castle : castles) {
            if (castle.locationInRange(latitude, longitude) != null) {
                return "Castle " + castle.locationInRange(latitude, longitude);
            }
        }
        for (ResourceEvent event : events) {
            if (event.locationInRange(latitude, longitude) != null) {
                return "Resource " + event.locationInRange(latitude, longitude);
            }
        }
        
        return null;
    }
    
    public boolean addEvent() {
        //TODO: check if new event doesn't overlap with other things
        return false;
    }
    
    public String addCastle(String owner, int latitude, int longitude) {
        // TODO: check if castle doesn't overlap with other things
        if (true) {
            castles.add(new Castle(owner, latitude, longitude, castleCount++));
            return "success";
        }
        
        return "fail";
    }
    
    public int upgradeCastle(int castleId) {
        for(Castle castle: castles) {
            if (castle.id == castleId) {
                return castle.upgradeCastle();
            }
        }
        return -1;
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
        for (ResourceEvent event: events) {
            output += "Id:" + event.id +
                    ", Type: " + event.type +
                    ", Available Resources: " + event.amount + 
                    ", Range: X:" + event.xStart + " - " + event.xEnd +
                    ", Y:" + event.yStart + " - " + event.yEnd + "\n";
        }
        return output;
    }
}
