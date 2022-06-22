package benchmark.blocking.jdbc.model;

import benchmark.model.WorldLike;

public class World implements WorldLike {
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
    public int getRandomnumber() {
        return randomnumber;
    }

    @Override
    public void setRandomnumber(int randomnumber) {
        this.randomnumber = randomnumber;
    }
}
