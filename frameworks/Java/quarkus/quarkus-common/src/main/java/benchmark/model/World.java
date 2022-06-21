package benchmark.model;

public class World {
    private final int id;
    private int randomnumber;

    public World(int id, int randomnumber) {
        this.id = id;
        this.randomnumber = randomnumber;
    }

    public int getId() {
        return id;
    }

    public int getRandomnumber() {
        return randomnumber;
    }

    public void setRandomnumber(int randomnumber) {
        this.randomnumber = randomnumber;
    }
}
