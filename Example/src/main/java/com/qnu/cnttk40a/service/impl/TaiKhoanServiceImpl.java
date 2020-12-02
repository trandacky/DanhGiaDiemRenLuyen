package com.qnu.cnttk40a.service.impl;

import com.qnu.cnttk40a.service.TaiKhoanService;
import com.qnu.cnttk40a.domain.TaiKhoan;
import com.qnu.cnttk40a.repository.TaiKhoanRepository;
import com.qnu.cnttk40a.repository.search.TaiKhoanSearchRepository;
import com.qnu.cnttk40a.service.dto.TaiKhoanDTO;
import com.qnu.cnttk40a.service.mapper.TaiKhoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TaiKhoan}.
 */
@Service
@Transactional
public class TaiKhoanServiceImpl implements TaiKhoanService {

    private final Logger log = LoggerFactory.getLogger(TaiKhoanServiceImpl.class);

    private final TaiKhoanRepository taiKhoanRepository;

    private final TaiKhoanMapper taiKhoanMapper;

    private final TaiKhoanSearchRepository taiKhoanSearchRepository;

    public TaiKhoanServiceImpl(TaiKhoanRepository taiKhoanRepository, TaiKhoanMapper taiKhoanMapper, TaiKhoanSearchRepository taiKhoanSearchRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.taiKhoanMapper = taiKhoanMapper;
        this.taiKhoanSearchRepository = taiKhoanSearchRepository;
    }

    @Override
    public TaiKhoanDTO save(TaiKhoanDTO taiKhoanDTO) {
        log.debug("Request to save TaiKhoan : {}", taiKhoanDTO);
        TaiKhoan taiKhoan = taiKhoanMapper.toEntity(taiKhoanDTO);
        taiKhoan = taiKhoanRepository.save(taiKhoan);
        TaiKhoanDTO result = taiKhoanMapper.toDto(taiKhoan);
        taiKhoanSearchRepository.save(taiKhoan);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaiKhoanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaiKhoans");
        return taiKhoanRepository.findAll(pageable)
            .map(taiKhoanMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TaiKhoanDTO> findOne(Long id) {
        log.debug("Request to get TaiKhoan : {}", id);
        return taiKhoanRepository.findById(id)
            .map(taiKhoanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaiKhoan : {}", id);
        taiKhoanRepository.deleteById(id);
        taiKhoanSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaiKhoanDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TaiKhoans for query {}", query);
        return taiKhoanSearchRepository.search(queryStringQuery(query), pageable)
            .map(taiKhoanMapper::toDto);
    }
}
