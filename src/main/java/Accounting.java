import java.time.LocalDate;
import java.util.List;

public class Accounting {
    private final IBudgetRepo budgetRepo;

    public Accounting(IBudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    public double totalAmount(LocalDate start, LocalDate end) {
        Period period = new Period(start, end);
        return budgetRepo.getAll()
                .stream()
                .mapToDouble(budget -> budget.overlappingAmount(period))
                .sum();
    }
}
