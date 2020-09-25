import java.time.LocalDate;

public class BudgetService {
    private final IBudgetRepo repo;

    public BudgetService(IBudgetRepo repo) {

        this.repo = repo;
    }

    public double totalAmount(LocalDate start, LocalDate end) {
        return 0;
    }
}
