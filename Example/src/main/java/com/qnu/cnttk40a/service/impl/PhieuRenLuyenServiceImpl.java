package com.qnu.cnttk40a.service.impl;

import com.qnu.cnttk40a.service.PhieuRenLuyenService;
import com.qnu.cnttk40a.domain.PhieuRenLuyen;
import com.qnu.cnttk40a.repository.PhieuRenLuyenRepository;
import com.qnu.cnttk40a.repository.search.PhieuRenLuyenSearchRepository;
import com.qnu.cnttk40a.service.dto.PhieuRenLuyenDTO;
import com.qnu.cnttk40a.service.mapper.PhieuRenLuyenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link PhieuRenLuyen}.
 */
@Service
@Transactional
public class PhieuRenLuyenServiceImpl implements PhieuRenLuyenService {

    private final Logger log = LoggerFactory.getLogger(PhieuRenLuyenServiceImpl.class);

    private final PhieuRenLuyenRepository phieuRenLuyenRepository;

    private final PhieuRenLuyenMapper phieuRenLuyenMapper;

    private final PhieuRenLuyenSearchRepository phieuRenLuyenSearchRepository;

    public PhieuRenLuyenServiceImpl(PhieuRenLuyenRepository phieuRenLuyenRepository, PhieuRenLuyenMapper phieuRenLuyenMapper, PhieuRenLuyenSearchRepository phieuRenLuyenSearchRepository) {
        this.phieuRenLuyenRepository = phieuRenLuyenRepository;
        this.phieuRenLuyenMapper = phieuRenLuyenMapper;
        this.phieuRenLuyenSearchRepository = phieuRenLuyenSearchRepository;
    }

    @Override
    public PhieuRenLuyenDTO save(PhieuRenLuyenDTO phieuRenLuyenDTO) {
        log.debug("Request to save PhieuRenLuyen : {}", phieuRenLuyenDTO);
        PhieuRenLuyen phieuRenLuyen = phieuRenLuyenMapper.toEntity(phieuRenLuyenDTO);
        phieuRenLuyen = phieuRenLuyenRepository.save(phieuRenLuyen);
        PhieuRenLuyenDTO result = phieuRenLuyenMapper.toDto(phieuRenLuyen);
        phieuRenLuyenSearchRepository.save(phieuRenLuyen);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PhieuRenLuyenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PhieuRenLuyens");
        return phieuRenLuyenRepository.findAll(pageable)
            .map(phieuRenLuyenMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PhieuRenLuyenDTO> findOne(Long id) {
        log.debug("Request to get PhieuRenLuyen : {}", id);
        return phieuRenLuyenRepository.findById(id)
            .map(phieuRenLuyenMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PhieuRenLuyen : {}", id);
        phieuRenLuyenRepository.deleteById(id);
        phieuRenLuyenSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PhieuRenLuyenDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PhieuRenLuyens for query {}", query);
        return phieuRenLuyenSearchRepository.search(queryStringQuery(query), pageable)
            .map(phieuRenLuyenMapper::toDto);
    }
}
