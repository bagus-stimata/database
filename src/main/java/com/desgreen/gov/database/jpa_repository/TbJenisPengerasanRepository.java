package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbJenisPengerasan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbJenisPengerasanRepository extends JpaRepository<TbJenisPengerasan, Integer> {
    TbJenisPengerasan findById(int id);
    List<TbJenisPengerasan> findByKode1(String kode1);

    @Query("SELECT u FROM TbJenisPengerasan u WHERE u.description LIKE :description_")
    List<TbJenisPengerasan> findByDescription(@Param("description_") String description_);


}
