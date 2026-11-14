package com.tripledigitaldaggers;

/** A free-time block on one day, e.g. Tue 13:00-15:00. */
public class TimeBlock {
    private final String day;
    private final int start;
    private final int end;

    public TimeBlock(String day, int start, int end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public int length() { return end - start; }

    /** Returns the shared part of two blocks, or null if they don't overlap. */
    public TimeBlock overlap(TimeBlock other) {
        if (!day.equals(other.day)) return null;
        int s = Math.max(start, other.start);
        int e = Math.min(end, other.end);
        return s < e ? new TimeBlock(day, s, e) : null;
    }

    @Override
    public String toString() {
        return String.format("%s %02d:00-%02d:00", day, start, end);
    }
}
