import java.util.Scanner;
public class Arithmetic {
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter first number:");
        int x = obj.nextInt(); 
        System.out.println("Enter second number:");
        int y = obj.nextInt();
        int sum = x + y;
        int diff = x - y;
        int product = x * y;
        double quotient = (double)x / y;
        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + diff);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);
        obj.close();
    }
}
