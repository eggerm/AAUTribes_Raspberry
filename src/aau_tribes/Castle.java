/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aau_tribes;

/**
 *
 * @author manuelegger
 */
public class Castle {
    String owner;
    int id;
    int xStart;
    int xEnd;
    int yStart;
    int yEnd;
    int lvl;
    int wood;
    int stone;
    int food;

    public Castle(String owner, int latitude, int longitude, int id) {
        this.owner = owner;
        this.id = id;
        xStart = latitude + 10;
        xEnd = latitude - 10;
        yStart = longitude + 10;
        yEnd = longitude - 10;
        lvl = 1;
        wood = 0;
        stone = 0;
        food = 0;
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
    
    public String locationInRange(int latitude, int longitude) {
        if(latitude <= xStart && latitude >= xEnd && longitude <= yStart && longitude >= yEnd) {
            return owner + " " + id;
        }
        return null;
    }
    
    public int upgradeCastle() {
        if (wood > lvl*lvl*10 && stone > lvl*lvl*10 && food > lvl*lvl*10) {
            wood -= lvl*lvl*10;
            stone -= lvl*lvl*10;
            food -= lvl*lvl*10;
            
            return ++lvl;
        }
        return -1;
    }
}
