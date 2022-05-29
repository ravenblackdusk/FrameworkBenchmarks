package benchmark.jdbc.common.service;

import benchmark.jdbc.common.model.Fortune;
import benchmark.service.FortuneLikeService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FortuneService implements FortuneLikeService<Fortune> {
    private final JdbcTemplate jdbcTemplate;

    public FortuneService(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Fortune> findAll() {
        return jdbcTemplate.query("select * from fortune", (rs, rowNum) -> new Fortune(rs.getInt(1), rs.getString(2)));
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
