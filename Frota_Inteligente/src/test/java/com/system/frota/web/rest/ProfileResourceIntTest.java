package com.system.frota.web.rest;

import com.system.frota.FrotaInteligenteApp;

import com.system.frota.domain.Profile;
import com.system.frota.repository.ProfileRepository;
import com.system.frota.service.ProfileService;
import com.system.frota.service.dto.ProfileDTO;
import com.system.frota.service.mapper.ProfileMapper;
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
import java.util.List;


import static com.system.frota.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProfileResource REST controller.
 *
 * @see ProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrotaInteligenteApp.class)
public class ProfileResourceIntTest {
//
//    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_USER_LOGIN = "AAAAAAAAAA";
//    private static final String UPDATED_USER_LOGIN = "BBBBBBBBBB";
//
//    private static final String DEFAULT_USER_PASSWORD = "AAAAAAAAAA";
//    private static final String UPDATED_USER_PASSWORD = "BBBBBBBBBB";
//
//    @Autowired
//    private ProfileRepository profileRepository;
//
//    @Autowired
//    private ProfileMapper profileMapper;
//
//    @Autowired
//    private ProfileService profileService;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private Validator validator;
//
//    private MockMvc restProfileMockMvc;
//
//    private Profile profile;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final ProfileResource profileResource = new ProfileResource(profileService);
//        this.restProfileMockMvc = MockMvcBuilders.standaloneSetup(profileResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter)
//            .setValidator(validator).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Profile createEntity(EntityManager em) {
//        Profile profile = new Profile()
//            .userName(DEFAULT_USER_NAME)
//            .userLogin(DEFAULT_USER_LOGIN)
//            .userPassword(DEFAULT_USER_PASSWORD);
//        return profile;
//    }
//
//    @Before
//    public void initTest() {
//        profile = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createProfile() throws Exception {
//        int databaseSizeBeforeCreate = profileRepository.findAll().size();
//
//        // Create the Profile
//        ProfileDTO profileDTO = profileMapper.toDto(profile);
//        restProfileMockMvc.perform(post("/api/profiles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Profile in the database
//        List<Profile> profileList = profileRepository.findAll();
//        assertThat(profileList).hasSize(databaseSizeBeforeCreate + 1);
//        Profile testProfile = profileList.get(profileList.size() - 1);
//        assertThat(testProfile.getUserName()).isEqualTo(DEFAULT_USER_NAME);
//        assertThat(testProfile.getUserLogin()).isEqualTo(DEFAULT_USER_LOGIN);
//        assertThat(testProfile.getUserPassword()).isEqualTo(DEFAULT_USER_PASSWORD);
//    }
//
//    @Test
//    @Transactional
//    public void createProfileWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = profileRepository.findAll().size();
//
//        // Create the Profile with an existing ID
//        profile.setId(1L);
//        ProfileDTO profileDTO = profileMapper.toDto(profile);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restProfileMockMvc.perform(post("/api/profiles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Profile in the database
//        List<Profile> profileList = profileRepository.findAll();
//        assertThat(profileList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void getAllProfiles() throws Exception {
//        // Initialize the database
//        profileRepository.saveAndFlush(profile);
//
//        // Get all the profileList
//        restProfileMockMvc.perform(get("/api/profiles?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(profile.getId().intValue())))
//            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
//            .andExpect(jsonPath("$.[*].userLogin").value(hasItem(DEFAULT_USER_LOGIN.toString())))
//            .andExpect(jsonPath("$.[*].userPassword").value(hasItem(DEFAULT_USER_PASSWORD.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getProfile() throws Exception {
//        // Initialize the database
//        profileRepository.saveAndFlush(profile);
//
//        // Get the profile
//        restProfileMockMvc.perform(get("/api/profiles/{id}", profile.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(profile.getId().intValue()))
//            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
//            .andExpect(jsonPath("$.userLogin").value(DEFAULT_USER_LOGIN.toString()))
//            .andExpect(jsonPath("$.userPassword").value(DEFAULT_USER_PASSWORD.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingProfile() throws Exception {
//        // Get the profile
//        restProfileMockMvc.perform(get("/api/profiles/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateProfile() throws Exception {
//        // Initialize the database
//        profileRepository.saveAndFlush(profile);
//
//        int databaseSizeBeforeUpdate = profileRepository.findAll().size();
//
//        // Update the profile
//        Profile updatedProfile = profileRepository.findById(profile.getId()).get();
//        // Disconnect from session so that the updates on updatedProfile are not directly saved in db
//        em.detach(updatedProfile);
//        updatedProfile
//            .userName(UPDATED_USER_NAME)
//            .userLogin(UPDATED_USER_LOGIN)
//            .userPassword(UPDATED_USER_PASSWORD);
//        ProfileDTO profileDTO = profileMapper.toDto(updatedProfile);
//
//        restProfileMockMvc.perform(put("/api/profiles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Profile in the database
//        List<Profile> profileList = profileRepository.findAll();
//        assertThat(profileList).hasSize(databaseSizeBeforeUpdate);
//        Profile testProfile = profileList.get(profileList.size() - 1);
//        assertThat(testProfile.getUserName()).isEqualTo(UPDATED_USER_NAME);
//        assertThat(testProfile.getUserLogin()).isEqualTo(UPDATED_USER_LOGIN);
//        assertThat(testProfile.getUserPassword()).isEqualTo(UPDATED_USER_PASSWORD);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingProfile() throws Exception {
//        int databaseSizeBeforeUpdate = profileRepository.findAll().size();
//
//        // Create the Profile
//        ProfileDTO profileDTO = profileMapper.toDto(profile);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restProfileMockMvc.perform(put("/api/profiles")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(profileDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Profile in the database
//        List<Profile> profileList = profileRepository.findAll();
//        assertThat(profileList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteProfile() throws Exception {
//        // Initialize the database
//        profileRepository.saveAndFlush(profile);
//
//        int databaseSizeBeforeDelete = profileRepository.findAll().size();
//
//        // Delete the profile
//        restProfileMockMvc.perform(delete("/api/profiles/{id}", profile.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Profile> profileList = profileRepository.findAll();
//        assertThat(profileList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Profile.class);
//        Profile profile1 = new Profile();
//        profile1.setId(1L);
//        Profile profile2 = new Profile();
//        profile2.setId(profile1.getId());
//        assertThat(profile1).isEqualTo(profile2);
//        profile2.setId(2L);
//        assertThat(profile1).isNotEqualTo(profile2);
//        profile1.setId(null);
//        assertThat(profile1).isNotEqualTo(profile2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(ProfileDTO.class);
//        ProfileDTO profileDTO1 = new ProfileDTO();
//        profileDTO1.setId(1L);
//        ProfileDTO profileDTO2 = new ProfileDTO();
//        assertThat(profileDTO1).isNotEqualTo(profileDTO2);
//        profileDTO2.setId(profileDTO1.getId());
//        assertThat(profileDTO1).isEqualTo(profileDTO2);
//        profileDTO2.setId(2L);
//        assertThat(profileDTO1).isNotEqualTo(profileDTO2);
//        profileDTO1.setId(null);
//        assertThat(profileDTO1).isNotEqualTo(profileDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(profileMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(profileMapper.fromId(null)).isNull();
//    }
}
