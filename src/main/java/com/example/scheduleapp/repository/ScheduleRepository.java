package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(ScheduleRequestDto dto) {
        String sql = "INSERT INTO schedules (title, writer, password, content) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getWriter(), dto.getPassword(), dto.getContent());
    }

    public List<ScheduleResponseDto> findAll(String modifiedAt, String writer) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedules WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (modifiedAt != null && !modifiedAt.isEmpty()) {
            sql.append(" AND DATE(modified_at) = ?");
            params.add(modifiedAt);
        }

        if (writer != null && !writer.isEmpty()) {
            sql.append(" AND writer = ?");
            params.add(writer);
        }

        sql.append(" ORDER BY modified_at DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("password"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                ));
    }

    public ScheduleResponseDto findById(Long id) {
        String sql = "SELECT * FROM schedules WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("password"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                )
        );
    }

    public void update(Long id, String title, String writer) {
        String sql = "UPDATE schedules SET title = ?, writer = ?, modified_at = NOW() WHERE id = ?";
        jdbcTemplate.update(sql, title, writer, id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedules WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


}


