package benchmark.web;

import benchmark.model.FortuneLike;
import benchmark.service.FortuneLikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@RequestMapping("/fortunes")
@Controller
public class FortuneController<F extends FortuneLike> {
    private final FortuneLikeService<F> fortuneService;

    public FortuneController(FortuneLikeService<F> fortuneService) {
        this.fortuneService = fortuneService;
    }

    @GetMapping
    public ModelAndView getFortunes() {
        return new ModelAndView("fortunes", Map.of("f",
                Stream.concat(Stream.of(fortuneService.create(0, "Additional fortune added at request time.")),
                                fortuneService.findAll().stream()).sorted(comparing(FortuneLike::getMessage))
                        .collect(toList())));
    }
}
