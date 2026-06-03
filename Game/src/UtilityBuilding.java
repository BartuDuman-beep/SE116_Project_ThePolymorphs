import java.util.LinkedList;
import java.util.Queue;

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

    public void bfs(CityGrid cityGrid){
        Cell[][]grid= cityGrid.getGrid();
        Queue<Cell>cellQueue=new LinkedList<>();
        boolean[][]visited=new boolean[grid.length][grid[0].length];
        cellQueue.add(grid[getX()][getY()]);
        visited[getX()][getY()]=true;
        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};
        while (!cellQueue.isEmpty() && getRemainingCapacity()>0){
        Cell ourCell=cellQueue.poll();
        for(int i=0;i<4;i++) {
                int ddx=ourCell.getX() + dx[i];
                int ddy=ourCell.getY() + dy[i];
                if (ddx<0||ddx>=grid.length||ddy<0||ddy>=grid[0].length){
                    continue;
                }
                if (visited[ddx][ddy]){
                    continue;
                }
                Cell neighborCell=grid[ddx][ddy];





        }




        }







    }

}

