package benchmark.jdbc.common.model;

import benchmark.model.FortuneLike;

public class Fortune implements FortuneLike {
    private final int id;
    private final String message;

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
