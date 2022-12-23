import java.util.stream.IntStream;

public class SumOfNumbers {
    public static void main(String[] args) {
        int n = 5;
        System.out.println("Sum of " + n + " numbers is: " + IntStream.rangeClosed(1, n).sum());
    }
}