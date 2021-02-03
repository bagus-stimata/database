package com.desgreen.gov.database.jpa_repository;

import java.util.List;
import java.util.Optional;

import com.desgreen.gov.database.model.TbKabupaten;
import com.desgreen.gov.database.model.TbKecamatan;
import com.desgreen.gov.database.model.TbKegiatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface TbKegiatanRepository extends CrudRepository<TbKegiatan, Long> {
    TbKegiatan findById(long id);
    List<TbKegiatan> findByKode1(String kode1);


    @Query("SELECT u FROM TbKegiatan u WHERE u.description1 LIKE :description1_")
    List<TbKegiatan> findByDescription(@Param("description1_") String description1_);

    @Query("SELECT u FROM TbKegiatan u WHERE u.opdBean.id = :opdId")
    List<TbKegiatan> findAllByOpd(@Param("opdId") int opdId);

    @Query("SELECT u FROM TbKegiatan u WHERE u.opdBean.id = :opdId  AND u.jenisSaranaBean.id = :jenisSaprasId " )
    List<TbKegiatan> findAllByOpd(@Param("opdId") int opdId, @Param("jenisSaprasId") int jenisSaprasId);


}
