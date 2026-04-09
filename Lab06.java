import java.util.Scanner;

class Lab06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

Student[] students = new Student[n];

for (int i = 0; i < n; i++) {
    System.out.println("Enter details for student " + (i + 1) + ":");
    
    System.out.print("Name: ");
    String name = sc.nextLine();
    
    System.out.print("Age: ");
    int age = sc.nextInt();
    
    System.out.print("Grade: ");
    char grade = sc.next().charAt(0);
    sc.nextLine();

    students[i] = new Student(name, age, grade);
}
        
        System.out.println("\nStudent Details:");
        for (Student student : students) {
            student.displayInfo();
        }

        System.out.print("Enter the number of teachers: ");
        int m = sc.nextInt();
        Teacher[] teachers = new Teacher[m];

        for (int i = 0; i < m; i++) {
            System.out.println("Enter details for teacher " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Subject: ");
            String subject = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            teachers[i] = new Teacher(name, subject, age);
        }

        System.out.println("\nTeacher Details:");
        for (Teacher teacher : teachers) {
            teacher.displayInfo();
        }

        System.out.print("Enter the number of schools: ");
        int p = sc.nextInt();
        sc.nextLine();
        School[] schools = new School[p];
        for (int i = 0; i < p; i++) {
            System.out.println("Enter details for school " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Location: ");
            String location = sc.nextLine();
            schools[i] = new School(name, location);
        }

        System.out.println("\nSchool Details:");
        for (School school : schools) {
            school.displayInfo();
        }

        sc.close();
    }
    public static class Student {
        private String name;
        private int age;
        private char grade;
        
        public Student(String name, int age, char grade) {
            this.name = name;
            this.age = age;
            this.grade = grade;
        }
        
        public void displayInfo() {
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Grade: " + grade);
            System.out.println();
        }
    }
    public static class Teacher {
        private String name;
        private String subject;
        private int age;
        
        public Teacher(String name, String subject, int age) {
            this.name = name;
            this.subject = subject;
            this.age = age;
        }
        
        public void displayInfo() {
            System.out.println("Name: " + name);
            System.out.println("Subject: " + subject);
            System.out.println("Age: " + age);
            System.out.println();
        }
    }
    public static class School {
        private String name;
        private String location;
        
        public School(String name, String location) {
            this.name = name;
            this.location = location;
        }
        
        public void displayInfo() {
            System.out.println("School Name: " + name);
            System.out.println("Location: " + location);
            System.out.println();
        }
    }
}
