package core;

import conf.LoggerConfiguration;
import database.DatabaseFactory;
import entity.Event;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EventPersistent {
    private final Logger logger = new LoggerConfiguration().getLogger(EventPersistent.class);
    private final DatabaseFactory factory;
    private EntityManagerFactory managerFactory;
    private EntityManager entityManager;

    public EventPersistent(DatabaseFactory factory) {
        this.factory = factory;
    }

    public void initializeEntity() {
        managerFactory = factory.getManagerFactory();
        entityManager = factory.getEntityManager();
    }

    public void saveEvent(Event event) {
        logger.debug("Open transaction to insert event \"" + event.getId() + "\" to database.");
        entityManager.getTransaction().begin();
        ;
        entityManager.persist(event);
        entityManager.getTransaction().commit();
        logger.debug("Transaction went successfully.");
    }

    public void closeEntity() {
        managerFactory.close();
        entityManager.close();
    }
}
