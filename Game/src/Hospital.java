public class Hospital extends ServiceProvider {
        public Hospital(int x, int y) {
            super(x, y,'D',3);
        }

        @Override
        public char getSymbol() {
            return 'D';
        }
    }
