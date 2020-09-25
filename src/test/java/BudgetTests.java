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
        givenBudgets();
        totalAmountShouldBe(0
                , LocalDate.of(2020, 4, 1)
                , LocalDate.of(2020, 4, 1));
    }

    @Test
    public void period_inside_budget_month() {
        givenBudgets(new Budget("202004", 30));

        totalAmountShouldBe(1
                , LocalDate.of(2020, 4, 1)
                , LocalDate.of(2020, 4, 1));
    }

    @Test
    public void period_without_overlap_before_budget_first_day() {
        givenBudgets(new Budget("202004", 30));

        totalAmountShouldBe(0
                , LocalDate.of(2020, 3, 31)
                , LocalDate.of(2020, 3, 31));
    }

    @Test
    public void period_without_overlap_after_budget_last_day() {
        givenBudgets(new Budget("202004", 30));

        totalAmountShouldBe(0
                , LocalDate.of(2020, 5, 1)
                , LocalDate.of(2020, 5, 1));
    }

    @Test
    public void period_overlap_budget_first_day() {
        givenBudgets(new Budget("202004", 30));

        totalAmountShouldBe(1
                , LocalDate.of(2020, 3, 31)
                , LocalDate.of(2020, 4, 1));
    }

    @Test
    public void period_overlap_budget_last_day() {
        givenBudgets(new Budget("202004", 30));

        totalAmountShouldBe(1
                , LocalDate.of(2020, 4, 30)
                , LocalDate.of(2020, 5, 1));
    }

    @Test
    public void invalid_period() {
        givenBudgets(new Budget("202004", 30));

        totalAmountShouldBe(0
                , LocalDate.of(2020, 4, 30)
                , LocalDate.of(2020, 4, 1));
    }

    @Test
    public void daily_amount_is_10() {
        givenBudgets(new Budget("202004", 300));

        totalAmountShouldBe(30
                , LocalDate.of(2020, 4, 1)
                , LocalDate.of(2020, 4, 3));
    }

    @Test
    public void multiple_budgets() {
        givenBudgets(
                new Budget("202003", 31),
                new Budget("202004", 300),
                new Budget("202005", 3100));

        totalAmountShouldBe(1 + 300 + 400
                , LocalDate.of(2020, 3, 31)
                , LocalDate.of(2020, 5, 4));
    }

    private void givenBudgets(Budget... budgets) {
        when(repo.getAll()).thenReturn(Arrays.asList(budgets));
    }

    private void totalAmountShouldBe(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected, budgetService.totalAmount(start, end), 0.00);
    }
}
