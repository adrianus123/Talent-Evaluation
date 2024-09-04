package com.app.data_table.model;

import java.math.BigInteger;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "karyawan")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_karyawan")
    private BigInteger idKaryawan;

    @NotBlank(message = "Nama karyawan tidak boleh kosong")
    @Column(name = "nama_karyawan")
    private String namaKaryawan;

    @NotBlank(message = "Jabatan tidak boleh kosong")
    @Column(name = "jabatan")
    private String jabatan;

    @NotBlank(message = "Departemen tidak boleh kosong")
    @Column(name = "departemen")
    private String departemen;

    @Column(name = "tanggal_bergabung")
    private LocalDate tanggalBergabung = LocalDate.now();

    @NotBlank(message = "Status tidak boleh kosong")
    @Column(name = "status")
    private String status;
}
