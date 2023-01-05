package com.example.introductiontosql.component;

import com.example.introductiontosql.entity.Avatar;
import com.example.introductiontosql.entity.Faculty;
import com.example.introductiontosql.entity.Student;
import com.example.introductiontosql.record.AvatarRecord;
import com.example.introductiontosql.record.FacultyRecord;
import com.example.introductiontosql.record.StudentRecord;
import org.springframework.stereotype.Component;

@Component
public class RecordMapper {

    public StudentRecord toRecord(Student student) {
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.setId(student.getId());
        studentRecord.setName(student.getName());
        studentRecord.setAge(student.getAge());
        if (student.getFaculty() != null) {
            studentRecord.setFaculty(toRecord(student.getFaculty()));
        }
        if (student.getAvatar() != null) {
            studentRecord.setAvatar(toRecord(student.getAvatar()));
        }
        return studentRecord;
    }

    public FacultyRecord toRecord(Faculty faculty) {
        FacultyRecord facultyRecord = new FacultyRecord();
        facultyRecord.setId(faculty.getId());
        facultyRecord.setName(faculty.getName());
        facultyRecord.setColor(faculty.getColor());
        return facultyRecord;
    }

    public AvatarRecord toRecord(Avatar avatar) {
        return new AvatarRecord(
                avatar.getId(),
                avatar.getMediaType(),
                null  //todo
                );

    }

    public Student toEntity(StudentRecord studentRecord) {
        Student student = new Student();
        student.setName(studentRecord.getName());
        student.setAge(studentRecord.getAge());
        if (studentRecord.getFaculty() != null) {
            student.setFaculty(toEntity(studentRecord.getFaculty()));
        }
        return student;
    }

    public Faculty toEntity(FacultyRecord facultyRecord) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyRecord.getName());
        faculty.setColor(facultyRecord.getColor());
        return faculty;
    }
}
