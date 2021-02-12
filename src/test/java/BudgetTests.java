import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class BudgetTests {
    @Test
    public void no_budgets() {

        Accounting accounting = new Accounting();
        assertEquals(0,
                accounting.totalAmount(LocalDate.of(2000, 4, 1), LocalDate.of(2000, 4, 1)),
                0.00);
    }
}
