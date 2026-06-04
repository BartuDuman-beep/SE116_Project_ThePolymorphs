package objectville.utilities;

public interface UtilityProvider {
    int getCapacity();
    int getRemainingCapacity();
    void consume(int amount);
    void reset();
}
