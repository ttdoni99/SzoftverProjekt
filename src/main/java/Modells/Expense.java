package Modells;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Expenses")
public class Expense {

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

    public Expense() {
    }

    public Expense(String name, Integer amount, LocalDate dayOfAdd) {
        this.name = nullChecker(name);
        this.amount = nullChecker(amount);
        this.dayOfAdd = nullChecker(dayOfAdd);
    }

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

    public void setName(String name) {

        this.name = nullChecker(name);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) throws VerifyError {

        Integer temp = nullChecker(amount);

        if (0 > temp)
            throw new VerifyError("The amount field cannot be lower than 0");

        this.amount = temp;
    }

    public LocalDate getDayOfAdd() {
        return dayOfAdd;
    }

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

