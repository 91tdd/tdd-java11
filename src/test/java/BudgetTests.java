import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class BudgetTests {

    private BudgetService budgetService = new BudgetService();

    @Test
    public void no_budgets() {
        TotalAmountShouldBe(0
                , LocalDate.of(2020, 4, 1)
                , LocalDate.of(2020, 4, 1));
    }

    private void TotalAmountShouldBe(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected, budgetService.totalAmount(start, end), 0.00);
    }
}
