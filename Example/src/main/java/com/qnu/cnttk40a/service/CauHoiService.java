package com.qnu.cnttk40a.service;

import com.qnu.cnttk40a.service.dto.CauHoiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.qnu.cnttk40a.domain.CauHoi}.
 */
public interface CauHoiService {

    /**
     * Save a cauHoi.
     *
     * @param cauHoiDTO the entity to save.
     * @return the persisted entity.
     */
    CauHoiDTO save(CauHoiDTO cauHoiDTO);

    /**
     * Get all the cauHois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CauHoiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cauHoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CauHoiDTO> findOne(Long id);

    /**
     * Delete the "id" cauHoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the cauHoi corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CauHoiDTO> search(String query, Pageable pageable);
}
