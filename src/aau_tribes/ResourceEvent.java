/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aau_tribes;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author manuelegger
 */
public class ResourceEvent {
    String type;
    int id;
    int amount;
    int xStart;
    int xEnd;
    int yStart;
    int yEnd;

    public ResourceEvent(String type, int id, int latitude, int longitude) {
        this.type = type;
        this.id = id;
        amount = 10;
        xStart = latitude + 10;
        xEnd = latitude - 10;
        yStart = longitude + 10;
        yEnd = longitude -10;
        
        TimerTask gainResource = new TimerTask() {
            public void run() {
                if(amount <= 50) {
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
    
    public String locationInRange(int latitude, int longitude) {
        if(latitude <= xStart && latitude >= xEnd && longitude <= yStart && longitude >= yEnd) {
            return type + " " + id + " " + amount;
        }
        return null;
    }
    
    public boolean gatherResources(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
            return true;
        }
        return false;
    }
}
