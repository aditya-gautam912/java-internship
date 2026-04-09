public class Practical1 {
    public static void main(String[] args) {
        // Print the number pattern
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        // Print the asterisk pattern
        for (int i = 1; i <= 5; i++) {
            System.out.print("* ");
        }
    }
}