import java.util.Scanner;
public class Second_To_Minute {  
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the value in seconds:");
        float x = obj.nextFloat();
        float y = x / 60;
        System.out.println("The value in minutes is: " + y);
        obj.close();

    }  
}
