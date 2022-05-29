package benchmark.mongo.template.service;

import benchmark.mongo.common.model.World;
import benchmark.service.WorldLikeService;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class WorldService implements WorldLikeService<World> {
    private final MongoTemplate mongoTemplate;

    public WorldService(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}

    @Override
    public World findById(int id) {
        return mongoTemplate.findById(id, World.class);
    }

    @Override
    public List<World> update(List<World> worlds) {
        mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, World.class).updateMulti(worlds.stream()
                .map(it -> Pair.of(query(where("_id").is(it.getId())),
                        new Update().set("randomnumber", it.getRandomnumber()))).collect(toList()));
        return worlds;
    }
}
