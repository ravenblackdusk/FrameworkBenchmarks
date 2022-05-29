package benchmark.mongo.common.model;

import benchmark.model.FortuneLike;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Fortune implements FortuneLike {
    @Id
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
