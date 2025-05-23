package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.repository.AuthorRepository;
import com.example.scheduleapp.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepo;
    private final AuthorRepository authorRepo;

    public ScheduleService(ScheduleRepository scheduleRepo, AuthorRepository authorRepo) {
        this.scheduleRepo = scheduleRepo;
        this.authorRepo = authorRepo;
    }

//    public void createSchedule(ScheduleRequestDto dto) {
//        repository.save(dto);
//    }

    //lv3
    public void create(ScheduleRequestDto dto) {
        Long authorId;
        var author = authorRepo.findByNameAndEmail(dto.getWriterName(), dto.getWriterEmail());

        if (author == null) {
            authorId = authorRepo.save(dto.getWriterName(), dto.getWriterEmail());
        } else {
            authorId = author.getId();
        }

        scheduleRepo.save(dto, authorId);
    }

    public List<ScheduleResponseDto> getAllSchedules(String modifiedAt, String writerName) {
        return scheduleRepo.findAll(modifiedAt, writerName);
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        return scheduleRepo.findById(id);
    }

    public void update(Long id, ScheduleRequestDto dto) {
        ScheduleResponseDto existing = scheduleRepo.findById(id);

        // 1. 비밀번호 일치 확인
        String savedPassword = scheduleRepo.findPasswordById(id);
        if (!savedPassword.equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 2. 작성자 찾기 (name + email)
        var author = authorRepo.findByNameAndEmail(dto.getWriterName(), dto.getWriterEmail());
        if (author == null) {
            throw new IllegalArgumentException("작성자를 찾을 수 없습니다.");
        }

        // 3. 수정
        scheduleRepo.update(id, dto, author.getId());
    }


    public void delete(Long id, String password) {
        ScheduleResponseDto schedule = scheduleRepo.findById(id);

        String savedPassword = scheduleRepo.findPasswordById(id);
        if (!savedPassword.equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepo.delete(id);
    }



}

