package com.app.data_table.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.data_table.dto.ApiResponse;
import com.app.data_table.model.Employee;
import com.app.data_table.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public ApiResponse<List<Employee>> getEmployees() {
        try {
            List<Employee> data = employeeRepository.findAll();
            return getResponse(data, "Berhasil mengambil daftar karyawan");
        } catch (Exception e) {
            log.error("Gagal get daftar karyawan", e.getMessage());
            throw e;
        }
    }

    public ApiResponse<Employee> getEmployee(BigInteger id) {
        try {
            Employee emp = employeeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Karyawan tidak ditemukan"));

            return getResponse(emp, "Berhasil mengambil data karyawan");
        } catch (Exception e) {
            log.error("Gagal mengambil data karyawan", e.getMessage());
            throw e;
        }
    }

    public ApiResponse<Employee> addEmployee(Employee requestData) {
        try {
            employeeRepository.save(requestData);
            return getResponse(requestData, "Berhasil menambahkan data karyawan");
        } catch (Exception e) {
            log.error("Gagal menambahkan data karyawan", e.getMessage());
            throw e;
        }
    }

    public ApiResponse<Employee> updateEmployee(Employee requestData, BigInteger id) {
        try {
            Employee emp = employeeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Karyawan tidak ditemukan"));

            emp.setNamaKaryawan(requestData.getNamaKaryawan());
            emp.setJabatan(requestData.getJabatan());
            emp.setDepartemen(requestData.getDepartemen());
            emp.setTanggalBergabung(requestData.getTanggalBergabung());
            emp.setStatus(requestData.getStatus());
            employeeRepository.save(emp);

            return getResponse(emp, "Berhasil memperbarui data karyawan");
        } catch (Exception e) {
            log.error("Gagal memperbarui data karyawan", e.getMessage());
            throw e;
        }
    }

    public ApiResponse<Employee> deleteEmployee(BigInteger id) {
        try {
            Employee emp = employeeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Karyawan tidak ditemukan"));

            employeeRepository.delete(emp);
            return getResponse(null, "Berhasil menghapus data karyawan");
        } catch (Exception e) {
            log.error("Gagal menghapus data karyawan", e.getMessage());
            throw e;
        }
    }

    public String deleteAllEmployee() {
        try {
            employeeRepository.deleteAll();
            return "Success";
        } catch (Exception e) {
            log.error("Error", e);
            throw e;
        }
    }

    protected <T> ApiResponse<T> getResponse(T data, String message) {
        return ApiResponse.<T>builder()
                .data(data)
                .message(message)
                .build();
    }
}
