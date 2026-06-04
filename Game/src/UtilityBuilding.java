

public abstract class UtilityBuilding extends Cell implements UtilityProvider {
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
    @Override
    public void consume(int amount){
        if (amount>remainingCapacity) {
            remainingCapacity = 0;
        }else{
            remainingCapacity-=amount;
        }
    }
    @Override
    public void reset(){
        remainingCapacity=capacity;
    }



}

