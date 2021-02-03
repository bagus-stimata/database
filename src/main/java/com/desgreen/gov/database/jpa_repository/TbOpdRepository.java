package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbOpd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbOpdRepository extends JpaRepository<TbOpd, Integer> {
    TbOpd findById(int id);
    List<TbOpd> findByKode1(String kode1);

    @Query("SELECT u FROM TbOpd u WHERE u.description LIKE :description_")
    List<TbOpd> findByDescription(@Param("description_") String description_);



}
