public class School extends ServiceProvider{
    public School(int x, int y) {
        super(x, y,'S');
    }

    @Override
    public char getSymbol() {
        return 'S';
    }
}
