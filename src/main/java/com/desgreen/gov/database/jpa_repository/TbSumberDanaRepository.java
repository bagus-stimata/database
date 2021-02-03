package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbSumberDana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbSumberDanaRepository extends JpaRepository<TbSumberDana, Integer> {
    TbSumberDana findById(int id);
    List<TbSumberDana> findByKode1(String kode1);

    @Query("SELECT u FROM TbSumberDana u WHERE u.description LIKE :description_")
    List<TbSumberDana> findByDescription(@Param("description_") String description_);


}
