package com.mcmanuel.MushinChoirProject.service.intf;
import com.mcmanuel.MushinChoirProject.entity.Lesson;

public interface LessonService {

    Lesson createNew(Lesson lesson);

    Lesson getLesson(Integer lessonId);

    Lesson UpdateLesson(Integer lessonId, String updatedTitle, String updatedContent);

    void delete(Integer lessonId);
}
