package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.service.LopService;
import com.qnu.cnttk40a.web.rest.errors.BadRequestAlertException;
import com.qnu.cnttk40a.service.dto.LopDTO;

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
 * REST controller for managing {@link com.qnu.cnttk40a.domain.Lop}.
 */
@RestController
@RequestMapping("/api")
public class LopResource {

    private final Logger log = LoggerFactory.getLogger(LopResource.class);

    private static final String ENTITY_NAME = "lop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LopService lopService;

    public LopResource(LopService lopService) {
        this.lopService = lopService;
    }

    /**
     * {@code POST  /lops} : Create a new lop.
     *
     * @param lopDTO the lopDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lopDTO, or with status {@code 400 (Bad Request)} if the lop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lops")
    public ResponseEntity<LopDTO> createLop(@RequestBody LopDTO lopDTO) throws URISyntaxException {
        log.debug("REST request to save Lop : {}", lopDTO);
        if (lopDTO.getId() != null) {
            throw new BadRequestAlertException("A new lop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LopDTO result = lopService.save(lopDTO);
        return ResponseEntity.created(new URI("/api/lops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lops} : Updates an existing lop.
     *
     * @param lopDTO the lopDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lopDTO,
     * or with status {@code 400 (Bad Request)} if the lopDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lopDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lops")
    public ResponseEntity<LopDTO> updateLop(@RequestBody LopDTO lopDTO) throws URISyntaxException {
        log.debug("REST request to update Lop : {}", lopDTO);
        if (lopDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LopDTO result = lopService.save(lopDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lopDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lops} : get all the lops.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lops in body.
     */
    @GetMapping("/lops")
    public ResponseEntity<List<LopDTO>> getAllLops(Pageable pageable) {
        log.debug("REST request to get a page of Lops");
        Page<LopDTO> page = lopService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lops/:id} : get the "id" lop.
     *
     * @param id the id of the lopDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lopDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lops/{id}")
    public ResponseEntity<LopDTO> getLop(@PathVariable Long id) {
        log.debug("REST request to get Lop : {}", id);
        Optional<LopDTO> lopDTO = lopService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lopDTO);
    }

    /**
     * {@code DELETE  /lops/:id} : delete the "id" lop.
     *
     * @param id the id of the lopDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lops/{id}")
    public ResponseEntity<Void> deleteLop(@PathVariable Long id) {
        log.debug("REST request to delete Lop : {}", id);
        lopService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/lops?query=:query} : search for the lop corresponding
     * to the query.
     *
     * @param query the query of the lop search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/lops")
    public ResponseEntity<List<LopDTO>> searchLops(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Lops for query {}", query);
        Page<LopDTO> page = lopService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
