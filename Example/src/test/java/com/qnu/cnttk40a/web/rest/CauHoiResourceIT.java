package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.PhieuRenLuyenApp;
import com.qnu.cnttk40a.domain.CauHoi;
import com.qnu.cnttk40a.repository.CauHoiRepository;
import com.qnu.cnttk40a.repository.search.CauHoiSearchRepository;
import com.qnu.cnttk40a.service.CauHoiService;
import com.qnu.cnttk40a.service.dto.CauHoiDTO;
import com.qnu.cnttk40a.service.mapper.CauHoiMapper;

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
 * Integration tests for the {@link CauHoiResource} REST controller.
 */
@SpringBootTest(classes = PhieuRenLuyenApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class CauHoiResourceIT {

    private static final String DEFAULT_TEN_CAU_HOI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CAU_HOI = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIEM_TOI_DA = 1;
    private static final Integer UPDATED_DIEM_TOI_DA = 2;

    private static final Boolean DEFAULT_TINH_TRANG = false;
    private static final Boolean UPDATED_TINH_TRANG = true;

    @Autowired
    private CauHoiRepository cauHoiRepository;

    @Autowired
    private CauHoiMapper cauHoiMapper;

    @Autowired
    private CauHoiService cauHoiService;

    /**
     * This repository is mocked in the com.qnu.cnttk40a.repository.search test package.
     *
     * @see com.qnu.cnttk40a.repository.search.CauHoiSearchRepositoryMockConfiguration
     */
    @Autowired
    private CauHoiSearchRepository mockCauHoiSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCauHoiMockMvc;

    private CauHoi cauHoi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHoi createEntity(EntityManager em) {
        CauHoi cauHoi = new CauHoi()
            .tenCauHoi(DEFAULT_TEN_CAU_HOI)
            .diemToiDa(DEFAULT_DIEM_TOI_DA)
            .tinhTrang(DEFAULT_TINH_TRANG);
        return cauHoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHoi createUpdatedEntity(EntityManager em) {
        CauHoi cauHoi = new CauHoi()
            .tenCauHoi(UPDATED_TEN_CAU_HOI)
            .diemToiDa(UPDATED_DIEM_TOI_DA)
            .tinhTrang(UPDATED_TINH_TRANG);
        return cauHoi;
    }

    @BeforeEach
    public void initTest() {
        cauHoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createCauHoi() throws Exception {
        int databaseSizeBeforeCreate = cauHoiRepository.findAll().size();
        // Create the CauHoi
        CauHoiDTO cauHoiDTO = cauHoiMapper.toDto(cauHoi);
        restCauHoiMockMvc.perform(post("/api/cau-hois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cauHoiDTO)))
            .andExpect(status().isCreated());

        // Validate the CauHoi in the database
        List<CauHoi> cauHoiList = cauHoiRepository.findAll();
        assertThat(cauHoiList).hasSize(databaseSizeBeforeCreate + 1);
        CauHoi testCauHoi = cauHoiList.get(cauHoiList.size() - 1);
        assertThat(testCauHoi.getTenCauHoi()).isEqualTo(DEFAULT_TEN_CAU_HOI);
        assertThat(testCauHoi.getDiemToiDa()).isEqualTo(DEFAULT_DIEM_TOI_DA);
        assertThat(testCauHoi.isTinhTrang()).isEqualTo(DEFAULT_TINH_TRANG);

        // Validate the CauHoi in Elasticsearch
        verify(mockCauHoiSearchRepository, times(1)).save(testCauHoi);
    }

    @Test
    @Transactional
    public void createCauHoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cauHoiRepository.findAll().size();

        // Create the CauHoi with an existing ID
        cauHoi.setId(1L);
        CauHoiDTO cauHoiDTO = cauHoiMapper.toDto(cauHoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCauHoiMockMvc.perform(post("/api/cau-hois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cauHoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CauHoi in the database
        List<CauHoi> cauHoiList = cauHoiRepository.findAll();
        assertThat(cauHoiList).hasSize(databaseSizeBeforeCreate);

        // Validate the CauHoi in Elasticsearch
        verify(mockCauHoiSearchRepository, times(0)).save(cauHoi);
    }


    @Test
    @Transactional
    public void getAllCauHois() throws Exception {
        // Initialize the database
        cauHoiRepository.saveAndFlush(cauHoi);

        // Get all the cauHoiList
        restCauHoiMockMvc.perform(get("/api/cau-hois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cauHoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenCauHoi").value(hasItem(DEFAULT_TEN_CAU_HOI)))
            .andExpect(jsonPath("$.[*].diemToiDa").value(hasItem(DEFAULT_DIEM_TOI_DA)))
            .andExpect(jsonPath("$.[*].tinhTrang").value(hasItem(DEFAULT_TINH_TRANG.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCauHoi() throws Exception {
        // Initialize the database
        cauHoiRepository.saveAndFlush(cauHoi);

        // Get the cauHoi
        restCauHoiMockMvc.perform(get("/api/cau-hois/{id}", cauHoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cauHoi.getId().intValue()))
            .andExpect(jsonPath("$.tenCauHoi").value(DEFAULT_TEN_CAU_HOI))
            .andExpect(jsonPath("$.diemToiDa").value(DEFAULT_DIEM_TOI_DA))
            .andExpect(jsonPath("$.tinhTrang").value(DEFAULT_TINH_TRANG.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCauHoi() throws Exception {
        // Get the cauHoi
        restCauHoiMockMvc.perform(get("/api/cau-hois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCauHoi() throws Exception {
        // Initialize the database
        cauHoiRepository.saveAndFlush(cauHoi);

        int databaseSizeBeforeUpdate = cauHoiRepository.findAll().size();

        // Update the cauHoi
        CauHoi updatedCauHoi = cauHoiRepository.findById(cauHoi.getId()).get();
        // Disconnect from session so that the updates on updatedCauHoi are not directly saved in db
        em.detach(updatedCauHoi);
        updatedCauHoi
            .tenCauHoi(UPDATED_TEN_CAU_HOI)
            .diemToiDa(UPDATED_DIEM_TOI_DA)
            .tinhTrang(UPDATED_TINH_TRANG);
        CauHoiDTO cauHoiDTO = cauHoiMapper.toDto(updatedCauHoi);

        restCauHoiMockMvc.perform(put("/api/cau-hois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cauHoiDTO)))
            .andExpect(status().isOk());

        // Validate the CauHoi in the database
        List<CauHoi> cauHoiList = cauHoiRepository.findAll();
        assertThat(cauHoiList).hasSize(databaseSizeBeforeUpdate);
        CauHoi testCauHoi = cauHoiList.get(cauHoiList.size() - 1);
        assertThat(testCauHoi.getTenCauHoi()).isEqualTo(UPDATED_TEN_CAU_HOI);
        assertThat(testCauHoi.getDiemToiDa()).isEqualTo(UPDATED_DIEM_TOI_DA);
        assertThat(testCauHoi.isTinhTrang()).isEqualTo(UPDATED_TINH_TRANG);

        // Validate the CauHoi in Elasticsearch
        verify(mockCauHoiSearchRepository, times(1)).save(testCauHoi);
    }

    @Test
    @Transactional
    public void updateNonExistingCauHoi() throws Exception {
        int databaseSizeBeforeUpdate = cauHoiRepository.findAll().size();

        // Create the CauHoi
        CauHoiDTO cauHoiDTO = cauHoiMapper.toDto(cauHoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHoiMockMvc.perform(put("/api/cau-hois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cauHoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CauHoi in the database
        List<CauHoi> cauHoiList = cauHoiRepository.findAll();
        assertThat(cauHoiList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CauHoi in Elasticsearch
        verify(mockCauHoiSearchRepository, times(0)).save(cauHoi);
    }

    @Test
    @Transactional
    public void deleteCauHoi() throws Exception {
        // Initialize the database
        cauHoiRepository.saveAndFlush(cauHoi);

        int databaseSizeBeforeDelete = cauHoiRepository.findAll().size();

        // Delete the cauHoi
        restCauHoiMockMvc.perform(delete("/api/cau-hois/{id}", cauHoi.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CauHoi> cauHoiList = cauHoiRepository.findAll();
        assertThat(cauHoiList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CauHoi in Elasticsearch
        verify(mockCauHoiSearchRepository, times(1)).deleteById(cauHoi.getId());
    }

    @Test
    @Transactional
    public void searchCauHoi() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        cauHoiRepository.saveAndFlush(cauHoi);
        when(mockCauHoiSearchRepository.search(queryStringQuery("id:" + cauHoi.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(cauHoi), PageRequest.of(0, 1), 1));

        // Search the cauHoi
        restCauHoiMockMvc.perform(get("/api/_search/cau-hois?query=id:" + cauHoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cauHoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenCauHoi").value(hasItem(DEFAULT_TEN_CAU_HOI)))
            .andExpect(jsonPath("$.[*].diemToiDa").value(hasItem(DEFAULT_DIEM_TOI_DA)))
            .andExpect(jsonPath("$.[*].tinhTrang").value(hasItem(DEFAULT_TINH_TRANG.booleanValue())));
    }
}
