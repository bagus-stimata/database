package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbKabupaten;
import com.desgreen.gov.database.model.TbKecamatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbKecamatanRepository extends JpaRepository<TbKecamatan, Integer> {
    TbKecamatan findById(int id);
    List<TbKecamatan> findByKode1(String kode1);

    @Query("SELECT u FROM TbKecamatan u WHERE u.description LIKE :description_")
    List<TbKecamatan> findByDescription(@Param("description_") String description_);


}
