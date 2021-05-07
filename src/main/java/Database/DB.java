package Database;

import Modells.TypeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class DB {

    private static Logger logger = LoggerFactory.getLogger("DB.class");

    private DB() {
    }

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static <T extends TypeInterface> void commitChange(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            logger.debug("Commit changes to the database");
        } catch (Exception e) {
            logger.error("Database error {}", e.getMessage());
        } finally {
            em.close();
        }
    }

    public static <T extends TypeInterface> void uploadEntityToDatabase(T entity) {
        EntityManager em = DB.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            em.close();
            logger.debug("Created new Entity {}", entity.toString());
        } catch (Exception e) {
            logger.error("Database error {}", e.getMessage());
        } finally {
            em.close();
        }
    }

    public static <T extends TypeInterface> void removeEntity(T entity) {
        EntityManager em = getEntityManager();
        try {
            logger.debug("Removing Entity {}", entity.toString());
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
            logger.debug("Removed Entity");
        } catch (Exception e) {
            logger.error("Database error {}", e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void closeEmf() {

        emf.close();
    }
}
