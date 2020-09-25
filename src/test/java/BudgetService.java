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
            return overlappingAmount(budget, new Period(start, end));
        }
        return 0;
    }

    private double overlappingAmount(Budget budget, Period period) {
        return period.overlappingDays(budget.createPeriod()) * budget.getDailyAmount();
    }
}
