package com.system.frota.repository;

import com.system.frota.domain.Profile;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Profile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT profile.id FROM Profile profile WHERE profile.userLogin = :userLogin AND profile.userPassword = :userPassword")
    Long verifyLogin(@Param("userLogin") String userLogin, @Param("userPassword") String userPassword);

}
