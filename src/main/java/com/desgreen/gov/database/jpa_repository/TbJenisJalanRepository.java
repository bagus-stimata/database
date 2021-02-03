package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbJenisJalan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbJenisJalanRepository extends JpaRepository<TbJenisJalan, Integer> {
    TbJenisJalan findById(int id);
    List<TbJenisJalan> findByKode1(String kode1);

    @Query("SELECT u FROM TbJenisJalan u WHERE u.description LIKE :description_")
    List<TbJenisJalan> findByDescription(@Param("description_") String description_);


}
