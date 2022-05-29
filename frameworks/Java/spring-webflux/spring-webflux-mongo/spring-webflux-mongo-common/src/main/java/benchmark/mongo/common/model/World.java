package benchmark.mongo.common.model;

import benchmark.common.model.WorldLike;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class World implements WorldLike {
    @Id
    private final int id;
    private Integer randomNumber;

    public World(int id, Integer randomNumber) {
        this.id = id;
        this.randomNumber = randomNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Integer getRandomnumber() {
        return randomNumber;
    }

    @Override
    public void setRandomnumber(Integer randomnumber) {
        this.randomNumber = randomnumber;
    }
}
