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
            return overlappingDays(new Period(start, end), budget);
        }
        return 0;
    }

    private long overlappingDays(Period period, Budget budget) {
        if (period.getEnd().isBefore(budget.firstDay()) || period.getStart().isAfter(budget.lastDay())) {
            return 0;
        }
        LocalDate overlappingStart = period.getStart().isAfter(budget.firstDay())
                ? period.getStart()
                : budget.firstDay();
        LocalDate overlappingEnd = period.getEnd().isBefore(budget.lastDay())
                ? period.getEnd()
                : budget.lastDay();
        return DAYS.between(overlappingStart, overlappingEnd) + 1;
    }
}
