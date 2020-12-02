package com.qnu.cnttk40a.service.impl;

import com.qnu.cnttk40a.service.ChiTietPhieuRenLuyenService;
import com.qnu.cnttk40a.domain.ChiTietPhieuRenLuyen;
import com.qnu.cnttk40a.repository.ChiTietPhieuRenLuyenRepository;
import com.qnu.cnttk40a.repository.search.ChiTietPhieuRenLuyenSearchRepository;
import com.qnu.cnttk40a.service.dto.ChiTietPhieuRenLuyenDTO;
import com.qnu.cnttk40a.service.mapper.ChiTietPhieuRenLuyenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ChiTietPhieuRenLuyen}.
 */
@Service
@Transactional
public class ChiTietPhieuRenLuyenServiceImpl implements ChiTietPhieuRenLuyenService {

    private final Logger log = LoggerFactory.getLogger(ChiTietPhieuRenLuyenServiceImpl.class);

    private final ChiTietPhieuRenLuyenRepository chiTietPhieuRenLuyenRepository;

    private final ChiTietPhieuRenLuyenMapper chiTietPhieuRenLuyenMapper;

    private final ChiTietPhieuRenLuyenSearchRepository chiTietPhieuRenLuyenSearchRepository;

    public ChiTietPhieuRenLuyenServiceImpl(ChiTietPhieuRenLuyenRepository chiTietPhieuRenLuyenRepository, ChiTietPhieuRenLuyenMapper chiTietPhieuRenLuyenMapper, ChiTietPhieuRenLuyenSearchRepository chiTietPhieuRenLuyenSearchRepository) {
        this.chiTietPhieuRenLuyenRepository = chiTietPhieuRenLuyenRepository;
        this.chiTietPhieuRenLuyenMapper = chiTietPhieuRenLuyenMapper;
        this.chiTietPhieuRenLuyenSearchRepository = chiTietPhieuRenLuyenSearchRepository;
    }

    @Override
    public ChiTietPhieuRenLuyenDTO save(ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO) {
        log.debug("Request to save ChiTietPhieuRenLuyen : {}", chiTietPhieuRenLuyenDTO);
        ChiTietPhieuRenLuyen chiTietPhieuRenLuyen = chiTietPhieuRenLuyenMapper.toEntity(chiTietPhieuRenLuyenDTO);
        chiTietPhieuRenLuyen = chiTietPhieuRenLuyenRepository.save(chiTietPhieuRenLuyen);
        ChiTietPhieuRenLuyenDTO result = chiTietPhieuRenLuyenMapper.toDto(chiTietPhieuRenLuyen);
        chiTietPhieuRenLuyenSearchRepository.save(chiTietPhieuRenLuyen);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChiTietPhieuRenLuyenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChiTietPhieuRenLuyens");
        return chiTietPhieuRenLuyenRepository.findAll(pageable)
            .map(chiTietPhieuRenLuyenMapper::toDto);
    }



    /**
     *  Get all the chiTietPhieuRenLuyens where PhieuRenLuyen is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ChiTietPhieuRenLuyenDTO> findAllWherePhieuRenLuyenIsNull() {
        log.debug("Request to get all chiTietPhieuRenLuyens where PhieuRenLuyen is null");
        return StreamSupport
            .stream(chiTietPhieuRenLuyenRepository.findAll().spliterator(), false)
            .filter(chiTietPhieuRenLuyen -> chiTietPhieuRenLuyen.getPhieuRenLuyen() == null)
            .map(chiTietPhieuRenLuyenMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChiTietPhieuRenLuyenDTO> findOne(Long id) {
        log.debug("Request to get ChiTietPhieuRenLuyen : {}", id);
        return chiTietPhieuRenLuyenRepository.findById(id)
            .map(chiTietPhieuRenLuyenMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChiTietPhieuRenLuyen : {}", id);
        chiTietPhieuRenLuyenRepository.deleteById(id);
        chiTietPhieuRenLuyenSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChiTietPhieuRenLuyenDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ChiTietPhieuRenLuyens for query {}", query);
        return chiTietPhieuRenLuyenSearchRepository.search(queryStringQuery(query), pageable)
            .map(chiTietPhieuRenLuyenMapper::toDto);
    }
}
