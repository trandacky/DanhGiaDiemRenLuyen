package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.PhieuRenLuyenApp;
import com.qnu.cnttk40a.domain.ChiTietPhieuRenLuyen;
import com.qnu.cnttk40a.repository.ChiTietPhieuRenLuyenRepository;
import com.qnu.cnttk40a.repository.search.ChiTietPhieuRenLuyenSearchRepository;
import com.qnu.cnttk40a.service.ChiTietPhieuRenLuyenService;
import com.qnu.cnttk40a.service.dto.ChiTietPhieuRenLuyenDTO;
import com.qnu.cnttk40a.service.mapper.ChiTietPhieuRenLuyenMapper;

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
 * Integration tests for the {@link ChiTietPhieuRenLuyenResource} REST controller.
 */
@SpringBootTest(classes = PhieuRenLuyenApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ChiTietPhieuRenLuyenResourceIT {

    private static final Integer DEFAULT_NAM_HOC = 1;
    private static final Integer UPDATED_NAM_HOC = 2;

    private static final Integer DEFAULT_HOC_KY = 1;
    private static final Integer UPDATED_HOC_KY = 2;

    private static final Integer DEFAULT_DIEM_LAN_1 = 1;
    private static final Integer UPDATED_DIEM_LAN_1 = 2;

    private static final Integer DEFAULT_DIEM_LAN_2 = 1;
    private static final Integer UPDATED_DIEM_LAN_2 = 2;

    private static final Integer DEFAULT_DIEM_LAN_3 = 1;
    private static final Integer UPDATED_DIEM_LAN_3 = 2;

    private static final Boolean DEFAULT_DA_DUYET_LAN_2 = false;
    private static final Boolean UPDATED_DA_DUYET_LAN_2 = true;

    private static final Boolean DEFAULT_DA_DUYET_LAN_3 = false;
    private static final Boolean UPDATED_DA_DUYET_LAN_3 = true;

    @Autowired
    private ChiTietPhieuRenLuyenRepository chiTietPhieuRenLuyenRepository;

    @Autowired
    private ChiTietPhieuRenLuyenMapper chiTietPhieuRenLuyenMapper;

    @Autowired
    private ChiTietPhieuRenLuyenService chiTietPhieuRenLuyenService;

    /**
     * This repository is mocked in the com.qnu.cnttk40a.repository.search test package.
     *
     * @see com.qnu.cnttk40a.repository.search.ChiTietPhieuRenLuyenSearchRepositoryMockConfiguration
     */
    @Autowired
    private ChiTietPhieuRenLuyenSearchRepository mockChiTietPhieuRenLuyenSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChiTietPhieuRenLuyenMockMvc;

    private ChiTietPhieuRenLuyen chiTietPhieuRenLuyen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTietPhieuRenLuyen createEntity(EntityManager em) {
        ChiTietPhieuRenLuyen chiTietPhieuRenLuyen = new ChiTietPhieuRenLuyen()
            .namHoc(DEFAULT_NAM_HOC)
            .hocKy(DEFAULT_HOC_KY)
            .diemLan1(DEFAULT_DIEM_LAN_1)
            .diemLan2(DEFAULT_DIEM_LAN_2)
            .diemLan3(DEFAULT_DIEM_LAN_3)
            .daDuyetLan2(DEFAULT_DA_DUYET_LAN_2)
            .daDuyetLan3(DEFAULT_DA_DUYET_LAN_3);
        return chiTietPhieuRenLuyen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTietPhieuRenLuyen createUpdatedEntity(EntityManager em) {
        ChiTietPhieuRenLuyen chiTietPhieuRenLuyen = new ChiTietPhieuRenLuyen()
            .namHoc(UPDATED_NAM_HOC)
            .hocKy(UPDATED_HOC_KY)
            .diemLan1(UPDATED_DIEM_LAN_1)
            .diemLan2(UPDATED_DIEM_LAN_2)
            .diemLan3(UPDATED_DIEM_LAN_3)
            .daDuyetLan2(UPDATED_DA_DUYET_LAN_2)
            .daDuyetLan3(UPDATED_DA_DUYET_LAN_3);
        return chiTietPhieuRenLuyen;
    }

    @BeforeEach
    public void initTest() {
        chiTietPhieuRenLuyen = createEntity(em);
    }

    @Test
    @Transactional
    public void createChiTietPhieuRenLuyen() throws Exception {
        int databaseSizeBeforeCreate = chiTietPhieuRenLuyenRepository.findAll().size();
        // Create the ChiTietPhieuRenLuyen
        ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO = chiTietPhieuRenLuyenMapper.toDto(chiTietPhieuRenLuyen);
        restChiTietPhieuRenLuyenMockMvc.perform(post("/api/chi-tiet-phieu-ren-luyens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chiTietPhieuRenLuyenDTO)))
            .andExpect(status().isCreated());

        // Validate the ChiTietPhieuRenLuyen in the database
        List<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyenList = chiTietPhieuRenLuyenRepository.findAll();
        assertThat(chiTietPhieuRenLuyenList).hasSize(databaseSizeBeforeCreate + 1);
        ChiTietPhieuRenLuyen testChiTietPhieuRenLuyen = chiTietPhieuRenLuyenList.get(chiTietPhieuRenLuyenList.size() - 1);
        assertThat(testChiTietPhieuRenLuyen.getNamHoc()).isEqualTo(DEFAULT_NAM_HOC);
        assertThat(testChiTietPhieuRenLuyen.getHocKy()).isEqualTo(DEFAULT_HOC_KY);
        assertThat(testChiTietPhieuRenLuyen.getDiemLan1()).isEqualTo(DEFAULT_DIEM_LAN_1);
        assertThat(testChiTietPhieuRenLuyen.getDiemLan2()).isEqualTo(DEFAULT_DIEM_LAN_2);
        assertThat(testChiTietPhieuRenLuyen.getDiemLan3()).isEqualTo(DEFAULT_DIEM_LAN_3);
        assertThat(testChiTietPhieuRenLuyen.isDaDuyetLan2()).isEqualTo(DEFAULT_DA_DUYET_LAN_2);
        assertThat(testChiTietPhieuRenLuyen.isDaDuyetLan3()).isEqualTo(DEFAULT_DA_DUYET_LAN_3);

        // Validate the ChiTietPhieuRenLuyen in Elasticsearch
        verify(mockChiTietPhieuRenLuyenSearchRepository, times(1)).save(testChiTietPhieuRenLuyen);
    }

    @Test
    @Transactional
    public void createChiTietPhieuRenLuyenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chiTietPhieuRenLuyenRepository.findAll().size();

        // Create the ChiTietPhieuRenLuyen with an existing ID
        chiTietPhieuRenLuyen.setId(1L);
        ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO = chiTietPhieuRenLuyenMapper.toDto(chiTietPhieuRenLuyen);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChiTietPhieuRenLuyenMockMvc.perform(post("/api/chi-tiet-phieu-ren-luyens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chiTietPhieuRenLuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTietPhieuRenLuyen in the database
        List<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyenList = chiTietPhieuRenLuyenRepository.findAll();
        assertThat(chiTietPhieuRenLuyenList).hasSize(databaseSizeBeforeCreate);

        // Validate the ChiTietPhieuRenLuyen in Elasticsearch
        verify(mockChiTietPhieuRenLuyenSearchRepository, times(0)).save(chiTietPhieuRenLuyen);
    }


    @Test
    @Transactional
    public void getAllChiTietPhieuRenLuyens() throws Exception {
        // Initialize the database
        chiTietPhieuRenLuyenRepository.saveAndFlush(chiTietPhieuRenLuyen);

        // Get all the chiTietPhieuRenLuyenList
        restChiTietPhieuRenLuyenMockMvc.perform(get("/api/chi-tiet-phieu-ren-luyens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiTietPhieuRenLuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].namHoc").value(hasItem(DEFAULT_NAM_HOC)))
            .andExpect(jsonPath("$.[*].hocKy").value(hasItem(DEFAULT_HOC_KY)))
            .andExpect(jsonPath("$.[*].diemLan1").value(hasItem(DEFAULT_DIEM_LAN_1)))
            .andExpect(jsonPath("$.[*].diemLan2").value(hasItem(DEFAULT_DIEM_LAN_2)))
            .andExpect(jsonPath("$.[*].diemLan3").value(hasItem(DEFAULT_DIEM_LAN_3)))
            .andExpect(jsonPath("$.[*].daDuyetLan2").value(hasItem(DEFAULT_DA_DUYET_LAN_2.booleanValue())))
            .andExpect(jsonPath("$.[*].daDuyetLan3").value(hasItem(DEFAULT_DA_DUYET_LAN_3.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getChiTietPhieuRenLuyen() throws Exception {
        // Initialize the database
        chiTietPhieuRenLuyenRepository.saveAndFlush(chiTietPhieuRenLuyen);

        // Get the chiTietPhieuRenLuyen
        restChiTietPhieuRenLuyenMockMvc.perform(get("/api/chi-tiet-phieu-ren-luyens/{id}", chiTietPhieuRenLuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chiTietPhieuRenLuyen.getId().intValue()))
            .andExpect(jsonPath("$.namHoc").value(DEFAULT_NAM_HOC))
            .andExpect(jsonPath("$.hocKy").value(DEFAULT_HOC_KY))
            .andExpect(jsonPath("$.diemLan1").value(DEFAULT_DIEM_LAN_1))
            .andExpect(jsonPath("$.diemLan2").value(DEFAULT_DIEM_LAN_2))
            .andExpect(jsonPath("$.diemLan3").value(DEFAULT_DIEM_LAN_3))
            .andExpect(jsonPath("$.daDuyetLan2").value(DEFAULT_DA_DUYET_LAN_2.booleanValue()))
            .andExpect(jsonPath("$.daDuyetLan3").value(DEFAULT_DA_DUYET_LAN_3.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingChiTietPhieuRenLuyen() throws Exception {
        // Get the chiTietPhieuRenLuyen
        restChiTietPhieuRenLuyenMockMvc.perform(get("/api/chi-tiet-phieu-ren-luyens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChiTietPhieuRenLuyen() throws Exception {
        // Initialize the database
        chiTietPhieuRenLuyenRepository.saveAndFlush(chiTietPhieuRenLuyen);

        int databaseSizeBeforeUpdate = chiTietPhieuRenLuyenRepository.findAll().size();

        // Update the chiTietPhieuRenLuyen
        ChiTietPhieuRenLuyen updatedChiTietPhieuRenLuyen = chiTietPhieuRenLuyenRepository.findById(chiTietPhieuRenLuyen.getId()).get();
        // Disconnect from session so that the updates on updatedChiTietPhieuRenLuyen are not directly saved in db
        em.detach(updatedChiTietPhieuRenLuyen);
        updatedChiTietPhieuRenLuyen
            .namHoc(UPDATED_NAM_HOC)
            .hocKy(UPDATED_HOC_KY)
            .diemLan1(UPDATED_DIEM_LAN_1)
            .diemLan2(UPDATED_DIEM_LAN_2)
            .diemLan3(UPDATED_DIEM_LAN_3)
            .daDuyetLan2(UPDATED_DA_DUYET_LAN_2)
            .daDuyetLan3(UPDATED_DA_DUYET_LAN_3);
        ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO = chiTietPhieuRenLuyenMapper.toDto(updatedChiTietPhieuRenLuyen);

        restChiTietPhieuRenLuyenMockMvc.perform(put("/api/chi-tiet-phieu-ren-luyens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chiTietPhieuRenLuyenDTO)))
            .andExpect(status().isOk());

        // Validate the ChiTietPhieuRenLuyen in the database
        List<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyenList = chiTietPhieuRenLuyenRepository.findAll();
        assertThat(chiTietPhieuRenLuyenList).hasSize(databaseSizeBeforeUpdate);
        ChiTietPhieuRenLuyen testChiTietPhieuRenLuyen = chiTietPhieuRenLuyenList.get(chiTietPhieuRenLuyenList.size() - 1);
        assertThat(testChiTietPhieuRenLuyen.getNamHoc()).isEqualTo(UPDATED_NAM_HOC);
        assertThat(testChiTietPhieuRenLuyen.getHocKy()).isEqualTo(UPDATED_HOC_KY);
        assertThat(testChiTietPhieuRenLuyen.getDiemLan1()).isEqualTo(UPDATED_DIEM_LAN_1);
        assertThat(testChiTietPhieuRenLuyen.getDiemLan2()).isEqualTo(UPDATED_DIEM_LAN_2);
        assertThat(testChiTietPhieuRenLuyen.getDiemLan3()).isEqualTo(UPDATED_DIEM_LAN_3);
        assertThat(testChiTietPhieuRenLuyen.isDaDuyetLan2()).isEqualTo(UPDATED_DA_DUYET_LAN_2);
        assertThat(testChiTietPhieuRenLuyen.isDaDuyetLan3()).isEqualTo(UPDATED_DA_DUYET_LAN_3);

        // Validate the ChiTietPhieuRenLuyen in Elasticsearch
        verify(mockChiTietPhieuRenLuyenSearchRepository, times(1)).save(testChiTietPhieuRenLuyen);
    }

    @Test
    @Transactional
    public void updateNonExistingChiTietPhieuRenLuyen() throws Exception {
        int databaseSizeBeforeUpdate = chiTietPhieuRenLuyenRepository.findAll().size();

        // Create the ChiTietPhieuRenLuyen
        ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO = chiTietPhieuRenLuyenMapper.toDto(chiTietPhieuRenLuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTietPhieuRenLuyenMockMvc.perform(put("/api/chi-tiet-phieu-ren-luyens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chiTietPhieuRenLuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTietPhieuRenLuyen in the database
        List<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyenList = chiTietPhieuRenLuyenRepository.findAll();
        assertThat(chiTietPhieuRenLuyenList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ChiTietPhieuRenLuyen in Elasticsearch
        verify(mockChiTietPhieuRenLuyenSearchRepository, times(0)).save(chiTietPhieuRenLuyen);
    }

    @Test
    @Transactional
    public void deleteChiTietPhieuRenLuyen() throws Exception {
        // Initialize the database
        chiTietPhieuRenLuyenRepository.saveAndFlush(chiTietPhieuRenLuyen);

        int databaseSizeBeforeDelete = chiTietPhieuRenLuyenRepository.findAll().size();

        // Delete the chiTietPhieuRenLuyen
        restChiTietPhieuRenLuyenMockMvc.perform(delete("/api/chi-tiet-phieu-ren-luyens/{id}", chiTietPhieuRenLuyen.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyenList = chiTietPhieuRenLuyenRepository.findAll();
        assertThat(chiTietPhieuRenLuyenList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ChiTietPhieuRenLuyen in Elasticsearch
        verify(mockChiTietPhieuRenLuyenSearchRepository, times(1)).deleteById(chiTietPhieuRenLuyen.getId());
    }

    @Test
    @Transactional
    public void searchChiTietPhieuRenLuyen() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        chiTietPhieuRenLuyenRepository.saveAndFlush(chiTietPhieuRenLuyen);
        when(mockChiTietPhieuRenLuyenSearchRepository.search(queryStringQuery("id:" + chiTietPhieuRenLuyen.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(chiTietPhieuRenLuyen), PageRequest.of(0, 1), 1));

        // Search the chiTietPhieuRenLuyen
        restChiTietPhieuRenLuyenMockMvc.perform(get("/api/_search/chi-tiet-phieu-ren-luyens?query=id:" + chiTietPhieuRenLuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiTietPhieuRenLuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].namHoc").value(hasItem(DEFAULT_NAM_HOC)))
            .andExpect(jsonPath("$.[*].hocKy").value(hasItem(DEFAULT_HOC_KY)))
            .andExpect(jsonPath("$.[*].diemLan1").value(hasItem(DEFAULT_DIEM_LAN_1)))
            .andExpect(jsonPath("$.[*].diemLan2").value(hasItem(DEFAULT_DIEM_LAN_2)))
            .andExpect(jsonPath("$.[*].diemLan3").value(hasItem(DEFAULT_DIEM_LAN_3)))
            .andExpect(jsonPath("$.[*].daDuyetLan2").value(hasItem(DEFAULT_DA_DUYET_LAN_2.booleanValue())))
            .andExpect(jsonPath("$.[*].daDuyetLan3").value(hasItem(DEFAULT_DA_DUYET_LAN_3.booleanValue())));
    }
}
