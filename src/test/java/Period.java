import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    long overlappingDays(Period another) {
        if (isValid()) {
            return 0;
        }
        if (withoutOverlap(another)) {
            return 0;
        }
        LocalDate overlappingStart = getStart().isAfter(another.start)
                ? getStart()
                : another.start;
        LocalDate overlappingEnd = getEnd().isBefore(another.end)
                ? getEnd()
                : another.end;
        return DAYS.between(overlappingStart, overlappingEnd) + 1;
    }

    private boolean withoutOverlap(Period another) {
        return getEnd().isBefore(another.start) || getStart().isAfter(another.end);
    }

    private boolean isValid() {
        return start.isAfter(end);
    }
}
