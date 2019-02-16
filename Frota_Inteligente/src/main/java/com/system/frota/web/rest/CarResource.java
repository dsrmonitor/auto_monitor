package com.system.frota.web.rest;
import com.system.frota.service.CarService;
import com.system.frota.web.rest.errors.BadRequestAlertException;
import com.system.frota.web.rest.util.HeaderUtil;
import com.system.frota.service.dto.CarDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Car.
 */
@RestController
@RequestMapping("/api")
public class CarResource {

    private final Logger log = LoggerFactory.getLogger(CarResource.class);

    private static final String ENTITY_NAME = "frotaInteligenteCar";

    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    /**
     * POST  /cars : Create a new car.
     *
     * @param carDTO the carDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carDTO, or with status 400 (Bad Request) if the car has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cars")
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) throws URISyntaxException {
        log.debug("REST request to save Car : {}", carDTO);
        if (carDTO.getId() != null) {
            throw new BadRequestAlertException("A new car cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarDTO result = carService.save(carDTO);
        return ResponseEntity.created(new URI("/api/cars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cars : Updates an existing car.
     *
     * @param carDTO the carDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carDTO,
     * or with status 400 (Bad Request) if the carDTO is not valid,
     * or with status 500 (Internal Server Error) if the carDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cars")
    public ResponseEntity<CarDTO> updateCar(@Valid @RequestBody CarDTO carDTO) throws URISyntaxException {
        log.debug("REST request to update Car : {}", carDTO);
        if (carDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CarDTO result = carService.save(carDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, carDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cars : get all the cars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cars in body
     */
    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        log.debug("REST request to get all Cars");
        return carService.findAll();
    }

    /**
     * GET  /cars/:id : get the "id" car.
     *
     * @param id the id of the carDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Long id) {
        log.debug("REST request to get Car : {}", id);
        Optional<CarDTO> carDTO = carService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carDTO);
    }

    /**
     * DELETE  /cars/:id : delete the "id" car.
     *
     * @param id the id of the carDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        log.debug("REST request to delete Car : {}", id);
        carService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/cars/deleteMulti")
    public ResponseEntity<Void> deleteMultiCar(@RequestBody List<Long>ids) {
        log.debug("REST request to delete Car : {}", ids);
        for(Long id: ids) {
            carService.delete(id);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, ids.toString())).build();
    }

    @PostMapping("/cars/updated-positions")
    public ResponseEntity<List<CarDTO>> updatePosition(@RequestBody List<Long> ids){
        List<CarDTO> result = carService.updatePosition(ids);
        return ResponseEntity.ok(result);
    }
}
