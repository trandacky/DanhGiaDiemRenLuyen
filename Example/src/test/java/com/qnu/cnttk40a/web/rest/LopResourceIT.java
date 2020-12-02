package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.PhieuRenLuyenApp;
import com.qnu.cnttk40a.domain.Lop;
import com.qnu.cnttk40a.repository.LopRepository;
import com.qnu.cnttk40a.repository.search.LopSearchRepository;
import com.qnu.cnttk40a.service.LopService;
import com.qnu.cnttk40a.service.dto.LopDTO;
import com.qnu.cnttk40a.service.mapper.LopMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LopResource} REST controller.
 */
@SpringBootTest(classes = PhieuRenLuyenApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class LopResourceIT {

    private static final String DEFAULT_TEN_LOP = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOP = "BBBBBBBBBB";

    private static final String DEFAULT_KHOA = "AAAAAAAAAA";
    private static final String UPDATED_KHOA = "BBBBBBBBBB";

    private static final Integer DEFAULT_KHOA_HOC = 1;
    private static final Integer UPDATED_KHOA_HOC = 2;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private LopMapper lopMapper;

    @Autowired
    private LopService lopService;

    /**
     * This repository is mocked in the com.qnu.cnttk40a.repository.search test package.
     *
     * @see com.qnu.cnttk40a.repository.search.LopSearchRepositoryMockConfiguration
     */
    @Autowired
    private LopSearchRepository mockLopSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLopMockMvc;

    private Lop lop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lop createEntity(EntityManager em) {
        Lop lop = new Lop()
            .tenLop(DEFAULT_TEN_LOP)
            .khoa(DEFAULT_KHOA)
            .khoaHoc(DEFAULT_KHOA_HOC);
        return lop;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lop createUpdatedEntity(EntityManager em) {
        Lop lop = new Lop()
            .tenLop(UPDATED_TEN_LOP)
            .khoa(UPDATED_KHOA)
            .khoaHoc(UPDATED_KHOA_HOC);
        return lop;
    }

    @BeforeEach
    public void initTest() {
        lop = createEntity(em);
    }

    @Test
    @Transactional
    public void createLop() throws Exception {
        int databaseSizeBeforeCreate = lopRepository.findAll().size();
        // Create the Lop
        LopDTO lopDTO = lopMapper.toDto(lop);
        restLopMockMvc.perform(post("/api/lops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lopDTO)))
            .andExpect(status().isCreated());

        // Validate the Lop in the database
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeCreate + 1);
        Lop testLop = lopList.get(lopList.size() - 1);
        assertThat(testLop.getTenLop()).isEqualTo(DEFAULT_TEN_LOP);
        assertThat(testLop.getKhoa()).isEqualTo(DEFAULT_KHOA);
        assertThat(testLop.getKhoaHoc()).isEqualTo(DEFAULT_KHOA_HOC);

        // Validate the Lop in Elasticsearch
        verify(mockLopSearchRepository, times(1)).save(testLop);
    }

    @Test
    @Transactional
    public void createLopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lopRepository.findAll().size();

        // Create the Lop with an existing ID
        lop.setId(1L);
        LopDTO lopDTO = lopMapper.toDto(lop);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLopMockMvc.perform(post("/api/lops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lop in the database
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeCreate);

        // Validate the Lop in Elasticsearch
        verify(mockLopSearchRepository, times(0)).save(lop);
    }


    @Test
    @Transactional
    public void getAllLops() throws Exception {
        // Initialize the database
        lopRepository.saveAndFlush(lop);

        // Get all the lopList
        restLopMockMvc.perform(get("/api/lops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lop.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenLop").value(hasItem(DEFAULT_TEN_LOP)))
            .andExpect(jsonPath("$.[*].khoa").value(hasItem(DEFAULT_KHOA)))
            .andExpect(jsonPath("$.[*].khoaHoc").value(hasItem(DEFAULT_KHOA_HOC)));
    }
    
    @Test
    @Transactional
    public void getLop() throws Exception {
        // Initialize the database
        lopRepository.saveAndFlush(lop);

        // Get the lop
        restLopMockMvc.perform(get("/api/lops/{id}", lop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lop.getId().intValue()))
            .andExpect(jsonPath("$.tenLop").value(DEFAULT_TEN_LOP))
            .andExpect(jsonPath("$.khoa").value(DEFAULT_KHOA))
            .andExpect(jsonPath("$.khoaHoc").value(DEFAULT_KHOA_HOC));
    }
    @Test
    @Transactional
    public void getNonExistingLop() throws Exception {
        // Get the lop
        restLopMockMvc.perform(get("/api/lops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLop() throws Exception {
        // Initialize the database
        lopRepository.saveAndFlush(lop);

        int databaseSizeBeforeUpdate = lopRepository.findAll().size();

        // Update the lop
        Lop updatedLop = lopRepository.findById(lop.getId()).get();
        // Disconnect from session so that the updates on updatedLop are not directly saved in db
        em.detach(updatedLop);
        updatedLop
            .tenLop(UPDATED_TEN_LOP)
            .khoa(UPDATED_KHOA)
            .khoaHoc(UPDATED_KHOA_HOC);
        LopDTO lopDTO = lopMapper.toDto(updatedLop);

        restLopMockMvc.perform(put("/api/lops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lopDTO)))
            .andExpect(status().isOk());

        // Validate the Lop in the database
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeUpdate);
        Lop testLop = lopList.get(lopList.size() - 1);
        assertThat(testLop.getTenLop()).isEqualTo(UPDATED_TEN_LOP);
        assertThat(testLop.getKhoa()).isEqualTo(UPDATED_KHOA);
        assertThat(testLop.getKhoaHoc()).isEqualTo(UPDATED_KHOA_HOC);

        // Validate the Lop in Elasticsearch
        verify(mockLopSearchRepository, times(1)).save(testLop);
    }

    @Test
    @Transactional
    public void updateNonExistingLop() throws Exception {
        int databaseSizeBeforeUpdate = lopRepository.findAll().size();

        // Create the Lop
        LopDTO lopDTO = lopMapper.toDto(lop);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLopMockMvc.perform(put("/api/lops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lop in the database
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Lop in Elasticsearch
        verify(mockLopSearchRepository, times(0)).save(lop);
    }

    @Test
    @Transactional
    public void deleteLop() throws Exception {
        // Initialize the database
        lopRepository.saveAndFlush(lop);

        int databaseSizeBeforeDelete = lopRepository.findAll().size();

        // Delete the lop
        restLopMockMvc.perform(delete("/api/lops/{id}", lop.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lop> lopList = lopRepository.findAll();
        assertThat(lopList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Lop in Elasticsearch
        verify(mockLopSearchRepository, times(1)).deleteById(lop.getId());
    }

    @Test
    @Transactional
    public void searchLop() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        lopRepository.saveAndFlush(lop);
        when(mockLopSearchRepository.search(queryStringQuery("id:" + lop.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(lop), PageRequest.of(0, 1), 1));

        // Search the lop
        restLopMockMvc.perform(get("/api/_search/lops?query=id:" + lop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lop.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenLop").value(hasItem(DEFAULT_TEN_LOP)))
            .andExpect(jsonPath("$.[*].khoa").value(hasItem(DEFAULT_KHOA)))
            .andExpect(jsonPath("$.[*].khoaHoc").value(hasItem(DEFAULT_KHOA_HOC)));
    }
}
