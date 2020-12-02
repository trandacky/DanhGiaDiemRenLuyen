package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.PhieuRenLuyenApp;
import com.qnu.cnttk40a.domain.TongDiem;
import com.qnu.cnttk40a.repository.TongDiemRepository;
import com.qnu.cnttk40a.repository.search.TongDiemSearchRepository;
import com.qnu.cnttk40a.service.TongDiemService;
import com.qnu.cnttk40a.service.dto.TongDiemDTO;
import com.qnu.cnttk40a.service.mapper.TongDiemMapper;

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
 * Integration tests for the {@link TongDiemResource} REST controller.
 */
@SpringBootTest(classes = PhieuRenLuyenApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TongDiemResourceIT {

    private static final Integer DEFAULT_TONG_DIEMLAN_1 = 1;
    private static final Integer UPDATED_TONG_DIEMLAN_1 = 2;

    private static final Integer DEFAULT_TONG_DIEMLAN_2 = 1;
    private static final Integer UPDATED_TONG_DIEMLAN_2 = 2;

    private static final Integer DEFAULT_TONG_DIEMLAN_3 = 1;
    private static final Integer UPDATED_TONG_DIEMLAN_3 = 2;

    @Autowired
    private TongDiemRepository tongDiemRepository;

    @Autowired
    private TongDiemMapper tongDiemMapper;

    @Autowired
    private TongDiemService tongDiemService;

    /**
     * This repository is mocked in the com.qnu.cnttk40a.repository.search test package.
     *
     * @see com.qnu.cnttk40a.repository.search.TongDiemSearchRepositoryMockConfiguration
     */
    @Autowired
    private TongDiemSearchRepository mockTongDiemSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTongDiemMockMvc;

    private TongDiem tongDiem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TongDiem createEntity(EntityManager em) {
        TongDiem tongDiem = new TongDiem()
            .tongDiemlan1(DEFAULT_TONG_DIEMLAN_1)
            .tongDiemlan2(DEFAULT_TONG_DIEMLAN_2)
            .tongDiemlan3(DEFAULT_TONG_DIEMLAN_3);
        return tongDiem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TongDiem createUpdatedEntity(EntityManager em) {
        TongDiem tongDiem = new TongDiem()
            .tongDiemlan1(UPDATED_TONG_DIEMLAN_1)
            .tongDiemlan2(UPDATED_TONG_DIEMLAN_2)
            .tongDiemlan3(UPDATED_TONG_DIEMLAN_3);
        return tongDiem;
    }

    @BeforeEach
    public void initTest() {
        tongDiem = createEntity(em);
    }

    @Test
    @Transactional
    public void createTongDiem() throws Exception {
        int databaseSizeBeforeCreate = tongDiemRepository.findAll().size();
        // Create the TongDiem
        TongDiemDTO tongDiemDTO = tongDiemMapper.toDto(tongDiem);
        restTongDiemMockMvc.perform(post("/api/tong-diems")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tongDiemDTO)))
            .andExpect(status().isCreated());

        // Validate the TongDiem in the database
        List<TongDiem> tongDiemList = tongDiemRepository.findAll();
        assertThat(tongDiemList).hasSize(databaseSizeBeforeCreate + 1);
        TongDiem testTongDiem = tongDiemList.get(tongDiemList.size() - 1);
        assertThat(testTongDiem.getTongDiemlan1()).isEqualTo(DEFAULT_TONG_DIEMLAN_1);
        assertThat(testTongDiem.getTongDiemlan2()).isEqualTo(DEFAULT_TONG_DIEMLAN_2);
        assertThat(testTongDiem.getTongDiemlan3()).isEqualTo(DEFAULT_TONG_DIEMLAN_3);

        // Validate the TongDiem in Elasticsearch
        verify(mockTongDiemSearchRepository, times(1)).save(testTongDiem);
    }

    @Test
    @Transactional
    public void createTongDiemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tongDiemRepository.findAll().size();

        // Create the TongDiem with an existing ID
        tongDiem.setId(1L);
        TongDiemDTO tongDiemDTO = tongDiemMapper.toDto(tongDiem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTongDiemMockMvc.perform(post("/api/tong-diems")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tongDiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TongDiem in the database
        List<TongDiem> tongDiemList = tongDiemRepository.findAll();
        assertThat(tongDiemList).hasSize(databaseSizeBeforeCreate);

        // Validate the TongDiem in Elasticsearch
        verify(mockTongDiemSearchRepository, times(0)).save(tongDiem);
    }


    @Test
    @Transactional
    public void getAllTongDiems() throws Exception {
        // Initialize the database
        tongDiemRepository.saveAndFlush(tongDiem);

        // Get all the tongDiemList
        restTongDiemMockMvc.perform(get("/api/tong-diems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tongDiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].tongDiemlan1").value(hasItem(DEFAULT_TONG_DIEMLAN_1)))
            .andExpect(jsonPath("$.[*].tongDiemlan2").value(hasItem(DEFAULT_TONG_DIEMLAN_2)))
            .andExpect(jsonPath("$.[*].tongDiemlan3").value(hasItem(DEFAULT_TONG_DIEMLAN_3)));
    }
    
    @Test
    @Transactional
    public void getTongDiem() throws Exception {
        // Initialize the database
        tongDiemRepository.saveAndFlush(tongDiem);

        // Get the tongDiem
        restTongDiemMockMvc.perform(get("/api/tong-diems/{id}", tongDiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tongDiem.getId().intValue()))
            .andExpect(jsonPath("$.tongDiemlan1").value(DEFAULT_TONG_DIEMLAN_1))
            .andExpect(jsonPath("$.tongDiemlan2").value(DEFAULT_TONG_DIEMLAN_2))
            .andExpect(jsonPath("$.tongDiemlan3").value(DEFAULT_TONG_DIEMLAN_3));
    }
    @Test
    @Transactional
    public void getNonExistingTongDiem() throws Exception {
        // Get the tongDiem
        restTongDiemMockMvc.perform(get("/api/tong-diems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTongDiem() throws Exception {
        // Initialize the database
        tongDiemRepository.saveAndFlush(tongDiem);

        int databaseSizeBeforeUpdate = tongDiemRepository.findAll().size();

        // Update the tongDiem
        TongDiem updatedTongDiem = tongDiemRepository.findById(tongDiem.getId()).get();
        // Disconnect from session so that the updates on updatedTongDiem are not directly saved in db
        em.detach(updatedTongDiem);
        updatedTongDiem
            .tongDiemlan1(UPDATED_TONG_DIEMLAN_1)
            .tongDiemlan2(UPDATED_TONG_DIEMLAN_2)
            .tongDiemlan3(UPDATED_TONG_DIEMLAN_3);
        TongDiemDTO tongDiemDTO = tongDiemMapper.toDto(updatedTongDiem);

        restTongDiemMockMvc.perform(put("/api/tong-diems")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tongDiemDTO)))
            .andExpect(status().isOk());

        // Validate the TongDiem in the database
        List<TongDiem> tongDiemList = tongDiemRepository.findAll();
        assertThat(tongDiemList).hasSize(databaseSizeBeforeUpdate);
        TongDiem testTongDiem = tongDiemList.get(tongDiemList.size() - 1);
        assertThat(testTongDiem.getTongDiemlan1()).isEqualTo(UPDATED_TONG_DIEMLAN_1);
        assertThat(testTongDiem.getTongDiemlan2()).isEqualTo(UPDATED_TONG_DIEMLAN_2);
        assertThat(testTongDiem.getTongDiemlan3()).isEqualTo(UPDATED_TONG_DIEMLAN_3);

        // Validate the TongDiem in Elasticsearch
        verify(mockTongDiemSearchRepository, times(1)).save(testTongDiem);
    }

    @Test
    @Transactional
    public void updateNonExistingTongDiem() throws Exception {
        int databaseSizeBeforeUpdate = tongDiemRepository.findAll().size();

        // Create the TongDiem
        TongDiemDTO tongDiemDTO = tongDiemMapper.toDto(tongDiem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTongDiemMockMvc.perform(put("/api/tong-diems")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tongDiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TongDiem in the database
        List<TongDiem> tongDiemList = tongDiemRepository.findAll();
        assertThat(tongDiemList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TongDiem in Elasticsearch
        verify(mockTongDiemSearchRepository, times(0)).save(tongDiem);
    }

    @Test
    @Transactional
    public void deleteTongDiem() throws Exception {
        // Initialize the database
        tongDiemRepository.saveAndFlush(tongDiem);

        int databaseSizeBeforeDelete = tongDiemRepository.findAll().size();

        // Delete the tongDiem
        restTongDiemMockMvc.perform(delete("/api/tong-diems/{id}", tongDiem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TongDiem> tongDiemList = tongDiemRepository.findAll();
        assertThat(tongDiemList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TongDiem in Elasticsearch
        verify(mockTongDiemSearchRepository, times(1)).deleteById(tongDiem.getId());
    }

    @Test
    @Transactional
    public void searchTongDiem() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        tongDiemRepository.saveAndFlush(tongDiem);
        when(mockTongDiemSearchRepository.search(queryStringQuery("id:" + tongDiem.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(tongDiem), PageRequest.of(0, 1), 1));

        // Search the tongDiem
        restTongDiemMockMvc.perform(get("/api/_search/tong-diems?query=id:" + tongDiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tongDiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].tongDiemlan1").value(hasItem(DEFAULT_TONG_DIEMLAN_1)))
            .andExpect(jsonPath("$.[*].tongDiemlan2").value(hasItem(DEFAULT_TONG_DIEMLAN_2)))
            .andExpect(jsonPath("$.[*].tongDiemlan3").value(hasItem(DEFAULT_TONG_DIEMLAN_3)));
    }
}
