package com.bhawna.SpringBootWeek2Task.services;

import com.bhawna.SpringBootWeek2Task.DTO.DepartmentDTO;
import com.bhawna.SpringBootWeek2Task.entities.DepartmentEntity;
import com.bhawna.SpringBootWeek2Task.exceptions.ResourceNotFoundException;
import com.bhawna.SpringBootWeek2Task.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntity = departmentRepository.findAll();
        return departmentEntity
                .stream()
                .map(departmentEntity1 -> modelMapper.map(departmentEntity1, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveEntity = modelMapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO, Long departmentId) {
        isIdPresent(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedEntity, DepartmentDTO.class);
    }

    public void isIdPresent(Long departmentId){
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists)
            throw new ResourceNotFoundException("Department Not Found with id: " + departmentId);
    }
    public boolean deleteDepartmentById(Long departmentId) {
        isIdPresent(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public DepartmentDTO updatePartialDepartmentById(Map<String, Object> updates, Long departmentId) {
        isIdPresent(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).orElse(null);
        updates.forEach((field, value)->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
        });
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }
}
