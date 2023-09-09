package kz.abylay.example.services;

import kz.abylay.example.models.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getById(Integer id);
}
