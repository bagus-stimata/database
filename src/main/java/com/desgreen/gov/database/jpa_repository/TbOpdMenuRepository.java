package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbOpdBidang;
import com.desgreen.gov.database.model.TbOpdMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbOpdMenuRepository extends JpaRepository<TbOpdMenu, Integer> {
    TbOpdMenu findById(int id);
    // List<TbOpdMenu> findByKode1(String kode1);

    // @Query("SELECT u FROM TbOpdMenu u WHERE u.description LIKE :description_")
    // List<TbOpdMenu> findByDescription(@Param("description_") String description_);

    @Query("SELECT u FROM TbOpdMenu u WHERE u.opdBean.id = :opdId")
    List<TbOpdMenu> findAllBy_OpdId(@Param("opdId") Integer opdId);

}
