package benchmark.common.web;

import benchmark.common.model.FortuneLike;
import benchmark.common.service.FortuneLikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

import static java.util.Comparator.comparing;

@RequestMapping("/fortunes")
@Controller
public class FortuneController<F extends FortuneLike> {
    private final FortuneLikeService<F> fortuneService;

    public FortuneController(FortuneLikeService<F> fortuneService) {
        this.fortuneService = fortuneService;
    }

    @GetMapping
    public Rendering getFortunes() {
        return Rendering.view("fortunes").modelAttribute("f", fortuneService.findAll()
                .concatWith(Mono.just(fortuneService.create(0, "Additional fortune added at request time.")))
                .sort(comparing(FortuneLike::getMessage))).build();
    }
}
