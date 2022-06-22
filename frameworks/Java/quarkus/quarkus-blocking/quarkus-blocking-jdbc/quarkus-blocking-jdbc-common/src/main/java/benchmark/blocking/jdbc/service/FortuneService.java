package benchmark.blocking.jdbc.service;

import benchmark.blocking.jdbc.model.Fortune;
import benchmark.blocking.service.FortuneLikeService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FortuneService implements FortuneLikeService {
    @Inject
    DataSource dataSource;

    @Override
    public List<Fortune> findAllFortunes() {
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from fortune");
            List<Fortune> fortunes = new ArrayList<>();
            while (resultSet.next()) {
                fortunes.add(new Fortune(resultSet.getInt(1), resultSet.getString(2)));
            }
            return fortunes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
