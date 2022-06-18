package io.quarkus.benchmark.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class World {
    @Id
    private int id;
    private int randomnumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRandomnumber() {
        return randomnumber;
    }

    public void setRandomnumber(int randomNumber) {
        this.randomnumber = randomNumber;
    }
}
