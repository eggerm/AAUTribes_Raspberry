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
public class Player {
    String name;
    int wood;
    int stone;
    int food;

    public Player(String name) {
        this.name = name;
        wood = 0;
        stone = 0;
        food = 0;
    }

    public String getName() {
        return name;
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
                if (wood > 100)
                    wood = 100;
                break;
            case "stone":
                stone += amount;
                if (stone > 100)
                    stone = 100;
                break;
            case "food":
                food += amount;
                if (food > 100)
                    food = 100;
                break;
            default:
                System.out.println("Resource not available");
        }
    }
    
    public void clearResources() {
        wood = 0;
        stone = 0;
        food = 0;
    } 
}
