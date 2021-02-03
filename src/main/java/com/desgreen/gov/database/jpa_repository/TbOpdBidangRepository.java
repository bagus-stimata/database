package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbOpdBidang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbOpdBidangRepository extends JpaRepository<TbOpdBidang, Integer> {
    TbOpdBidang findById(int id);
    List<TbOpdBidang> findByKode1(String kode1);

    @Query("SELECT u FROM TbOpdBidang u WHERE u.description LIKE :description_")
    List<TbOpdBidang> findByDescription(@Param("description_") String description_);


}
