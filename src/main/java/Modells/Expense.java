package Modells;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Model of an expense. Stores the name of the expense.
 * The date when the purchase was made and the amount of it.
 */
@Data
@Entity
@Table(name = "Expenses")
public class Expense implements TypeInterface {

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
    public Expense() {
    }

    /**
     * Creates a new instance of the Expense class.
     * The recommended constructor for this class.
     * @param name     the name of the Expense.
     * @param amount   the amount of the Expense.
     * @param dayOfAdd the day of the transaction.
     */
    public Expense(String name, Integer amount, LocalDate dayOfAdd) {
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
     * Sets the name of the bought product.
     * Uses {@code nullChecker(T param)} to determinate weather the name is value or null.
     * @param name is the name you want to override the current name.
     */
    public void setName(String name) {

        this.name = nullChecker(name);
    }

    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money spent in a product.
     * Uses {@code nullChecker(T param)} to determinate weather the amount is a value or null.
     * @param amount is the amount of money to override the current one
     * @throws VerifyError if the value is lower than 0.
     */
    public void setAmount(Integer amount) throws VerifyError {

        Integer temp = nullChecker(amount);

        if (0 > temp)
            throw new VerifyError("The amount field cannot be lower than 0");

        this.amount = temp;
    }

    public LocalDate getDayOfAdd() {
        return dayOfAdd;
    }

    /**
     * Sets the date of the transaction. Uses {@code dateNullChecker()}
     * to determinate weather the date is a value or null.
     * @param localDate the time of the transaction.
     */
    public void setDayOfAdd(LocalDate localDate) {

        this.dayOfAdd = nullChecker(localDate);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "PrimaryKey=" + primaryKey +
                ", Name='" + name + '\'' +
                ", Amount=" + amount +
                ", DayOfAdd=" + dayOfAdd +
                '}';
    }
}

