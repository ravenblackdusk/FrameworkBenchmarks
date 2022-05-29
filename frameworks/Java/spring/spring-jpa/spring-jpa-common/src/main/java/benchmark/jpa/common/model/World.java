package benchmark.jpa.common.model;

import benchmark.model.WorldLike;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class World implements WorldLike {
    @Id
    private int id;
    private int randomnumber;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getRandomnumber() {
        return randomnumber;
    }

    public void setRandomnumber(int randomnumber) {
        this.randomnumber = randomnumber;
    }
}
