import java.util.Scanner;

class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudentUI(scanner, sms);
                    break;
                case "2":
                    editStudentUI(scanner, sms);
                    break;
                case "3":
                    removeStudentUI(scanner, sms);
                    break;
                case "4":
                    searchStudentUI(scanner, sms);
                    break;
                case "5":
                    sms.displayAllStudents();
                    break;
                case "6":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void addStudentUI(Scanner scanner, StudentManagementSystem sms) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter Roll Number: ");
        int rollNumber = validateIntegerInput(scanner);
        if (rollNumber == -1) return;

        if (sms.searchStudent(rollNumber) != null) {
            System.out.println("Student with this roll number already exists.");
            return;
        }

        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine().trim();
        if (grade.isEmpty()) {
            System.out.println("Grade cannot be empty!");
            return;
        }

        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty() || !email.contains("@")) {
            System.out.println("Invalid email!");
            return;
        }

        sms.addStudent(new Student(name, rollNumber, grade, email));
        System.out.println("Student added successfully.");
    }

    private static void editStudentUI(Scanner scanner, StudentManagementSystem sms) {
        System.out.print("Enter Roll Number to Edit: ");
        int rollNumber = validateIntegerInput(scanner);
        if (rollNumber == -1) return;

        Student student = sms.searchStudent(rollNumber);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name (leave blank to keep unchanged): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) student.setName(name);

        System.out.print("Enter New Grade (leave blank to keep unchanged): ");
        String grade = scanner.nextLine().trim();
        if (!grade.isEmpty()) student.setGrade(grade);

        System.out.print("Enter New Email (leave blank to keep unchanged): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty() && email.contains("@")) student.setEmail(email);

        sms.removeStudent(rollNumber); // Remove old entry
        sms.addStudent(student);       // Re-add updated entry
        System.out.println("Student information updated.");
    }

    private static void removeStudentUI(Scanner scanner, StudentManagementSystem sms) {
        System.out.print("Enter Roll Number to Remove: ");
        int rollNumber = validateIntegerInput(scanner);
        if (rollNumber == -1) return;

        if (sms.removeStudent(rollNumber)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentUI(Scanner scanner, StudentManagementSystem sms) {
        System.out.print("Enter Roll Number to Search: ");
        int rollNumber = validateIntegerInput(scanner);
        if (rollNumber == -1) return;

        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static int validateIntegerInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
            return -1;
        }
    }
}

