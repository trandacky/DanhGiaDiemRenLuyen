package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.PhieuRenLuyenApp;
import com.qnu.cnttk40a.domain.PhieuRenLuyen;
import com.qnu.cnttk40a.repository.PhieuRenLuyenRepository;
import com.qnu.cnttk40a.repository.search.PhieuRenLuyenSearchRepository;
import com.qnu.cnttk40a.service.PhieuRenLuyenService;
import com.qnu.cnttk40a.service.dto.PhieuRenLuyenDTO;
import com.qnu.cnttk40a.service.mapper.PhieuRenLuyenMapper;

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
 * Integration tests for the {@link PhieuRenLuyenResource} REST controller.
 */
@SpringBootTest(classes = PhieuRenLuyenApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PhieuRenLuyenResourceIT {

    private static final Integer DEFAULT_NAM_HOC = 1;
    private static final Integer UPDATED_NAM_HOC = 2;

    private static final Long DEFAULT_HOC_KY = 1L;
    private static final Long UPDATED_HOC_KY = 2L;

    @Autowired
    private PhieuRenLuyenRepository phieuRenLuyenRepository;

    @Autowired
    private PhieuRenLuyenMapper phieuRenLuyenMapper;

    @Autowired
    private PhieuRenLuyenService phieuRenLuyenService;

    /**
     * This repository is mocked in the com.qnu.cnttk40a.repository.search test package.
     *
     * @see com.qnu.cnttk40a.repository.search.PhieuRenLuyenSearchRepositoryMockConfiguration
     */
    @Autowired
    private PhieuRenLuyenSearchRepository mockPhieuRenLuyenSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPhieuRenLuyenMockMvc;

    private PhieuRenLuyen phieuRenLuyen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhieuRenLuyen createEntity(EntityManager em) {
        PhieuRenLuyen phieuRenLuyen = new PhieuRenLuyen()
            .namHoc(DEFAULT_NAM_HOC)
            .hocKy(DEFAULT_HOC_KY);
        return phieuRenLuyen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhieuRenLuyen createUpdatedEntity(EntityManager em) {
        PhieuRenLuyen phieuRenLuyen = new PhieuRenLuyen()
            .namHoc(UPDATED_NAM_HOC)
            .hocKy(UPDATED_HOC_KY);
        return phieuRenLuyen;
    }

    @BeforeEach
    public void initTest() {
        phieuRenLuyen = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhieuRenLuyen() throws Exception {
        int databaseSizeBeforeCreate = phieuRenLuyenRepository.findAll().size();
        // Create the PhieuRenLuyen
        PhieuRenLuyenDTO phieuRenLuyenDTO = phieuRenLuyenMapper.toDto(phieuRenLuyen);
        restPhieuRenLuyenMockMvc.perform(post("/api/phieu-ren-luyens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(phieuRenLuyenDTO)))
            .andExpect(status().isCreated());

        // Validate the PhieuRenLuyen in the database
        List<PhieuRenLuyen> phieuRenLuyenList = phieuRenLuyenRepository.findAll();
        assertThat(phieuRenLuyenList).hasSize(databaseSizeBeforeCreate + 1);
        PhieuRenLuyen testPhieuRenLuyen = phieuRenLuyenList.get(phieuRenLuyenList.size() - 1);
        assertThat(testPhieuRenLuyen.getNamHoc()).isEqualTo(DEFAULT_NAM_HOC);
        assertThat(testPhieuRenLuyen.getHocKy()).isEqualTo(DEFAULT_HOC_KY);

        // Validate the PhieuRenLuyen in Elasticsearch
        verify(mockPhieuRenLuyenSearchRepository, times(1)).save(testPhieuRenLuyen);
    }

    @Test
    @Transactional
    public void createPhieuRenLuyenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = phieuRenLuyenRepository.findAll().size();

        // Create the PhieuRenLuyen with an existing ID
        phieuRenLuyen.setId(1L);
        PhieuRenLuyenDTO phieuRenLuyenDTO = phieuRenLuyenMapper.toDto(phieuRenLuyen);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhieuRenLuyenMockMvc.perform(post("/api/phieu-ren-luyens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(phieuRenLuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhieuRenLuyen in the database
        List<PhieuRenLuyen> phieuRenLuyenList = phieuRenLuyenRepository.findAll();
        assertThat(phieuRenLuyenList).hasSize(databaseSizeBeforeCreate);

        // Validate the PhieuRenLuyen in Elasticsearch
        verify(mockPhieuRenLuyenSearchRepository, times(0)).save(phieuRenLuyen);
    }


    @Test
    @Transactional
    public void getAllPhieuRenLuyens() throws Exception {
        // Initialize the database
        phieuRenLuyenRepository.saveAndFlush(phieuRenLuyen);

        // Get all the phieuRenLuyenList
        restPhieuRenLuyenMockMvc.perform(get("/api/phieu-ren-luyens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phieuRenLuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].namHoc").value(hasItem(DEFAULT_NAM_HOC)))
            .andExpect(jsonPath("$.[*].hocKy").value(hasItem(DEFAULT_HOC_KY.intValue())));
    }
    
    @Test
    @Transactional
    public void getPhieuRenLuyen() throws Exception {
        // Initialize the database
        phieuRenLuyenRepository.saveAndFlush(phieuRenLuyen);

        // Get the phieuRenLuyen
        restPhieuRenLuyenMockMvc.perform(get("/api/phieu-ren-luyens/{id}", phieuRenLuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(phieuRenLuyen.getId().intValue()))
            .andExpect(jsonPath("$.namHoc").value(DEFAULT_NAM_HOC))
            .andExpect(jsonPath("$.hocKy").value(DEFAULT_HOC_KY.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPhieuRenLuyen() throws Exception {
        // Get the phieuRenLuyen
        restPhieuRenLuyenMockMvc.perform(get("/api/phieu-ren-luyens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhieuRenLuyen() throws Exception {
        // Initialize the database
        phieuRenLuyenRepository.saveAndFlush(phieuRenLuyen);

        int databaseSizeBeforeUpdate = phieuRenLuyenRepository.findAll().size();

        // Update the phieuRenLuyen
        PhieuRenLuyen updatedPhieuRenLuyen = phieuRenLuyenRepository.findById(phieuRenLuyen.getId()).get();
        // Disconnect from session so that the updates on updatedPhieuRenLuyen are not directly saved in db
        em.detach(updatedPhieuRenLuyen);
        updatedPhieuRenLuyen
            .namHoc(UPDATED_NAM_HOC)
            .hocKy(UPDATED_HOC_KY);
        PhieuRenLuyenDTO phieuRenLuyenDTO = phieuRenLuyenMapper.toDto(updatedPhieuRenLuyen);

        restPhieuRenLuyenMockMvc.perform(put("/api/phieu-ren-luyens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(phieuRenLuyenDTO)))
            .andExpect(status().isOk());

        // Validate the PhieuRenLuyen in the database
        List<PhieuRenLuyen> phieuRenLuyenList = phieuRenLuyenRepository.findAll();
        assertThat(phieuRenLuyenList).hasSize(databaseSizeBeforeUpdate);
        PhieuRenLuyen testPhieuRenLuyen = phieuRenLuyenList.get(phieuRenLuyenList.size() - 1);
        assertThat(testPhieuRenLuyen.getNamHoc()).isEqualTo(UPDATED_NAM_HOC);
        assertThat(testPhieuRenLuyen.getHocKy()).isEqualTo(UPDATED_HOC_KY);

        // Validate the PhieuRenLuyen in Elasticsearch
        verify(mockPhieuRenLuyenSearchRepository, times(1)).save(testPhieuRenLuyen);
    }

    @Test
    @Transactional
    public void updateNonExistingPhieuRenLuyen() throws Exception {
        int databaseSizeBeforeUpdate = phieuRenLuyenRepository.findAll().size();

        // Create the PhieuRenLuyen
        PhieuRenLuyenDTO phieuRenLuyenDTO = phieuRenLuyenMapper.toDto(phieuRenLuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhieuRenLuyenMockMvc.perform(put("/api/phieu-ren-luyens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(phieuRenLuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhieuRenLuyen in the database
        List<PhieuRenLuyen> phieuRenLuyenList = phieuRenLuyenRepository.findAll();
        assertThat(phieuRenLuyenList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PhieuRenLuyen in Elasticsearch
        verify(mockPhieuRenLuyenSearchRepository, times(0)).save(phieuRenLuyen);
    }

    @Test
    @Transactional
    public void deletePhieuRenLuyen() throws Exception {
        // Initialize the database
        phieuRenLuyenRepository.saveAndFlush(phieuRenLuyen);

        int databaseSizeBeforeDelete = phieuRenLuyenRepository.findAll().size();

        // Delete the phieuRenLuyen
        restPhieuRenLuyenMockMvc.perform(delete("/api/phieu-ren-luyens/{id}", phieuRenLuyen.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PhieuRenLuyen> phieuRenLuyenList = phieuRenLuyenRepository.findAll();
        assertThat(phieuRenLuyenList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PhieuRenLuyen in Elasticsearch
        verify(mockPhieuRenLuyenSearchRepository, times(1)).deleteById(phieuRenLuyen.getId());
    }

    @Test
    @Transactional
    public void searchPhieuRenLuyen() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        phieuRenLuyenRepository.saveAndFlush(phieuRenLuyen);
        when(mockPhieuRenLuyenSearchRepository.search(queryStringQuery("id:" + phieuRenLuyen.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(phieuRenLuyen), PageRequest.of(0, 1), 1));

        // Search the phieuRenLuyen
        restPhieuRenLuyenMockMvc.perform(get("/api/_search/phieu-ren-luyens?query=id:" + phieuRenLuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phieuRenLuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].namHoc").value(hasItem(DEFAULT_NAM_HOC)))
            .andExpect(jsonPath("$.[*].hocKy").value(hasItem(DEFAULT_HOC_KY.intValue())));
    }
}
