package benchmark.jpa.common.config;

import benchmark.jpa.common.repo.FortuneRepository;
import benchmark.jpa.common.repo.WorldRepository;
import benchmark.jpa.common.service.FortuneService;
import benchmark.jpa.common.service.WorldService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = benchmark.jpa.common.repo.EnableJpaRepositories.class)
@EntityScan(basePackageClasses = benchmark.jpa.common.model.EntityScan.class)
@PropertySource("classpath:jpa.properties")
@Configuration
public class JpaAutoConfiguration {
    @Bean
    public FortuneService fortuneService(FortuneRepository fortuneRepository) {
        return new FortuneService(fortuneRepository);
    }

    @Bean
    public WorldService worldService(WorldRepository worldRepository) {
        return new WorldService(worldRepository);
    }
}
