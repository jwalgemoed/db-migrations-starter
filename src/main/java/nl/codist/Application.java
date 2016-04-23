package nl.codist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        final ApplicationContext context = SpringApplication.run(Application.class);
        LOGGER.info("Checking if database setup functioned ok:");
        context.getBean(JdbcTemplate.class).execute("SELECT username FROM person");
        LOGGER.info("Query success: Database in good shape.");        
    }
}
