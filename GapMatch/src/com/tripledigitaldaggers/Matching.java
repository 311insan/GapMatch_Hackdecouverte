package com.tripledigitaldaggers;
import java.util.*;

/**
 * Simple, transparent scoring (as in the pitch):
 *   shared courses  = 10 points each  (counts the most)
 *   overlapping free time = 3 points per shared hour
 *   same program    = 5 points
 * Students with no overlapping free time are skipped (you could never meet).
 */
public class Matching {

    public static List<MatchResult> findMatches(Student me, Collection<Student> everyone) {
        List<MatchResult> results = new ArrayList<>();

        for (Student other : everyone) {
            if (other.getUser().equalsIgnoreCase(me.getUser())) continue;

            // Shared courses
            List<String> shared = new ArrayList<>(me.getCourses());
            shared.retainAll(other.getCourses());

            // Overlapping free time
            List<String> slots = new ArrayList<>();
            int hours = 0;
            for (TimeBlock a : me.getBlocks()) {
                for (TimeBlock b : other.getBlocks()) {
                    TimeBlock o = a.overlap(b);
                    if (o != null) {
                        hours += o.length();
                        slots.add(o.length() + "h free on " + o);
                    }
                }
            }
            if (hours == 0) continue;

            boolean sameProgram = !me.getProgram().isEmpty()
                    && me.getProgram().equalsIgnoreCase(other.getProgram());

            int score = shared.size() * 10 + hours * 3 + (sameProgram ? 5 : 0);

            List<String> parts = new ArrayList<>();
            if (!shared.isEmpty()) parts.add("Both in " + String.join(", ", shared));
            parts.addAll(slots);
            if (sameProgram) parts.add("Same program");

            results.add(new MatchResult(other, score, String.join(" | ", parts)));
        }

        results.sort((x, y) -> y.score - x.score);
        return results;
    }
}
