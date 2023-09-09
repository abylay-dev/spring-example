package kz.abylay.example.services.impl;

import kz.abylay.example.models.Course;
import kz.abylay.example.repository.CourseRepository;
import kz.abylay.example.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }
}
