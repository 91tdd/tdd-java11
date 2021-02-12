import java.time.LocalDate;
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
        YearMonth yearMonth = getYearMonth();
        return yearMonth.atEndOfMonth();
    }

    public int days() {
        return getYearMonth().lengthOfMonth();
    }

    Period createPeriod() {
        return new Period(firstDay(), lastDay());
    }

    private YearMonth getYearMonth() {
        return YearMonth.parse(this.yearMonth, ofPattern("yyyyMM"));
    }
}
