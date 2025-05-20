package com.example.scheduleapp.entity;

import java.time.LocalDateTime;

public class Schedule {
    private Long id;
    private String title;
    private String writer;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Schedule() {}

    public Schedule(Long id, String title, String writer, String password,
                    LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }



    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getWriter() { return writer; }
    public String getPassword() { return password; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getModifiedAt() { return modifiedAt; }
}
