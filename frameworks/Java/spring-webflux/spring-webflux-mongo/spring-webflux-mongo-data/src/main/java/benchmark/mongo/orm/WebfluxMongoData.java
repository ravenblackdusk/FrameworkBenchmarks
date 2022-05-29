package benchmark.mongo.orm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class WebfluxMongoData {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxMongoData.class, args);
    }
}
