package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbKabupaten;
import com.desgreen.gov.database.model.TbKecamatan;
import com.desgreen.gov.database.model.TbKegiatan;
import com.desgreen.gov.database.model.TbKegiatanJenis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbKegiatanJenisRepository extends JpaRepository<TbKegiatanJenis, Integer> {
    TbKegiatanJenis findById(int id);
    List<TbKegiatanJenis> findByKode1(String kode1);

    @Query("SELECT u FROM TbKegiatanJenis u WHERE u.description LIKE :description_")
    List<TbKegiatanJenis> findByDescription(@Param("description_") String description_);


}
