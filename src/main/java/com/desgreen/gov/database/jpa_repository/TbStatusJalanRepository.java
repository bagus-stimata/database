package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbJenisJalan;
import com.desgreen.gov.database.model.TbStatusJalan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbStatusJalanRepository extends JpaRepository<TbStatusJalan, Integer> {
    TbStatusJalan findById(int id);
    List<TbStatusJalan> findByKode1(String kode1);

    // @Query("SELECT u FROM TbStatusJalan u WHERE u.description LIKE :description_")
    // List<TbStatusJalan> findByDescription(@Param("description_") String description_);


}
