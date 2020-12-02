package com.qnu.cnttk40a.service.impl;

import com.qnu.cnttk40a.service.TongDiemService;
import com.qnu.cnttk40a.domain.TongDiem;
import com.qnu.cnttk40a.repository.TongDiemRepository;
import com.qnu.cnttk40a.repository.search.TongDiemSearchRepository;
import com.qnu.cnttk40a.service.dto.TongDiemDTO;
import com.qnu.cnttk40a.service.mapper.TongDiemMapper;
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
 * Service Implementation for managing {@link TongDiem}.
 */
@Service
@Transactional
public class TongDiemServiceImpl implements TongDiemService {

    private final Logger log = LoggerFactory.getLogger(TongDiemServiceImpl.class);

    private final TongDiemRepository tongDiemRepository;

    private final TongDiemMapper tongDiemMapper;

    private final TongDiemSearchRepository tongDiemSearchRepository;

    public TongDiemServiceImpl(TongDiemRepository tongDiemRepository, TongDiemMapper tongDiemMapper, TongDiemSearchRepository tongDiemSearchRepository) {
        this.tongDiemRepository = tongDiemRepository;
        this.tongDiemMapper = tongDiemMapper;
        this.tongDiemSearchRepository = tongDiemSearchRepository;
    }

    @Override
    public TongDiemDTO save(TongDiemDTO tongDiemDTO) {
        log.debug("Request to save TongDiem : {}", tongDiemDTO);
        TongDiem tongDiem = tongDiemMapper.toEntity(tongDiemDTO);
        tongDiem = tongDiemRepository.save(tongDiem);
        TongDiemDTO result = tongDiemMapper.toDto(tongDiem);
        tongDiemSearchRepository.save(tongDiem);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TongDiemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TongDiems");
        return tongDiemRepository.findAll(pageable)
            .map(tongDiemMapper::toDto);
    }



    /**
     *  Get all the tongDiems where PhieuRenLuyen is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<TongDiemDTO> findAllWherePhieuRenLuyenIsNull() {
        log.debug("Request to get all tongDiems where PhieuRenLuyen is null");
        return StreamSupport
            .stream(tongDiemRepository.findAll().spliterator(), false)
            .filter(tongDiem -> tongDiem.getPhieuRenLuyen() == null)
            .map(tongDiemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TongDiemDTO> findOne(Long id) {
        log.debug("Request to get TongDiem : {}", id);
        return tongDiemRepository.findById(id)
            .map(tongDiemMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TongDiem : {}", id);
        tongDiemRepository.deleteById(id);
        tongDiemSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TongDiemDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TongDiems for query {}", query);
        return tongDiemSearchRepository.search(queryStringQuery(query), pageable)
            .map(tongDiemMapper::toDto);
    }
}
