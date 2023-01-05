package com.example.introductiontosql.service;

import com.example.introductiontosql.component.RecordMapper;
import com.example.introductiontosql.exeption.FacultyNotFoundException;
import com.example.introductiontosql.entity.Faculty;
import com.example.introductiontosql.record.FacultyRecord;
import com.example.introductiontosql.record.StudentRecord;
import com.example.introductiontosql.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final RecordMapper recordMapper;

    public FacultyService(FacultyRepository facultyRepository, RecordMapper recordMapper) {
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }

    public FacultyRecord create(FacultyRecord facultyRecord) {
        return recordMapper.toRecord(facultyRepository.save(recordMapper.toEntity(facultyRecord)));
    }

    public FacultyRecord read(long id) {
        return recordMapper.toRecord(facultyRepository.findById(id).orElseThrow(()->new FacultyNotFoundException(id)));
    }

    public FacultyRecord update(long id,
                                FacultyRecord facultyRecord) {
        Faculty oldFaculty = facultyRepository.findById(id).orElseThrow(()->new FacultyNotFoundException(id));
        oldFaculty.setName(facultyRecord.getName());
        oldFaculty.setColor(facultyRecord.getColor());
        return recordMapper.toRecord(facultyRepository.save(oldFaculty));
    }

    public FacultyRecord delete(long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(()->new FacultyNotFoundException(id));
        facultyRepository.delete(faculty);
        return recordMapper.toRecord(faculty);
    }

    public Collection<FacultyRecord> findByColor(String color) {
        return facultyRepository.findAllByColor(color).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<FacultyRecord> findByColorOnName(String colorOnName) {
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(colorOnName,colorOnName).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getStudentsByFaculty(long id) {
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .map(students ->
                        students.stream()
                                .map(recordMapper::toRecord)
                                .collect(Collectors.toList())
                )
                .orElseThrow(()->new FacultyNotFoundException(id));

    }
}
