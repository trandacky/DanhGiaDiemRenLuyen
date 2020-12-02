package com.qnu.cnttk40a.service;

import com.qnu.cnttk40a.service.dto.LopDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.qnu.cnttk40a.domain.Lop}.
 */
public interface LopService {

    /**
     * Save a lop.
     *
     * @param lopDTO the entity to save.
     * @return the persisted entity.
     */
    LopDTO save(LopDTO lopDTO);

    /**
     * Get all the lops.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LopDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lop.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LopDTO> findOne(Long id);

    /**
     * Delete the "id" lop.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the lop corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LopDTO> search(String query, Pageable pageable);
}
