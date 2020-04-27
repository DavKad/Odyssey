package database;

import conf.LoggerConfiguration;
import lombok.Getter;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Getter
public class DatabaseFactory {
    private final Logger logger = new LoggerConfiguration().getLogger(DatabaseFactory.class);
    private EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("challenge");
    private EntityManager entityManager = managerFactory.createEntityManager();
}
