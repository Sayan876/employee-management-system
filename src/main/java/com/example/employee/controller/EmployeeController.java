package com.example.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeService employeeService;
    
    
    @PostMapping("/employee")
    public Employee postEmployee(@RequestBody Employee employee) {
    	return employeeService.postEmployee(employee);
    }
    
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
    	return employeeService.getAllEmployee();
    }
    
    
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
    	try {
    		employeeService.deleteEmployee(id);
    		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    	}catch(EntityNotFoundException e){
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    	}
    }
    
    @GetMapping("/employee/{id}")
    
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
    	Employee employee = employeeService.getEmployeeById(id);
    	if(employee == null) {
    		return ResponseEntity.notFound().build();
    	}else {
    		return ResponseEntity.ok(employee);
    	}
    }
    
    @PatchMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
    	Employee updateEmployee = employeeService.updateEmployee(id, employee);
    	if(updateEmployee == null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}else {
    		return ResponseEntity.ok(updateEmployee);
    	}
    }
}
