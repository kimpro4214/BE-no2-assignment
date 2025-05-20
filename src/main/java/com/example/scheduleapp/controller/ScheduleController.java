package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.ScheduleRequestDto;
import com.example.scheduleapp.dto.ScheduleResponseDto;
import com.example.scheduleapp.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }
    //Post
    @PostMapping
    public ResponseEntity<String> create(@RequestBody ScheduleRequestDto dto) {
        service.createSchedule(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("일정이 등록되었습니다.");
    }
    //이름 또는 수정일에 따라 검색
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAll(
            @RequestParam(required = false) String modifiedAt,
            @RequestParam(required = false) String writer) {
        return ResponseEntity.ok(service.getAllSchedules(modifiedAt, writer));
    }
    //id로 검색
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getOne(@PathVariable Long id) {
        ScheduleResponseDto dto = service.getScheduleById(id);
        return ResponseEntity.ok(dto);
    }
    //수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long id,
                                            @RequestBody ScheduleRequestDto dto) {
        try {
            service.update(id, dto);
            return ResponseEntity.ok("일정이 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id,
                                            @RequestBody Map<String, String> body) {
        String password = body.get("password");

        try {
            service.delete(id, password);
            return ResponseEntity.ok("일정이 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}


