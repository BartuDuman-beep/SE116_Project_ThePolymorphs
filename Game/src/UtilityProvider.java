
public interface UtilityProvider {
    String getUtilityType();
    int getCapacity();
    int getRemainingCapacity();
    void consume(int amount);
    void reset();
}
