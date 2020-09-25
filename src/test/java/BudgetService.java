import java.time.LocalDate;
import java.util.List;

public class BudgetService {
    private final IBudgetRepo repo;

    public BudgetService(IBudgetRepo repo) {
        this.repo = repo;
    }

    public double totalAmount(LocalDate start, LocalDate end) {
        Period period = new Period(start, end);

        return repo.getAll()
                .stream()
                .mapToDouble(budget -> {
                    return budget.overlappingAmount(period);
                })
                .sum();
    }
}
