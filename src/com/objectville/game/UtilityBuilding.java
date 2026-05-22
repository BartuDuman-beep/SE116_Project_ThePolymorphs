
public abstract class UtilityBuilding implements UtilityProvider {
    private String utilityType;
    private int capacity;
    private int remainingCapacity;
    private int row;
    private int column;


    public UtilityBuilding(String utilityType, int capacity,int remainingCapacity, int row, int column) {
        this.utilityType = utilityType;
        this.capacity=capacity;
        this.row=row;
        this.column=column;
        this.remainingCapacity=remainingCapacity;


    }


    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }


    public int getCapacity() {
        return capacity;
    }


    public String getUtilityType() {
        return utilityType;

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

    public void bfs(){

    }

}

