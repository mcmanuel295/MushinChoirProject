package com.mcmanuel.MushinChoirProject.service.impl;

import com.mcmanuel.MushinChoirProject.entity.Assignment;
import com.mcmanuel.MushinChoirProject.exception.AssignmentNotFoundException;
import com.mcmanuel.MushinChoirProject.repository.AssignmentRepository;
import com.mcmanuel.MushinChoirProject.service.intf.AssignmentService;
import org.springframework.stereotype.Service;


@Service
public class AssignmentServiceImpl implements AssignmentService {
    private AssignmentRepository assignmentRepo;

    @Override
    public Assignment createNew(Assignment assignment) {
        return null;
    }

    @Override
    public Assignment getAssignment(Integer assignmentId) {
        return assignmentRepo.findById(assignmentId).orElseThrow(()-> new AssignmentNotFoundException("assignment"+assignmentId+" not found"));
    }

    @Override
    public Assignment updateAssigment(Integer assignmentId, String updatedTitle, String updatedContent) {
        Assignment assignment = getAssignment(assignmentId);

        if (assignment != null) {
            assignment.setTitle(updatedTitle);
            assignment.setContent(updatedContent);
            return assignmentRepo.save(assignment);
        }
        return null;
    }
}
