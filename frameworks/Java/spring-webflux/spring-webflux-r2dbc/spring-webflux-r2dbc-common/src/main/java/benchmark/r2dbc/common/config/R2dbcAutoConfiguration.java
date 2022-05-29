package benchmark.r2dbc.common.config;

import benchmark.r2dbc.common.repo.FortuneRepository;
import benchmark.r2dbc.common.repo.WorldRepository;
import benchmark.r2dbc.common.service.FortuneService;
import benchmark.r2dbc.common.service.WorldService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackageClasses = benchmark.r2dbc.common.repo.EnableR2dbcRepositories.class)
@PropertySource("classpath:r2dbc.properties")
@Configuration
public class R2dbcAutoConfiguration {
    @Bean
    public FortuneService fortuneService(FortuneRepository fortuneRepository) {
        return new FortuneService(fortuneRepository);
    }

    @Bean
    public WorldService worldService(WorldRepository worldRepository) {
        return new WorldService(worldRepository);
    }
}
