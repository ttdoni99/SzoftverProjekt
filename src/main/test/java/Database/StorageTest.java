package Database;

import Modells.Distribution;
import Modells.Expense;
import Modells.Income;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private static Storage testStorage = new Storage();
    private static ArrayList<Expense> testExpenseData = new ArrayList<>();
    private static ArrayList<Income> testIncomeData = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        testExpenseData.add(new Expense("Margarin", 100, LocalDate.now()));
        testExpenseData.add(new Expense("Kávé", 50, LocalDate.now()));
        testExpenseData.add(new Expense("Kávé", 150, LocalDate.now()));
        testExpenseData.add(new Expense("Pizza", 300, LocalDate.now()));
        testIncomeData.add(new Income("Fizetés", 100, LocalDate.now()));
        testIncomeData.add(new Income("Kávé", 50, LocalDate.now()));
        testIncomeData.add(new Income("Kávé", 150, LocalDate.now()));
        testIncomeData.add(new Income("Fizetés", 300, LocalDate.now()));

        testStorage.setExpenses(testExpenseData);
        testStorage.setIncomes(testIncomeData);
    }

    @AfterEach
    public void tearDown() {
        testExpenseData.clear();
        testIncomeData.clear();
    }

    @Test
    public void testingDistinctElements() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Fizetés");
        expected.add("Kávé");
        expected.add("Margarin");
        expected.add("Pizza");

        ArrayList<String> actual = testStorage.getDistinctNames();

        assertEquals(expected, actual);
    }

    @Test
    public void testingCalculateDistributionOfExpenses() {

        testStorage.calculateDistributionOfExpenses();

        List<Distribution> expected = new ArrayList<>();
        expected.add(new Distribution("Margarin", 100, 16.666666666666664));
        expected.add(new Distribution("Kávé", 200, 33.33333333333333));
        expected.add(new Distribution("Pizza", 300, 50.0));

        assertEquals(expected, testStorage.getDist());
    }

    @Test
    public void testingGetBalance() {
        Integer expected = 0;

        assertEquals(expected, testStorage.getBalance());
    }

    @Test
    public void testingGetTheSumOfExpenses() {
        Integer expected = 600;

        assertEquals(expected, testStorage.getSumOfExpenses());
    }

    @Test
    public void testingGetTheSumOfIncomes() {
        Integer expected = 600;

        assertEquals(expected, testStorage.getSumOfIncomes());
    }
}
