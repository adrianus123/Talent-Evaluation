package com.app.data_table.dto;

import java.util.List;

import com.app.data_table.model.Employee;

import lombok.Data;

@Data
public class EmployeeRequest {
    private List<Employee> data;
}
