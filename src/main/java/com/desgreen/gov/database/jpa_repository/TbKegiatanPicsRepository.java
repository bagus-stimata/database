package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbKegiatanPics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbKegiatanPicsRepository extends JpaRepository<TbKegiatanPics, Long> {
    TbKegiatanPics findById(long id);
    // List<TbKegiatanPics> findByKode1(String kode1);

    @Query("SELECT u FROM TbKegiatanPics u WHERE u.title LIKE :title_")
    List<TbKegiatanPics> findByTitle(@Param("title_") String title_);

    @Query("SELECT u FROM TbKegiatanPics u WHERE u.description LIKE :description_")
    List<TbKegiatanPics> findByDescription(@Param("description_") String description_);


}
