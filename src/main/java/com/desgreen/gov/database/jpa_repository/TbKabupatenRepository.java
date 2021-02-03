package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbKabupaten;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbKabupatenRepository extends JpaRepository<TbKabupaten, Integer> {
    TbKabupaten findById(int id);
    List<TbKabupaten> findByKode1(String kode1);

    @Query("SELECT u FROM TbKabupaten u WHERE u.description LIKE :description_")
    List<TbKabupaten> findByDescription(@Param("description_") String description_);


}
