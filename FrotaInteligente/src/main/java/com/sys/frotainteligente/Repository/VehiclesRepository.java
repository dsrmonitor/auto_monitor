package com.sys.frotainteligente.Repository;

import com.sys.frotainteligente.Entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
public interface VehiclesRepository extends JpaRepository<Vehicles,Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicles v SET v.name= :name, v.chassi_number= :chassi, v.description= :description, v.license= :license, v.phone_number= :phone, v.updated_at = :updated_at  WHERE v.id = :id")
    void updateVehicle(@Param("id") Long id, @Param("name") String name, @Param("chassi")String chassi, @Param("description") String description, @Param("license") String license, @Param("phone") String phon, @Param("updated_at")Date updated_at);

    @Query("SELECT v FROM Vehicles v WHERE UPPER(v.name) LIKE CONCAT('%',UPPER(:param),'%')")
    List<Vehicles> searchVehicleByParam(@Param("param") String param);
}
