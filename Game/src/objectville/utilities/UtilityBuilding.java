package objectville.utilities;


import objectville.city.Cell;

public abstract class UtilityBuilding extends Cell {
    private int capacity;
    private int remainingCapacity;


    public UtilityBuilding(int x, int y, char symbol ,int capacity,int remainingCapacity) {
        super(x, y, symbol);
        this.capacity=capacity;
        this.remainingCapacity=remainingCapacity;


    }

    public int getCapacity() {
        return capacity;
    }



    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public void consume(int amount){
        if (amount>remainingCapacity) {
            remainingCapacity = 0;
        }else{
            remainingCapacity-=amount;
        }
    }

    public void reset(){
        remainingCapacity=capacity;
    }
}

