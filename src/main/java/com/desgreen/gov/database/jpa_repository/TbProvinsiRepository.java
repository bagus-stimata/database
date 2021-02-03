package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbProvinsi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbProvinsiRepository extends JpaRepository<TbProvinsi, Integer> {
    TbProvinsi findById(int id);
    List<TbProvinsi> findByKode1(String kode1);

    @Query("SELECT u FROM TbProvinsi u WHERE u.description LIKE :description_")
    List<TbProvinsi> findByDescription(@Param("description_") String description_);


}
