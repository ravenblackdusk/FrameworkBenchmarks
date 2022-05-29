package benchmark.mongo.common.model;

import benchmark.common.model.FortuneLike;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
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
