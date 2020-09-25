import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class BudgetService {
    private final IBudgetRepo repo;

    public BudgetService(IBudgetRepo repo) {
        this.repo = repo;
    }

    public double totalAmount(LocalDate start, LocalDate end) {
        List<Budget> budgets = repo.getAll();
        if (budgets.size() > 0) {
            long days = DAYS.between(start, end) + 1;
            return days;
        }
        return 0;
    }
}
