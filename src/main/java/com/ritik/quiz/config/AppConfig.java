package com.ritik.quiz.config;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@PropertySource("file:${user.home}/.config/quiz-backend/application.properties")
@EnableWebMvc
@ComponentScan("com.ritik.quiz")
public class AppConfig {

    /***
     * this method is used to configure the database credentils by using the class
     * PGSimpleDataSource and making it's object and returning the object
     *
     *
     *
     * @return javax.sql.DataSource
     */
    @Bean
    public DataSource dataSource(Environment environment) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setURL(
                environment.getRequiredProperty("quiz.db.url")
        );
        dataSource.setUser(
                environment.getRequiredProperty("quiz.db.user")
        );
        dataSource.setPassword(
                environment.getRequiredProperty("quiz.db.password")
        );

        return dataSource;
    }

    /**
     * this method is used to creates a configured
     * JdbcTemplate object and gives it to Spring
     * @param dataSource
     * @return
     */

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


    /**
     * Configures Flyway and runs pending database migrations during startup.
     *
     * @param dataSource database connection configuration
     * @return configured Flyway migration manager
     */
    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .load();
    }
}
