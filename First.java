import java.util.Scanner;
public class First {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.println("Input integer value:");
        int x = obj.nextInt();
        System.out.println("User input: " + x);
        obj.nextLine();
        System.out.println("Enter your name:");
        String name = obj.nextLine();
        System.out.println("Your name is: " + name);
        obj.close();
    }
}
