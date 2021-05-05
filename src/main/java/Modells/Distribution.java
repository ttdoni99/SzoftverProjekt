package Modells;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Distribution {

    private String name;
    private Integer amount;
    private Double percentage;

    public Distribution(String Name, Integer Amount) {
        name = Name;
        amount = Amount;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Név='" + name +
                ", Összeg=" + amount +
                ", Százalék=" + percentage;
    }
}
