package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbJenisPrasarana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbJenisPrasaranaRepository extends JpaRepository<TbJenisPrasarana, Integer> {
    TbJenisPrasarana findById(int id);
    List<TbJenisPrasarana> findByKode1(String kode1);

    @Query("SELECT u FROM TbJenisPrasarana u WHERE u.description LIKE :description_")
    List<TbJenisPrasarana> findByDescription(@Param("description_") String description_);


}
