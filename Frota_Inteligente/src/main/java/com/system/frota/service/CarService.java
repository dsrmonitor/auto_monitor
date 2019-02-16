package com.system.frota.service;

import com.system.frota.service.dto.CarDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Car.
 */
public interface CarService {

    /**
     * Save a car.
     *
     * @param carDTO the entity to save
     * @return the persisted entity
     */
    CarDTO save(CarDTO carDTO);

    /**
     * Get all the cars.
     *
     * @return the list of entities
     */
    List<CarDTO> findAll();


    /**
     * Get the "id" car.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CarDTO> findOne(Long id);

    /**
     * Delete the "id" car.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * @param ids
     * @return
     */
    List<CarDTO> updatePosition(List<Long> ids);
}
