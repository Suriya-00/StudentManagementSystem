import java.io.*;
import java.util.*;

class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = loadStudentsFromFile();
    }

    // Add a student
    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
    }

    // Remove a student by roll number
    public boolean removeStudent(int rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber() == rollNumber) {
                iterator.remove();
                saveStudentsToFile();
                return true;
            }
        }
        return false;
    }

    // Search for a student
    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Save students to file
    private void saveStudentsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // Load students from file
    @SuppressWarnings("unchecked")
    private List<Student> loadStudentsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
