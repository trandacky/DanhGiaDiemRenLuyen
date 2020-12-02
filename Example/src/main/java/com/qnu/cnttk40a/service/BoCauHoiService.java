package com.qnu.cnttk40a.service;

import com.qnu.cnttk40a.service.dto.BoCauHoiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.qnu.cnttk40a.domain.BoCauHoi}.
 */
public interface BoCauHoiService {

    /**
     * Save a boCauHoi.
     *
     * @param boCauHoiDTO the entity to save.
     * @return the persisted entity.
     */
    BoCauHoiDTO save(BoCauHoiDTO boCauHoiDTO);

    /**
     * Get all the boCauHois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BoCauHoiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" boCauHoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BoCauHoiDTO> findOne(Long id);

    /**
     * Delete the "id" boCauHoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the boCauHoi corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BoCauHoiDTO> search(String query, Pageable pageable);
}
