// Concept of Unboxing and AutoBoxing.
// Concept of concatenation of string and integer.


import java.util.Scanner;
public class Lab04 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = sc.nextInt();
        Integer boxedNum = num;

        System.out.println("You entered: " + boxedNum);

        int unboxedNum = boxedNum;
        System.out.println("Unboxed integer value: " + unboxedNum);

        System.out.println("Enter a string and an integer to demonstrate concatenation:");
        System.out.print("Enter a string: ");
        String str = sc.next();
        System.out.print("Enter an integer: ");
        int concatNum = sc.nextInt();
        System.out.println("Concatenated result: " + str + concatNum);

        sc.close();
    }    
}
