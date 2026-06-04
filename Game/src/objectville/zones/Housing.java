package objectville.zones;

public class Housing extends Zone {

    public Housing(int x, int y) {
        super(x, y, 'H');
    }
    @Override
    public void updateLevel() {
        boolean hasUtilities = electricity > 0 && water > 0 && internet > 0;
        if (!hasUtilities) {
            dropToZero();
            return;
        }
        boolean canReachLevel2 = security && health && education;
        boolean canReachLevel3 = canReachLevel2 && lifestyle > 0;
        int targetLevel;
        if (canReachLevel3) {
            targetLevel = 3;
        } else if (canReachLevel2) {
            targetLevel = 2;
        } else {
            targetLevel = 1;
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
            output = (2 * m) + lifestyle;
        }
        if(output > 0){
            System.out.println("House at ("+getX()+","+getY()+ ") generated "+output+" population");
        }
    }
}

