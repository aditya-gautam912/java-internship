// Largest of 3 numbers.
// Take user input as marks and perform grading as per following table:
// Marks        Grade
// 90 and above   O
// 80 to 89.99    A+
// 60 to 79.99    A
// 40 to 59.99    B
// Below 40       F
// Take input as year and check if it is leap year or not. 
// Check if the number is even or odd.
// Write a program to implement i/use the concept of :
// Arithmetic Operators
// Logical Operators
// Bitwise Operators
// Implement the program of type casting. 
// Write a program to implemnt switch and use it to print the name of the day of the week based on user input (1 for Monday, 2 for Tuesday, etc.).
// What is Fallthrough in switch, and how can it be prevented?. Write to demonstrate both cases. 


import java.util.Scanner;
public class Lab03 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter three numbers to find the largest:");
        double num1 = sc.nextDouble();
        double num2 = sc.nextDouble();
        double num3 = sc.nextDouble();
        double largest = num1;
        if(num2 > largest){
            largest = num2;
        }
        if(num3 > largest){
            largest = num3;
        }
        System.out.println("The largest number is: " + largest);

        System.out.println("Enter marks to determine grade:");
        double marks = sc.nextDouble();
        if(marks >= 90){
            System.out.println("Grade: O");
        } else if(marks >= 80){
            System.out.println("Grade: A+");
        } else if(marks >= 60){
            System.out.println("Grade: A");
        } else if(marks >= 40){
            System.out.println("Grade: B");
        } else {
            System.out.println("Grade: F");
        }

        System.out.print("Enter an integer: ");
        int num = sc.nextInt();
        if(num % 2 == 0){
            System.out.println(num + " is even.");
        } else {
            System.out.println(num + " is odd.");
        }

        System.out.println("Enter a year to check if it's a leap year:");
        int year = sc.nextInt();
        if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)){
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }

        // Arithmetic Operators
        System.out.println("Enter two numbers for arithmetic operations:");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulus: " + (a % b));
        // Logical Operators
        System.out.println("Enter two boolean values (true/false) for logical operations:");
        boolean bool1 = sc.nextBoolean();
        boolean bool2 = sc.nextBoolean();
        System.out.println("Logical AND: " + (bool1 && bool2));
        System.out.println("Logical OR: " + (bool1 || bool2));
        System.out.println("Logical NOT of first boolean: " + (!bool1));
        // Bitwise Operators
        System.out.println("Enter two integers for bitwise operations:");
        int int1 = sc.nextInt();
        int int2 = sc.nextInt();
        System.out.println("Bitwise AND: " + (int1 & int2));
        System.out.println("Bitwise OR: " + (int1 | int2));
        System.out.println("Bitwise XOR: " + (int1 ^ int2));
        System.out.println("Bitwise NOT of first integer: " + (~int1));

        System.out.println("Enter a number for type casting:");
        double d = sc.nextDouble();
        int i = (int)d;
        System.out.println("Type casted value: " + i);

        System.out.println("Enter a number (1-7) to find the corresponding day of the week:");
        int dayNumber = sc.nextInt();
        switch(dayNumber){
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day number.");
        }

        System.out.println("Demonstrating fallthrough in switch:");
        System.out.println("Enter a number (1-3) to demonstrate switch with fallthrough:");
        int fallthroughExample = sc.nextInt();
        switch(fallthroughExample){
            case 1:
                System.out.println("Case 1");
            case 2:
                System.out.println("Case 2");
            case 3:
                System.out.println("Case 3");
                break;
            default:
                System.out.println("Default case");
        }
        System.out.println("Preventing fallthrough in switch:");
        System.out.println("Enter a number (1-3) to demonstrate switch without fallthrough:");
        int noFallthroughExample = sc.nextInt();
        switch(noFallthroughExample){
            case 1:
                System.out.println("Case 1");
                break;
            case 2:
                System.out.println("Case 2");
                break;
            case 3:
                System.out.println("Case 3");
                break;
            default:
                System.out.println("Default case");
        }

        System.out.println("End of program.");

        sc.close();
    }
}
