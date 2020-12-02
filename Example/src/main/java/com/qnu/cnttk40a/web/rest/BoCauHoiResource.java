package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.service.BoCauHoiService;
import com.qnu.cnttk40a.web.rest.errors.BadRequestAlertException;
import com.qnu.cnttk40a.service.dto.BoCauHoiDTO;

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
 * REST controller for managing {@link com.qnu.cnttk40a.domain.BoCauHoi}.
 */
@RestController
@RequestMapping("/api")
public class BoCauHoiResource {

    private final Logger log = LoggerFactory.getLogger(BoCauHoiResource.class);

    private static final String ENTITY_NAME = "boCauHoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BoCauHoiService boCauHoiService;

    public BoCauHoiResource(BoCauHoiService boCauHoiService) {
        this.boCauHoiService = boCauHoiService;
    }

    /**
     * {@code POST  /bo-cau-hois} : Create a new boCauHoi.
     *
     * @param boCauHoiDTO the boCauHoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new boCauHoiDTO, or with status {@code 400 (Bad Request)} if the boCauHoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bo-cau-hois")
    public ResponseEntity<BoCauHoiDTO> createBoCauHoi(@RequestBody BoCauHoiDTO boCauHoiDTO) throws URISyntaxException {
        log.debug("REST request to save BoCauHoi : {}", boCauHoiDTO);
        if (boCauHoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new boCauHoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BoCauHoiDTO result = boCauHoiService.save(boCauHoiDTO);
        return ResponseEntity.created(new URI("/api/bo-cau-hois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bo-cau-hois} : Updates an existing boCauHoi.
     *
     * @param boCauHoiDTO the boCauHoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boCauHoiDTO,
     * or with status {@code 400 (Bad Request)} if the boCauHoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the boCauHoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bo-cau-hois")
    public ResponseEntity<BoCauHoiDTO> updateBoCauHoi(@RequestBody BoCauHoiDTO boCauHoiDTO) throws URISyntaxException {
        log.debug("REST request to update BoCauHoi : {}", boCauHoiDTO);
        if (boCauHoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BoCauHoiDTO result = boCauHoiService.save(boCauHoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, boCauHoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bo-cau-hois} : get all the boCauHois.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of boCauHois in body.
     */
    @GetMapping("/bo-cau-hois")
    public ResponseEntity<List<BoCauHoiDTO>> getAllBoCauHois(Pageable pageable) {
        log.debug("REST request to get a page of BoCauHois");
        Page<BoCauHoiDTO> page = boCauHoiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bo-cau-hois/:id} : get the "id" boCauHoi.
     *
     * @param id the id of the boCauHoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the boCauHoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bo-cau-hois/{id}")
    public ResponseEntity<BoCauHoiDTO> getBoCauHoi(@PathVariable Long id) {
        log.debug("REST request to get BoCauHoi : {}", id);
        Optional<BoCauHoiDTO> boCauHoiDTO = boCauHoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(boCauHoiDTO);
    }

    /**
     * {@code DELETE  /bo-cau-hois/:id} : delete the "id" boCauHoi.
     *
     * @param id the id of the boCauHoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bo-cau-hois/{id}")
    public ResponseEntity<Void> deleteBoCauHoi(@PathVariable Long id) {
        log.debug("REST request to delete BoCauHoi : {}", id);
        boCauHoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/bo-cau-hois?query=:query} : search for the boCauHoi corresponding
     * to the query.
     *
     * @param query the query of the boCauHoi search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/bo-cau-hois")
    public ResponseEntity<List<BoCauHoiDTO>> searchBoCauHois(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of BoCauHois for query {}", query);
        Page<BoCauHoiDTO> page = boCauHoiService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
