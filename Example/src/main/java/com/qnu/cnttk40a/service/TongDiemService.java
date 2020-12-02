package com.qnu.cnttk40a.service;

import com.qnu.cnttk40a.service.dto.TongDiemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.qnu.cnttk40a.domain.TongDiem}.
 */
public interface TongDiemService {

    /**
     * Save a tongDiem.
     *
     * @param tongDiemDTO the entity to save.
     * @return the persisted entity.
     */
    TongDiemDTO save(TongDiemDTO tongDiemDTO);

    /**
     * Get all the tongDiems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TongDiemDTO> findAll(Pageable pageable);
    /**
     * Get all the TongDiemDTO where PhieuRenLuyen is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<TongDiemDTO> findAllWherePhieuRenLuyenIsNull();


    /**
     * Get the "id" tongDiem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TongDiemDTO> findOne(Long id);

    /**
     * Delete the "id" tongDiem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the tongDiem corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TongDiemDTO> search(String query, Pageable pageable);
}
