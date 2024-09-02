package com.app.data_table.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.data_table.model.Karyawan;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, BigInteger> {

}
