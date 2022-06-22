package benchmark.blocking.panache.model;

import benchmark.model.WorldLike;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class World implements WorldLike {
    @Id
    private int id;
    private int randomnumber;

    public World() {
        // jpa
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
