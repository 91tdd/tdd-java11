import java.time.LocalDate;
import java.util.List;

public class Accounting {
    private final IBudgetRepo budgetRepo;

    public Accounting(IBudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    public double totalAmount(LocalDate start, LocalDate end) {
        Period period = new Period(start, end);
        List<Budget> budgets = budgetRepo.getAll();
        if (budgets.size() > 0) {
            double totalAmount = 0;
            for (Budget budget : budgets) {

//                Budget budget = budgets.get(0);
                totalAmount += budget.overlappingAmount(period);
            }
            return totalAmount;
        }
        return 0;
    }
}
