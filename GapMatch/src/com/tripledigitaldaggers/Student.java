package com.tripledigitaldaggers;
import java.util.*;

public class Student {
    private String name;
    private String program;
    private final String user;
    private final List<String> courses = new ArrayList<>();
    private final List<TimeBlock> blocks = new ArrayList<>();

    public Student(String name, String program, String user) {
        this.name = name;
        this.program = program;
        this.user = user;
    }

    public String getName() { return name; }
    public String getProgram() { return program; }
    public String getUser() { return user; }
    public List<String> getCourses() { return courses; }
    public List<TimeBlock> getBlocks() { return blocks; }

    public void setName(String name) { this.name = name; }
    public void setProgram(String program) { this.program = program; }

    /** Replaces the course list from text like "Programming 1, Calculus". */
    public void setCoursesFromText(String text) {
        courses.clear();
        for (String c : text.split(",")) {
            c = c.trim().toUpperCase();
            if (!c.isEmpty() && !courses.contains(c)) courses.add(c);
        }
    }

    public String coursesAsText() { return String.join(", ", courses); }
}
