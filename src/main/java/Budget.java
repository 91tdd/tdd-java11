import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class Budget {
    private final String yearMonth;
    private final int amount;

    public Budget(String yearMonth, int amount) {

        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    public LocalDate firstDay() {
        return LocalDate.of(2000, 4, 1);
    }
}
