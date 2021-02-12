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
            if (start.isAfter(budget.lastDay()) || end.isBefore(budget.firstDay())) {
                return 0;
            }
            return DAYS.between(start, end) + 1;
        }
        return 0;
    }
}
