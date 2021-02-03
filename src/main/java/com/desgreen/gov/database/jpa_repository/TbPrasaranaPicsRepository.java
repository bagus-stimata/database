package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbOpd;
import com.desgreen.gov.database.model.TbPrasarana;
import com.desgreen.gov.database.model.TbPrasaranaPics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbPrasaranaPicsRepository extends JpaRepository<TbPrasaranaPics, Long> {
    TbPrasaranaPics findById(long id);
    List<TbPrasaranaPics> findByTitle(String title);

    @Query("SELECT u FROM TbPrasaranaPics u WHERE u.description LIKE :description_")
    List<TbPrasaranaPics> findByDescription(@Param("description_") String description_);


}
