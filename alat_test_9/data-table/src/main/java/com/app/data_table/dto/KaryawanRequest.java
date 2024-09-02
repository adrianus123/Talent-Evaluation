package com.app.data_table.dto;

import java.util.List;

import com.app.data_table.model.Karyawan;

import lombok.Data;

@Data
public class KaryawanRequest {
    private List<Karyawan> data;
}
