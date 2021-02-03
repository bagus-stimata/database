package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbKegiatanLokasi;
import com.desgreen.gov.database.model.TbKegiatanLokasiPics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbKegiatanLokasiPicsRepository extends JpaRepository<TbKegiatanLokasiPics, Long> {
    TbKegiatanLokasiPics findById(long id);
    // List<TbKegiatanLokasi> findByKode1(String kode1);

    // @Query("SELECT u FROM TbKegiatanLokasi u WHERE u.description LIKE :description_")
    // List<TbKegiatanLokasi> findByDescription(@Param("description_") String description_);

    @Query("SELECT u FROM TbKegiatanLokasiPics u WHERE u.kegiatanLokasiBean.id = :parentId")
    List<TbKegiatanLokasiPics> findAllByParentId(@Param("parentId") Long parentId);

}
