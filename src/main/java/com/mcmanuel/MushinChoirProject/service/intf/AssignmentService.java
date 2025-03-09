package com.mcmanuel.MushinChoirProject.service.intf;

import com.mcmanuel.MushinChoirProject.entity.Assignment;


public interface AssignmentService {

    Assignment createNew(Assignment assignment);

    Assignment getAssignment(Integer assignmentId);

    Assignment updateAssigment(Integer assignmentId, String updatedTitle, String updatedContent);
}
