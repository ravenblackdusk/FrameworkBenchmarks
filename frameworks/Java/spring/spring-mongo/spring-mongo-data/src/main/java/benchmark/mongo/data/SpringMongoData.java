package benchmark.mongo.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class SpringMongoData {
    public static void main(String[] args) {
        SpringApplication.run(SpringMongoData.class, args);
    }
}
