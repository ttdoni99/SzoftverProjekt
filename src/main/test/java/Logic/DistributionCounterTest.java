package Logic;

import Modells.Distribution;
import Modells.Expense;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DistributionCounterTest {

    private static ArrayList<Expense> testData = new ArrayList<>();
    private DistributionCounter<Expense> expenseDistributionCounterUnderTest;

    @BeforeEach
    public void setUp() {
        testData.add(new Expense("Margarin", 100, LocalDate.now()));
        testData.add(new Expense("Kávé", 50, LocalDate.now()));
        testData.add(new Expense("Kávé", 150, LocalDate.now()));
        testData.add(new Expense("Pizza", 300, LocalDate.now()));
        expenseDistributionCounterUnderTest = new DistributionCounter<>(testData);
    }

    @AfterEach
    public void tearDown() {
        testData.clear();
    }

    @Test
    public void testCalculateSum() {
        expenseDistributionCounterUnderTest.calculateSum();

        assertEquals(600, expenseDistributionCounterUnderTest.calculateSum());
    }

    @Test
    public void testCalculateDistributionOfExpenses() {
        ArrayList<Distribution> actual = (ArrayList<Distribution>) expenseDistributionCounterUnderTest.calculateDistribution();

        List<Distribution> expected = new ArrayList<>();
        expected.add(new Distribution("Margarin", 100, 16.666666666666664));
        expected.add(new Distribution("Kávé", 200, 33.33333333333333));
        expected.add(new Distribution("Pizza", 300, 50.0));

        assertEquals(actual, expected);
    }

}
