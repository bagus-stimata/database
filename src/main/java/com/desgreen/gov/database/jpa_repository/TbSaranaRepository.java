package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbSarana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbSaranaRepository extends JpaRepository<TbSarana, Integer> {
    TbSarana findById(int id);
    List<TbSarana> findByKode1(String kode1);

    // @Query("SELECT u FROM TbSarana u WHERE u.description LIKE :description_")
    // List<TbSarana> findByDescription(@Param("description_") String description_);


}
