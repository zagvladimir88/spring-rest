package com.zagvladimir.spring.rest.controllers;

import com.zagvladimir.spring.rest.entity.Employee;
import com.zagvladimir.spring.rest.exception_handling.EmployeeIncorectData;
import com.zagvladimir.spring.rest.exception_handling.NoSuchEmployeeException;
import com.zagvladimir.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployee() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/employees/{id}")
    public Employee showEployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if(employee == null){
            throw new NoSuchEmployeeException("The is no employee with id = " + id + " in DataBase");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String addNewEmployee(@PathVariable int id){

        Employee employee = employeeService.getEmployee(id);

        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with id = " + id + " in database");
        }

        employeeService.deleteEmployee(employee);
        return "Employee with ID = " + id + " was deleted";
    }
}
