
public abstract class ServiceProvider extends Cell {
    public char serviceType;
    protected int radius;

    public ServiceProvider(int x, int y, char symbol) {
        super(x, y, symbol);
    }
}