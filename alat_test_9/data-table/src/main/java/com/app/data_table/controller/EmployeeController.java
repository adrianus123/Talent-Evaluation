package com.app.data_table.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.data_table.dto.ApiResponse;
import com.app.data_table.model.Employee;
import com.app.data_table.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Employee>>> getEmployees() {
        try {
            ApiResponse<List<Employee>> data = employeeService.getEmployees();
            return ResponseEntity.status(HttpStatus.OK.value()).body(data);
        } catch (Exception e) {
            log.error("Controller GET", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable BigInteger id) {
        try {
            ApiResponse<Employee> data = employeeService.getEmployee(id);
            return ResponseEntity.status(HttpStatus.OK.value()).body(data);
        } catch (Exception e) {
            log.error("Controller GET", e);
            throw e;
        }
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@RequestBody Employee request) {
        try {
            ApiResponse<Employee> data = employeeService.addEmployee(request);
            return ResponseEntity.status(HttpStatus.OK.value()).body(data);
        } catch (Exception e) {
            log.error("Controller POST", e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable BigInteger id,
            @RequestBody Employee request) {
        try {
            ApiResponse<Employee> res = employeeService.updateEmployee(request, id);
            return ResponseEntity.status(HttpStatus.OK.value()).body(res);
        } catch (Exception e) {
            log.error("Controller PUT", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> deleteEmployee(@PathVariable BigInteger id) {
        try {
            ApiResponse<Employee> res = employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.OK.value()).body(res);
        } catch (Exception e) {
            log.error("Controller DELETE", e);
            throw e;
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllEmployee() {
        try {
            String res = employeeService.deleteAllEmployee();
            return ResponseEntity.status(HttpStatus.OK.value()).body(res);
        } catch (Exception e) {
            log.error("ERROR", e);
            throw e;
        }
    }
}
