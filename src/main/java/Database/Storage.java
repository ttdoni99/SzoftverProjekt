package Database;

import Logic.DistributionCounter;
import Modells.Distribution;
import Modells.Expense;
import Modells.Income;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Main data storage class.
 * You can store the expenses and the incomes and their distributions here.
 */
public class Storage {
    private ArrayList<Expense> arrayListOfExpenses = new ArrayList<>();
    private ArrayList<Income> arrayListOfIncomes = new ArrayList<>();
    private ArrayList<Distribution> eDist;

    Integer Balance() {
        return getTheSumOfIncomes() - getTheSumOfExpenses();
    }

    /**
     * No args constructor for Storage class.
     */
    public Storage() {
    }

    /**
     * Uses the DistributionCounter class to calculate and load the distributions into the storage.
     */
    public void calculateDistributionOfExpenses() {
        DistributionCounter<Expense> distributionCounter =
                new DistributionCounter<>(arrayListOfExpenses);
        eDist = (ArrayList<Distribution>) distributionCounter.calculateDistribution();

    }

    /**
     * Calculates the distinct names in the storage.
     * @return a list with the distinct names in the storage.
     */
    public ArrayList<String> getDistinctNames() {

        ArrayList<String> temp = new ArrayList<>();

        for (var item : arrayListOfIncomes)
            if (!temp.contains(item.getName()))
                temp.add(item.getName());

        for (var item : arrayListOfExpenses)
            if (!temp.contains(item.getName()))
                temp.add(item.getName());

        return temp;
    }

    private Integer getTheSumOfExpenses() {
        Integer tmp = 0;

        for (int i = 0; i < arrayListOfExpenses.size(); i++) {
            tmp += arrayListOfExpenses.get(i).getAmount();
        }
        return tmp;
    }

    private Integer getTheSumOfIncomes() {
        Integer tmp = 0;

        for (int i = 0; i < arrayListOfIncomes.size(); i++) {
            tmp += arrayListOfIncomes.get(i).getAmount();
        }
        return tmp;
    }

    public void addIncome(Income income) {
        arrayListOfIncomes.add(income);
    }

    public void addExpense(Expense expense) {
        arrayListOfExpenses.add(expense);
    }

    public void removeExpense(Expense expense) {
        arrayListOfExpenses.remove(expense);
    }

    public void removeIncome(Income income) {
        arrayListOfIncomes.remove(income);
    }

    public ArrayList<Expense> getExpenses() {
        return arrayListOfExpenses;
    }

    public ArrayList<Income> getIncomes() {
        return arrayListOfIncomes;
    }

    public Integer getSumOfExpenses() {
        return getTheSumOfExpenses();
    }

    public Integer getSumOfIncomes() {
        return getTheSumOfIncomes();
    }

    /**
     * Calculates and returns the balance.
     * @return all incomes - all expenses.
     */
    public Integer getBalance() {
        return Balance();
    }

    public ArrayList<Distribution> getDist() {
        return eDist;
    }

    public void setExpenses(Collection arraylistofexpenses) {
        arrayListOfExpenses = (ArrayList<Expense>) arraylistofexpenses;
    }

    public void setIncomes(Collection arraylistofincomes) {
        arrayListOfIncomes = (ArrayList<Income>) arraylistofincomes;
    }
}
