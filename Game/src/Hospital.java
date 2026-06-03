    public class Hospital extends ServiceProvider{
        public Hospital(int x, int y) {
            super(x, y,'D');
        }

        @Override
        public char getSymbol() {
            return 'D';
        }
    }
