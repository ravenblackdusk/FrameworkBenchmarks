package benchmark.jdbc.common.service;

import benchmark.jdbc.common.model.World;
import benchmark.service.WorldLikeService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class WorldService implements WorldLikeService<World> {
    private final JdbcTemplate jdbcTemplate;

    public WorldService(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public World findById(int id) {
        return jdbcTemplate.queryForObject("select * from world where id = ?",
                (rs, rowNum) -> new World(rs.getInt(1), rs.getInt(2)), id);
    }

    @Override
    public List<World> update(List<World> worlds) {
        jdbcTemplate.batchUpdate("update world set randomnumber = ? where id = ?",
                worlds.stream().map(it -> new Object[]{it.getRandomnumber(), it.getId()}).collect(toList()));
        return worlds;
    }
}
