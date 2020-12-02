package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.PhieuRenLuyenApp;
import com.qnu.cnttk40a.domain.BoCauHoi;
import com.qnu.cnttk40a.repository.BoCauHoiRepository;
import com.qnu.cnttk40a.repository.search.BoCauHoiSearchRepository;
import com.qnu.cnttk40a.service.BoCauHoiService;
import com.qnu.cnttk40a.service.dto.BoCauHoiDTO;
import com.qnu.cnttk40a.service.mapper.BoCauHoiMapper;

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
 * Integration tests for the {@link BoCauHoiResource} REST controller.
 */
@SpringBootTest(classes = PhieuRenLuyenApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class BoCauHoiResourceIT {

    private static final String DEFAULT_TEN_BO_CAU_HOI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_BO_CAU_HOI = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TINH_TRANG = false;
    private static final Boolean UPDATED_TINH_TRANG = true;

    @Autowired
    private BoCauHoiRepository boCauHoiRepository;

    @Autowired
    private BoCauHoiMapper boCauHoiMapper;

    @Autowired
    private BoCauHoiService boCauHoiService;

    /**
     * This repository is mocked in the com.qnu.cnttk40a.repository.search test package.
     *
     * @see com.qnu.cnttk40a.repository.search.BoCauHoiSearchRepositoryMockConfiguration
     */
    @Autowired
    private BoCauHoiSearchRepository mockBoCauHoiSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBoCauHoiMockMvc;

    private BoCauHoi boCauHoi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BoCauHoi createEntity(EntityManager em) {
        BoCauHoi boCauHoi = new BoCauHoi()
            .tenBoCauHoi(DEFAULT_TEN_BO_CAU_HOI)
            .tinhTrang(DEFAULT_TINH_TRANG);
        return boCauHoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BoCauHoi createUpdatedEntity(EntityManager em) {
        BoCauHoi boCauHoi = new BoCauHoi()
            .tenBoCauHoi(UPDATED_TEN_BO_CAU_HOI)
            .tinhTrang(UPDATED_TINH_TRANG);
        return boCauHoi;
    }

    @BeforeEach
    public void initTest() {
        boCauHoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createBoCauHoi() throws Exception {
        int databaseSizeBeforeCreate = boCauHoiRepository.findAll().size();
        // Create the BoCauHoi
        BoCauHoiDTO boCauHoiDTO = boCauHoiMapper.toDto(boCauHoi);
        restBoCauHoiMockMvc.perform(post("/api/bo-cau-hois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(boCauHoiDTO)))
            .andExpect(status().isCreated());

        // Validate the BoCauHoi in the database
        List<BoCauHoi> boCauHoiList = boCauHoiRepository.findAll();
        assertThat(boCauHoiList).hasSize(databaseSizeBeforeCreate + 1);
        BoCauHoi testBoCauHoi = boCauHoiList.get(boCauHoiList.size() - 1);
        assertThat(testBoCauHoi.getTenBoCauHoi()).isEqualTo(DEFAULT_TEN_BO_CAU_HOI);
        assertThat(testBoCauHoi.isTinhTrang()).isEqualTo(DEFAULT_TINH_TRANG);

        // Validate the BoCauHoi in Elasticsearch
        verify(mockBoCauHoiSearchRepository, times(1)).save(testBoCauHoi);
    }

    @Test
    @Transactional
    public void createBoCauHoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = boCauHoiRepository.findAll().size();

        // Create the BoCauHoi with an existing ID
        boCauHoi.setId(1L);
        BoCauHoiDTO boCauHoiDTO = boCauHoiMapper.toDto(boCauHoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoCauHoiMockMvc.perform(post("/api/bo-cau-hois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(boCauHoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BoCauHoi in the database
        List<BoCauHoi> boCauHoiList = boCauHoiRepository.findAll();
        assertThat(boCauHoiList).hasSize(databaseSizeBeforeCreate);

        // Validate the BoCauHoi in Elasticsearch
        verify(mockBoCauHoiSearchRepository, times(0)).save(boCauHoi);
    }


    @Test
    @Transactional
    public void getAllBoCauHois() throws Exception {
        // Initialize the database
        boCauHoiRepository.saveAndFlush(boCauHoi);

        // Get all the boCauHoiList
        restBoCauHoiMockMvc.perform(get("/api/bo-cau-hois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boCauHoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenBoCauHoi").value(hasItem(DEFAULT_TEN_BO_CAU_HOI)))
            .andExpect(jsonPath("$.[*].tinhTrang").value(hasItem(DEFAULT_TINH_TRANG.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getBoCauHoi() throws Exception {
        // Initialize the database
        boCauHoiRepository.saveAndFlush(boCauHoi);

        // Get the boCauHoi
        restBoCauHoiMockMvc.perform(get("/api/bo-cau-hois/{id}", boCauHoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(boCauHoi.getId().intValue()))
            .andExpect(jsonPath("$.tenBoCauHoi").value(DEFAULT_TEN_BO_CAU_HOI))
            .andExpect(jsonPath("$.tinhTrang").value(DEFAULT_TINH_TRANG.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBoCauHoi() throws Exception {
        // Get the boCauHoi
        restBoCauHoiMockMvc.perform(get("/api/bo-cau-hois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBoCauHoi() throws Exception {
        // Initialize the database
        boCauHoiRepository.saveAndFlush(boCauHoi);

        int databaseSizeBeforeUpdate = boCauHoiRepository.findAll().size();

        // Update the boCauHoi
        BoCauHoi updatedBoCauHoi = boCauHoiRepository.findById(boCauHoi.getId()).get();
        // Disconnect from session so that the updates on updatedBoCauHoi are not directly saved in db
        em.detach(updatedBoCauHoi);
        updatedBoCauHoi
            .tenBoCauHoi(UPDATED_TEN_BO_CAU_HOI)
            .tinhTrang(UPDATED_TINH_TRANG);
        BoCauHoiDTO boCauHoiDTO = boCauHoiMapper.toDto(updatedBoCauHoi);

        restBoCauHoiMockMvc.perform(put("/api/bo-cau-hois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(boCauHoiDTO)))
            .andExpect(status().isOk());

        // Validate the BoCauHoi in the database
        List<BoCauHoi> boCauHoiList = boCauHoiRepository.findAll();
        assertThat(boCauHoiList).hasSize(databaseSizeBeforeUpdate);
        BoCauHoi testBoCauHoi = boCauHoiList.get(boCauHoiList.size() - 1);
        assertThat(testBoCauHoi.getTenBoCauHoi()).isEqualTo(UPDATED_TEN_BO_CAU_HOI);
        assertThat(testBoCauHoi.isTinhTrang()).isEqualTo(UPDATED_TINH_TRANG);

        // Validate the BoCauHoi in Elasticsearch
        verify(mockBoCauHoiSearchRepository, times(1)).save(testBoCauHoi);
    }

    @Test
    @Transactional
    public void updateNonExistingBoCauHoi() throws Exception {
        int databaseSizeBeforeUpdate = boCauHoiRepository.findAll().size();

        // Create the BoCauHoi
        BoCauHoiDTO boCauHoiDTO = boCauHoiMapper.toDto(boCauHoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoCauHoiMockMvc.perform(put("/api/bo-cau-hois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(boCauHoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BoCauHoi in the database
        List<BoCauHoi> boCauHoiList = boCauHoiRepository.findAll();
        assertThat(boCauHoiList).hasSize(databaseSizeBeforeUpdate);

        // Validate the BoCauHoi in Elasticsearch
        verify(mockBoCauHoiSearchRepository, times(0)).save(boCauHoi);
    }

    @Test
    @Transactional
    public void deleteBoCauHoi() throws Exception {
        // Initialize the database
        boCauHoiRepository.saveAndFlush(boCauHoi);

        int databaseSizeBeforeDelete = boCauHoiRepository.findAll().size();

        // Delete the boCauHoi
        restBoCauHoiMockMvc.perform(delete("/api/bo-cau-hois/{id}", boCauHoi.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BoCauHoi> boCauHoiList = boCauHoiRepository.findAll();
        assertThat(boCauHoiList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the BoCauHoi in Elasticsearch
        verify(mockBoCauHoiSearchRepository, times(1)).deleteById(boCauHoi.getId());
    }

    @Test
    @Transactional
    public void searchBoCauHoi() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        boCauHoiRepository.saveAndFlush(boCauHoi);
        when(mockBoCauHoiSearchRepository.search(queryStringQuery("id:" + boCauHoi.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(boCauHoi), PageRequest.of(0, 1), 1));

        // Search the boCauHoi
        restBoCauHoiMockMvc.perform(get("/api/_search/bo-cau-hois?query=id:" + boCauHoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boCauHoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenBoCauHoi").value(hasItem(DEFAULT_TEN_BO_CAU_HOI)))
            .andExpect(jsonPath("$.[*].tinhTrang").value(hasItem(DEFAULT_TINH_TRANG.booleanValue())));
    }
}
