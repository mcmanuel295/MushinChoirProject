package com.mcmanuel.MushinChoirProject.controller;

import com.mcmanuel.MushinChoirProject.entity.Assignment;
import com.mcmanuel.MushinChoirProject.entity.Lesson;
import com.mcmanuel.MushinChoirProject.service.intf.AssignmentService;
import com.mcmanuel.MushinChoirProject.service.intf.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{id}/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/")
    public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment){
        return new ResponseEntity<>(assignmentService.createNew(assignment), HttpStatus.CREATED);
    }


}
