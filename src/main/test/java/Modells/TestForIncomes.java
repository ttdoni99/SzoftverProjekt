package Modells;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestForIncomes {
    Income testIncome;

    @BeforeEach
    public void setUp(){
        testIncome = new Income("Kávé",333, LocalDate.now());
    }

    @Test
    public void testingDateSetterShouldSetTheDate(){
        testIncome.setDayOfAdd(LocalDate.of(2021,5,12));

        assertEquals(LocalDate.of(2021,5,12),testIncome.getDayOfAdd());
    }

    @Test
    public void testingDateSetterShouldThrowVerifyErrorBecauseNullValue(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testIncome.setDayOfAdd(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingConstructorShouldThrowVerifyErrorBecauseNullValue(){

        VerifyError verifyError = assertThrows(VerifyError.class, () -> { new Income(null,null,null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingNameSetterShouldSetTheName(){
        testIncome.setName("Brekeke");

        assertEquals("Brekeke",testIncome.getName());
    }

    @Test
    public void testingNameSetterShouldThrowVerifyErrorBecauseNullValue(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testIncome.setName(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingAmountSetterShouldSetTheName(){
        testIncome.setAmount(2021);

        assertEquals(2021,testIncome.getAmount());
    }

    @Test
    public void testingAmountSetterShouldThrowVerifyErrorBecauseNullValue(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testIncome.setAmount(null); } );

        assertEquals("Null cell was found.", verifyError.getMessage());
    }

    @Test
    public void testingAmountSetterShouldThrowVerifyErrorBecauseSettingValueLowerThanZero(){
        VerifyError verifyError = assertThrows(VerifyError.class, () -> { testIncome.setAmount(-2021); } );

        assertEquals("The amount field cannot be lower than 0", verifyError.getMessage());
    }
}

