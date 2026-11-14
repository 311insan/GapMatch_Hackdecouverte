package com.tripledigitaldaggers;

public class MatchResult {
    public final Student student;
    public final int score;
    public final String reason;

    public MatchResult(Student student, int score, String reason) {
        this.student = student;
        this.score = score;
        this.reason = reason;
    }
}
