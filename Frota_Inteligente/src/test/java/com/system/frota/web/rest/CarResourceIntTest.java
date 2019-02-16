package com.system.frota.web.rest;

import com.system.frota.FrotaInteligenteApp;

import com.system.frota.domain.Car;
import com.system.frota.repository.CarRepository;
import com.system.frota.service.CarService;
import com.system.frota.service.dto.CarDTO;
import com.system.frota.service.mapper.CarMapper;
import com.system.frota.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


import static com.system.frota.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CarResource REST controller.
 *
 * @see CarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrotaInteligenteApp.class)
public class CarResourceIntTest {

    private static final String DEFAULT_LICENSE = "AAAAAAAA";
    private static final String UPDATED_LICENSE = "BBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMEI = "AAAAAAAAAA";
    private static final String UPDATED_IMEI = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CHASSI_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CHASSI_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDateTime DEFAULT_UPDATED_AT = LocalDateTime.now();
    private static final LocalDateTime UPDATED_UPDATED_AT = LocalDateTime.now(ZoneId.systemDefault());

    private static final LocalDateTime DEFAULT_DELETED_AT = LocalDateTime.now();
    private static final LocalDateTime UPDATED_DELETED_AT = LocalDateTime.now(ZoneId.systemDefault());

    private static final LocalDateTime DEFAULT_LAST_UPDATE = LocalDateTime.now();
    private static final LocalDateTime UPDATED_LAST_UPDATE = LocalDateTime.now(ZoneId.systemDefault());

    private static final String DEFAULT_LAST_WST_COORD = "AAAAAAAAAA";
    private static final String UPDATED_LAST_WST_COORD = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_SOUTH_COORD = "AAAAAAAAAA";
    private static final String UPDATED_LAST_SOUTH_COORD = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_SPEED_INFO = "AAAAAAAAAA";
    private static final String UPDATED_LAST_SPEED_INFO = "BBBBBBBBBB";

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCarMockMvc;

    private Car car;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CarResource carResource = new CarResource(carService);
        this.restCarMockMvc = MockMvcBuilders.standaloneSetup(carResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Car createEntity(EntityManager em) {
        Car car = new Car()
            .license(DEFAULT_LICENSE)
            .name(DEFAULT_NAME)
            .imei(DEFAULT_IMEI)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .chassiNumber(DEFAULT_CHASSI_NUMBER)
            .description(DEFAULT_DESCRIPTION)
            .updatedAt(DEFAULT_UPDATED_AT)
            .deletedAt(DEFAULT_DELETED_AT)
            .lastUpdate(DEFAULT_LAST_UPDATE)
            .lastWstCoord(DEFAULT_LAST_WST_COORD)
            .lastSouthCoord(DEFAULT_LAST_SOUTH_COORD)
            .lastSpeedInfo(DEFAULT_LAST_SPEED_INFO);
        return car;
    }

    @Before
    public void initTest() {
        car = createEntity(em);
    }

    @Test
    @Transactional
    public void createCar() throws Exception {
        int databaseSizeBeforeCreate = carRepository.findAll().size();

        // Create the Car
        CarDTO carDTO = carMapper.toDto(car);
        restCarMockMvc.perform(post("/api/cars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carDTO)))
            .andExpect(status().isCreated());

        // Validate the Car in the database
        List<Car> carList = carRepository.findAll();
        assertThat(carList).hasSize(databaseSizeBeforeCreate + 1);
        Car testCar = carList.get(carList.size() - 1);
        assertThat(testCar.getLicense()).isEqualTo(DEFAULT_LICENSE);
        assertThat(testCar.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCar.getImei()).isEqualTo(DEFAULT_IMEI);
        assertThat(testCar.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testCar.getChassiNumber()).isEqualTo(DEFAULT_CHASSI_NUMBER);
        assertThat(testCar.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCar.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCar.getDeletedAt()).isEqualTo(DEFAULT_DELETED_AT);
        assertThat(testCar.getLastUpdate()).isEqualTo(DEFAULT_LAST_UPDATE);
        assertThat(testCar.getLastWstCoord()).isEqualTo(DEFAULT_LAST_WST_COORD);
        assertThat(testCar.getLastSouthCoord()).isEqualTo(DEFAULT_LAST_SOUTH_COORD);
        assertThat(testCar.getLastSpeedInfo()).isEqualTo(DEFAULT_LAST_SPEED_INFO);
    }

    @Test
    @Transactional
    public void createCarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = carRepository.findAll().size();

        // Create the Car with an existing ID
        car.setId(1L);
        CarDTO carDTO = carMapper.toDto(car);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarMockMvc.perform(post("/api/cars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Car in the database
        List<Car> carList = carRepository.findAll();
        assertThat(carList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCars() throws Exception {
        // Initialize the database
        carRepository.saveAndFlush(car);

        // Get all the carList
        restCarMockMvc.perform(get("/api/cars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(car.getId().intValue())))
            .andExpect(jsonPath("$.[*].license").value(hasItem(DEFAULT_LICENSE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].imei").value(hasItem(DEFAULT_IMEI.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].chassiNumber").value(hasItem(DEFAULT_CHASSI_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].deletedAt").value(hasItem(DEFAULT_DELETED_AT.toString())))
            .andExpect(jsonPath("$.[*].lastUpdate").value(hasItem(DEFAULT_LAST_UPDATE.toString())))
            .andExpect(jsonPath("$.[*].lastWstCoord").value(hasItem(DEFAULT_LAST_WST_COORD.toString())))
            .andExpect(jsonPath("$.[*].lastSouthCoord").value(hasItem(DEFAULT_LAST_SOUTH_COORD.toString())))
            .andExpect(jsonPath("$.[*].lastSpeedInfo").value(hasItem(DEFAULT_LAST_SPEED_INFO.toString())));
    }
    
    @Test
    @Transactional
    public void getCar() throws Exception {
        // Initialize the database
        carRepository.saveAndFlush(car);

        // Get the car
        restCarMockMvc.perform(get("/api/cars/{id}", car.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(car.getId().intValue()))
            .andExpect(jsonPath("$.license").value(DEFAULT_LICENSE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.imei").value(DEFAULT_IMEI.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.chassiNumber").value(DEFAULT_CHASSI_NUMBER.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.deletedAt").value(DEFAULT_DELETED_AT.toString()))
            .andExpect(jsonPath("$.lastUpdate").value(DEFAULT_LAST_UPDATE.toString()))
            .andExpect(jsonPath("$.lastWstCoord").value(DEFAULT_LAST_WST_COORD.toString()))
            .andExpect(jsonPath("$.lastSouthCoord").value(DEFAULT_LAST_SOUTH_COORD.toString()))
            .andExpect(jsonPath("$.lastSpeedInfo").value(DEFAULT_LAST_SPEED_INFO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCar() throws Exception {
        // Get the car
        restCarMockMvc.perform(get("/api/cars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCar() throws Exception {
        // Initialize the database
        carRepository.saveAndFlush(car);

        int databaseSizeBeforeUpdate = carRepository.findAll().size();

        // Update the car
        Car updatedCar = carRepository.findById(car.getId()).get();
        // Disconnect from session so that the updates on updatedCar are not directly saved in db
        em.detach(updatedCar);
        updatedCar
            .license(UPDATED_LICENSE)
            .name(UPDATED_NAME)
            .imei(UPDATED_IMEI)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .chassiNumber(UPDATED_CHASSI_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT)
            .lastUpdate(UPDATED_LAST_UPDATE)
            .lastWstCoord(UPDATED_LAST_WST_COORD)
            .lastSouthCoord(UPDATED_LAST_SOUTH_COORD)
            .lastSpeedInfo(UPDATED_LAST_SPEED_INFO);
        CarDTO carDTO = carMapper.toDto(updatedCar);

        restCarMockMvc.perform(put("/api/cars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carDTO)))
            .andExpect(status().isOk());

        // Validate the Car in the database
        List<Car> carList = carRepository.findAll();
        assertThat(carList).hasSize(databaseSizeBeforeUpdate);
        Car testCar = carList.get(carList.size() - 1);
        assertThat(testCar.getLicense()).isEqualTo(UPDATED_LICENSE);
        assertThat(testCar.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCar.getImei()).isEqualTo(UPDATED_IMEI);
        assertThat(testCar.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testCar.getChassiNumber()).isEqualTo(UPDATED_CHASSI_NUMBER);
        assertThat(testCar.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCar.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCar.getDeletedAt()).isEqualTo(UPDATED_DELETED_AT);
        assertThat(testCar.getLastUpdate()).isEqualTo(UPDATED_LAST_UPDATE);
        assertThat(testCar.getLastWstCoord()).isEqualTo(UPDATED_LAST_WST_COORD);
        assertThat(testCar.getLastSouthCoord()).isEqualTo(UPDATED_LAST_SOUTH_COORD);
        assertThat(testCar.getLastSpeedInfo()).isEqualTo(UPDATED_LAST_SPEED_INFO);
    }

    @Test
    @Transactional
    public void updateNonExistingCar() throws Exception {
        int databaseSizeBeforeUpdate = carRepository.findAll().size();

        // Create the Car
        CarDTO carDTO = carMapper.toDto(car);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarMockMvc.perform(put("/api/cars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Car in the database
        List<Car> carList = carRepository.findAll();
        assertThat(carList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCar() throws Exception {
        // Initialize the database
        carRepository.saveAndFlush(car);

        int databaseSizeBeforeDelete = carRepository.findAll().size();

        // Delete the car
        restCarMockMvc.perform(delete("/api/cars/{id}", car.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Car> carList = carRepository.findAll();
        assertThat(carList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Car.class);
        Car car1 = new Car();
        car1.setId(1L);
        Car car2 = new Car();
        car2.setId(car1.getId());
        assertThat(car1).isEqualTo(car2);
        car2.setId(2L);
        assertThat(car1).isNotEqualTo(car2);
        car1.setId(null);
        assertThat(car1).isNotEqualTo(car2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarDTO.class);
        CarDTO carDTO1 = new CarDTO();
        carDTO1.setId(1L);
        CarDTO carDTO2 = new CarDTO();
        assertThat(carDTO1).isNotEqualTo(carDTO2);
        carDTO2.setId(carDTO1.getId());
        assertThat(carDTO1).isEqualTo(carDTO2);
        carDTO2.setId(2L);
        assertThat(carDTO1).isNotEqualTo(carDTO2);
        carDTO1.setId(null);
        assertThat(carDTO1).isNotEqualTo(carDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(carMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(carMapper.fromId(null)).isNull();
    }
}
