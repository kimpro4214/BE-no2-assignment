package com.example.scheduleapp.dto;

import java.time.LocalDateTime;

public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String writer;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Long id, String title, String writer, String password, String content,
                               LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // Getter만 있으면 충분합니다
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getPassword() {
        return password;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
