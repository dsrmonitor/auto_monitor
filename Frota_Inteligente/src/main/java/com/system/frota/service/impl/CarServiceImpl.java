package com.system.frota.service.impl;

import com.system.frota.service.CarService;
import com.system.frota.domain.Car;
import com.system.frota.repository.CarRepository;
import com.system.frota.service.dto.CarDTO;
import com.system.frota.service.mapper.CarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Car.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    /**
     * Save a car.
     *
     * @param carDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CarDTO save(CarDTO carDTO) {
        log.debug("Request to save Car : {}", carDTO);
        Car car = carMapper.toEntity(carDTO);
        car = carRepository.save(car);
        return carMapper.toDto(car);
    }

    /**
     * Get all the cars.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CarDTO> findAll() {
        log.debug("Request to get all Cars");
        return carRepository.findAll().stream()
            .map(carMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one car by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CarDTO> findOne(Long id) {
        log.debug("Request to get Car : {}", id);
        return carRepository.findById(id)
            .map(carMapper::toDto);
    }

    /**
     * Delete the car by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Car : {}", id);        carRepository.deleteById(id);
    }

    @Override
    public List<CarDTO> updatePosition(List<Long> ids){
        return carMapper.toDto(carRepository.updatePosition(ids));
    }
}
