package com.desgreen.gov.database.jpa_repository;

import com.desgreen.gov.database.model.FUserRoles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRolesRepository extends JpaRepository<FUserRoles, Integer> {
    // Optional<FUser> findByEmail(String email);

    // Optional<FUser> findByUsername(String username);


}
