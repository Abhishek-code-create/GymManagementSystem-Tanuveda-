package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Attendance {
    private Set<LocalDate> checkInDates = new HashSet<>();

    public boolean checkIn(LocalDate date) {
        return checkInDates.add(date);
    }

    public int getCurrentStreak() {
        int streak = 0;
        LocalDate today = LocalDate.now();
        LocalDate day = today;
        while (checkInDates.contains(day)) {
            streak++;
            day = day.minusDays(1);
        }
        return streak;
    }

    public boolean hasCheckedInToday() {
        return checkInDates.contains(LocalDate.now());
    }
}
