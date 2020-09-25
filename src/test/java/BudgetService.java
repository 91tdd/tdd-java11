import java.time.LocalDate;
import java.util.List;

public class BudgetService {
    private final IBudgetRepo repo;

    public BudgetService(IBudgetRepo repo) {
        this.repo = repo;
    }

    public double totalAmount(LocalDate start, LocalDate end) {
        List<Budget> budgets = repo.getAll();
        if (budgets.size() > 0) {
            Budget budget = budgets.get(0);
            return new Period(start, end).overlappingDays(new Period(budget.firstDay(), budget.lastDay()));
        }
        return 0;
    }
}
