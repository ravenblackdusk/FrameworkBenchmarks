package benchmark.service;

import benchmark.model.World;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class WorldService {
    @Inject
    DataSource dataSource;

    public World findById(int id) {
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

    public List<World> update(List<World> worlds) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update world set randomnumber = ? where id = ?")) {
            for (World world : worlds) {
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
