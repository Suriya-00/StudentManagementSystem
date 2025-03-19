import java.io.Serializable;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private String email;

    public Student(String name, int rollNumber, String grade, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
    }

    // Getters and setters
    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade + ", Email: " + email;
    }
}
