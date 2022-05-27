package benchmark.common.config;

import benchmark.common.model.FortuneLike;
import benchmark.common.model.WorldLike;
import benchmark.common.service.FortuneLikeService;
import benchmark.common.service.WorldLikeService;
import benchmark.common.web.FortuneController;
import benchmark.common.web.WorldController;
import org.springframework.boot.web.reactive.result.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@EnableScheduling
@Configuration
public class CommonAutoConfiguration implements WebFluxConfigurer {
    private final MustacheViewResolver mustacheViewResolver;

    public CommonAutoConfiguration(MustacheViewResolver mustacheViewResolver) {
        this.mustacheViewResolver = mustacheViewResolver;
    }

    @Bean
    public HeaderFilter headerFilter() {
        return new HeaderFilter();
    }

    @Bean
    public <F extends FortuneLike> FortuneController<F> fortuneController(FortuneLikeService<F> fortuneService) {
        return new FortuneController<>(fortuneService);
    }

    @Bean
    public <W extends WorldLike> WorldController<W> worldController(WorldLikeService<W> worldService) {
        return new WorldController<>(worldService);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(mustacheViewResolver);
    }
}
