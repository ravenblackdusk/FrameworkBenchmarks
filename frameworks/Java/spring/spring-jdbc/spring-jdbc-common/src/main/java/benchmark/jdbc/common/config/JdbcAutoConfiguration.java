package benchmark.jdbc.common.config;

import benchmark.jdbc.common.service.FortuneService;
import benchmark.jdbc.common.service.WorldService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@PropertySource("classpath:jdbc.properties")
@Configuration
public class JdbcAutoConfiguration {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAutoConfiguration(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Bean
    public FortuneService fortuneService() {
        return new FortuneService(jdbcTemplate);
    }

    @Bean
    public WorldService worldService() {
        return new WorldService(jdbcTemplate);
    }
}
