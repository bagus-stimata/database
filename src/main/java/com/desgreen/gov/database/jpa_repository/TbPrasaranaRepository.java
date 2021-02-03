package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbOpd;
import com.desgreen.gov.database.model.TbPrasarana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbPrasaranaRepository extends JpaRepository<TbPrasarana, Long> {
    TbPrasarana findById(long id);
    List<TbPrasarana> findByKode1(String kode1);

    @Query("SELECT u FROM TbPrasarana u WHERE u.description LIKE :description_")
    List<TbPrasarana> findByDescription(@Param("description_") String description_);


}
