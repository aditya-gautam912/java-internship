import java.util.*;
public class WrapperClass {
    public static void main(String[] args){
        // 1. parseInt() method
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        String input = sc.nextLine();
        int number = Integer.parseInt(input);
        System.out.println("You entered: " + number);

       // String Class
        String str = "Hello, World!";
        System.out.println("String: " + str);
        System.out.println("Length: " + str.length());
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Lowercase: " + str.toLowerCase());

        // StringBuilder Class
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(", World!");
        sb.insert(5, " Java");
        System.out.println("StringBuilder: " + sb.toString());

        // StringBuffer Class
        StringBuffer sBuffer = new StringBuffer("Hello");
        sBuffer.append(", World!");
        sBuffer.insert(5, " Java");
        System.out.println("StringBuffer: " + sBuffer.toString());

        sc.close();
    }
    
}

// AutoBoxing and Unboxing example.
// AutoBoxing: Converting a primitive type to its corresponding wrapper class.
// Unboxing: Converting a wrapper class back to its corresponding primitive type.
// Not in code.
// AutoBoxing example
// int num = 10;
// Integer wrapperNum = num; // AutoBoxing
// System.out.println("Wrapper class value: " + wrapperNum);
// Unboxing example
// Integer wrapperNum2 = 20;
// int num2 = wrapperNum2; // Unboxing
// System.out.println("Primitive type value: " + num2);
