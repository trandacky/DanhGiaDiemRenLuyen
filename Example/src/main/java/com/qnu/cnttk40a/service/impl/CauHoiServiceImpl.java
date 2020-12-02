package com.qnu.cnttk40a.service.impl;

import com.qnu.cnttk40a.service.CauHoiService;
import com.qnu.cnttk40a.domain.CauHoi;
import com.qnu.cnttk40a.repository.CauHoiRepository;
import com.qnu.cnttk40a.repository.search.CauHoiSearchRepository;
import com.qnu.cnttk40a.service.dto.CauHoiDTO;
import com.qnu.cnttk40a.service.mapper.CauHoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link CauHoi}.
 */
@Service
@Transactional
public class CauHoiServiceImpl implements CauHoiService {

    private final Logger log = LoggerFactory.getLogger(CauHoiServiceImpl.class);

    private final CauHoiRepository cauHoiRepository;

    private final CauHoiMapper cauHoiMapper;

    private final CauHoiSearchRepository cauHoiSearchRepository;

    public CauHoiServiceImpl(CauHoiRepository cauHoiRepository, CauHoiMapper cauHoiMapper, CauHoiSearchRepository cauHoiSearchRepository) {
        this.cauHoiRepository = cauHoiRepository;
        this.cauHoiMapper = cauHoiMapper;
        this.cauHoiSearchRepository = cauHoiSearchRepository;
    }

    @Override
    public CauHoiDTO save(CauHoiDTO cauHoiDTO) {
        log.debug("Request to save CauHoi : {}", cauHoiDTO);
        CauHoi cauHoi = cauHoiMapper.toEntity(cauHoiDTO);
        cauHoi = cauHoiRepository.save(cauHoi);
        CauHoiDTO result = cauHoiMapper.toDto(cauHoi);
        cauHoiSearchRepository.save(cauHoi);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CauHoiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CauHois");
        return cauHoiRepository.findAll(pageable)
            .map(cauHoiMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CauHoiDTO> findOne(Long id) {
        log.debug("Request to get CauHoi : {}", id);
        return cauHoiRepository.findById(id)
            .map(cauHoiMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CauHoi : {}", id);
        cauHoiRepository.deleteById(id);
        cauHoiSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CauHoiDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CauHois for query {}", query);
        return cauHoiSearchRepository.search(queryStringQuery(query), pageable)
            .map(cauHoiMapper::toDto);
    }
}
