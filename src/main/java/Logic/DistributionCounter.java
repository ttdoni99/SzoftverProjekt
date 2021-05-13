package Logic;

import Modells.Distribution;
import Modells.TypeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The main logic of the program.
 * Calculates the distribution of a given list of types that implements the TypeInterface.
 * @param <T> Data classes that implements the TypeInterface.
 */
public class DistributionCounter<T extends TypeInterface> {

    private static Logger logger = LoggerFactory.getLogger("DistributionCounter.class");

    private static List<Distribution> distributionList;
    private List<T> listOfTargets;
    private Integer sumOfExpenses;


    /**
     * The Constructor you must give it a list to calculate it's distribution.
     * @param listOfTargets List of object that implements the TypeInterface.
     */
    public DistributionCounter(List<T> listOfTargets) {
        distributionList = new ArrayList<>();
        this.listOfTargets = listOfTargets;
        sumOfExpenses = calculateSum();
    }


    /**
     * Creates a list form the Distribution objects.
     * @return a List of Distribution objects.
     */
    public List<Distribution> calculateDistribution() {
        String[] distinctExpenses = getDistinctElements(listOfTargets);
        distributionList = initializeList(distinctExpenses);

        for (int i = 0; i < distributionList.size(); i++) {
            for (int j = 0; j < distinctExpenses.length; j++) {

                if (distinctExpenses[i].equals(distributionList.get(i).getName())) {
                    distributionList.get(i).setAmount(valueLoader(distinctExpenses[i]));
                    double tmp1 = valueLoader(distinctExpenses[i]);
                    double tmp2 = sumOfExpenses;
                    distributionList.get(i).setPercentage(tmp1 / tmp2 * 100);
                    logger.debug("Calculated the distribution of an entity. {}",
                            distributionList.get(i).toString());
                }
            }
        }
        return distributionList;
    }

    private ArrayList<Distribution> initializeList(String[] distinctExpenses) {

        ArrayList<Distribution> tmp = new ArrayList<>();
        for (int i = 0; distinctExpenses.length > i; i++) {
            try {
                tmp.add(new Distribution(distinctExpenses[i], 0));
                logger.debug("Created new Distribution");
            } catch (Exception e) {
                logger.error("Failed to create new Distribution: {}", e.getMessage());
            }
        }
        return tmp;
    }

    private Integer valueLoader(String name) {

        Integer[] sumArray = listOfTargets.stream()
                .filter(T -> T.getName().equals(name))
                .map(T::getAmount).toArray(Integer[]::new);
        logger.debug("Now retuning the sums");
        return Arrays.stream(sumArray).reduce(0, Integer::sum);
    }

    private String[] getDistinctElements(List<T> targets) {
        String[] tmp = targets.stream()
                .map(T::getName).distinct().toArray(String[]::new);
        logger.debug("Now returning distinct names.");
        return tmp;
    }

    /**
     * Calculates the sum of a given list of the correct type.
     * @return an Integer. The number is the sum of the amounts.
     */
    public Integer calculateSum() {
        logger.debug("Calculating and returning the sum of expenses.");
        return listOfTargets.stream().map(T::getAmount).reduce(0, Integer::sum);
    }

    /**
     * Sets the list of the targets of this counting operation.
     * @param listOfTargets list of object that implements the TypeInterface
     */
    public void setListOfTargets(Collection<T> listOfTargets) {
        this.listOfTargets = (List<T>) listOfTargets;
        this.sumOfExpenses = calculateSum();
    }


}

