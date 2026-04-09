// Concept of Auto Boxing and Unboxing.

public class Lab05 {
    public static void main(String[] args) {
        // Auto Boxing
        Integer a = 10; // int to Integer
        Double b = 20.5; // double to Double

        // Unboxing
        int c = a; // Integer to int
        double d = b; // Double to double

        System.out.println("Auto Boxing: " + a + ", " + b);
        System.out.println("Unboxing: " + c + ", " + d);
    }
    
}
