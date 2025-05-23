package com.example.scheduleapp.dto;

public class ScheduleRequestDto {
    private String title;
    private String writer;
    private String password;
    private String content;
    private String writerName;
    private String writerEmail;

    public ScheduleRequestDto() {}

    public ScheduleRequestDto(String title, String writer, String password, String content) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
        this.writerName = writerName;
        this.writerEmail = writerEmail;
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
    //lv3
    public String getWriterName() {
        return writerName;
    }
    //lv3
    public String getWriterEmail() {
        return writerEmail;
    }
}
