package benchmark.r2dbc.model;

import benchmark.common.model.FortuneLike;
import org.springframework.data.annotation.Id;

public class Fortune implements FortuneLike {
    @Id
    public int id;
    public String message;

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
