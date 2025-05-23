package com.example.scheduleapp.dto;

import java.time.LocalDateTime;

public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String writerName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Long id, String title, String writerName, String content,
                               LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.writerName = writerName;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriterName() {
        return writerName;
    }

//    public String getPassword() {
//        return password;
//    }

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
