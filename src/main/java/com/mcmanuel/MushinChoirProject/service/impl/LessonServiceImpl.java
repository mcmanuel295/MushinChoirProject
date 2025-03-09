package com.mcmanuel.MushinChoirProject.service.impl;

import com.mcmanuel.MushinChoirProject.entity.Lesson;
import com.mcmanuel.MushinChoirProject.exception.LessonNotFoundException;
import com.mcmanuel.MushinChoirProject.repository.LessonRepository;
import com.mcmanuel.MushinChoirProject.service.intf.LessonService;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {
    private LessonRepository lessonRepo;

    @Override
    public Lesson createNew(Lesson lesson) {
        return lessonRepo.save(lesson);
    }

    @Override
    public Lesson getLesson(Integer lessonId) {
        return lessonRepo.findById(lessonId)
                .orElseThrow(()-> new LessonNotFoundException("Lesson "+lessonId+" not found"));
    }

    @Override
    public Lesson UpdateLesson(Integer lessonId, String updatedTitle, String updatedContent) {
        Lesson lesson = getLesson(lessonId);
        if (lesson != null) {
            lesson.setTitle(updatedTitle);
            lesson.setContent(updatedContent);
            return lessonRepo.save(lesson);
        }
        else {
            throw new LessonNotFoundException("Lesson "+lessonId+" not found");
        }
    }

    @Override
    public void delete(Integer lessonId) {
        Lesson lesson = getLesson(lessonId);

        if (lesson != null) {
            lessonRepo.deleteById(lesson.getLessonId());
        }
        else {
            throw new LessonNotFoundException("Lesson "+lessonId+" not found");
        }
    }
}
