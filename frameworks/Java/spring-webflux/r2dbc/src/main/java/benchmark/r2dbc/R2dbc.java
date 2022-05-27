package benchmark.r2dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class R2dbc {
    public static void main(String[] args) {
        SpringApplication.run(R2dbc.class, args);
    }
}
