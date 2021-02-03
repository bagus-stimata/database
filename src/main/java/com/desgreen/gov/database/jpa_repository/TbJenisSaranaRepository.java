package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbJenisSarana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbJenisSaranaRepository extends JpaRepository<TbJenisSarana, Integer> {
    TbJenisSarana findById(int id);
    List<TbJenisSarana> findByKode1(String kode1);

    @Query("SELECT u FROM TbJenisSarana u WHERE u.description LIKE :description_")
    List<TbJenisSarana> findByDescription(@Param("description_") String description_);


}
