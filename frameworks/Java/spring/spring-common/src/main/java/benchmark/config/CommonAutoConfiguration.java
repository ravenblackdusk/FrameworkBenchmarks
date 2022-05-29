package benchmark.config;

import benchmark.model.FortuneLike;
import benchmark.model.WorldLike;
import benchmark.service.FortuneLikeService;
import benchmark.service.WorldLikeService;
import benchmark.web.FortuneController;
import benchmark.web.WorldController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:common.properties")
@Configuration
public class CommonAutoConfiguration {
    @Bean
    public <F extends FortuneLike> FortuneController<F> fortuneController(FortuneLikeService<F> fortuneLikeService) {
        return new FortuneController<>(fortuneLikeService);
    }

    @Bean
    public <W extends WorldLike> WorldController<W> worldController(WorldLikeService<W> worldLikeService) {
        return new WorldController<>(worldLikeService);
    }
}
