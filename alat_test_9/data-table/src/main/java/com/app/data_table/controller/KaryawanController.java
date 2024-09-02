package com.app.data_table.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.data_table.dto.ApiResponse;
import com.app.data_table.dto.KaryawanDeleteRequest;
import com.app.data_table.dto.KaryawanRequest;
import com.app.data_table.model.Karyawan;
import com.app.data_table.service.KaryawanService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/karyawan")
@Slf4j
public class KaryawanController {
    @Autowired
    private KaryawanService karyawanService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Karyawan>>> daftarKaryawan() {
        try {
            ApiResponse<List<Karyawan>> data = karyawanService.daftarKaryawan();
            return ResponseEntity.status(HttpStatus.OK.value()).body(data);
        } catch (Exception e) {
            log.error("Controller GET", e);
            throw e;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<List<Karyawan>>> tambahKaryawan(@RequestBody KaryawanRequest request) {
        try {
            ApiResponse<List<Karyawan>> data = karyawanService.tambahKaryawan(request.getData());
            return ResponseEntity.status(HttpStatus.OK.value()).body(data);
        } catch (Exception e) {
            log.error("Controller POST", e);
            throw e;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<List<Karyawan>>> editKaryawan(@RequestBody KaryawanRequest request) {
        try {
            ApiResponse<List<Karyawan>> res = karyawanService.editKaryawan(request.getData());
            return ResponseEntity.status(HttpStatus.OK.value()).body(res);
        } catch (Exception e) {
            log.error("Controller PUT", e);
            throw e;
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Karyawan>> hapusKaryawan(@RequestBody KaryawanDeleteRequest request) {
        try {
            ApiResponse<Karyawan> res = karyawanService.hapusKaryawan(request.getData());
            return ResponseEntity.status(HttpStatus.OK.value()).body(res);
        } catch (Exception e) {
            log.error("Controller DELETE", e);
            throw e;
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> hapusSemuaKaryawan() {
        try {
            String res = karyawanService.hapusSemuaKaryawan();
            return ResponseEntity.status(HttpStatus.OK.value()).body(res);
        } catch (Exception e) {
            log.error("ERROR", e);
            throw e;
        }
    }
}
