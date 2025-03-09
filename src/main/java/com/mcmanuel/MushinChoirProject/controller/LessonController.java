package com.mcmanuel.MushinChoirProject.controller;

import com.mcmanuel.MushinChoirProject.entity.Lesson;
import com.mcmanuel.MushinChoirProject.service.intf.LessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Lesson> addLesson(@RequestBody Lesson lesson){
        return new ResponseEntity<>(lessonService.createNew(lesson), HttpStatus.CREATED);
    }

    @PutMapping("/{lessonId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Lesson> editLesson(@PathVariable @Valid Integer lessonId, @RequestBody String title,@RequestBody String content ){
        return new ResponseEntity<>(lessonService.UpdateLesson(lessonId,title,content), HttpStatus.CREATED);
    }

    @PostMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLesson(@PathVariable @Valid Integer lessonId){
        return new ResponseEntity<>(lessonService.getLesson(lessonId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{lessonId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Lesson> deleteLesson(@PathVariable @Valid Integer lessonId){
        lessonService.delete(lessonId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
