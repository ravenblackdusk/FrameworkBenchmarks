package benchmark.blocking.jdbc.service;

import benchmark.blocking.jdbc.model.World;
import benchmark.blocking.service.WorldLikeService;
import benchmark.model.WorldLike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class WorldService implements WorldLikeService {
    @Inject
    DataSource dataSource;

    @Override
    public World findFortuneById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from world where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new World(resultSet.getInt(1), resultSet.getInt(2));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WorldLike> update(List<WorldLike> worlds) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update world set randomnumber = ? where id = ?")) {
            for (WorldLike world : worlds) {
                preparedStatement.setInt(1, world.getRandomnumber());
                preparedStatement.setInt(2, world.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            return worlds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
