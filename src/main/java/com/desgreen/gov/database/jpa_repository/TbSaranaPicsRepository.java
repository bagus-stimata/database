package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbSarana;
import com.desgreen.gov.database.model.TbSaranaPics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbSaranaPicsRepository extends JpaRepository<TbSaranaPics, Long> {
    TbSaranaPics findById(long id);
    List<TbSaranaPics> findByTitle(String title);

    @Query("SELECT u FROM TbSaranaPics u WHERE u.description LIKE :description_")
    List<TbSaranaPics> findByDescription(@Param("description_") String description_);


}
