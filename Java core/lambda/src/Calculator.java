
@FunctionalInterface
public interface Calculator {
    int sum(int a, int b);

    default int add(int a, int b) {
        return a + b;
    }
}
