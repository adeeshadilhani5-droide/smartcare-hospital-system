package com.smartcare.system.service;

import com.smartcare.system.entity.Department;
import com.smartcare.system.exception.ResourceNotFoundException;
import com.smartcare.system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Integer id, Department departmentDetails) {
        Department department = getDepartmentById(id);
        department.setDepartmentName(departmentDetails.getDepartmentName());
        department.setLocation(departmentDetails.getLocation());
        department.setHeadDoctor(departmentDetails.getHeadDoctor());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Integer id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}