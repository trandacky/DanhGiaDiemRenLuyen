package com.qnu.cnttk40a.service.impl;

import com.qnu.cnttk40a.service.BoCauHoiService;
import com.qnu.cnttk40a.domain.BoCauHoi;
import com.qnu.cnttk40a.repository.BoCauHoiRepository;
import com.qnu.cnttk40a.repository.search.BoCauHoiSearchRepository;
import com.qnu.cnttk40a.service.dto.BoCauHoiDTO;
import com.qnu.cnttk40a.service.mapper.BoCauHoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link BoCauHoi}.
 */
@Service
@Transactional
public class BoCauHoiServiceImpl implements BoCauHoiService {

    private final Logger log = LoggerFactory.getLogger(BoCauHoiServiceImpl.class);

    private final BoCauHoiRepository boCauHoiRepository;

    private final BoCauHoiMapper boCauHoiMapper;

    private final BoCauHoiSearchRepository boCauHoiSearchRepository;

    public BoCauHoiServiceImpl(BoCauHoiRepository boCauHoiRepository, BoCauHoiMapper boCauHoiMapper, BoCauHoiSearchRepository boCauHoiSearchRepository) {
        this.boCauHoiRepository = boCauHoiRepository;
        this.boCauHoiMapper = boCauHoiMapper;
        this.boCauHoiSearchRepository = boCauHoiSearchRepository;
    }

    @Override
    public BoCauHoiDTO save(BoCauHoiDTO boCauHoiDTO) {
        log.debug("Request to save BoCauHoi : {}", boCauHoiDTO);
        BoCauHoi boCauHoi = boCauHoiMapper.toEntity(boCauHoiDTO);
        boCauHoi = boCauHoiRepository.save(boCauHoi);
        BoCauHoiDTO result = boCauHoiMapper.toDto(boCauHoi);
        boCauHoiSearchRepository.save(boCauHoi);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BoCauHoiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BoCauHois");
        return boCauHoiRepository.findAll(pageable)
            .map(boCauHoiMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BoCauHoiDTO> findOne(Long id) {
        log.debug("Request to get BoCauHoi : {}", id);
        return boCauHoiRepository.findById(id)
            .map(boCauHoiMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BoCauHoi : {}", id);
        boCauHoiRepository.deleteById(id);
        boCauHoiSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BoCauHoiDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of BoCauHois for query {}", query);
        return boCauHoiSearchRepository.search(queryStringQuery(query), pageable)
            .map(boCauHoiMapper::toDto);
    }
}
