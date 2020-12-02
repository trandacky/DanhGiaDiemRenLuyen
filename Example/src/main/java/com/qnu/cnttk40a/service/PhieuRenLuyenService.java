package com.qnu.cnttk40a.service;

import com.qnu.cnttk40a.service.dto.PhieuRenLuyenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.qnu.cnttk40a.domain.PhieuRenLuyen}.
 */
public interface PhieuRenLuyenService {

    /**
     * Save a phieuRenLuyen.
     *
     * @param phieuRenLuyenDTO the entity to save.
     * @return the persisted entity.
     */
    PhieuRenLuyenDTO save(PhieuRenLuyenDTO phieuRenLuyenDTO);

    /**
     * Get all the phieuRenLuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PhieuRenLuyenDTO> findAll(Pageable pageable);


    /**
     * Get the "id" phieuRenLuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PhieuRenLuyenDTO> findOne(Long id);

    /**
     * Delete the "id" phieuRenLuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the phieuRenLuyen corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PhieuRenLuyenDTO> search(String query, Pageable pageable);
}
