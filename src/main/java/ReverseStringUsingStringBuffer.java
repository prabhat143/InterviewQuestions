public class ReverseStringUsingStringBuffer {
    public static void main(String[] args) {
        String inputString = "input string";
        String reversedString = new StringBuilder(inputString).reverse().toString();
        System.out.println(reversedString);
    }
}
