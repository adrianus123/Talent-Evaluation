package com.app.data_table.dto;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;

@Data
public class EmployeeDeleteRequest {
    private List<BigInteger> data;
}
