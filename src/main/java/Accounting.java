import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Accounting {
    private final IBudgetRepo budgetRepo;

    public Accounting(IBudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    public double totalAmount(LocalDate start, LocalDate end) {
        List<Budget> budgets = budgetRepo.getAll();
        if (budgets.size() > 0) {
            Budget budget = budgets.get(0);
            return overlappingDays(start, end, budget);
        }
        return 0;
    }

    private long overlappingDays(LocalDate start, LocalDate end, Budget budget) {
        if (start.isAfter(budget.lastDay()) || end.isBefore(budget.firstDay())) {
            return 0;
        }

        LocalDate overlappingStart = start.isAfter(budget.firstDay())
                ? start
                : budget.firstDay();
        LocalDate overlappingEnd = end.isBefore(budget.lastDay())
                ? end
                : budget.lastDay();
        return DAYS.between(overlappingStart, overlappingEnd) + 1;
    }
}
