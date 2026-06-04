package objectville.zones;

import objectville.city.Cell;

public abstract class Zone extends Cell {

    protected int level;
    protected int output;

    protected int electricity;
    protected int water;
    protected int internet;

    protected boolean security;
    protected boolean health;
    protected boolean education;

    protected int population;
    protected int goods;
    protected int lifestyle;

    public Zone(int x, int y, char symbol) {
        super(x,y,symbol);
        this.level = 0;
        this.output = 0;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public void setEducation(boolean education) {
        this.education = education;
    }

    public boolean isZone() {
        return true;
    }

    public boolean isConnectable() {
        return true;
    }

    public int getLevel() {
        return level;
    }

    public int getOutput() {
        return output;
    }

    public void receiveElectricity(int amount) {
        electricity += amount;
    }

    public void receiveWater(int amount) {
        water += amount;
    }

    public void receiveInternet(int amount) {
        internet += amount;
    }

    public void receiveSecurity() {
        security = true;
    }

    public void receiveHealth() {
        health = true;
    }

    public void receiveEducation() {
        education = true;
    }

    public void receivePopulation(int amount) {
        population += amount;
    }

    public void receiveGoods(int amount) {
        goods += amount;
    }

    public void receiveLifestyle(int amount) {
        lifestyle += amount;
    }

    public int getUtilityDemand() {
        if (output < 1) {
            return 1;
        }
        return output;
    }

    protected int minUtility() {
        return Math.min(electricity, Math.min(water, internet));
    }

    protected void increaseLevel() {
        if (level < 3) {
            int oldLevel = level;
            level++;
            String zoneName = this.getClass().getSimpleName();
            if(zoneName.equals("Housing")) zoneName = "House";

            System.out.println(zoneName+" at ("+x+","+y+") levels up from "+oldLevel+" to "+level);
        }
    }

    protected void decreaseLevel() {
        if (level > 0) {
            int oldLevel = level;
            level--;
            String zoneName = this.getClass().getSimpleName();
            if(zoneName.equals("Housing")) zoneName = "House";

            System.out.println(zoneName+" at ("+x+","+y+") levels down from "+oldLevel+" to "+level);
        }
    }

    protected void dropToZero() {
        level = 0;
    }

    public void resetReceivedValues() {
        electricity = 0;
        water = 0;
        internet = 0;

        security = false;
        health = false;
        education = false;

        population = 0;
        goods = 0;
        lifestyle = 0;
    }

    public abstract void updateLevel();

    public abstract void calculateOutput();
}

