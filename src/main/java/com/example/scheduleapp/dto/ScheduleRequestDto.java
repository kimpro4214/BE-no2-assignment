package com.example.scheduleapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ScheduleRequestDto {

    //lv6
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 200, message = "내용은 200자 이내여야 합니다.")
    private String content;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "작성자 이름은 필수입니다.")
    private String writerName;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")

    private String writerEmail;

    private String writer;


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
