package com.example.scheduleapp.repository;

import com.example.scheduleapp.model.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {

    private final JdbcTemplate jdbc;

    public AuthorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    //lv3
    public Author findByNameAndEmail(String name, String email) {
        String sql = "SELECT * FROM authors WHERE name = ? AND email = ?";
        List<Author> authors = jdbc.query(sql, (rs, rowNum) ->
                        new Author(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getTimestamp("created_at").toLocalDateTime(),
                                rs.getTimestamp("updated_at").toLocalDateTime()
                        ),
                name, email
        );

        return authors.isEmpty() ? null : authors.get(0);
    }
    //lv3
    public Long save(String name, String email) {
        jdbc.update("INSERT INTO authors (name, email) VALUES (?, ?)", name, email);
        return jdbc.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }
}
