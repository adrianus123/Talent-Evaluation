package com.app.data_table.model;

import java.math.BigInteger;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "karyawan")
public class Karyawan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_karyawan")
    private BigInteger idKaryawan;

    @Column(name = "nama_karyawan")
    private String namaKaryawan;

    @Column(name = "jabatan")
    private String jabatan;

    @Column(name = "departemen")
    private String departemen;

    @Column(name = "tanggal_bergabung")
    private LocalDate tanggalBergabung;

    @Column(name = "status")
    private String status;
}
