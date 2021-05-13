package Modells;

import java.time.LocalDate;

/**
 * Interface for modelling an expense/income.
 * The classes that are implements it can use the generic Distribution counter.
 */
public interface TypeInterface {

    Integer primaryKey = null;
    String name = "null";
    LocalDate dayOfAdd = LocalDate.now();
    Integer amount = 0;

    String getName();

    void setName(String name);

    LocalDate getDayOfAdd();

    void setDayOfAdd(LocalDate localDate);

    Integer getAmount();

    void setAmount(Integer amount);

}
