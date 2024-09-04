package com.app.data_table.dto;

import com.app.data_table.model.Employee;
import java.util.List;

import lombok.Data;

@Data
public class OperationRequest {
    private String operationType;
    private List<Employee> employees;
}
