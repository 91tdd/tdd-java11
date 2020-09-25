import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class BudgetTests {
    @Test
    public void no_budgets() {
        BudgetService budgetService = new BudgetService();
        LocalDate start = LocalDate.of(2020, 4, 1);
        LocalDate end = LocalDate.of(2020, 4, 1);
        double totalAmount = budgetService.totalAmount(start, end);
        assertEquals(0, totalAmount, 0.00);
    }
}
