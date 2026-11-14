package com.tripledigitaldaggers;
import java.util.*;

/** Tiny in-memory "database" of students. Given more time, this would be SQL. */
public class User {
    private static final Map<String, Student> students = new LinkedHashMap<>();

    private static String key(String user) { return user.trim().toLowerCase(); }

    public static void addStudent(Student s) { students.put(key(s.getUser()), s); }
    public static boolean findUsername(String user) { return students.containsKey(key(user)); }
    public static Student getStudent(String user) { return students.get(key(user)); }
    public static Collection<Student> all() { return students.values(); }
}
