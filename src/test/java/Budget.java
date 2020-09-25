import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Budget {
    private final int amount;
    private final String yearMonth;

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    double overlappingAmount(Period period) {
        return period.overlappingDays(createPeriod()) * getDailyAmount();
    }

    private Period createPeriod() {
        return new Period(firstDay(), lastDay());
    }

    private double getDailyAmount() {
        return (double) amount / days();
    }

    private LocalDate firstDay() {
        return LocalDate.parse(yearMonth + "01", ofPattern("yyyyMMdd"));
    }

    private LocalDate lastDay() {
        YearMonth month = getYearMonth();
        return month.atEndOfMonth();
    }

    private int days() {
        YearMonth month = getYearMonth();
        return month.lengthOfMonth();
    }

    private YearMonth getYearMonth() {
        LocalDate firstDay = firstDay();
        return YearMonth.of(firstDay.getYear(), firstDay.getMonth());
    }
}
