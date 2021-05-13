package Database;

import Modells.Expense;
import Modells.Income;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Class for the singleton storage variable. You can access the storage globally.
 * Loads data to the storage.
 */
public class Loader {
    private static Logger logger = LoggerFactory.getLogger("Loader.class");

    private Loader() {
    }


    public static Storage storage = new Storage();

    /**
     * Loads everything form the expense table to the storage.
     */
    public static void loadExpenseTable() {
        try {
            logger.debug("starting to load data");
            EntityManager em = DB.getEntityManager();
            Query q = em.createQuery("SELECT e from Expense e", Expense.class);
            storage.setExpenses(q.getResultList());
            logger.debug("finished loading data {} ", q.getResultList().size());

        } catch (Exception e) {
            logger.error("Oop database error {} ", e.getMessage());
        }
    }

    /**
     * Loads everything form the income table to the storage.
     */
    public static void loadIncomeTable() {
        try {
            logger.debug("starting to load data");
            EntityManager em = DB.getEntityManager();
            Query q = em.createQuery("SELECT e from Income e", Income.class);
            storage.setIncomes(q.getResultList());
            logger.debug("finished loading data {} ", q.getResultList().size());

        } catch (Exception e) {
            logger.error("Oop database error {} ", e.getMessage());
        }
    }
}

