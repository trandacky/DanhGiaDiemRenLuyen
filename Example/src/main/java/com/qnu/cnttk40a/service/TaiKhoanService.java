package com.qnu.cnttk40a.service;

import com.qnu.cnttk40a.service.dto.TaiKhoanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.qnu.cnttk40a.domain.TaiKhoan}.
 */
public interface TaiKhoanService {

    /**
     * Save a taiKhoan.
     *
     * @param taiKhoanDTO the entity to save.
     * @return the persisted entity.
     */
    TaiKhoanDTO save(TaiKhoanDTO taiKhoanDTO);

    /**
     * Get all the taiKhoans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaiKhoanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" taiKhoan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaiKhoanDTO> findOne(Long id);

    /**
     * Delete the "id" taiKhoan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the taiKhoan corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaiKhoanDTO> search(String query, Pageable pageable);
}
