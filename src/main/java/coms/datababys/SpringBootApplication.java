package coms.datababys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Lovme on 2017/2/16.
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan({"coms.datababys.*"})
@EnableAutoConfiguration
@EnableMongoRepositories
@EnableJpaRepositories
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}
