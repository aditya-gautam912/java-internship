import java.util.Scanner;
public class Datatype { 
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter integer value: ");
        int a = obj.nextInt();
        System.out.println("Enter float value: ");
        float b = obj.nextFloat();
        System.out.println("Enter character value: ");
        char c = obj.next().charAt(0);
        System.out.println("You entered integer: " + a);
        System.out.println("You entered float: " + b);
        System.out.println("You entered character: " + c);
        obj.close();
    }   
}
