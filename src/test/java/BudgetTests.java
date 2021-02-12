import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class BudgetTests {

    private final Accounting accounting = new Accounting();

    @Test
    public void no_budgets() {
        totalAmountShouldBe(0,
                LocalDate.of(2000, 4, 1),
                LocalDate.of(2000, 4, 1));
    }

    private void totalAmountShouldBe(int expected, LocalDate start, LocalDate end) {
        assertEquals(expected,
                accounting.totalAmount(start, end),
                0.00);
    }
}
