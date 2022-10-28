package com.example.introductiontosql.controller;

import com.example.introductiontosql.record.FacultyRecord;
import com.example.introductiontosql.record.StudentRecord;
import com.example.introductiontosql.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentRecord create(@RequestBody StudentRecord studentRecord) {
        return studentService.create(studentRecord);
    }

    @GetMapping("/{id}")
    public StudentRecord read(@PathVariable long id) {
        return studentService.read(id);
    }

    @PutMapping("/{id}")
    public StudentRecord update(@PathVariable long id,
                          @RequestBody StudentRecord studentRecord) {
        return studentService.update(id,studentRecord);
    }

    @DeleteMapping ("/{id}")
    public StudentRecord delete(@PathVariable long id) {
        return studentService.delete(id);
    }

    @GetMapping(params="age")
    public Collection<StudentRecord> findByAge(@RequestParam int age) {
        return studentService.findByAge(age);
    }

    @GetMapping(params={"MinAge","MaxAge"})
    public Collection<StudentRecord> findByAgeBetween(@RequestParam int MinAge,
                                                      @RequestParam int MaxAge) {
        return studentService.findByAgeBetween(MinAge,MaxAge);
    }
    @GetMapping("/{id}/faculty")
    public FacultyRecord getFacultyByStudent(@PathVariable long id) {
        return studentService.getFacultyByStudent(id);
    }



}
