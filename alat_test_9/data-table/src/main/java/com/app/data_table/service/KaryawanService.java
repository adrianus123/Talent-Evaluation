package com.app.data_table.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.data_table.dto.ApiResponse;
import com.app.data_table.model.Karyawan;
import com.app.data_table.repository.KaryawanRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KaryawanService {
    @Autowired
    private KaryawanRepository karyawanRepository;

    public ApiResponse<List<Karyawan>> daftarKaryawan() {
        try {
            List<Karyawan> data = karyawanRepository.findAll();
            log.info("GET Karyawan", data);

            return ApiResponse.<List<Karyawan>>builder().data(data).message("Berhasil mengambil daftar karyawan.")
                    .build();
        } catch (Exception e) {
            log.error("Gagal get daftar karyawan", e.getMessage());
            throw e;
        }
    }

    public ApiResponse<List<Karyawan>> tambahKaryawan(List<Karyawan> requestData) {
        try {
            karyawanRepository.saveAll(requestData);
            log.info("POST Karyawan", requestData);

            return ApiResponse.<List<Karyawan>>builder().data(requestData).message("Berhasil menambahkan data karyawan")
                    .build();
        } catch (Exception e) {
            log.error("Gagal menambahkan data karyawan", e.getMessage());
            throw e;
        }
    }

    public ApiResponse<List<Karyawan>> editKaryawan(List<Karyawan> requestData) {
        try {
            karyawanRepository.saveAll(requestData);
            log.info("PUT Karyawan", requestData);

            return ApiResponse.<List<Karyawan>>builder().data(requestData).message("Berhasil memperbarui data karyawan")
                    .build();
        } catch (Exception e) {
            log.error("Gagal memperbarui data karyawan", e.getMessage());
            throw e;
        }
    }

    public ApiResponse<Karyawan> hapusKaryawan(List<BigInteger> requestData) {
        try {
            karyawanRepository.deleteAllById(requestData);
            log.info("DELETE Karyawan", requestData);
            return ApiResponse.<Karyawan>builder().data(null).message("Berhasil menghapus data karyawan")
                    .build();
        } catch (Exception e) {
            log.error("Gagal menghapus data karyawan", e.getMessage());
            throw e;
        }
    }

    public String hapusSemuaKaryawan() {
        try {
            karyawanRepository.deleteAll();
            return "Success";
        } catch (Exception e) {
            log.error("Error", e);
            throw e;
        }
    }
}
