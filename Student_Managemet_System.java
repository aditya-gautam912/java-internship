import java.util.*;

public class Student_Managemet_System {
    // Simple student data class with roll number
    static class Student {
        int rollNo;
        String name;
        int age;
        String grade;
        double totalFee;
        double paidFee;

        Student(int rollNo, String name, int age, String grade, double totalFee) {
            this.rollNo = rollNo;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.totalFee = totalFee;
            this.paidFee = 0.0;
        }

        @Override
        public String toString() {
            return "RollNo: " + rollNo + ", Name: " + name + ", Age: " + age + ", Grade: " + grade +
                    ", FeePaid: " + paidFee + "/" + totalFee;
        }
    }

    // User and authentication (with role)
    static class User {
        String username;
        String password;
        String role;
        int linkedRollNo; // for student users to link to a student record (0 if none)

        User(String username, String password, String role) {
            this(username, password, role, 0);
        }

        User(String username, String password, String role, int linkedRollNo) {
            this.username = username;
            this.password = password;
            this.role = role;
            this.linkedRollNo = linkedRollNo;
        }
    }

    // Storage
    static List<Student> students = new ArrayList<>();
    static Map<Integer, Integer> attendancePresentDays = new HashMap<>(); // rollNo -> present count
    static Map<Integer, Integer> attendanceTotalDays = new HashMap<>(); // rollNo -> total days considered
    static Map<Integer, Map<String, Integer>> marks = new HashMap<>(); // rollNo -> (subject -> marks)
    static List<User> users = new ArrayList<>();
    static int nextRollNo = 1;

    // Permissions: Admin, Teacher, Student
    static void addUser(String username, String password, String role) {
        users.add(new User(username, password, role));
    }

    static void addUser(String username, String password, String role, int linkedRollNo) {
        users.add(new User(username, password, role, linkedRollNo));
    }

    static boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) return true;
        }
        return false;
    }

    static User getUser(String username) {
        for (User u : users) if (u.username.equals(username)) return u;
        return null;
    }

    // Student operations
    static Student getStudentByRoll(int rollNo) {
        for (Student s : students) if (s.rollNo == rollNo) return s;
        return null;
    }

    static void addStudentInteractive(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter age: ");
        int age = parseIntInput(scanner);
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine().trim();
        System.out.print("Enter total fee (or press enter for default 1000): ");
        String feeLine = scanner.nextLine().trim();
        double totalFee = 1000.0;
        if (!feeLine.isEmpty()) {
            try { totalFee = Double.parseDouble(feeLine); } catch (NumberFormatException ignored) {}
        }
        int roll = nextRollNo++;
        Student s = new Student(roll, name, age, grade, totalFee);
        students.add(s);
        // init attendance/maps
        attendancePresentDays.put(roll, 0);
        attendanceTotalDays.put(roll, 0);
        marks.put(roll, new HashMap<>());
        System.out.println("Student added. Assigned Roll No: " + roll);
    }

    static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : students) System.out.println(student);
    }

    static void searchStudentByName(String name) {
        boolean found = false;
        for (Student student : students) {
            if (student.name.equalsIgnoreCase(name)) {
                System.out.println(student);
                found = true;
            }
        }
        if (!found) System.out.println("Student not found.");
    }

    static void deleteStudentByRoll(int roll) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.rollNo == roll) {
                iterator.remove();
                attendancePresentDays.remove(roll);
                attendanceTotalDays.remove(roll);
                marks.remove(roll);
                // remove any users linked to this roll
                users.removeIf(u -> u.linkedRollNo == roll);
                System.out.println("Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void updateStudentByRollInteractive(Scanner scanner) {
        System.out.print("Enter roll no to update: ");
        int roll = parseIntInput(scanner);
        Student s = getStudentByRoll(roll);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter new name (or press enter to keep " + s.name + "): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) s.name = name;
        System.out.print("Enter new age (or press enter to keep " + s.age + "): ");
        String ageLine = scanner.nextLine().trim();
        if (!ageLine.isEmpty()) {
            try { s.age = Integer.parseInt(ageLine); } catch (NumberFormatException ignored) {}
        }
        System.out.print("Enter new grade (or press enter to keep " + s.grade + "): ");
        String grade = scanner.nextLine().trim();
        if (!grade.isEmpty()) s.grade = grade;
        System.out.println("Student updated.");
    }

    // Attendance
    static void markAttendanceForStudent(Scanner scanner) {
        System.out.print("Enter roll no: ");
        int roll = parseIntInput(scanner);
        Student s = getStudentByRoll(roll);
        if (s == null) { System.out.println("Student not found."); return; }
        System.out.print("Mark present? (y/n): ");
        String yn = scanner.nextLine().trim().toLowerCase();
        attendanceTotalDays.put(roll, attendanceTotalDays.getOrDefault(roll, 0) + 1);
        if (yn.equals("y") || yn.equals("yes")) {
            attendancePresentDays.put(roll, attendancePresentDays.getOrDefault(roll, 0) + 1);
            System.out.println("Marked present for " + s.name);
        } else {
            System.out.println("Marked absent for " + s.name);
        }
    }

    static void viewAttendanceForStudent(Scanner scanner) {
        System.out.print("Enter roll no: ");
        int roll = parseIntInput(scanner);
        Student s = getStudentByRoll(roll);
        if (s == null) { System.out.println("Student not found."); return; }
        int present = attendancePresentDays.getOrDefault(roll, 0);
        int total = attendanceTotalDays.getOrDefault(roll, 0);
        double percent = total == 0 ? 0.0 : (present * 100.0 / total);
        System.out.printf("Attendance for %s (Roll %d): %d/%d (%.2f%%)%n", s.name, roll, present, total, percent);
    }

    // Marks & Results
    static void addOrUpdateMarks(Scanner scanner) {
        System.out.print("Enter roll no: ");
        int roll = parseIntInput(scanner);
        Student s = getStudentByRoll(roll);
        if (s == null) { System.out.println("Student not found."); return; }
        Map<String, Integer> subjMarks = marks.get(roll);
        System.out.print("Enter subject name: ");
        String subj = scanner.nextLine().trim();
        System.out.print("Enter marks (0-100): ");
        int m = parseIntInput(scanner);
        subjMarks.put(subj, m);
        System.out.println("Marks saved for " + s.name);
    }

    static void viewMarksAndResult(Scanner scanner) {
        System.out.print("Enter roll no: ");
        int roll = parseIntInput(scanner);
        Student s = getStudentByRoll(roll);
        if (s == null) { System.out.println("Student not found."); return; }
        Map<String, Integer> subjMarks = marks.get(roll);
        if (subjMarks == null || subjMarks.isEmpty()) {
            System.out.println("No marks available for this student.");
            return;
        }
        int total = 0, count = 0;
        for (Map.Entry<String, Integer> e : subjMarks.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
            total += e.getValue();
            count++;
        }
        double percent = count == 0 ? 0.0 : (total * 1.0 / (count));
        String grade = percent >= 90 ? "A+" : percent >= 80 ? "A" : percent >= 70 ? "B+" : percent >= 60 ? "B" : percent >= 50 ? "C" : "F";
        System.out.printf("Average Marks: %.2f, Grade: %s%n", percent, grade);
    }

    // Fee Management
    static void payFee(Scanner scanner) {
        System.out.print("Enter roll no: ");
        int roll = parseIntInput(scanner);
        Student s = getStudentByRoll(roll);
        if (s == null) { System.out.println("Student not found."); return; }
        System.out.println("Total fee: " + s.totalFee + ", Already paid: " + s.paidFee);
        System.out.print("Enter amount to pay: ");
        double amt = parseDoubleInput(scanner);
        if (amt <= 0) { System.out.println("Invalid amount."); return; }
        s.paidFee += amt;
        if (s.paidFee > s.totalFee) s.paidFee = s.totalFee;
        System.out.println("Payment successful. New paid amount: " + s.paidFee);
    }

    static void viewFeeStatus(Scanner scanner) {
        System.out.print("Enter roll no: ");
        int roll = parseIntInput(scanner);
        Student s = getStudentByRoll(roll);
        if (s == null) { System.out.println("Student not found."); return; }
        System.out.println("Fee status: Paid " + s.paidFee + " / " + s.totalFee + ". Due: " + (s.totalFee - s.paidFee));
    }

    // Dashboard
    static void showDashboard() {
        System.out.println("----- Dashboard -----");
        System.out.println("Total students: " + students.size());
        long feePending = students.stream().filter(st -> st.paidFee < st.totalFee).count();
        System.out.println("Students with pending fees: " + feePending);
        // Average attendance percentage
        double avgPerc = 0.0;
        int counted = 0;
        for (Student s : students) {
            int roll = s.rollNo;
            int tot = attendanceTotalDays.getOrDefault(roll, 0);
            if (tot > 0) {
                double perc = attendancePresentDays.getOrDefault(roll, 0) * 100.0 / tot;
                avgPerc += perc;
                counted++;
            }
        }
        if (counted > 0) avgPerc /= counted;
        System.out.printf("Average attendance: %.2f%%%n", avgPerc);
    }

    // Menus and role-based flows
    static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student by name");
            System.out.println("4. Delete Student by roll");
            System.out.println("5. Update Student by roll");
            System.out.println("6. Create user");
            System.out.println("7. View Dashboard");
            System.out.println("8. Logout");
            System.out.print("Enter choice: ");
            String c = scanner.nextLine().trim();
            switch (c) {
                case "1": addStudentInteractive(scanner); break;
                case "2": displayStudents(); break;
                case "3":
                    System.out.print("Enter name to search: ");
                    searchStudentByName(scanner.nextLine().trim());
                    break;
                case "4":
                    System.out.print("Enter roll to delete: ");
                    deleteStudentByRoll(parseIntInput(scanner));
                    break;
                case "5": updateStudentByRollInteractive(scanner); break;
                case "6":
                    System.out.print("Enter username: ");
                    String u = scanner.nextLine().trim();
                    System.out.print("Enter password: ");
                    String p = scanner.nextLine().trim();
                    System.out.print("Enter role (Admin/Teacher/Student): ");
                    String r = scanner.nextLine().trim();
                    int linked = 0;
                    if (r.equalsIgnoreCase("Student")) {
                        System.out.print("Enter linked student roll no: ");
                        linked = parseIntInput(scanner);
                        if (getStudentByRoll(linked) == null) {
                            System.out.println("Invalid roll. Creating user without link.");
                            linked = 0;
                        }
                    }
                    addUser(u, p, r, linked);
                    System.out.println("User created.");
                    break;
                case "7": showDashboard(); break;
                case "8": return;
                default: System.out.println("Invalid choice."); break;
            }
        }
    }

    static void teacherMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nTeacher Menu:");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance");
            System.out.println("3. Add/Update Marks");
            System.out.println("4. View Marks & Result");
            System.out.println("5. Display Students");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            String c = scanner.nextLine().trim();
            switch (c) {
                case "1": markAttendanceForStudent(scanner); break;
                case "2": viewAttendanceForStudent(scanner); break;
                case "3": addOrUpdateMarks(scanner); break;
                case "4": viewMarksAndResult(scanner); break;
                case "5": displayStudents(); break;
                case "6": return;
                default: System.out.println("Invalid choice."); break;
            }
        }
    }

    static void studentMenu(Scanner scanner, User user) {
        int linkedRoll = user.linkedRollNo;
        if (linkedRoll == 0) {
            System.out.println("No student record linked to this account.");
            return;
        }
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. View Attendance");
            System.out.println("3. View Marks & Result");
            System.out.println("4. View Fee Status");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            String c = scanner.nextLine().trim();
            switch (c) {
                case "1":
                    Student s = getStudentByRoll(linkedRoll);
                    if (s != null) System.out.println(s); else System.out.println("Student record not found.");
                    break;
                case "2":
                    int present = attendancePresentDays.getOrDefault(linkedRoll, 0);
                    int total = attendanceTotalDays.getOrDefault(linkedRoll, 0);
                    double percent = total == 0 ? 0.0 : (present * 100.0 / total);
                    System.out.printf("Attendance: %d/%d (%.2f%%)%n", present, total, percent);
                    break;
                case "3":
                    Map<String, Integer> subjMarks = marks.get(linkedRoll);
                    if (subjMarks == null || subjMarks.isEmpty()) System.out.println("No marks available.");
                    else {
                        subjMarks.forEach((sub, m) -> System.out.println(sub + ": " + m));
                        double avg = subjMarks.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
                        System.out.printf("Average: %.2f%n", avg);
                    }
                    break;
                case "4":
                    Student st = getStudentByRoll(linkedRoll);
                    if (st != null) System.out.println("Fee: " + st.paidFee + "/" + st.totalFee + ". Due: " + (st.totalFee - st.paidFee));
                    else System.out.println("Student record not found.");
                    break;
                case "5": return;
                default: System.out.println("Invalid choice."); break;
            }
        }
    }

    // Helpers
    private static int parseIntInput(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try { return Integer.parseInt(line); } catch (NumberFormatException e) { System.out.print("Invalid number, please enter again: "); }
        }
    }

    private static double parseDoubleInput(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try { return Double.parseDouble(line); } catch (NumberFormatException e) { System.out.print("Invalid number, please enter again: "); }
        }
    }

    // Interactive initial setup helper. Call this from main (pass the main Scanner) to replace hardcoded demo data.
    static void interactiveInitialSetup(Scanner scanner) {
        System.out.print("Would you like to enter initial students interactively? (y/n): ");
        String ans = scanner.nextLine().trim().toLowerCase();
        if (!ans.equals("y") && !ans.equals("yes")) return;

        System.out.print("How many students to add: ");
        int n = parseIntInput(scanner);
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            addStudentInteractive(scanner);
            Student last = students.get(students.size() - 1);

            System.out.print("Create login for this student? (y/n): ");
            String create = scanner.nextLine().trim().toLowerCase();
            if (create.equals("y") || create.equals("yes")) {
                System.out.print("Enter username (or press enter to use \"" + last.name.toLowerCase() + "\"): ");
                String uname = scanner.nextLine().trim();
                if (uname.isEmpty()) uname = last.name.toLowerCase().replaceAll("\\s+", "");
                System.out.print("Enter password (or press enter for default 'pass'): ");
                String pwd = scanner.nextLine().trim();
                if (pwd.isEmpty()) pwd = "pass";
                addUser(uname, pwd, "Student", last.rollNo);
                System.out.println("Student user created: " + uname);
            }
        }

        System.out.print("\nCreate an Admin account interactively? (y/n): ");
        if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            System.out.print("Admin username: ");
            String au = scanner.nextLine().trim();
            System.out.print("Admin password: ");
            String ap = scanner.nextLine().trim();
            addUser(au.isEmpty() ? "admin" : au, ap.isEmpty() ? "admin123" : ap, "Admin");
            System.out.println("Admin user created.");
        }

        System.out.print("Create a Teacher account interactively? (y/n): ");
        if (scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            System.out.print("Teacher username: ");
            String tu = scanner.nextLine().trim();
            System.out.print("Teacher password: ");
            String tp = scanner.nextLine().trim();
            addUser(tu.isEmpty() ? "teacher" : tu, tp.isEmpty() ? "teacher123" : tp, "Teacher");
            System.out.println("Teacher user created.");
        }

        System.out.println("\nInteractive setup complete.");
    }
    // Main
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        interactiveInitialSetup(scanner); 
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            if ("1".equals(choice)) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine().trim();
                System.out.print("Enter password: ");
                String password = scanner.nextLine().trim();
                if (authenticate(username, password)) {
                    User user = getUser(username);
                    System.out.println("Login successful. Role: " + user.role);
                    if ("Admin".equalsIgnoreCase(user.role)) adminMenu(scanner);
                    else if ("Teacher".equalsIgnoreCase(user.role)) teacherMenu(scanner);
                    else if ("Student".equalsIgnoreCase(user.role)) studentMenu(scanner, user);
                    else {
                        System.out.println("Unknown role. Returning to main menu.");
                    }
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            } else if ("2".equals(choice)) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
