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
        YearMonth month = getYearMonth();
        return month.atEndOfMonth();
    }

    public int days() {
        YearMonth month = getYearMonth();
        return month.lengthOfMonth();
    }

    Period createPeriod() {
        return new Period(firstDay(), lastDay());
    }

    double getDailyAmount() {
        return (double) amount / days();
    }

    private YearMonth getYearMonth() {
        LocalDate firstDay = firstDay();
        return YearMonth.of(firstDay.getYear(), firstDay.getMonth());
    }
}
