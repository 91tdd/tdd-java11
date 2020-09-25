import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BudgetTests {

    private IBudgetRepo repo = Mockito.mock(IBudgetRepo.class);
    private BudgetService budgetService = new BudgetService(repo);

    @Test
    public void no_budgets() {
        totalAmountShouldBe(0
                , LocalDate.of(2020, 4, 1)
                , LocalDate.of(2020, 4, 1));
    }

    @Test
    public void period_inside_budget_month() {
        when(repo.getAll()).thenReturn(Arrays.asList(new Budget("202004", 30)));

        totalAmountShouldBe(1
                , LocalDate.of(2020, 4, 1)
                , LocalDate.of(2020, 4, 1));
    }

    private void totalAmountShouldBe(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected, budgetService.totalAmount(start, end), 0.00);
    }
}
