package benchmark.blocking.panache.model;

import benchmark.model.FortuneLike;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fortune implements FortuneLike {
    @Id
    private int id;
    private String message;

    public Fortune() {
        // jpa
    }

    public Fortune(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
