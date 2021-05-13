package Modells;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Model of an Income. Stores the name of the income.
 * Stores the date when you got the money and the amount of money that you got.
 */
@Data
@Entity
@Table(name = "Incomes")
public class Income implements TypeInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PrimaryKey")
    private Integer primaryKey;
    @Column(name = "Name")
    private String name;
    @Column(name = "DayOfAdd")
    private LocalDate dayOfAdd;
    @Column(name = "Amount")
    private Integer amount;

    private static Logger logger = LoggerFactory.getLogger("Expense.class");

    /**
     * Public no args constructor.
     */
    public Income() {
    }

    /**
     * Creates a new instance of the Expense class.
     * The recommended constructor for this class.
     * @param name     the name of the Income.
     * @param amount   the amount of the Income.
     * @param dayOfAdd the day of the transaction.
     */
    public Income(String name, Integer amount, LocalDate dayOfAdd) {
        this.name = nullChecker(name);
        this.amount = nullChecker(amount);
        this.dayOfAdd = nullChecker(dayOfAdd);
    }

    /**
     * Generic method for checking if the given parameter is not null.
     * @param param data to set.
     * @param <T>   anything
     * @return the parameter
     * @throws VerifyError if the parameter is null.
     */
    private <T> T nullChecker(T param) throws VerifyError {

        logger.trace("We are checking if date is null");
        if (param == null) {
            logger.warn("The field was null.");
            throw new VerifyError("Null cell was found.");
        } else {
            logger.debug("Now returning the field with the value of {}", param);
            return param;
        }
    }

    public Integer getPrimaryKey() {
        return this.primaryKey;
    }

    public String getName() {
        return name;
    }

    /**
     * Sets the name of the transaction.
     * Uses {@code nullChecker(T param)} to determinate weather the name is valid or null.
     * @param name the new name of the transaction.
     */
    public void setName(String name) {

        this.name = nullChecker(name);
    }

    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     * Uses {@code nullChecker(T param)} to determinate weather the name is valid or null.
     * @param amount new value to override the existing one.
     */
    public void setAmount(Integer amount) {

        Integer temp = nullChecker(amount);

        if (0 > temp)
            throw new VerifyError("The amount field cannot be lower than 0");

        this.amount = temp;
    }

    public LocalDate getDayOfAdd() {
        return dayOfAdd;
    }

    /**
     * Sets the date of the transaction.
     * Uses {@code nullChecker(T param)} to determinate weather the date is valid or null.
     * @param localDate the  new time of the transaction.
     */
    public void setDayOfAdd(LocalDate localDate) {

        this.dayOfAdd = nullChecker(localDate);
    }

    @Override
    public String toString() {
        return "Income{" +
                "PrimaryKey=" + primaryKey +
                ", Name='" + name + '\'' +
                ", Amount=" + amount +
                ", DayOfAdd=" + dayOfAdd +
                '}';
    }

}
