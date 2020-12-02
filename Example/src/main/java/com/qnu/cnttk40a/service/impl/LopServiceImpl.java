package com.qnu.cnttk40a.service.impl;

import com.qnu.cnttk40a.service.LopService;
import com.qnu.cnttk40a.domain.Lop;
import com.qnu.cnttk40a.repository.LopRepository;
import com.qnu.cnttk40a.repository.search.LopSearchRepository;
import com.qnu.cnttk40a.service.dto.LopDTO;
import com.qnu.cnttk40a.service.mapper.LopMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Lop}.
 */
@Service
@Transactional
public class LopServiceImpl implements LopService {

    private final Logger log = LoggerFactory.getLogger(LopServiceImpl.class);

    private final LopRepository lopRepository;

    private final LopMapper lopMapper;

    private final LopSearchRepository lopSearchRepository;

    public LopServiceImpl(LopRepository lopRepository, LopMapper lopMapper, LopSearchRepository lopSearchRepository) {
        this.lopRepository = lopRepository;
        this.lopMapper = lopMapper;
        this.lopSearchRepository = lopSearchRepository;
    }

    @Override
    public LopDTO save(LopDTO lopDTO) {
        log.debug("Request to save Lop : {}", lopDTO);
        Lop lop = lopMapper.toEntity(lopDTO);
        lop = lopRepository.save(lop);
        LopDTO result = lopMapper.toDto(lop);
        lopSearchRepository.save(lop);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LopDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Lops");
        return lopRepository.findAll(pageable)
            .map(lopMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LopDTO> findOne(Long id) {
        log.debug("Request to get Lop : {}", id);
        return lopRepository.findById(id)
            .map(lopMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Lop : {}", id);
        lopRepository.deleteById(id);
        lopSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LopDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Lops for query {}", query);
        return lopSearchRepository.search(queryStringQuery(query), pageable)
            .map(lopMapper::toDto);
    }
}
