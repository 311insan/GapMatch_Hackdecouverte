package com.tripledigitaldaggers;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        seedDemoStudents();
        SwingUtilities.invokeLater(() -> new UserInterface().display());
    }

    /** A few fake students so the demo has someone to match with. */
    private static void seedDemoStudents() {
        add("alice", "Alice Tremblay", "Computer Science", "Programming 1, Calculus",
                new TimeBlock("Tue", 13, 16), new TimeBlock("Mon", 10, 12));
        add("ben", "Ben Roy", "Computer Science", "Programming 1, Physics",
                new TimeBlock("Tue", 14, 17));
        add("chloe", "Chloe Martin", "Engineering", "Calculus, English",
                new TimeBlock("Mon", 11, 13), new TimeBlock("Thu", 12, 14));
        add("dev", "Dev Patel", "Nursing", "Biology, English",
                new TimeBlock("Wed", 9, 11));
    }

    private static void add(String user, String name, String program, String courses, TimeBlock... blocks) {
        Student s = new Student(name, program, user);
        s.setCoursesFromText(courses);
        for (TimeBlock b : blocks) s.getBlocks().add(b);
        User.addStudent(s);
    }
}
