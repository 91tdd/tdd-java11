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
            Budget budget = budgets.get(0);
            if (end.isBefore(budget.firstDay()) || start.isAfter(budget.lastDay())) {
                return 0;
            }
            return DAYS.between(start, end) + 1;
        }
        return 0;
    }
}
