// Create a program to input student name,marks in 3 subjects
// Calculate average and grade (A/B/C/Fail)
// Display student report
// Goal: Learn classes,arrays, and basic logic.

import java.util.Scanner;
public class Student_Grade_Management_System {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        double[] marks = new double[3];
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextDouble();
        }
        
        double average = (marks[0] + marks[1] + marks[2]) / 3;
        char grade;
        
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 75) {
            grade = 'B';
        } else if (average >= 60) {
            grade = 'C';
        } else {
            grade = 'F';
        }
        
        System.out.println("\nStudent Report:");
        System.out.println("Name: " + name);
        System.out.println("Average Marks: " + average);
        System.out.println("Grade: " + grade);
        
        scanner.close();
    }
}