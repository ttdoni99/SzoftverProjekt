package Modells;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestForExpenses {

    Expense testExpense;

    @BeforeEach
    public void setUp(){
        testExpense = new Expense("Kávé",333, LocalDate.now());
    }

    @Test
    public void testingDateSetterShouldSetTheDate(){
        testExpense.setDayOfAdd(LocalDate.of(2021,5,12));

        assertEquals(LocalDate.of(2021,5,12),testExpense.getDayOfAdd());
    }

    @Test
    public void testingDateSetterShouldThrowVerifyErrorBecauseNullValue(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testExpense.setDayOfAdd(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }
    @Test
    public void testingConstructorShouldThrowVerifyErrorBecauseNullValue(){

        VerifyError verifyError = assertThrows(VerifyError.class, () -> { new Expense(null,null,null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingNameSetterShouldSetTheName(){
        testExpense.setName("Brekeke");

        assertEquals("Brekeke",testExpense.getName());
    }

    @Test
    public void testingNameSetterShouldThrowVerifyErrorBecauseNullValue(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testExpense.setName(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingAmountSetterShouldSetTheName(){
        testExpense.setAmount(2021);

        assertEquals(2021,testExpense.getAmount());
    }

    @Test
    public void testingAmountSetterShouldThrowVerifyErrorBecauseNullValue(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testExpense.setAmount(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }
    @Test
    public void testingAmountSetterShouldThrowVerifyErrorBecauseSettingValueLowerThanZero(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testExpense.setAmount(-2021); } );

        assertEquals("The amount field cannot be lower than 0", verifyError.getMessage());
    }
}
