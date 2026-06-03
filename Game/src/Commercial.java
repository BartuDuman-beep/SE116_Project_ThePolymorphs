public class Commercial extends Zone {

    public Commercial(int x, int y) {
        super(x, y, 'C');
    }
    @Override
    public void updateLevel() {
        boolean hasUtilities = electricity > 0 && water > 0 && internet > 0;
        if (!hasUtilities) {
            dropToZero();
            return;
        }
        boolean canReachLevel1 = population > 0 && goods > 0;
        boolean canReachLevel2 = canReachLevel1 && security;
        boolean canReachLevel3 = canReachLevel2 && population > 1 && goods > 1;
        int targetLevel;
        if (canReachLevel3) {
            targetLevel = 3;
        } else if (canReachLevel2) {
            targetLevel = 2;
        } else if (canReachLevel1) {
            targetLevel = 1;
        } else {
            targetLevel = 0;
        }
        if (targetLevel > level) {
            increaseLevel();
        } else if (targetLevel < level) {
            decreaseLevel();
        }
    }
    @Override
    public void calculateOutput() {
        int m = minUtility();
        if (level == 0) {
            output = 0;
        } else if (level == 1) {
            output = m;
        } else if (level == 2) {
            output = 2 * m;
        } else {
            output = (2 * m) + Math.min(population, goods);
        }
    }
}