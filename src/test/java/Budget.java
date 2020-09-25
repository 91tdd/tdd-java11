import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Budget {
    public final int amount;
    private final String yearMonth;

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    public LocalDate firstDay() {
        return LocalDate.parse(yearMonth + "01", ofPattern("yyyyMMdd"));
    }

    public LocalDate lastDay() {
        LocalDate firstDay = firstDay();
        YearMonth month = YearMonth.of(firstDay.getYear(), firstDay.getMonth());
        return month.atEndOfMonth();
    }

    public int days() {
        LocalDate firstDay = firstDay();
        YearMonth month = YearMonth.of(firstDay.getYear(), firstDay.getMonth());
        return month.lengthOfMonth();
    }

    Period createPeriod() {
        return new Period(firstDay(), lastDay());
    }
}
