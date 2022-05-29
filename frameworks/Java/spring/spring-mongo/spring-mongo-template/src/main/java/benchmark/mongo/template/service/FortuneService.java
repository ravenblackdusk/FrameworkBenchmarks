package benchmark.mongo.template.service;

import benchmark.mongo.common.model.Fortune;
import benchmark.service.FortuneLikeService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FortuneService implements FortuneLikeService<Fortune> {
    private final MongoTemplate mongoTemplate;

    public FortuneService(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}

    @Override
    public List<Fortune> findAll() {
        return mongoTemplate.findAll(Fortune.class);
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
