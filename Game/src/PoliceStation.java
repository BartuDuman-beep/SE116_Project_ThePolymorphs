public class PoliceStation extends ServiceProvider{
    public PoliceStation(int x, int y) {
            super(x, y,5);
        }
        @Override
        public char getSymbol() {
            return 'F';
        }
    }
