package benchmark.jpa.common.model;

import benchmark.model.FortuneLike;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fortune implements FortuneLike {
    @Id
    private int id;
    private String message;

    protected Fortune() {
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
