package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.service.PhieuRenLuyenService;
import com.qnu.cnttk40a.web.rest.errors.BadRequestAlertException;
import com.qnu.cnttk40a.service.dto.PhieuRenLuyenDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.qnu.cnttk40a.domain.PhieuRenLuyen}.
 */
@RestController
@RequestMapping("/api")
public class PhieuRenLuyenResource {

    private final Logger log = LoggerFactory.getLogger(PhieuRenLuyenResource.class);

    private static final String ENTITY_NAME = "phieuRenLuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PhieuRenLuyenService phieuRenLuyenService;

    public PhieuRenLuyenResource(PhieuRenLuyenService phieuRenLuyenService) {
        this.phieuRenLuyenService = phieuRenLuyenService;
    }

    /**
     * {@code POST  /phieu-ren-luyens} : Create a new phieuRenLuyen.
     *
     * @param phieuRenLuyenDTO the phieuRenLuyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new phieuRenLuyenDTO, or with status {@code 400 (Bad Request)} if the phieuRenLuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/phieu-ren-luyens")
    public ResponseEntity<PhieuRenLuyenDTO> createPhieuRenLuyen(@RequestBody PhieuRenLuyenDTO phieuRenLuyenDTO) throws URISyntaxException {
        log.debug("REST request to save PhieuRenLuyen : {}", phieuRenLuyenDTO);
        if (phieuRenLuyenDTO.getId() != null) {
            throw new BadRequestAlertException("A new phieuRenLuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhieuRenLuyenDTO result = phieuRenLuyenService.save(phieuRenLuyenDTO);
        return ResponseEntity.created(new URI("/api/phieu-ren-luyens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /phieu-ren-luyens} : Updates an existing phieuRenLuyen.
     *
     * @param phieuRenLuyenDTO the phieuRenLuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phieuRenLuyenDTO,
     * or with status {@code 400 (Bad Request)} if the phieuRenLuyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the phieuRenLuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/phieu-ren-luyens")
    public ResponseEntity<PhieuRenLuyenDTO> updatePhieuRenLuyen(@RequestBody PhieuRenLuyenDTO phieuRenLuyenDTO) throws URISyntaxException {
        log.debug("REST request to update PhieuRenLuyen : {}", phieuRenLuyenDTO);
        if (phieuRenLuyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhieuRenLuyenDTO result = phieuRenLuyenService.save(phieuRenLuyenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, phieuRenLuyenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /phieu-ren-luyens} : get all the phieuRenLuyens.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of phieuRenLuyens in body.
     */
    @GetMapping("/phieu-ren-luyens")
    public ResponseEntity<List<PhieuRenLuyenDTO>> getAllPhieuRenLuyens(Pageable pageable) {
        log.debug("REST request to get a page of PhieuRenLuyens");
        Page<PhieuRenLuyenDTO> page = phieuRenLuyenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /phieu-ren-luyens/:id} : get the "id" phieuRenLuyen.
     *
     * @param id the id of the phieuRenLuyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the phieuRenLuyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/phieu-ren-luyens/{id}")
    public ResponseEntity<PhieuRenLuyenDTO> getPhieuRenLuyen(@PathVariable Long id) {
        log.debug("REST request to get PhieuRenLuyen : {}", id);
        Optional<PhieuRenLuyenDTO> phieuRenLuyenDTO = phieuRenLuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(phieuRenLuyenDTO);
    }

    /**
     * {@code DELETE  /phieu-ren-luyens/:id} : delete the "id" phieuRenLuyen.
     *
     * @param id the id of the phieuRenLuyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/phieu-ren-luyens/{id}")
    public ResponseEntity<Void> deletePhieuRenLuyen(@PathVariable Long id) {
        log.debug("REST request to delete PhieuRenLuyen : {}", id);
        phieuRenLuyenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/phieu-ren-luyens?query=:query} : search for the phieuRenLuyen corresponding
     * to the query.
     *
     * @param query the query of the phieuRenLuyen search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/phieu-ren-luyens")
    public ResponseEntity<List<PhieuRenLuyenDTO>> searchPhieuRenLuyens(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PhieuRenLuyens for query {}", query);
        Page<PhieuRenLuyenDTO> page = phieuRenLuyenService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
