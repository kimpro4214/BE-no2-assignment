package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    public void createSchedule(ScheduleRequestDto dto) {
        repository.save(dto);
    }

    public List<ScheduleResponseDto> getAllSchedules(String modifiedAt, String writer) {
        return repository.findAll(modifiedAt, writer);
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        return repository.findById(id);
    }

    public void update(Long id, ScheduleRequestDto dto) {
        ScheduleResponseDto existing = repository.findById(id);

        if (!existing.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        repository.update(id, dto.getTitle(), dto.getWriter());
    }

    public void delete(Long id, String password) {
        ScheduleResponseDto schedule = repository.findById(id);

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        repository.delete(id);
    }



}

