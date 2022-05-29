package benchmark.mongo.common.model;

import benchmark.model.WorldLike;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class World implements WorldLike {
    @Id
    private final int id;
    private int randomNumber;

    public World(int id, int randomNumber) {
        this.id = id;
        this.randomNumber = randomNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getRandomnumber() {
        return randomNumber;
    }

    @Override
    public void setRandomnumber(int randomnumber) {
        this.randomNumber = randomnumber;
    }
}
