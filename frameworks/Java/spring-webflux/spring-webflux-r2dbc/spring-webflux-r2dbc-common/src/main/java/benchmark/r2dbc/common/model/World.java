package benchmark.r2dbc.common.model;

import benchmark.common.model.WorldLike;
import org.springframework.data.annotation.Id;

public class World implements WorldLike {
    @Id
    private final int id;
    private int randomnumber;

    public World(int id, int randomnumber) {
        this.id = id;
        this.randomnumber = randomnumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Integer getRandomnumber() {
        return randomnumber;
    }

    @Override
    public void setRandomnumber(Integer randomnumber) {
        this.randomnumber = randomnumber;
    }
}
