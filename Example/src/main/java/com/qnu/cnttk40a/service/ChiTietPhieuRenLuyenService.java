package com.qnu.cnttk40a.service;

import com.qnu.cnttk40a.service.dto.ChiTietPhieuRenLuyenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.qnu.cnttk40a.domain.ChiTietPhieuRenLuyen}.
 */
public interface ChiTietPhieuRenLuyenService {

    /**
     * Save a chiTietPhieuRenLuyen.
     *
     * @param chiTietPhieuRenLuyenDTO the entity to save.
     * @return the persisted entity.
     */
    ChiTietPhieuRenLuyenDTO save(ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO);

    /**
     * Get all the chiTietPhieuRenLuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChiTietPhieuRenLuyenDTO> findAll(Pageable pageable);
    /**
     * Get all the ChiTietPhieuRenLuyenDTO where PhieuRenLuyen is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ChiTietPhieuRenLuyenDTO> findAllWherePhieuRenLuyenIsNull();


    /**
     * Get the "id" chiTietPhieuRenLuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChiTietPhieuRenLuyenDTO> findOne(Long id);

    /**
     * Delete the "id" chiTietPhieuRenLuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the chiTietPhieuRenLuyen corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChiTietPhieuRenLuyenDTO> search(String query, Pageable pageable);
}
