package com.example.employee.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository; 
	
	public Employee postEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	public void deleteEmployee(Long id) {
		if(!employeeRepository.existsById(id)) {
			throw new EntityNotFoundException(id + " Not found");
		}else {
			
			employeeRepository.deleteById(id);
		}
		
	}
	
	
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee updateEmployee(Long id, Employee employee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if(optionalEmployee.isPresent()) {
			Employee existingEmployee = optionalEmployee.get();
			existingEmployee.setName(employee.getName());
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setPhone(employee.getPhone());
			existingEmployee.setDepartment(employee.getDepartment());
			
			return employeeRepository.save(existingEmployee);
		}return null; 
	}
}
