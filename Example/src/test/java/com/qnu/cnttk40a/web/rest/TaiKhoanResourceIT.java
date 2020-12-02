package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.PhieuRenLuyenApp;
import com.qnu.cnttk40a.domain.TaiKhoan;
import com.qnu.cnttk40a.repository.TaiKhoanRepository;
import com.qnu.cnttk40a.repository.search.TaiKhoanSearchRepository;
import com.qnu.cnttk40a.service.TaiKhoanService;
import com.qnu.cnttk40a.service.dto.TaiKhoanDTO;
import com.qnu.cnttk40a.service.mapper.TaiKhoanMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.qnu.cnttk40a.domain.enumeration.Quyen;
/**
 * Integration tests for the {@link TaiKhoanResource} REST controller.
 */
@SpringBootTest(classes = PhieuRenLuyenApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TaiKhoanResourceIT {

    private static final String DEFAULT_MAT_KHAU = "AAAAAAAAAA";
    private static final String UPDATED_MAT_KHAU = "BBBBBBBBBB";

    private static final Quyen DEFAULT_QUYEN = Quyen.ADMIN;
    private static final Quyen UPDATED_QUYEN = Quyen.USER;

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_THANG_NAM_SINH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THANG_NAM_SINH = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private TaiKhoanMapper taiKhoanMapper;

    @Autowired
    private TaiKhoanService taiKhoanService;

    /**
     * This repository is mocked in the com.qnu.cnttk40a.repository.search test package.
     *
     * @see com.qnu.cnttk40a.repository.search.TaiKhoanSearchRepositoryMockConfiguration
     */
    @Autowired
    private TaiKhoanSearchRepository mockTaiKhoanSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaiKhoanMockMvc;

    private TaiKhoan taiKhoan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiKhoan createEntity(EntityManager em) {
        TaiKhoan taiKhoan = new TaiKhoan()
            .matKhau(DEFAULT_MAT_KHAU)
            .quyen(DEFAULT_QUYEN)
            .ten(DEFAULT_TEN)
            .ngayThangNamSinh(DEFAULT_NGAY_THANG_NAM_SINH);
        return taiKhoan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiKhoan createUpdatedEntity(EntityManager em) {
        TaiKhoan taiKhoan = new TaiKhoan()
            .matKhau(UPDATED_MAT_KHAU)
            .quyen(UPDATED_QUYEN)
            .ten(UPDATED_TEN)
            .ngayThangNamSinh(UPDATED_NGAY_THANG_NAM_SINH);
        return taiKhoan;
    }

    @BeforeEach
    public void initTest() {
        taiKhoan = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaiKhoan() throws Exception {
        int databaseSizeBeforeCreate = taiKhoanRepository.findAll().size();
        // Create the TaiKhoan
        TaiKhoanDTO taiKhoanDTO = taiKhoanMapper.toDto(taiKhoan);
        restTaiKhoanMockMvc.perform(post("/api/tai-khoans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taiKhoanDTO)))
            .andExpect(status().isCreated());

        // Validate the TaiKhoan in the database
        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
        assertThat(taiKhoanList).hasSize(databaseSizeBeforeCreate + 1);
        TaiKhoan testTaiKhoan = taiKhoanList.get(taiKhoanList.size() - 1);
        assertThat(testTaiKhoan.getMatKhau()).isEqualTo(DEFAULT_MAT_KHAU);
        assertThat(testTaiKhoan.getQuyen()).isEqualTo(DEFAULT_QUYEN);
        assertThat(testTaiKhoan.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testTaiKhoan.getNgayThangNamSinh()).isEqualTo(DEFAULT_NGAY_THANG_NAM_SINH);

        // Validate the TaiKhoan in Elasticsearch
        verify(mockTaiKhoanSearchRepository, times(1)).save(testTaiKhoan);
    }

    @Test
    @Transactional
    public void createTaiKhoanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taiKhoanRepository.findAll().size();

        // Create the TaiKhoan with an existing ID
        taiKhoan.setId(1L);
        TaiKhoanDTO taiKhoanDTO = taiKhoanMapper.toDto(taiKhoan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaiKhoanMockMvc.perform(post("/api/tai-khoans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taiKhoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaiKhoan in the database
        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
        assertThat(taiKhoanList).hasSize(databaseSizeBeforeCreate);

        // Validate the TaiKhoan in Elasticsearch
        verify(mockTaiKhoanSearchRepository, times(0)).save(taiKhoan);
    }


    @Test
    @Transactional
    public void getAllTaiKhoans() throws Exception {
        // Initialize the database
        taiKhoanRepository.saveAndFlush(taiKhoan);

        // Get all the taiKhoanList
        restTaiKhoanMockMvc.perform(get("/api/tai-khoans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiKhoan.getId().intValue())))
            .andExpect(jsonPath("$.[*].matKhau").value(hasItem(DEFAULT_MAT_KHAU)))
            .andExpect(jsonPath("$.[*].quyen").value(hasItem(DEFAULT_QUYEN.toString())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].ngayThangNamSinh").value(hasItem(DEFAULT_NGAY_THANG_NAM_SINH.toString())));
    }
    
    @Test
    @Transactional
    public void getTaiKhoan() throws Exception {
        // Initialize the database
        taiKhoanRepository.saveAndFlush(taiKhoan);

        // Get the taiKhoan
        restTaiKhoanMockMvc.perform(get("/api/tai-khoans/{id}", taiKhoan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taiKhoan.getId().intValue()))
            .andExpect(jsonPath("$.matKhau").value(DEFAULT_MAT_KHAU))
            .andExpect(jsonPath("$.quyen").value(DEFAULT_QUYEN.toString()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.ngayThangNamSinh").value(DEFAULT_NGAY_THANG_NAM_SINH.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTaiKhoan() throws Exception {
        // Get the taiKhoan
        restTaiKhoanMockMvc.perform(get("/api/tai-khoans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaiKhoan() throws Exception {
        // Initialize the database
        taiKhoanRepository.saveAndFlush(taiKhoan);

        int databaseSizeBeforeUpdate = taiKhoanRepository.findAll().size();

        // Update the taiKhoan
        TaiKhoan updatedTaiKhoan = taiKhoanRepository.findById(taiKhoan.getId()).get();
        // Disconnect from session so that the updates on updatedTaiKhoan are not directly saved in db
        em.detach(updatedTaiKhoan);
        updatedTaiKhoan
            .matKhau(UPDATED_MAT_KHAU)
            .quyen(UPDATED_QUYEN)
            .ten(UPDATED_TEN)
            .ngayThangNamSinh(UPDATED_NGAY_THANG_NAM_SINH);
        TaiKhoanDTO taiKhoanDTO = taiKhoanMapper.toDto(updatedTaiKhoan);

        restTaiKhoanMockMvc.perform(put("/api/tai-khoans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taiKhoanDTO)))
            .andExpect(status().isOk());

        // Validate the TaiKhoan in the database
        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
        assertThat(taiKhoanList).hasSize(databaseSizeBeforeUpdate);
        TaiKhoan testTaiKhoan = taiKhoanList.get(taiKhoanList.size() - 1);
        assertThat(testTaiKhoan.getMatKhau()).isEqualTo(UPDATED_MAT_KHAU);
        assertThat(testTaiKhoan.getQuyen()).isEqualTo(UPDATED_QUYEN);
        assertThat(testTaiKhoan.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testTaiKhoan.getNgayThangNamSinh()).isEqualTo(UPDATED_NGAY_THANG_NAM_SINH);

        // Validate the TaiKhoan in Elasticsearch
        verify(mockTaiKhoanSearchRepository, times(1)).save(testTaiKhoan);
    }

    @Test
    @Transactional
    public void updateNonExistingTaiKhoan() throws Exception {
        int databaseSizeBeforeUpdate = taiKhoanRepository.findAll().size();

        // Create the TaiKhoan
        TaiKhoanDTO taiKhoanDTO = taiKhoanMapper.toDto(taiKhoan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiKhoanMockMvc.perform(put("/api/tai-khoans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taiKhoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaiKhoan in the database
        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
        assertThat(taiKhoanList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TaiKhoan in Elasticsearch
        verify(mockTaiKhoanSearchRepository, times(0)).save(taiKhoan);
    }

    @Test
    @Transactional
    public void deleteTaiKhoan() throws Exception {
        // Initialize the database
        taiKhoanRepository.saveAndFlush(taiKhoan);

        int databaseSizeBeforeDelete = taiKhoanRepository.findAll().size();

        // Delete the taiKhoan
        restTaiKhoanMockMvc.perform(delete("/api/tai-khoans/{id}", taiKhoan.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
        assertThat(taiKhoanList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TaiKhoan in Elasticsearch
        verify(mockTaiKhoanSearchRepository, times(1)).deleteById(taiKhoan.getId());
    }

    @Test
    @Transactional
    public void searchTaiKhoan() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        taiKhoanRepository.saveAndFlush(taiKhoan);
        when(mockTaiKhoanSearchRepository.search(queryStringQuery("id:" + taiKhoan.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(taiKhoan), PageRequest.of(0, 1), 1));

        // Search the taiKhoan
        restTaiKhoanMockMvc.perform(get("/api/_search/tai-khoans?query=id:" + taiKhoan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiKhoan.getId().intValue())))
            .andExpect(jsonPath("$.[*].matKhau").value(hasItem(DEFAULT_MAT_KHAU)))
            .andExpect(jsonPath("$.[*].quyen").value(hasItem(DEFAULT_QUYEN.toString())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].ngayThangNamSinh").value(hasItem(DEFAULT_NGAY_THANG_NAM_SINH.toString())));
    }
}
