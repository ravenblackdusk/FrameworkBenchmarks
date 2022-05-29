package benchmark.mongo.template.service;

import benchmark.common.service.WorldLikeService;
import benchmark.mongo.common.model.World;
import com.mongodb.client.model.UpdateOneModel;
import org.bson.Document;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import static java.util.stream.Collectors.toList;

@Service
public class WorldService implements WorldLikeService<World> {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public WorldService(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<World> findById(int id) {
        return reactiveMongoTemplate.findById(id, World.class);
    }

    @Override
    public Flux<World> update(Flux<World> worlds) {
        return reactiveMongoTemplate.getCollection("world").flatMapMany(worldCollection -> worlds.buffer(100).flatMap(
                        worldList -> Mono.from(worldCollection.bulkWrite(worldList.stream()
                                .map(world -> new UpdateOneModel<Document>(new Document("_id", world.getId()),
                                        new Document("$set", new Document("randomNumber", world.getRandomnumber()))))
                                .collect(toList()))).zipWith(Mono.just(worldList)).map(Tuple2::getT2))
                .flatMap(Flux::fromIterable));
    }
}
