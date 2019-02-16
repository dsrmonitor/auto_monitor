package com.system.frota.repository;

import com.system.frota.domain.Car;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Car entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT car FROM Car car WHERE car.id IN :list")
    List<Car> updatePosition(@Param("list") List<Long> list);

}
