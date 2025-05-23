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

    public void save(ScheduleRequestDto dto, Long authorId) {
        String sql = "INSERT INTO schedules (title, password, content, author_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getPassword(), dto.getContent(), authorId);
    }

    public List<ScheduleResponseDto> findAll(String modifiedAt, String writerName) {
        StringBuilder sql = new StringBuilder(
                "SELECT s.id, s.title, s.content, s.created_at, s.modified_at, a.name AS writerName " +
                        "FROM schedules s JOIN authors a ON s.author_id = a.id "
        );

        List<Object> params = new ArrayList<>();
        List<String> conditions = new ArrayList<>();

        if (modifiedAt != null && !modifiedAt.isEmpty()) {
            sql.append(" AND DATE(modified_at) = ?");
            params.add(modifiedAt);
        }

        if (writerName != null && !writerName.isEmpty()) {
            sql.append(" AND writer = ?");
            params.add(writerName);
        }
        //lv3
        if (!conditions.isEmpty()) {
            sql.append("WHERE ").append(String.join(" AND ", conditions));
        }

        sql.append(" ORDER BY modified_at DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("writerName"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                ));
    }

    public ScheduleResponseDto findById(Long id) {
        String sql = "SELECT s.id, s.title, s.content, s.created_at, s.modified_at, a.name AS writerName " +
                "FROM schedules s JOIN authors a ON s.author_id = a.id WHERE s.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("writerName"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                )
        );
    }

    public void update(Long id, ScheduleRequestDto dto, Long authorId) {
        String sql = "UPDATE schedules SET title = ?, content = ?, modified_at = NOW() " +
                "WHERE id = ? AND author_id = ? AND password = ?";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getContent(), id, authorId, dto.getPassword());
    }


    public void delete(Long id) {
        String sql = "DELETE FROM schedules WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    // lv3
    public String findPasswordById(Long id) {
        String sql = "SELECT password FROM schedules WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
    // lv4
    public List<ScheduleResponseDto> findPaged(int offset, int size) {
        String sql = """
        SELECT s.id, s.title, s.content, s.created_at, s.modified_at, a.name AS writerName
        FROM schedules s
        JOIN authors a ON s.author_id = a.id
        ORDER BY s.modified_at DESC
        LIMIT ? OFFSET ?
    """;

        return jdbcTemplate.query(sql, new Object[]{size, offset}, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("writerName"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("modified_at").toLocalDateTime()
                )
        );
    }




}


