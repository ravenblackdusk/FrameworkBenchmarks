package benchmark.r2dbc.common.model;

import benchmark.common.model.FortuneLike;
import org.springframework.data.annotation.Id;

public class Fortune implements FortuneLike {
    @Id
    public final int id;
    public final String message;

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
