package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbDesaRepository extends JpaRepository<TbDesa, Integer> {
    TbDesa findById(int id);
    List<TbDesa> findByKode1(String kode1);

    @Query("SELECT u FROM TbDesa u WHERE u.description LIKE :description_")
    List<TbDesa> findByDescription(@Param("description_") String description_);


}
